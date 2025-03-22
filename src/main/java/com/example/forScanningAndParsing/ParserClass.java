package com.example.forScanningAndParsing;

/** Парсинг базовых параметров */
public class ParserClass {
  /** Преобразование строки в число int */
  public static int parseInt(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("\u001B[31mInvalid integer: \u001B[0m" + value, e);
    }
  }

  /** Преобразование строки в число long */
  public static long parseLong(String value) {
    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("\u001B[31mInvalid long: \u001B[0m" + value, e);
    }
  }

  /** Преобразование строки в число float */
  public static float parseFloat(String value) {
    try {
      return Float.parseFloat(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("\u001B[31mInvalid float: \u001B[0m" + value, e);
    }
  }

  /** Преобразование строки в число double */
  public static double parseDouble(String value) {
    try {
      return Double.parseDouble(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("\u001B[31mInvalid double: \u001B[0m" + value, e);
    }
  }

  /** Преобразование строки в enum с заданным типом */
  public static <E extends Enum<E>> E parseEnum(String value, Class<E> enumType) {
    try {
      return Enum.valueOf(enumType, value);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(
          "\u001B[31mInvalid enum value: \u001B[0m"
              + value
              + "\u001B[31m for \u001B[0m"
              + enumType.getSimpleName(),
          e);
    }
  }
}
