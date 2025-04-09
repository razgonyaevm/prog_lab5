package com.example.mainProgram;

import com.example.forCollection.MovieCollection;
import com.example.forXML.XMLHandler;
import com.example.mainProgram.forCommands.CommandInvoker;
import com.example.mainProgram.forCommands.commands.*;
import java.util.*;

/**
 * Основная программа. При запуске аргументом указывается имя xml-файла. Если перед аргументом нет
 * ключевого слова jar, то программа загружает данные из локальной директории. Иначе программа
 * загружает данные из jar-файла
 */
public class Program {
  public static void main(String[] args) {
    MovieCollection collection = new MovieCollection();
    int index = 0;
    if (args.length < 1) {
      System.out.println("Ошибка: укажите файл для загрузки данных");
      return;
    }

    if (Objects.equals(args[0], "jar")) index = 1;

    if (index == 1) {
      System.out.println("Загрузка из jar-файла");
      XMLHandler xmlHandler;
      if (args.length == 1) {
        xmlHandler = new XMLHandler("xml/movies.xml");
      } else {
        xmlHandler = new XMLHandler("xml/" + args[1]);
      }

      collection.setMovies(xmlHandler.loadJar());
    } else {
      if (!args[index].trim().endsWith(".xml")) {
        System.out.println("Ошибка: укажите файл с расширением .xml");
        return;
      }

      String filePath = args[0];
      XMLHandler xmlHandler = new XMLHandler(filePath);
      collection.setMovies(xmlHandler.loadLocal());
    }

    Scanner scanner = new Scanner(System.in);
    CommandInvoker invoker = new CommandInvoker();

    // Регистрация команд
    invoker.register("help", new HelpCommand());
    invoker.register("info", new InfoCommand(collection));
    invoker.register("add", new AddCommand(collection, scanner));
    invoker.register("show", new ShowCommand(collection));
    invoker.register("clear", new ClearCommand(collection));
    invoker.register("exit", new ExitCommand());
    invoker.register("remove_first", new RemoveFirstProgram(collection));
    invoker.register("reorder", new ReorderCommand(collection));
    invoker.register("sum_of_length", new SumOfLengthCommand(collection));
    invoker.register(
        "print_field_descending_oscars_count", new PrintDescendingOscarsCountCommand(collection));

    while (scanner.hasNextLine()) {
      String command = scanner.nextLine().trim();
      notSimpleMethods(command, collection, scanner, invoker);
    }
  }

  public static void notSimpleMethods(
      String command, MovieCollection collection, Scanner scanner, CommandInvoker invoker) {
    String[] parts = command.split(" ");
    switch (parts[0]) {
      case "update" -> invoker.execute(new UpdateCommand(collection, scanner, command));
      case "execute_script" -> {
        if (parts.length == 2) {
          invoker.execute(new ExecuteScriptCommand(collection, parts[1], invoker));
        } else {
          System.out.println("Ошибка: укажите имя файла");
        }
      }
      case "save" -> {
        if (parts.length == 2) {
          invoker.execute(new SaveCommand(collection, parts[1]));
        } else {
          System.out.println("Ошибка: укажите имя файла");
        }
      }
      case "count_by_operator" -> invoker.execute(new CountByOperator(collection, command));
      case "remove_by_id" -> invoker.execute(new RemoveByIdCommand(collection, command));
      case "remove_at" -> invoker.execute(new RemoveAtCommand(collection, command));
      default -> invoker.execute(command);
    }
  }
}
