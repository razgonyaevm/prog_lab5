package com.example.app.commandhandling.commands;

import com.example.app.commandhandling.Command;
import com.example.parsing.ScanMovie;
import com.example.service.MovieCollection;
import java.util.Scanner;

/** Добавление элемента в коллекцию */
public class AddCommand implements Command {
  private final MovieCollection collection;
  private final Scanner scanner;

  public AddCommand(MovieCollection collection, Scanner scanner) {
    this.collection = collection;
    this.scanner = scanner;
  }

  @Override
  public void execute() {
    try {
      collection.add(new ScanMovie(scanner).getMovie());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
