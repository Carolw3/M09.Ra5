/* Este programa tiene por objetivo codificar un texto sumando 13 posiciones
 respecto a la posicion de la letra en los abecedarios especificados */

public class Rot13 {
    static String minuscul = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz";
    static String majuscul = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ";

    static char[] min=minuscul.toCharArray();
    static char[] maj=majuscul.toCharArray();
    public static void main(String[] args) {
        String[] textArray = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        String[] textCArray ={
            "IÏJ",
            "FGH",
            "Òwúi, Ùá. jiúkwb",
            "Zmálx, zmá bc acñ nà?"
        };

        System.out.printf("Xifrat\n---------\n");

        for(int i = 0 ; i < textArray.length ; i++ ){
            String text = textArray[i];
            String textACodi = Rot13.xifraRot13(text);

            System.out.printf("%-30s \t => %-30s\n", text, textACodi);
        }
        System.out.printf("\nDesxifrat\n---------\n");

        for(int i = 0 ; i < textArray.length ; i++ ){
            String textC = textCArray[i];
            String codiAText = Rot13.desxifraRot13(textC);

            System.out.printf("%-30s \t => %-30s\n", textC, codiAText);
        }
    }

    public static String xifraRot13(String text) {
        String textCodificat = "";
        for (int i = 0; i < text.length(); i++) {
            boolean trobat = false;
            char lletraText = text.charAt(i);
            for (int e = 0 ; e < min.length ; e++){
                char lletraMin = min[e];
                char lletraMaj = maj[e];
                int index = 0;
                if (lletraMaj == lletraText){
                    textCodificat += maj[(e + 13) % maj.length];
                    trobat = true;
                }else if(lletraMin == lletraText){
                    textCodificat +=  min[(e + 13) % min.length];
                    trobat = true;
                }
            }
            if(!trobat){textCodificat += lletraText;}
        }
        return textCodificat;
    }
    public static String desxifraRot13(String codi) {
        String textDescodificat = "";
        for (int i = 0; i < codi.length(); i++) {
            boolean trobat = false;
            char lletra = codi.charAt(i);
            for (int e = 0 ; e < min.length ; e++){
                char lletraMin = min[e];
                char lletraMaj = maj[e];
                int index = 0;
                if (lletraMaj == lletra){
                    trobat = true;
                    textDescodificat += maj[(e - 13 + maj.length ) % maj.length];
                }else if(lletraMin == lletra){
                    trobat = true;
                    textDescodificat +=  min[(e - 13 + min.length) % min.length];
                }
            }
            if(!trobat){textDescodificat += lletra;}
        }
        return textDescodificat;

    }

    
}

