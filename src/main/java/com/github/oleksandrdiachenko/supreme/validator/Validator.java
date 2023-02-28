package com.github.oleksandrdiachenko.supreme.validator;

public interface Validator<T> {

    void validate(T t);
}
