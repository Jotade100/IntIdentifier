import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.NumberFormatException;

import javax.swing.text.html.HTMLDocument.HTMLReader.CharacterAction;

public class Tokenizador {

    //Elementos
    List<String> elementos = new ArrayList<>(); //La lista con los strings separados a analizar
    List<Token> tokens = new ArrayList<>(); //La lista con los tokens ya analizados

    //Catacteres
    List<Character> decimal =  Arrays.asList('1','2','3','4','5','6','7','8','9','0');
    List<Character> hexadecimal =  Arrays.asList('a','A','b','B','c','C','d','D','e','E','f','F');

    // Constructor
    public Tokenizador() {}

    //Instancia y getInstancia()
    private static final Tokenizador tokenizador = new Tokenizador();

    public static Tokenizador getInstancia() {
        return tokenizador;
    }

    // Métodos
    public void tokenizar(){
        String chunche = LectorDeArchivo.getInstancia().leerArchivo("numerosyalgomas.txt");
        StringTokenizer separador = new StringTokenizer(chunche, " ");
        while(separador.hasMoreTokens()) {
            elementos.add(separador.nextToken());
        }
    }

    public void evaluarTodos(){
        for(String e : elementos){
            Tokenizador.getInstancia().crearToken(e);
        }
    }

    public void crearToken(String elemento){
        char[] separado = elemento.toCharArray();
        boolean valido = true;
        boolean hex = false;
        // S1
        for(int i= 0; i<separado.length; i++){
            switch(i){
                case 0:
                    if(separado[i] == '-' && separado.length>1){ //Entero con signo //S2
                        valido = true;
                    } else if(separado[i] == '0'){ //0 posible prefijo Hex S3
                        valido = true;
                        hex = true;
                    } else if(decimal.contains(separado[i])){ //Entero normal S4
                        valido = true;
                    } else {
                        valido = false;
                    }
                    break;
                case 1:
                    if(separado[i] == 'x' && hex){ //S5
                        valido = true;
                    } else if(decimal.contains(separado[i])){ //Entero normal S4
                        valido = true;
                        hex = false;
                    } else {
                        valido = false;
                        hex = false;
                    }
                    break;
                default:
                    if(hex) { //S6
                        if(decimal.contains(separado[i]) || hexadecimal.contains(separado[i])){
                            valido = true;
                        } else {
                            valido = false;
                        }

                    } else {
                        if(decimal.contains(separado[i])){ //S4
                            valido = true;
                        } else {
                            valido = false;
                        }
                    }
            }
            //System.out.println(i + " " + valido + " " + hex); //Para comprobar paso a paso
            if(!valido) {
                break;
            }

            
            

        }
        Token agregar = new Token();
        if(valido){
            agregar.type = 1;
            try {
                if(hex) {
                    agregar.value =  Integer.parseInt(elemento.substring(2), 16);
                } else {
                    agregar.value = Integer.parseInt(elemento);
                }
            } catch(NumberFormatException e) {
                agregar.type = 2;
                agregar.value = elemento;
            }
            

        } else {
            agregar.type = 2;
            agregar.value = elemento;
        }
        tokens.add(agregar);

    }



}