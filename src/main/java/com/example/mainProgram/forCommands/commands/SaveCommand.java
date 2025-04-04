package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

public class SaveCommand implements Command {
  private final MovieCollection collection;
  private final String filePath;

  public SaveCommand(MovieCollection collection, String filePath) {
    this.collection = collection;
    this.filePath = filePath;
  }

  @Override
  public void execute() {
    collection.save(filePath);
  }
}
