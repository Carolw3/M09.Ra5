/* Aquest codi encripta un text amb el xifrat per substitució monoalfabetica. Que consisteix en
crear un alfabet endreçat de manera aleatoria i substituir cada lletra del text per la lletra que
es trobi en la mateixa posició que la seva en el alfabet normal. */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Monoalfabetic {
    static String majuscul = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ";
    static char[] alfabet = majuscul.toCharArray();

    static String[] textos = {"Hola que tal? Això está encriptat.", "Com estás en 123", "-Veritat?!"};
    static char[] alfabetPermutat;

    public static void main(String[] args) {
        System.out.printf("Alfabet endresat\n----------------\n");
        System.out.println(alfabet);
        alfabetPermutat = permutaAlfabet(alfabet);
        System.out.printf("\nAlfabet permutat\n----------------\n");
        System.out.println(alfabetPermutat);

        String cadenaDesxifrada = "";
        String text = "";
        String cadenaXifrada = "";

        System.out.println("\nText d'entrada  \t\t\t Encriptat  \t\t\t\t Desencriptat");

        for (int i = 0 ;i < textos.length ; i++){
            text = textos[i];
            cadenaXifrada = xifraMonoAlfa(text);
            cadenaDesxifrada = desxifraMonoAlfa(cadenaXifrada);
            System.out.printf("\n%-35s -> %-35s -> %-35s",text, cadenaXifrada, cadenaDesxifrada);
        }
        System.out.println();

    }

    private static String xifraMonoAlfa(String cadena){
        String xifrada = "";
        for(int i = 0 ; i < cadena.length() ; i++){
            Boolean trobat = false;
            char car = cadena.charAt(i);
            for (int e = 0 ; e < alfabet.length ; e++){
                if(Character.isUpperCase(car)){
                    if (car == alfabet[e]){
                        xifrada += alfabetPermutat[e];
                        trobat = true;
                    }

                }else if(Character.isLowerCase(car)){
                    if(Character.toUpperCase(car) == alfabet[e]){
                        xifrada += Character.toLowerCase(alfabetPermutat[e]);
                        trobat = true;
                    }
                }
            }
            if (!trobat){
                xifrada += car; //Per als simbols
            }
        }
        return xifrada;
    }

    private static String desxifraMonoAlfa(String cadena){
        String desxifrada = "";
        for(int i = 0 ; i < cadena.length() ; i++){
            Boolean trobat = false;
            char car = cadena.charAt(i);
            for(int e = 0 ; e < alfabetPermutat.length ; e++){
                if (Character.isUpperCase(car)){
                    if (car == alfabetPermutat[e]){
                        desxifrada += alfabet[e];
                        trobat = true;
                    }
                }else if(Character.isLowerCase(car)){
                    if(Character.toUpperCase(car) == alfabetPermutat[e]){
                        desxifrada += Character.toLowerCase(alfabet[e]);
                        trobat = true;
                    }
                }
            }
            if (!trobat){
                desxifrada += car;
            }
        }
        return desxifrada;
    }

    private static char[] permutaAlfabet(char[]alfabet){
        List<Character> list = new ArrayList<>();

        for (char item : alfabet){
            list.add(item);
        }

        Collections.shuffle(list);
        char[] alfabetPermutat = new char[list.size()];

        for(int i = 0 ; i < alfabetPermutat.length ; i++){
            alfabetPermutat[i]= list.get(i);
        }
        
        return alfabetPermutat;
    }
}
