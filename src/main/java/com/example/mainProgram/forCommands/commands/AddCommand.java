package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.forScanningAndParsing.ScanMovie;
import com.example.mainProgram.forCommands.Command;
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
