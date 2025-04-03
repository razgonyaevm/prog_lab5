package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

public class SaveCommand implements Command {
  private final MovieCollection collection;

  public SaveCommand(MovieCollection collection) {
    this.collection = collection;
  }

  @Override
  public void execute() {
    collection.save();
    System.out.println("Коллекция сохранена");
  }
}
