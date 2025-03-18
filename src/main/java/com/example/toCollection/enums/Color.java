package com.example.toCollection.enums;

public enum Color {
  YELLOW("Желтый"),
  ORANGE("Оранжевый"),
  WHITE("Белый");

  private String color;

  Color(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return color;
  }
}
