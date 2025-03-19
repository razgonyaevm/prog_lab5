package com.example.toCollection.classes;

import com.example.mainProgram.*;
import com.example.toCollection.enums.*;
import com.example.toCollection.enums.MovieGenre;
import com.example.toCollection.enums.MpaaRating;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class Movie extends ParserClass implements Comparable<Movie> {
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

  public Movie() {
    this.id = idGenerator.getAndIncrement();
    this.creationDate = LocalDate.now();
  }

  public Movie(
      String name,
      Coordinates coordinates,
      int oscarsCount,
      Integer length,
      MovieGenre genre,
      MpaaRating mpaaRating,
      Person operator) {

    this();

    setName(name);
    setCoordinates(coordinates);
    setOscarsCount(oscarsCount);
    setLength(length);
    setGenre(genre);
    setMpaaRating(mpaaRating);
    setOperator(operator);
  }

  public Movie(String parameters) {
    String[] parts = parameters.split(";");
    if (parts.length < 12) {
      throw new IllegalArgumentException(
          "Invalid parameter string: expected 12, got " + parts.length);
    }
    this.id = idGenerator.getAndIncrement();
    this.creationDate = LocalDate.now();
    setName(parts[0].replace("_", " "));
    setCoordinates(new Coordinates(parseDouble(parts[1]), parseLong(parts[2])));
    setOscarsCount(parseInt(parts[3]));
    setLength(parseInt(parts[4]));
    this.genre = parseEnum(parts[5], MovieGenre.class);
    this.mpaaRating = parseEnum(parts[6], MpaaRating.class);
    setOperator(
        new Person(
            parts[7].replace("_", " "),
            parseLong(parts[8]),
            parseFloat(parts[9]),
            parseEnum(parts[10], Color.class),
            parseEnum(parts[11], Country.class)));
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

  @Override
  public int compareTo(Movie other) {
    return Integer.compare(this.oscarsCount, other.oscarsCount);
  }
}
