package d_flux.lambda;

public class Lambda {
    public static void main(String[] args) {
        Stringifier stringifier = new Stringifier();
        soutCurrencyAmount(stringifier); // 143.98 €

        //Same As
        CurrencyStringifier cs = new CurrencyStringifier() { // Ceci reviens à creer un classe qui implémente l'interface CurrencyStringifier
            @Override
            public String convert(double amount) {
                return String.format("%.2f $", amount);
            }
        };
        soutCurrencyAmount(cs); // 143.98 $

        //Same As
        CurrencyStringifier currencyStringifier = amount -> String.format("%.2f £", amount);
        soutCurrencyAmount(currencyStringifier); // 143.98 £

        // Same As
        soutCurrencyAmount(amount -> String.valueOf(amount)); // 143.98765432

        // Same As (Passage par référence)
        soutCurrencyAmount(String::valueOf); // 143.98765432
    }

    private static void soutCurrencyAmount(CurrencyStringifier stringifier) {
        double amount = 143.98765432;
        System.out.println(stringifier.convert(amount));
    }
}
