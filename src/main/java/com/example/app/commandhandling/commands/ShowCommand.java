package com.example.app.commandhandling.commands;

import com.example.service.MovieCollection;
import com.example.app.commandhandling.Command;

/** Выводит все элементы коллекции в строковом представлении */
public class ShowCommand implements Command {
  private final MovieCollection collection;

  public ShowCommand(MovieCollection collection) {
    this.collection = collection;
  }

  @Override
  public void execute() {
    collection.show();
  }
}
