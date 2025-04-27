package com.example.app.commandhandling.commands;

import com.example.app.commandhandling.Command;
import com.example.service.MovieCollection;

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
