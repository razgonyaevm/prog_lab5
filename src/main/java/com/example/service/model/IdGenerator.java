package com.example.service.model;

/** Класс генерации id для {@link com.example.service.model.Movie} */
public class IdGenerator {
  private static long nextId = 1;

  /** Увеличивает id на 1 */
  public static long getNextId() {
    return nextId++;
  }
}
