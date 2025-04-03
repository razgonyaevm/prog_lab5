package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

public class SumOfLengthCommand implements Command {
  private final MovieCollection collection;

  public SumOfLengthCommand(MovieCollection collection) {
    this.collection = collection;
  }

  @Override
  public void execute() {
    collection.sumOfLength();
  }
}
