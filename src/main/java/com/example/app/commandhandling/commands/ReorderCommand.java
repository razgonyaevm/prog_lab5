package com.example.app.commandhandling.commands;

import com.example.service.MovieCollection;
import com.example.app.commandhandling.Command;

public class ReorderCommand implements Command {
  private final MovieCollection collection;

  public ReorderCommand(MovieCollection collection) {
    this.collection = collection;
  }

  @Override
  public void execute() {
    collection.reorder();
  }
}
