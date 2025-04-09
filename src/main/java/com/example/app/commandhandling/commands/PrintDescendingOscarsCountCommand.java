package com.example.app.commandhandling.commands;

import com.example.service.MovieCollection;
import com.example.app.commandhandling.Command;

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
