package com.github.rifttech.jpon;

import java.io.IOException;

public interface Parser {
    void parse() throws IOException;
    void addListener(TraversalEvent.Listener listener);
}
