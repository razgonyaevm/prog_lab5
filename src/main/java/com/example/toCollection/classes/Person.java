package com.example.toCollection.classes;

import com.example.toCollection.enums.Color;
import com.example.toCollection.enums.Country;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class Person {
  private String name;
  private Long height;
  private float weight;
  @Setter private Color eyeColor;
  @Setter private Country nationality;

  public Person(String name, Long height, float weight, Color eyeColor, Country nationality) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (height == null || height <= 0) {
      throw new IllegalArgumentException("Height must be greater than 0");
    }
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight must be greater than 0");
    }
    this.name = name;
    this.height = height;
    this.weight = weight;
    this.eyeColor = eyeColor;
    this.nationality = nationality;
  }

  public String setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    return this.name = name;
  }

  public Long setHeight(Long height) {
    if (height == null || height <= 0) {
      throw new IllegalArgumentException("Height must be greater than 0");
    }
    return this.height = height;
  }

  public float setWeight(float weight) {
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight must be greater than 0");
    }
    return this.weight = weight;
  }
}
