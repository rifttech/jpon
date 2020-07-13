package com.github.rifttech.jpon;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
/*package private*/
class StreamParser implements Parser {
    private enum ElementKind {OBJECT, ARRAY, OTHER}
    private final JsonParser parser;
    private final List<TraversalEvent.Listener> listeners;

    public StreamParser(InputStream is) throws IOException {
        JsonFactory factory = new JsonFactory();
        this.parser = factory.createParser(is);
        this.listeners = new ArrayList<>();
    }

    @Override
    public void parse() throws IOException {
        JsonToken token = parser.nextToken();
        switch (token){
            case START_ARRAY:
                traverse(parser, null, 0, "$", "$", ElementKind.ARRAY);
                break;
            case START_OBJECT:
                traverse(parser, null, 0, "$", "$", ElementKind.OBJECT);
                break;
            default:{

            }
        }

        emitFinalizeEvent(listeners);
    }

    private void emitFinalizeEvent(List<TraversalEvent.Listener> listeners) {
        for (TraversalEvent.Listener listener : listeners) {
            try {
                listener.onFinalize();
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }
    }

    @Override
    public void addListener(TraversalEvent.Listener listener) {
        this.listeners.add(listener);
    }

    private void emit(final TraversalEvent event){
        this.listeners.forEach(s -> s.onValue(event));
    }

    private JsonToken resolveTag(ElementKind kind, boolean reverse){
        switch (kind){
            case ARRAY:
                return  (reverse) ? JsonToken.START_ARRAY : JsonToken.END_ARRAY;
            case OBJECT:
                return (reverse) ? JsonToken.START_OBJECT : JsonToken.END_OBJECT;
            case OTHER:
            default: {
                throw new IllegalArgumentException("");
            }
        }
    }

    private void validate(JsonParser parser, ElementKind kind){
        if(parser.getCurrentToken() != resolveTag(kind, true)){
            throw new IllegalStateException(parser.getCurrentToken().toString());
        }
    }

    private void traverse(
            JsonParser parser, String propName, int depth, String path, String parentId, ElementKind kind) throws IOException {
        validate(parser, kind);

        int index = 0;
            while(parser.nextToken() != resolveTag(kind, false)){
                String currentName = parser.currentName();
                parser.nextToken();
                if (parser.getCurrentToken() == JsonToken.START_OBJECT){
                    String currentObjectId  = UUID.randomUUID().toString();
                    traverse(
                            parser,
                            currentName,
                            depth + 1,
                            String.join(".", path, currentName),
                            String.join(".", parentId, currentObjectId),
                            ElementKind.OBJECT
                    );
                }else if (parser.getCurrentToken() == JsonToken.START_ARRAY){
                    traverse(
                            parser,
                            currentName,
                            depth + 1,
                            String.join(".", path, currentName),
                            String.join(".", parentId),
                            ElementKind.ARRAY
                    );
                }else {

                    TraversalEvent traversalEvent = new TraversalEvent();
                    traversalEvent.setKey(currentName);
                    traversalEvent.setParents(parentId);
                    traversalEvent.setPath(path);
                    traversalEvent.setValue(parser.getValueAsString());
                    emit(traversalEvent);
                }
                index++;
            }
    }
}
