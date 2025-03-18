package com.example.to_collection.enums;

public enum MpaaRating {
  G("Без ограничений"),
  PG_13("Детям до 13 лет просмотр не желателен"),
  NC_17("Детям до 18 лет просмотр запрещен");

  private String rating;

  MpaaRating(String rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return rating;
  }
}
