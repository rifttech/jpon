package com.github.rifttech.jpon;

public interface Parser {
    void parse();
    void addListener(TraversalEvent.Listener listener);
}
