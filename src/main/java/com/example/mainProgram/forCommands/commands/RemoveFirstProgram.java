package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

public class RemoveFirstProgram implements Command {
  private final MovieCollection collection;

  public RemoveFirstProgram(MovieCollection collection) {
    this.collection = collection;
  }

  @Override
  public void execute() {
    collection.removeFirst();
  }
}
