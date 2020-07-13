package com.github.rifttech.jpon;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

@Slf4j
public class StreamParserTest {
    @Test
    public void parse00() throws IOException {
        InputStream resource = getFile("data.00.json");
        Parser parser = new StreamParser(resource);
        parser.addListener(new DefaultListener());
        parser.parse();;
    }

    @Test
    public void parse01() throws IOException {
        InputStream resource = getFile("data.01.json");
        Parser parser = new StreamParser(resource);
        parser.addListener(new DefaultListener());
        parser.parse();
    }

    private class ParserBuilder{
        private TraversalEvent.Listener listener;
        private String path;
        ParserBuilder withListener(TraversalEvent.Listener listener){
            this.listener = listener;
            return this;
        }

        ParserBuilder file(String path){
            this.path = path;
            return this;
        }

        Parser getParser(){
            try (InputStream resource = getFile(this.path)) {
                StreamParser streamParser = new StreamParser(resource);
                streamParser.addListener(this.listener);
                return streamParser;
            }catch (IOException e){
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    private static class DefaultListener implements  TraversalEvent.Listener {
        @Override
        public void onValue(TraversalEvent event) {
            System.out.println(event.getPath() + " - " + event.getKey() + " - " + event.getValue());
        }
    }

    private InputStream getFile(String path){
        return getClass().getClassLoader().getResourceAsStream(path);
    }
}
