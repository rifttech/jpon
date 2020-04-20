package com.github.rifttech.jpon;

import lombok.Data;

@Data
public class TraversalEvent {
    public interface Listener {
        void onValue(TraversalEvent event);
        default void onFinalize(){}
    }

    private String key;
    private String value;
    private String path;
    private String parents;

}
