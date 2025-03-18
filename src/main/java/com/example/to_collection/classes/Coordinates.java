package com.example.to_collection.classes;

import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
public class Coordinates {
  private double x; // Значение поля должно быть больше -817
  private Long y; // Поле не может быть null

  public Coordinates(double x, Long y) {
    if (x <= -817) {
      throw new IllegalArgumentException("X must be greater than -817");
    }
    if (y == null) {
      throw new IllegalArgumentException("Y cannot be null");
    }
    this.x = x;
    this.y = y;
  }

  public void setX(double x) {
    if (x <= -817) {
      throw new IllegalArgumentException("X must be greater than -817");
    }
    this.x = x;
  }

  public void setY(Long y) {
    if (y == null) {
      throw new IllegalArgumentException("Y cannot be null");
    }
    this.y = y;
  }
}
