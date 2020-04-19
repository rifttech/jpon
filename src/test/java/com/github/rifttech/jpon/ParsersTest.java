package com.github.rifttech.jpon;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParsersTest {

    @Test
    public void newStreamParser() {
        Parser parser = Parsers.newStreamParser(null);
        assert parser != null;
        assert parser instanceof StreamParser;
    }
}