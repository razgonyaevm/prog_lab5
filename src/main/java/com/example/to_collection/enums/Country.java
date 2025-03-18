package com.example.to_collection.enums;

public enum Country {
  FRANCE ("Франция"),
  SPAIN ("Испания"),
  NORTH_KOREA ("Северная Корея");

  private String country;

  Country(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return country;
  }
}
