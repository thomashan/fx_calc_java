package com.thomashan.fx.console;

public interface UserInputProcessor<I, O> {
    String processInput(I userInput);
}
