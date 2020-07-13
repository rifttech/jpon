package com.github.rifttech.jpon.descriptor;

import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class BaseTest {
    public InputStream getStream(String path){
        return getClass().getClassLoader().getResourceAsStream(path);
    }

    public static <T, U, R> Function<U, R> partial(BiFunction<T, U, R> f, T x) {
        return (y) -> f.apply(x, y);
    }


    public static <T, U> Consumer<U> partial(BiConsumer<T, U> f, T x) {
        return (y) -> f.accept(x, y);
    }
}
