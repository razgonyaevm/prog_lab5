package com.example.mainProgram;

import static com.example.mainProgram.Commands.commands_list;

import com.example.forCollection.MovieCollection;
import com.example.forXML.XMLHandler;
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
      System.out.println(
          "\u001B[31mОшибка\u001B[0m: \u001B[33mукажите файл для загрузки данных\u001B[0m");
      return;
    }

    if (Objects.equals(args[0], "jar")) index = 1;

    if (!args[index].contains(".xml")) {
      System.out.println(
          "\u001B[31mОшибка\u001B[0m: \u001B[33mукажите файл с расширением .xml\u001B[0m");
      return;
    }

    if (index == 1) {
      System.out.println("\u001B[3;34mЗагрузка из jar-файла\u001B[0m");
      String filePath = args[1];
      XMLHandler xmlHandler = new XMLHandler("xml/" + filePath);

      collection.setMovies(xmlHandler.loadJar());
    } else {

      String filePath = args[0];
      XMLHandler xmlHandler = new XMLHandler(filePath);
      collection.setMovies(xmlHandler.loadLocal());
    }
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("> ");
      String command = scanner.nextLine().trim();
      if (Objects.equals(command.split(" ")[0], "exit")) {
        System.out.println("\u001B[1;32mПрограмма завершена\u001B[0m");
        return;
      }
      commands_list(command, collection, scanner);
    }
  }
}
