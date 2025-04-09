package com.example.forCollection.classes;

import com.example.forCollection.enums.Color;
import com.example.forCollection.enums.Country;
import com.example.forScanningAndParsing.ParserClass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** Класс, описывающий человека */
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
public class Person extends ParserClass {
  private String name;
  private Long height;
  private float weight;
  @Setter private Color eyeColor;
  @Setter private Country nationality;

  public Person(String name, Long height, float weight, Color eyeColor, Country nationality) {
    setName(name);
    setHeight(height);
    setWeight(weight);
    this.eyeColor = eyeColor;
    this.nationality = nationality;
  }

  /** Устанавливает имя */
  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    this.name = name;
  }

  /** Устанавливает рост */
  public void setHeight(Long height) {
    if (height == null || height <= 0) {
      throw new IllegalArgumentException("Height must be greater than 0");
    }
    this.height = height;
  }

  /** Устанавливает вес */
  public void setWeight(float weight) {
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight must be greater than 0");
    }
    this.weight = weight;
  }
}
