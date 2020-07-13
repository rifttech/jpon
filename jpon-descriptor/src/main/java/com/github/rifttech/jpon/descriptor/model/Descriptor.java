package com.github.rifttech.jpon.descriptor.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Descriptor {
    private List<Node> nodes;
}
