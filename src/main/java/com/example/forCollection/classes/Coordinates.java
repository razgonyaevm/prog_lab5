package com.example.forCollection.classes;

import lombok.*;

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
      throw new IllegalArgumentException("\u001B[31mX must be greater than -817\u001B[0m");
    }
    if (y == null) {
      throw new IllegalArgumentException("\u001B[31mY cannot be null\u001B[0m");
    }
    this.x = x;
    this.y = y;
  }

  /** Устанавливает координату X */
  public void setX(double x) {
    if (x <= -817) {
      throw new IllegalArgumentException("\u001B[31mX must be greater than -817\u001B[0m");
    }
    this.x = x;
  }

  /** Устанавливает координату Y */
  public void setY(Long y) {
    if (y == null) {
      throw new IllegalArgumentException("\u001B[31mY cannot be null\u001B[0m");
    }
    this.y = y;
  }
}
