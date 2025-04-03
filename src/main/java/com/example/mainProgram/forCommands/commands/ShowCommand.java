package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

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
