package com.example.toCollection.enums;

public enum MovieGenre {
  COMEDY("Комедия"),
  MUSICAL("Мюзикл"),
  THRILLER("Триллер"),
  HORROR("Ужас");

  private String genre;

  MovieGenre(String genre) {
    this.genre = genre;
  }

  @Override
  public String toString() {
    return genre;
  }
}
