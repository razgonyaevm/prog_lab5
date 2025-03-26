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

  /** Конструктор для парсинга данных в одну строку */
  public Person(String params) {
    String[] paramsArray = params.split(";");
    if (paramsArray.length != 5) {
      throw new IllegalArgumentException(
          "\u001B[31mInvalid number of parameters: expected 5, got "
              + paramsArray.length
              + "\u001B[0m");
    }

    setName(paramsArray[0].replace("_", " "));
    setHeight(parseLong(paramsArray[1]));
    setWeight(parseFloat(paramsArray[2]));
    this.eyeColor = parseEnum(paramsArray[3], Color.class);
    this.nationality = parseEnum(paramsArray[4], Country.class);
  }

  /** Устанавливает имя */
  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("\u001B[31mName cannot be null or empty\u001B[0m");
    }
    this.name = name;
  }

  /** Устанавливает рост */
  public void setHeight(Long height) {
    if (height == null || height <= 0) {
      throw new IllegalArgumentException("\u001B[31mHeight must be greater than 0\u001B[0m");
    }
    this.height = height;
  }

  /** Устанавливает вес */
  public void setWeight(float weight) {
    if (weight <= 0) {
      throw new IllegalArgumentException("\u001B[31mWeight must be greater than 0\u001B[0m");
    }
    this.weight = weight;
  }
}
