package com.thomashan.fx.console;

public final class UserInput {
    private final String currency1;
    private final String currency2;
    private final String amount;

    private UserInput(String currency1, String currency2, String amount) {
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.amount = amount;
    }

    public static UserInput of(String currency1, String currency2, String amount) {
        return new UserInput(currency1, currency2, amount);
    }

    public String getCurrency1() {
        return currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return currency1.hashCode() + 3 * currency2.hashCode() + 5 * amount.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserInput)) {
            return false;
        }

        UserInput other = (UserInput) obj;

        return currency1.equals(other.getCurrency1()) && currency2.equals(other.getCurrency2()) &&
                amount.equals(other.getAmount());
    }
}
