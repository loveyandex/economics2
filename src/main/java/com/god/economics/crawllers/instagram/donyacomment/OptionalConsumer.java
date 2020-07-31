package com.god.economics.crawllers.instagram.donyacomment;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalConsumer<T> implements Consumer<Optional<T>> {
    private final Consumer<T> c;
    private final Do d;

    public OptionalConsumer(Consumer<T> c, Do d) {
        this.c = c;
        this.d = d;
    }

    public static <T> OptionalConsumer<T> of(Consumer<T> c, Do d) {
        return new OptionalConsumer(c, d);
    }

    @Override
    public void accept(Optional<T> t) {
        if (t.isPresent()) {
            c.accept(t.get());
        } else {
            d.Do();

        }
    }
}