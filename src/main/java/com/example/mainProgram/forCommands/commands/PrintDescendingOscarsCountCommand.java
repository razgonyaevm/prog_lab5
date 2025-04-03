package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

public class PrintDescendingOscarsCountCommand implements Command {
  private final MovieCollection collection;

  public PrintDescendingOscarsCountCommand(MovieCollection collection) {
    this.collection = collection;
  }

  @Override
  public void execute() {
    collection.printDescendingOscarsCount();
  }
}
