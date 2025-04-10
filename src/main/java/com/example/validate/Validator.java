package com.example.validate;

public interface Validator<T> {
  void validate(T value) throws IllegalArgumentException;
}
