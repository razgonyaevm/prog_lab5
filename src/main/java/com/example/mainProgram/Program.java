package com.example.mainProgram;

import static com.example.mainProgram.Commands.commands_list;

import com.example.mainProgram.forXML.XMLHandler;
import java.util.*;

public class Program {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Ошибка: укажите файл для загрузки данных");
      return;
    }

    String filePath = args[0];
    XMLHandler xmlHandler = new XMLHandler("./resources/xml/" + filePath);
    MovieCollection collection = new MovieCollection();
    collection.setMovies(
        xmlHandler
            .load_local()); // Загрузка коллекции с локальной директории, использовать load_jar()
    // для загрузки из jar-файла

    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("> ");
      String command = scanner.nextLine().trim();

      if (Objects.equals(command.split(" ")[0], "exit")) {
        System.out.println("Программа завершена");
        return;
      }
      commands_list(command, collection, scanner);
    }
  }
}
