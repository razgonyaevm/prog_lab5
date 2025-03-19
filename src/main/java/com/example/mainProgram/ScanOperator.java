package com.example.mainProgram;

import static com.example.mainProgram.ParserClass.*;

import com.example.toCollection.classes.Person;
import com.example.toCollection.enums.Color;
import com.example.toCollection.enums.Country;
import java.util.Arrays;
import java.util.Scanner;
import lombok.Getter;

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
        if (height.isEmpty()) {
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
        if (weight.isEmpty()) {
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
            "Введите цвет глаз оператора: (возможные значения: "
                + Arrays.toString(Color.values())
                + "): ");
        operator.setEyeColor(parseEnum(scanner.nextLine(), Color.class));
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
        operator.setNationality(parseEnum(scanner.nextLine(), Country.class));
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n");
      }
    }
  }
}
