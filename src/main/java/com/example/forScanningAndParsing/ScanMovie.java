package com.example.forScanningAndParsing;

import static com.example.forScanningAndParsing.ParserClass.*;

import com.example.forCollection.classes.*;
import com.example.forCollection.enums.*;
import java.util.Arrays;
import java.util.Scanner;
import lombok.Getter;

/**
 * Класс для сканирования и создания экземпляров класса {@link
 * com.example.forCollection.classes.Movie}
 */
public class ScanMovie {
  @Getter private Movie movie;
  private Coordinates coordinates;
  private Scanner scanner;

  public ScanMovie(Scanner scanner) {
    this.scanner = scanner;
    movie = new Movie();
    coordinates = new Coordinates();

    setName();
    setCoordinates();
    setOscarsCount();
    setLength();
    setGenre();
    setMpaaRating();
    ScanOperator operator = new ScanOperator(scanner);
    movie.setOperator(operator.getOperator());
  }

  /** Устанавливает название фильма */
  public void setName() {
    while (true) {
      try {
        System.out.print("Введите название фильма: ");
        movie.setName(scanner.nextLine());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }

  /** Устанавливает координаты фильма (знать бы еще, что это и зачем оно нужно) */
  public void setCoordinates() {
    while (true) {
      try {
        System.out.print("Введите первую координату: ");
        String x = scanner.nextLine();
        if (x.trim().isEmpty()) {
          coordinates.setX(0);
        } else {
          coordinates.setX(parseDouble(x));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    while (true) {
      try {
        System.out.print("Введите вторую координату: ");
        String y = scanner.nextLine();
        if (y.trim().isEmpty()) {
          coordinates.setY(null);
        } else {
          coordinates.setY(parseLong(y));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    movie.setCoordinates(coordinates);
  }

  /** Устанавливает количество оскаров у фильма */
  public void setOscarsCount() {
    while (true) {
      try {
        System.out.print("Введите количество оскаров: ");
        String count = scanner.nextLine();
        if (count.trim().isEmpty()) {
          movie.setOscarsCount(0);
        } else {
          movie.setOscarsCount(parseInt(count));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }

  /** Устанавливает продолжительность фильма */
  public void setLength() {
    while (true) {
      try {
        System.out.print("Введите длительность фильма: ");
        String length = scanner.nextLine();
        if (length.trim().isEmpty()) {
          movie.setLength(0);
        } else {
          movie.setLength(parseInt(length));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }

  /** Устанавливает жанр фильма */
  public void setGenre() {
    while (true) {
      try {
        System.out.print(
            "Введите жанр фильма (возможные значения: "
                + Arrays.toString(MovieGenre.values())
                + "): ");
        String genre = scanner.nextLine().toUpperCase();
        if (genre.trim().isEmpty()) {
          movie.setGenre(null);
        } else {
          movie.setGenre(parseEnum(genre, MovieGenre.class));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }

  /** Устанавливает mpaa рейтинг фильма (рейтинг по возрасту) */
  public void setMpaaRating() {
    while (true) {
      try {
        System.out.print(
            "Введите рейтинг фильма (возможные значения: "
                + Arrays.toString(MpaaRating.values())
                + "): ");
        String mpaaRating = scanner.nextLine().toUpperCase();
        if (mpaaRating.trim().isEmpty()) {
          movie.setMpaaRating(null);
        } else {
          movie.setMpaaRating(parseEnum(mpaaRating, MpaaRating.class));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }
}
