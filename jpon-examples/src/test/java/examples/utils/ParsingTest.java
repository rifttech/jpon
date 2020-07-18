package examples.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rifttech.jpon.descriptor.model.Descriptor;

import java.io.IOException;

public class ParsingTest extends BaseTest {
    protected Descriptor getDescriptor(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(getSource(path), Descriptor.class);
    }

}
