package com.github.rifttech.jpon;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StreamParserTest {
    @Test
    public void name() throws IOException {
        InputStream resource = getClass().getClassLoader().getResourceAsStream("data.00.json");
        Parser parser = new StreamParser(resource);
        parser.addListener(new DefaultListener());
        parser.parse();
    }

    private static class DefaultListener implements  TraversalEvent.Listener {

        @Override
        public void onValue(TraversalEvent event) {
            System.out.println(event.getPath() + " - " + event.getKey() + " - " + event.getValue());
        }
    }
}