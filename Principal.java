public class Principal {
    public static void main (String [ ] args) {
        // Método principal
        System.out.println ("\tJuan Diego Sique Mart\u00ednez \n\tGeordie Josue Quiroa Bulnes \n \tUniversidad Francisco Marroqu\u00edn \n\tCompiladores \n \n");
        //System.out.print(LectorDeArchivo.getInstancia().leerArchivo("numerosyalgomas.txt")); // Comprobación de lectura
        Tokenizador.getInstancia().tokenizar();
        Tokenizador.getInstancia().evaluarTodos();
        for (Token t : Tokenizador.getInstancia().tokens){
            t.tokenBonito();
            System.out.print("  ");
        }
    } 
} 