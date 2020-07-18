package examples.utils;

import java.io.InputStream;

public class BaseTest {
    protected InputStream getSource(String path){
        return this.getClass().getClassLoader().getResourceAsStream(path);
    }
}
