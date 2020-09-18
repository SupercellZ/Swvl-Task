package com.example.swvl.utils.functions;

import java.io.Serializable;

public interface Function1<T> extends Serializable {
    void apply(T input);
}
