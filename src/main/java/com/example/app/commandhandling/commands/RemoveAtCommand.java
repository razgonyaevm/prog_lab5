package com.example.app.commandhandling.commands;

import static com.example.parsing.ParserClass.parseInt;

import com.example.app.commandhandling.Command;
import com.example.service.MovieCollection;

/** Удаление элемента из коллекции по индексу */
public class RemoveAtCommand implements Command {
  private final MovieCollection collection;
  private final String command;

  public RemoveAtCommand(MovieCollection collection, String command) {
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
      collection.removeAt(parseInt(command.split(" ")[1]));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
