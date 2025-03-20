package com.example.mainProgram;

import static com.example.mainProgram.ParserClass.*;

import com.example.mainProgram.forXML.XMLHandler;
import com.example.toCollection.classes.*;
import java.util.*;

public class Program {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Ошибка: укажите файл для загрузки данных");
      MovieCollection movieCollection = new MovieCollection();
      movieCollection.save();
      return;
    }

    String filePath = args[0];
    XMLHandler xmlHandler = new XMLHandler("xml/" + filePath);
    MovieCollection collection = new MovieCollection();
    collection.setMovies(xmlHandler.load());

    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("> ");
      String command = scanner.nextLine().trim();

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
          add_stream(command, collection, scanner);
          break;
        case "update":
          if (command.split(" ").length != 2) {
            System.out.println("Ошибка: укажите id");
            break;
          }
          try {
            Movie movie_update = new ScanMovie(scanner).getMovie();
            collection.update(parseLong(command.split(" ")[1]), movie_update);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "remove_by_id":
          if (command.split(" ").length != 2) {
            System.out.println("Ошибка: укажите id");
            break;
          }
          collection.removeById(parseLong(command.split(" ")[1]));
          break;
        case "clear":
          collection.clear();
          break;
        case "save":
          collection.save();
          break;
        case "execute_script":
          if (command.split(" ").length != 2) {
            System.out.println("Ошибка: укажите имя файла");
            break;
          }
          collection.executeScript(command.split(" ")[1]);
          break;
        case "exit":
          System.out.println("Выход из программы");
          return;
        case "remove_at":
          if (command.split(" ").length != 2) {
            System.out.println("Ошибка: укажите id");
            break;
          }
          collection.removeById(Long.parseLong(command.split(" ")[1]));
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
          if (command.split(" ").length != 2) {
            System.out.println("Ошибка: слишком много параметров");
            break;
          }
          collection.countByOperator(new Person(command.split(" ")[1]));
          break;
        case "print_field_descending_oscars_count":
          collection.printDescendingOscarsCount();
          break;
      }
    }
  }

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
                    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                    exit : завершить программу (без сохранения в файл)
                    remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)
                    remove_first : удалить первый элемент из коллекции
                    reorder : отсортировать коллекцию в порядке, обратном нынешнему
                    sum_of_length : вывести сумму значений поля length для всех элементов коллекции
                    count_by_operator operator : вывести количество элементов, значение поля operator которых равно заданному
                    print_field_descending_oscars_count : вывести значения поля oscarsCount всех элементов в порядке убывания
                    """);
  }

  public static void info(MovieCollection collection) {
    System.out.println("Тип коллекции: LinkedList");
    System.out.println("Количество элементов: " + collection.size());
    System.out.println("Дата инициализации: " + collection.getInitializationDate());
  }

  public static void add_stream(String command, MovieCollection collection, Scanner scanner) {
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
}
