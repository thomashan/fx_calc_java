package com.thomashan.fx.console;

public class InputValidationProcessor implements UserInputProcessor<String, UserInput> {
    private final UserInputProcessor<UserInput, CurrencyPairAmount> next;

    private InputValidationProcessor(UserInputProcessor<UserInput, CurrencyPairAmount> next) {
        this.next = next;
    }

    public static InputValidationProcessor addNext(UserInputProcessor<UserInput, CurrencyPairAmount> userInputProcessor) {
        return new InputValidationProcessor(userInputProcessor);
    }

    @Override
    public String processInput(String userInput) {
        if (!UserInputParser.validInput(userInput)) {
            return MessageProvider.invalidFormat();
        }

        return next.processInput(UserInputParser.getUserInputs(userInput).get());
    }
}
