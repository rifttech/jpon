package com.github.rifttech.jpon.descriptor;

import com.github.rifttech.jpon.descriptor.model.Descriptor;

import java.io.IOException;
import java.io.InputStream;

public interface DescriptorLoader {
    Descriptor load() throws IOException;
}
