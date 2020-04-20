package com.github.rifttech.jpon;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ParsersTest {

    @Test
    public void newStreamParser() throws IOException {
        Parser parser = Parsers.newStreamParser(null);
        assert parser != null;
        assert parser instanceof StreamParser;
    }
}