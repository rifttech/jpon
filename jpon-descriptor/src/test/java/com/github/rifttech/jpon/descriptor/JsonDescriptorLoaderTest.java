package com.github.rifttech.jpon.descriptor;

import com.github.rifttech.jpon.descriptor.model.Descriptor;
import com.github.rifttech.jpon.descriptor.model.Node;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;


public class JsonDescriptorLoaderTest extends DescriptorLoaderTest {
    @Test
    public void loadDescriptor() {
        final String descriptorURI = "descriptors/descriptor.00.json";
        testLoad(descriptorURI, assertDescriptor00());
    }


    private Consumer<Descriptor> assertDescriptor00(){
        final Descriptor descriptor = new Descriptor();
        Node node00 = new Node();
        node00.setName("data_rows");
        node00.setPath("$.rows");
        node00.setFields(Arrays.asList("field1", "field2"));

        Node node01 = new Node();
        node01.setName("data_rows_array");
        node01.setPath("$.rows.array");
        node01.setFields(Arrays.asList("name", "year"));

        descriptor.setNodes(Arrays.asList(node00, node01));

        return partial(this::assertDescriptor, descriptor);
    }



}
