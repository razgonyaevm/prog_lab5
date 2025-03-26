package com.example.mainProgram;

import static com.example.forScanningAndParsing.ParserClass.parseInt;
import static com.example.forScanningAndParsing.ParserClass.parseLong;

import com.example.forCollection.MovieCollection;
import com.example.forCollection.classes.Movie;
import com.example.forScanningAndParsing.ScanMovie;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/** Класс обрабатывающий команды */
public class Commands {
  /** Обработка команд */
  public static void commands_list(String command, MovieCollection collection, Scanner scanner) {
    switch (command.split(" ")[0]) {
      case "help":
        help();
        break;

      case "info":
        info(collection);
        break;

      case "show":
        collection.show();
        break;

      case "add":
        add(command, collection, scanner);
        break;

      case "update":
        update(command, collection, scanner);
        break;

      case "remove_by_id":
        removeById(command, collection);
        break;

      case "clear":
        collection.clear();
        System.out.println("\u001B[1;34mКоллекция очищена\u001B[0m");
        break;

      case "save":
        collection.save();
        break;

      case "execute_script":
        executeScript(command, collection);
        break;

      case "remove_at":
        removeAt(command, collection);
        break;

      case "remove_first":
        collection.removeFirst();
        break;

      case "reorder":
        collection.reorder();
        break;

      case "sum_of_length":
        collection.sumOfLength();
        break;

      case "count_by_operator":
        countByOperator(command, collection);
        break;

      case "print_field_descending_oscars_count":
        collection.printDescendingOscarsCount();
        break;

      default:
        System.out.println(
            "\u001B[3;33mОшибка в команде. Для вывода справки, воспользуйтесь командой help\u001B[0m");
    }
  }

  /** Вывод справки */
  public static void help() {
    System.out.println(
        """
                            Доступные команды:

                            \u001B[3;32mhelp\u001B[0m : вывести справку по доступным командам

                            \u001B[3;32minfo\u001B[0m : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)

                            \u001B[3;32mshow\u001B[0m : вывести в стандартный поток вывода все элементы коллекции в строковом представлении

                            \u001B[3;32madd {element}\u001B[0m : добавить новый элемент в коллекцию

                            \u001B[3;32mupdate id {element}\u001B[0m : обновить значение элемента коллекции, id которого равен заданному

                            \u001B[3;32mremove_by_id id\u001B[0m : удалить элемент из коллекции по его id

                            \u001B[3;32mclear\u001B[0m : очистить коллекцию

                            \u001B[3;32msave\u001B[0m : сохранить коллекцию в файл

                            \u001B[3;32mexecute_script file_name\u001B[0m : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. Команды выполняются с исходной коллекцией

                            \u001B[3;32mexit\u001B[0m : завершить программу (без сохранения в файл)

                            \u001B[3;32mremove_at index\u001B[0m : удалить элемент, находящийся в заданной позиции коллекции (index)

                            \u001B[3;32mremove_first\u001B[0m : удалить первый элемент из коллекции

                            \u001B[3;32mreorder\u001B[0m : отсортировать коллекцию в порядке, обратном нынешнему

                            \u001B[3;32msum_of_length\u001B[0m : вывести сумму значений поля length для всех элементов коллекции

                            \u001B[3;32mcount_by_operator operator\u001B[0m : вывести количество элементов, значение поля operator которых равно заданному (вводится имя оператора)

                            \u001B[3;32mprint_field_descending_oscars_count\u001B[0m : вывести значения поля oscarsCount всех элементов в порядке убывания
                            """);
  }

  /** Вывод информации о коллекции */
  public static void info(MovieCollection collection) {
    System.out.println("Тип коллекции: LinkedList");
    System.out.println("Количество элементов: " + collection.size());
    System.out.println("Дата инициализации: " + collection.getInitializationDate());
  }

  /** Добавление элемента в коллекцию */
  public static void add(String command, MovieCollection collection, Scanner scanner) {
    if (command.split(" ").length != 1) {
      System.out.println(
          "\u001B[31mОшибка\u001B[0m:\u001B[33m в команде add не должно быть параметров\u001B[0m");
      return;
    }
    try {
      Movie movie = new ScanMovie(scanner).getMovie();
      collection.add(movie);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /** Обновление элемента в коллекции по его id */
  public static void update(String command, MovieCollection collection, Scanner scanner) {
    if (command.split(" ").length != 2) {
      System.out.println("\u001B[31mОшибка\u001B[0m:\u001B[33m укажите id\u001B[0m");
      return;
    }
    try {
      long id = parseLong(command.split(" ")[1]);
      if (id <= 0) {
        System.out.println("\u001B[31mID must be greater than 0\u001B[0m");
      } else {
        for (int i = 0; i < collection.size(); i++) {
          if (collection.get(i).getId().equals(id)) {
            break;
          }
          if (i == collection.size() - 1) {
            System.out.println("Фильм с таким ID не найден.");
            return;
          }
        }
        Movie movie_update = new ScanMovie(scanner).getMovie();
        collection.update(parseLong(command.split(" ")[1]), movie_update);
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /** Удаление элемента из коллекции по его id */
  public static void removeById(String command, MovieCollection collection) {
    try {
      if (command.split(" ").length != 2) {
        System.out.println("\u001B[31mОшибка\u001B[0m:\u001B[33m укажите id\u001B[0m");
        return;
      }
      collection.removeById(parseLong(command.split(" ")[1]));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /** Удаление элемента из коллекции по индексу */
  public static void removeAt(String command, MovieCollection collection) {
    if (command.split(" ").length != 2) {
      System.out.println("\u001B[31mОшибка\u001B[0m:\u001B[33m укажите id\u001B[0m");
      return;
    }
    try {
      collection.removeAt(parseInt(command.split(" ")[1]));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /** Подсчет количества фильмов, у которых имя оператора равно заданному */
  public static void countByOperator(String command, MovieCollection collection) {
    if (command.length() <= 18) {
      System.out.println("\u001B[31mОшибка\u001B[0m: \u001B[33m укажите имя оператора\u001B[0m");
      return;
    }
    collection.countByOperator(command.substring(18));
  }

  /** Выполнение скрипта, хранящегося в директории с jar архивом (./resources/scripts/) */
  public static void executeScript(String command, MovieCollection fileCollection) {
    if (command.split(" ").length != 2) {
      System.out.println("\u001B[31mОшибка\u001B[0m:\u001B[33m укажите имя файла\u001B[0m");
      return;
    }

    try (BufferedReader br =
        new BufferedReader(new FileReader("./resources/scripts/" + command.split(" ")[1]))) {
      Scanner fileScanner = new Scanner(br);
      while (fileScanner.hasNextLine()) {
        String command_script = fileScanner.nextLine();
        commands_list(command_script, fileCollection, fileScanner);
      }
    } catch (FileNotFoundException e) {
      System.out.println("\u001B[31mФайл не найден\u001B[0m");
    } catch (Exception e) {
      System.out.println("\u001B[31mОшибка выполнения скрипта: \u001B[0m" + e.getMessage());
    }
  }
}
