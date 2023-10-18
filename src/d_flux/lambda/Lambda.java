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

        int i = 0;
        //Same As
        /*L'écriture lambda consiste en un ou plusieurs arguments suivis du symbole -> et du corps de la méthode
        * les arguments se présentent sous la forme : (arg1, arg2, ...). Dans le cas ou il n'y à qu'un seul argument les parenthèses ne sont pas obligatoires
        * le corps de la méthode (apres ->) peut se faire en une ligne et ne nécessite pas de préciser 'return'
        * Si plusieurs lignes (ou instructions) sont nécessaires, il faudra mettre le corps entre {} et preciser explicitement le return
        * */
        CurrencyStringifier currencyStringifier = amount -> String.format("%.2f £", amount);
        /*CurrencyStringifier currencyStringifier = amount -> {
            int j = i; // une variable externe à une lambda peut être utilisée
            // i = 15; mais ne peut pas être réaffectée
            j = 15; // je ne peux réaffecter que les variables 'locales'
            return  String.format("%.2f £", amount);
        };*/
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
