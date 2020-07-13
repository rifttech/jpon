package com.github.rifttech.jpon.descriptor;

import com.github.rifttech.jpon.descriptor.model.Descriptor;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

public class DescriptorLoaderTest extends BaseTest{


    protected void testLoad(String path, Consumer<Descriptor> assertFn){
        try(InputStream stream = getStream(path)){
            DescriptorLoader loader = new JsonDescriptorLoader(stream);
            Descriptor load = loader.load();
            assertFn.accept(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void assertDescriptor(Descriptor actual, Descriptor expected){
        Assert.assertEquals(expected, actual);
    }
}
