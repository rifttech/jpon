package com.github.rifttech.jpon.descriptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rifttech.jpon.descriptor.model.Descriptor;

import java.io.IOException;
import java.io.InputStream;

public class JsonDescriptorLoader implements DescriptorLoader {
    private final InputStream stream;

    public JsonDescriptorLoader(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public Descriptor load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stream, Descriptor.class);
    }
}
