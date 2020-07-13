package com.github.rifttech.jpon;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ParsersTest {

    @Test
    public void newStreamParser() throws IOException {
        Parser parser = Parsers.newStreamParser(null);
        Assert.assertNotNull(parser);
        Assert.assertTrue(parser instanceof StreamParser);
    }
}