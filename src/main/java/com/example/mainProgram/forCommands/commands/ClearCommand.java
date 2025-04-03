package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

/** Очищает коллекцию */
public class ClearCommand implements Command {
  private final MovieCollection collection;

  public ClearCommand(MovieCollection collection) {
    this.collection = collection;
  }

  @Override
  public void execute() {
    collection.clear();
    System.out.println("Коллекция очищена");
  }
}
