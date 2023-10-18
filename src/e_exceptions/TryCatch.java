package e_exceptions;

public class TryCatch {
    public static void main(String[] args) {
        // Exception || Error
        /*
        * Une exception est une erreur provoquée par une 'mauvaise gestion du code'
        * C'est une situation ou on estime que les développeurs peuvent agir
        * Une erreur n'est pas liée au code, mais a l'environnement dans lequel celui-ci s'exécute
        * ex : StackOverflowError, mémoire système insuffisante
        * */
        System.out.println();
        String nullString = null;
        // String nullStringReplace = nullString.replace(";", "!"); Process finished with exit code 1 (Programme planté à cause de l'exception

        try {
            String nullStringReplace = nullString.replace(";", "!");

        } catch (NullPointerException e) { // Process finished with exit code 0 (L'exception ne bloque plus mon programme)
            System.out.println("Classe d'Exception : " + e.getClass().getSimpleName());
            System.out.println("Message d'Exception : " + e.getMessage());
            e.printStackTrace(System.out);
        }
        System.out.println();

        try {
            throwException();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println();

        try {
            //throwException();
            throwCustomException(); // ne sera jamais atteint puisqu'une exception aura été déclanchée avant
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println();

        try {
            throwRuntimeException();
        } catch (Exception e) { // Il est possible d'avoir une gestion 'globale' en capturant l'exception Parent
            e.printStackTrace(System.out);
        }
        System.out.println();

        try {
            throwError();
            throwRuntimeException();
        } catch (RuntimeException | Error  e) { // Je peux préciser plusieurs types de throwable pour un cas de catch en les séparant par un |
            e.printStackTrace(System.out);
        }/* catch (Error e) {
            e.printStackTrace(System.out);
        }*/
        System.out.println();

        try {
            throwException();
        } catch (Exception e) {
            System.out.println("Exception levée : " + e.getMessage());
            try {
                throwError();
            } catch (Error error) {
                System.out.println("Erreur levée : " + error.getMessage());

            }
        } finally { // sera toujours exécuté (autant si tout se passe bien que si une exception est levée)
            try {
                throwRuntimeException();
            } catch (RuntimeException e) {
                System.out.println("La stacktrace se trouve toujours en fin de console");
                e.printStackTrace(System.out);
            } finally {
                throwCustomRuntimeException();
            }
        }

    }

    private static void throwException() throws Exception { // Je préviens que ma méthode peut provoquer une Exception
        throw new Exception("Generic Exception"); // Je la provoque explicitement
    }

    private static void throwCustomException() throws CustomException {
        throw new CustomException();
    }

    private static void throwRuntimeException() throws RuntimeException {
        // Une runtime exception fera aussi bien planter le programme qu'une exception classique
        // La seule différence est qu'elle ne force pas sa gestion
        throw new RuntimeException("Generic Runtime Exception");
    }

    private static void throwCustomRuntimeException()  {
        throw new CustomRuntimeException("Custom Runtime Exception");
    }

    private static  void throwError() {
        throw new Error("Generic Error");
    }
}
