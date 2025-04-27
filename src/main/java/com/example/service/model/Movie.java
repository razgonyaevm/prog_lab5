package com.example.service.model;

import com.example.parsing.ParserClass;
import com.example.service.enums.MovieGenre;
import com.example.service.enums.MpaaRating;
import com.example.validate.*;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Основной класс, описывающий фильм */
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Movie extends ParserClass implements Comparable<Movie> {

  private Long id;
  private String name;
  private Coordinates coordinates;
  private final LocalDate creationDate;
  private int oscarsCount;
  private Integer length;
  @Setter private MovieGenre genre;
  @Setter private MpaaRating mpaaRating;
  @Setter private Person operator;

  private static final Validator<String> nameValidator = new NameValidator();
  private static final Validator<Coordinates> coordinatesValidator = new CoordinateValidator();
  private static final Validator<Integer> oscarsCountValidator = new OscarsCountValidator();
  private static final Validator<Integer> lengthValidator = new LengthValidator();

  public Movie() {
    generateId();
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

  /** Устанавливает id */
  private void generateId() {
    this.id = IdGenerator.getNextId();
  }

  /** Установка собственного значения id для метода update */
  public void updateId(long id) {
    this.id = id;
  }

  /** Устанавливает названия фильма */
  public void setName(String name) {
    nameValidator.validate(name);
    this.name = name;
  }

  /** Устанавливает координаты фильма (что это вообще значит) */
  public void setCoordinates(Coordinates coordinates) {
    coordinatesValidator.validate(coordinates);
    this.coordinates = coordinates;
  }

  /** Устанавливает количество оскаров у фильма */
  public void setOscarsCount(int oscarsCount) {
    oscarsCountValidator.validate(oscarsCount);
    this.oscarsCount = oscarsCount;
  }

  /** Устанавливает продолжительность фильма */
  public void setLength(Integer length) {
    lengthValidator.validate(length);
    this.length = length;
  }

  /** Сравнивает фильмы по количеству оскаров (для сортировки по умолчанию) */
  @Override
  public int compareTo(Movie other) {
    return Integer.compare(this.oscarsCount, other.oscarsCount);
  }
}
