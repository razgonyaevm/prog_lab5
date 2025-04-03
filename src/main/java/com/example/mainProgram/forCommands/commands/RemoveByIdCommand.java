package com.example.mainProgram.forCommands.commands;

import static com.example.forScanningAndParsing.ParserClass.parseLong;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

/** Удаление элемента из коллекции по его id */
public class RemoveByIdCommand implements Command {
  private final MovieCollection collection;
  private final String command;

  public RemoveByIdCommand(MovieCollection collection, String command) {
    this.collection = collection;
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
      collection.removeById(parseLong(parts[1]));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
