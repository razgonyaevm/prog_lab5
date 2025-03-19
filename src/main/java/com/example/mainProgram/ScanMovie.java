package com.example.mainProgram;

import static com.example.mainProgram.ParserClass.*;

import com.example.toCollection.classes.*;
import com.example.toCollection.enums.*;
import java.util.Arrays;
import java.util.Scanner;
import lombok.Getter;

public class ScanMovie extends ScanOperator {
  @Getter private Movie movie;
  private Coordinates coordinates;
  private Scanner scanner;

  public ScanMovie(Scanner scanner) {
    super(scanner);
    this.scanner = scanner;
    movie = new Movie();
    coordinates = new Coordinates();

    setName();
    setCoordinates();
    setOscarsCount();
    setLength();
    setGenre();
    setMpaaRating();
    setOperator();
    movie.setOperator(super.getOperator());
  }

  private void setName() {
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

  private void setCoordinates() {
    while (true) {
      try {
        System.out.print("Введите первую координату: ");
        String x = scanner.nextLine();
        if (x.isEmpty()) {
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
        if (y.isEmpty()) {
          coordinates.setY(null);
        } else {
          coordinates.setY(parseLong(scanner.nextLine()));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    movie.setCoordinates(coordinates);
  }

  public void setOscarsCount() {
    while (true) {
      try {
        System.out.print("Введите количество оскаров: ");
        String count = scanner.nextLine();
        if (count.isEmpty()) {
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

  public void setLength() {
    while (true) {
      try {
        System.out.print("Введите длительность фильма: ");
        String length = scanner.nextLine();
        if (length.isEmpty()) {
          movie.setLength(0);
        } else {
          movie.setLength(parseInt(scanner.nextLine()));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }

  public void setGenre() {
    while (true) {
      try {
        System.out.print(
            "Введите жанр фильма (возможные значения: "
                + Arrays.toString(MovieGenre.values())
                + "): ");
        movie.setGenre(parseEnum(scanner.nextLine(), MovieGenre.class));
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }

  public void setMpaaRating() {
    while (true) {
      try {
        System.out.print(
            "Введите рейтинг фильма (возможные значения: "
                + Arrays.toString(MpaaRating.values())
                + "): ");
        movie.setMpaaRating(parseEnum(scanner.nextLine(), MpaaRating.class));
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }
}
