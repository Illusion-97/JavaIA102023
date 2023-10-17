package d_flux.lambda;

public class Stringifier implements CurrencyStringifier {
    @Override
    public String convert(double amount) {
        return String.format("%.2f €", amount);
    }
}
