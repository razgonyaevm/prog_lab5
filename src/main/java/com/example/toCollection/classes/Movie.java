package com.example.toCollection.classes;

import com.example.toCollection.enums.MovieGenre;
import com.example.toCollection.enums.MpaaRating;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class Movie {
  private static final AtomicLong idGenerator = new AtomicLong(1);

  private Long id; // генерируется автоматически, уникален
  private String name;
  private Coordinates coordinates;
  private LocalDate creationDate; // генерируется автоматически
  private int oscarsCount;
  private Integer length;
  @Setter private MovieGenre genre;
  @Setter private MpaaRating mpaaRating;
  @Setter private Person operator;

  public Movie(
      String name,
      Coordinates coordinates,
      int oscarsCount,
      Integer length,
      MovieGenre genre,
      MpaaRating mpaaRating,
      Person operator) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (coordinates == null) {
      throw new IllegalArgumentException("Coordinates cannot be null");
    }
    if (oscarsCount <= 0) {
      throw new IllegalArgumentException("Oscars count must be greater than 0");
    }
    if (length == null || length <= 0) {
      throw new IllegalArgumentException("Length must be greater than 0");
    }

    this.id = idGenerator.getAndIncrement();
    this.name = name;
    this.coordinates = coordinates;
    this.creationDate = LocalDate.now();
    this.oscarsCount = oscarsCount;
    this.length = length;
    this.genre = genre;
    this.mpaaRating = mpaaRating;
    this.operator = operator;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    this.name = name;
  }

  public void setCoordinates(Coordinates coordinates) {
    if (coordinates == null) {
      throw new IllegalArgumentException("Coordinates cannot be null");
    }
    this.coordinates = coordinates;
  }

  public void setOscarsCount(int oscarsCount) {
    if (oscarsCount <= 0) {
      throw new IllegalArgumentException("Oscars count must be greater than 0");
    }
    this.oscarsCount = oscarsCount;
  }

  public void setLength(Integer length) {
    if (length == null || length <= 0) {
      throw new IllegalArgumentException("Length must be greater than 0");
    }
    this.length = length;
  }
}
