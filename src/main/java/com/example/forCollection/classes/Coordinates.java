package com.example.forCollection.classes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** Класс координат (знать бы еще, что это) */
@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Coordinates {
  private double x;
  private Long y;

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

  /** Устанавливает координату X */
  public void setX(double x) {
    if (x <= -817) {
      throw new IllegalArgumentException("X must be greater than -817");
    }
    this.x = x;
  }

  /** Устанавливает координату Y */
  public void setY(Long y) {
    if (y == null) {
      throw new IllegalArgumentException("Y cannot be null");
    }
    this.y = y;
  }
}
