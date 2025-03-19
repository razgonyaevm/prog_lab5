package com.example.mainProgram;

import static com.example.mainProgram.ParserClass.*;

import com.example.mainProgram.forXML.XMLHandler;
import com.example.toCollection.classes.*;
import java.util.*;

public class Program {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Ошибка: укажите файл для загрузки данных");
      XMLHandler xmlHandler = new XMLHandler("xml/movies.xml");
      System.out.println(xmlHandler.load());
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
          System.out.println(
              "Доступные команды: help, info, show, add, update, remove_by_id, clear, save, execute_script, exit, remove_at, remove_first, reorder, sum_of_length, count_by_operator, print_field_descending_oscars_count");
          break;
        case "info":
          System.out.println("Тип коллекции: LinkedList");
          System.out.println("Количество элементов: " + collection.size());
          System.out.println("Дата инициализации: " + collection.getInitializationDate());
          break;
        case "show":
          collection.show();
          break;
        case "add":
          if (command.split(" ").length != 1) {
            System.out.println("Ошибка: слишком много параметров");
            break;
          }
          try {
            Movie movie = new ScanMovie(scanner).getMovie();
            collection.add(movie);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }

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
}
