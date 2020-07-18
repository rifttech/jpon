package com.github.rifttech.jpon;

import java.io.IOException;

public interface Parser extends AutoCloseable {
    void parse() throws IOException;
    void addListener(TraversalEvent.Listener listener);
}
