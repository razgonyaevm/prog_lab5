package com.example.mainProgram;

import static com.example.mainProgram.ParserClass.parseInt;
import static com.example.mainProgram.ParserClass.parseLong;

import com.example.mainProgram.forScanning.ScanMovie;
import com.example.toCollection.classes.Movie;
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
        System.out.println("Ошибка в команде. Для вывода справки, воспользуйтесь командой help");
    }
  }

  /** Вывод справки */
  public static void help() {
    System.out.println(
        """
                            Доступные команды:
                            help : вывести справку по доступным командам
                            info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                            show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                            add {element} : добавить новый элемент в коллекцию
                            update id {element} : обновить значение элемента коллекции, id которого равен заданному
                            remove_by_id id : удалить элемент из коллекции по его id
                            clear : очистить коллекцию
                            save : сохранить коллекцию в файл
                            execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. Команды выполняются с исходной коллекцией
                            exit : завершить программу (без сохранения в файл)
                            remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)
                            remove_first : удалить первый элемент из коллекции
                            reorder : отсортировать коллекцию в порядке, обратном нынешнему
                            sum_of_length : вывести сумму значений поля length для всех элементов коллекции
                            count_by_operator operator : вывести количество элементов, значение поля operator которых равно заданному (вводится имя оператора)
                            print_field_descending_oscars_count : вывести значения поля oscarsCount всех элементов в порядке убывания
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
      System.out.println("Ошибка: слишком много параметров");
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
      System.out.println("Ошибка: укажите id");
      return;
    }
    try {
      Movie movie_update = new ScanMovie(scanner).getMovie();
      collection.update(parseLong(command.split(" ")[1]), movie_update);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /** Удаление элемента из коллекции по его id */
  public static void removeById(String command, MovieCollection collection) {
    if (command.split(" ").length != 2) {
      System.out.println("Ошибка: укажите id");
      return;
    }
    collection.removeById(parseLong(command.split(" ")[1]));
  }

  /** Удаление элемента из коллекции по индексу */
  public static void removeAt(String command, MovieCollection collection) {
    if (command.split(" ").length != 2) {
      System.out.println("Ошибка: укажите id");
      return;
    }
    collection.removeAt(parseInt(command.split(" ")[1]));
  }

  /** Подсчет количества фильмов, у которых имя оператора равно заданному */
  public static void countByOperator(String command, MovieCollection collection) {
    if (command.length() <= 18) {
      System.out.println("Укажите имя оператора");
      return;
    }
    collection.countByOperator(command.substring(18));
  }

  /** Выполнение скрипта, хранящегося в директории с jar архивом (./resources/scripts/) */
  public static void executeScript(String command, MovieCollection fileCollection) {
    if (command.split(" ").length != 2) {
      System.out.println("Ошибка: укажите имя файла");
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
      System.out.println("Файл не найден");
    } catch (Exception e) {
      System.out.println("Ошибка выполнения скрипта: " + e.getMessage());
    }
  }
}
