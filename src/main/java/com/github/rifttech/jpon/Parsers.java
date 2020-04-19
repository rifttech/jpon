package com.github.rifttech.jpon;

import java.io.InputStream;

public class Parsers {
    private Parsers() {}
    public static Parser newStreamParser(InputStream is){
        return new StreamParser(is);
    }
}
