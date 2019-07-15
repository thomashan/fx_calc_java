package com.thomashan.fx.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleAppRunner {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("%> ");
            String userInputString = br.readLine();

            InputValidationProcessor processors = InputValidationProcessor
                    .addNext(ResolvableCurrencyProcessor
                    .addNext(IdentityQuoteProcessor
                    .addNext(ResolvableQuoteProcessor
                    .terminate())));

            String response = processors.processInput(userInputString);

            System.out.println(response);
        }
    }
}
