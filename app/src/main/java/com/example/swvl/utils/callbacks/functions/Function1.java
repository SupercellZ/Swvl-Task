package com.example.swvl.utils.callbacks.functions;

import java.io.Serializable;

public interface Function1<T> extends Serializable {
    void apply(T input);
}
