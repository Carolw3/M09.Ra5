package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    String majuscul = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ";
    char[] alfabet = majuscul.toCharArray();

    private char[] alfabetPermutat = new char[alfabet.length];
    long clauSecreta = 753;

    Random random;
    public void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                        "Test 02 Taüll, DÍA, año",
                        "Test 03 Peça, Òrrius, Bòvila"};
                        
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n------------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    private void initRandom(long clau){
        random = new Random(clau);
    }

    private String xifraPoliAlfa(String msg){
        String xifrada = "";
        for (int i = 0 ; i < msg.length() ;i++){
            Boolean trobat = false;
            char car = msg.charAt(i);
            permutaAlfabet();
            for(int e = 0 ; e < alfabet.length ; e++ ){
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
            if(!trobat){ xifrada += car ;}

        }
        return xifrada;

    }

    private String desxifraPoliAlfa(String msg){
        String desxifrada = "";
        for(int i = 0 ; i < msg.length() ; i++){
            Boolean trobat = false;
            char car = msg.charAt(i);
            permutaAlfabet();
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


    public void permutaAlfabet(){
        List<Character> list = new ArrayList<>();

        for (char item : alfabet){
            list.add(item);
        }

        Collections.shuffle(list, random);

        for(int i = 0 ; i < alfabetPermutat.length ; i++){
            alfabetPermutat[i]= list.get(i);
        }
        alfabetPermutat = alfabetPermutat;
    }
}
