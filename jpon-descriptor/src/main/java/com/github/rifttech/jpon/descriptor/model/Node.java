package com.github.rifttech.jpon.descriptor.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Node {
    private String name;
    private String path;
    private List<String> fields;
}
