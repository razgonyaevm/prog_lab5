package com.example.mainProgram.forCommands.commands;

import static com.example.forScanningAndParsing.ParserClass.parseLong;

import com.example.forCollection.MovieCollection;
import com.example.forCollection.classes.Movie;
import com.example.forScanningAndParsing.ScanMovie;
import com.example.mainProgram.forCommands.Command;
import java.util.Scanner;

/** Обновление элемента в коллекции по его id */
public class UpdateCommand implements Command {
  private final MovieCollection collection;
  private final Scanner scanner;
  private final String command;

  public UpdateCommand(MovieCollection collection, Scanner scanner, String command) {
    this.collection = collection;
    this.scanner = scanner;
    this.command = command;
  }

  @Override
  public void execute() {
    String[] parts = command.split(" ");
    if (parts.length != 2) {
      System.out.println("Ошибка: укажите id");
      return;
    }
    try {
      long id = parseLong(parts[1]);
      if (id <= 0) {
        System.out.println("ID must be greater than 0");
        return;
      }
      for (int i = 0; i < collection.size(); i++) {
        if (collection.get(i).getId().equals(id)) {
          Movie movieUpdate = new ScanMovie(scanner).getMovie();
          collection.update(id, movieUpdate);
          return;
        }
      }
      System.out.println("Фильм с таким ID не найден.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
