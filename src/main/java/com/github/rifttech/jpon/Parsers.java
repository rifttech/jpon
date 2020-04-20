package com.github.rifttech.jpon;

import java.io.IOException;
import java.io.InputStream;

public class Parsers {
    private Parsers() {}

    public static Parser newStreamParser(InputStream is) throws IOException {
        return new StreamParser(is);
    }
}
