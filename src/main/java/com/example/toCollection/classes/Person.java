package com.example.toCollection.classes;

import com.example.mainProgram.ParserClass;
import com.example.toCollection.enums.Color;
import com.example.toCollection.enums.Country;
import lombok.*;

@Getter
@EqualsAndHashCode
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

  public Person(String params) {
    String[] paramsArray = params.split(";");
    if (paramsArray.length != 5) {
      throw new IllegalArgumentException(
          "Invalid number of parameters: expected 5, got " + paramsArray.length);
    }

    setName(paramsArray[0].replace("_", " "));
    setHeight(parseLong(paramsArray[1]));
    setWeight(parseFloat(paramsArray[2]));
    this.eyeColor = parseEnum(paramsArray[3], Color.class);
    this.nationality = parseEnum(paramsArray[4], Country.class);
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    this.name = name;
  }

  public void setHeight(Long height) {
    if (height == null || height <= 0) {
      throw new IllegalArgumentException("Height must be greater than 0");
    }
    this.height = height;
  }

  public void setWeight(float weight) {
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight must be greater than 0");
    }
    this.weight = weight;
  }
}
