package com.epam.supreme.validator;

public interface Validator<T> {

    void validate(T t);
}
