package com.example.mainProgram;

public class ParserClass {
  /** Парсинг базовых параметров (static так как не работает с полями класса) */
  protected static int parseInt(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid integer: " + value, e);
    }
  }

  protected static long parseLong(String value) {
    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid long: " + value, e);
    }
  }

  protected static float parseFloat(String value) {
    try {
      return Float.parseFloat(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid float: " + value, e);
    }
  }

  protected static double parseDouble(String value) {
    try {
      return Double.parseDouble(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid double: " + value, e);
    }
  }

  protected static <E extends Enum<E>> E parseEnum(String value, Class<E> enumType) {
    try {
      return Enum.valueOf(enumType, value);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(
          "Invalid enum value: " + value + " for " + enumType.getSimpleName(), e);
    }
  }
}
