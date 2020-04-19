package com.github.rifttech.jpon;

import org.junit.Test;

import static org.junit.Assert.*;

public class StreamParserTest {
    @Test
    public void name() {
        Parser parser = new StreamParser(null);
        parser.addListener(new DefaultListener());
        parser.parse();
    }

    private static class DefaultListener implements  TraversalEvent.Listener {

        @Override
        public void onValue(TraversalEvent event) {

        }
    }
}