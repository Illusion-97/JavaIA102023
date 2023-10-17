package d_flux.lambda;

@FunctionalInterface // N'est pas obligatoire
public interface CurrencyStringifier { // Une interface qui ne possede qu'une seule signature de méthode est appelée Interface Fonctionnelle
    String convert(double amount);
    // String valueOf(double d)
}
