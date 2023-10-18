package d_flux;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        String[] months = DateFormatSymbols.getInstance().getMonths();

        /*for(String month : months) {
            System.out.println(month);
        }*/

        //Arrays.stream(months).forEach(month -> System.out.println(month));
        //Arrays.stream(months).forEach(System.out::println); ForEach effectue une action en utilisant les éléments présent dans le stream et met fin a son utilisation

        List<String> monthsList = Arrays.stream(months).toList(); // Récupere tous les élément du stream dans une liste qui va être renvoyée
        //monthsList.stream().map(m-> m.toUpperCase()).forEach(month -> System.out.println(month));

        monthsList.stream()
                .map(String::toUpperCase) // Map transforme les élément selon une méthode donnée (ou écrite) (Function)
                .forEach(System.out::println);


        /*monthsList.stream()
                .filter(month -> month.length() == 4) // Filter me permet de récupérer uniquement les élément dans mon flux répondant au critere demandé (Predicate)
                .forEach(System.out::println);*/


        //monthsList.stream().filter(month -> month.isBlank()).count();
        System.out.println("Nombre de chaine vide = " + monthsList.stream().filter(String::isBlank).count());
        monthsList = monthsList.stream().filter(month -> !month.isBlank()).toList();

        // mars, avril, mai, juin, août
        System.out.println(monthsList.stream()
                .filter(month -> month.length() <= 5)
                .collect(Collectors.joining(", ")));

        System.out.println("La liste contiens au moins un élément contenant des caractères spéciaux : " + monthsList.stream().anyMatch(Streams::containsSpecialChar));

        System.out.println("Total de lettres : " +
                monthsList.stream()
                        .map(String::length)
                        .reduce(0, (a,b) -> a+b)); // Reduce permet de cumuler les éléments du stream en un seul, son retour est du meme type que les éléments du stream
        // La fonction BiOperator indique comment cumuler ces elements (ici une somme)
        // Identity n'est rien d'autre que la valeur initiale

        /*System.out.println("Total de lettres : " +
                monthsList.stream()
                        .map(String::length)
                        .reduce(0, Integer::sum));*/
        // Les 3 mois avec le plus grand nombre de lettres (et les afficher)
        System.out.println("Les 3 mois avec le plus grand nombre de lettres");
        monthsList.stream()
                //.sorted() sorted par défaut utilise l'ordre naturel (alphabétique pour du texte, croissant pour des nombres)
                .sorted((current,next) -> current.length() - next.length()) // un Comparateur utilise la valeur courante et la suivante pour obtenir un nombre : inférieur a 0 si la courante est avant la suivante, et inversement
                .skip(monthsList.size() - 3)
                .forEach(System.out::println);
        /*monthsList.stream()
                .sorted(Comparator.comparingInt(String::length))
                .skip(monthsList.size() - 3)
                .forEach(System.out::println);*/
        /*monthsList.stream()
                //.sorted() sorted par défaut utilise l'ordre naturel (alphabétique pour du texte, croissant pour des nombres)
                .sorted((current,next) ->  next.length() - current.length()) // un Comparateur utilise la valeur courante et la suivante pour obtenir un nombre : inférieur a 0 si la courante est avant la suivante, et inversement
                //.skip(monthsList.size() - 3)
                .limit(3)
                .forEach(System.out::println);*/
        // Afficher la premiere moitié de ma liste
        System.out.println("Première moitié de la liste");
        monthsList.stream()
                .limit(monthsList.size() / 2)
                //.limit((long)Math.ceil(monthsList.size() / 2f))
                .forEach(System.out::println);
    }

    private static boolean containsSpecialChar(String value) {
        return !value.matches("[a-z]*");
    }
}
