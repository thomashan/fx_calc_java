package com.thomashan.fx.console;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UserInputParser {
    private static final String regex = "^(\\w{3})\\s(\\d+(?:\\.\\d+)?)\\sin\\s(\\w{3})$";
    private static final Pattern pattern = Pattern.compile(regex);

    private UserInputParser() {
        throw new AssertionError();
    }

    public static boolean validInput(String input) {
        return input.matches(regex);
    }

    public static Optional<UserInput> getUserInputs(String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Optional.of(UserInput.of(matcher.group(1), matcher.group(3), matcher.group(2)));
        }

        return Optional.empty();
    }
}
