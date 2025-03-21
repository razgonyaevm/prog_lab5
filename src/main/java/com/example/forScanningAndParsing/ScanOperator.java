package com.example.forScanningAndParsing;

import static com.example.forScanningAndParsing.ParserClass.*;

import com.example.forCollection.classes.Person;
import com.example.forCollection.enums.Color;
import com.example.forCollection.enums.Country;
import java.util.Arrays;
import java.util.Scanner;
import lombok.Getter;

/**
 * Класс для сканирования оператора в виде объекта класса {@link
 * com.example.forCollection.classes.Person}
 */
public class ScanOperator {
  private Scanner scanner;
  @Getter private Person operator;

  public ScanOperator(Scanner scanner) {
    this.scanner = scanner;
    operator = new Person();

    setOperator();
  }

  public void setOperator() {
    while (true) {
      try {
        System.out.print("Введите имя оператора: ");
        operator.setName(scanner.nextLine());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    while (true) {
      try {
        System.out.print("Введите рост оператора: ");
        String height = scanner.nextLine();
        if (height.trim().isEmpty()) {
          operator.setHeight(null);
        } else {
          operator.setHeight(parseLong(height));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    while (true) {
      try {
        System.out.print("Введите вес оператора: ");
        String weight = scanner.nextLine();
        if (weight.trim().isEmpty()) {
          operator.setWeight(0);
        } else {
          operator.setWeight(parseFloat(weight));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    while (true) {
      try {
        System.out.println(
            "Введите любимый цвет оператора: (возможные значения: "
                + Arrays.toString(Color.values())
                + "): ");
        String color = scanner.nextLine().toUpperCase();
        if (color.trim().isEmpty()) {
          operator.setEyeColor(null);
        } else {
          operator.setEyeColor(parseEnum(color, Color.class));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }

    while (true) {
      try {
        System.out.println(
            "Введите национальность оператора (возможные значения: "
                + Arrays.toString(Country.values())
                + "): ");
        String nationality = scanner.nextLine().toUpperCase();
        if (nationality.trim().isEmpty()) {
          operator.setNationality(null);
        } else {
          operator.setNationality(parseEnum(nationality, Country.class));
        }
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }
}
