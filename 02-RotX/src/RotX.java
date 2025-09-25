/* Este programa tiene por objetivo codificar un texto sumando x posiciones
respecto a la posicion de la letra en los abecedarios especificados. Lo haremos
usando la furza bruta hasta dar con el desplazamiento correcto */

public class RotX {
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

        String sorpresa = "Rétèp, rét ùú súë fu?";

        System.out.printf("Xifrat\n---------\n");

        for(int i = 0 ; i < textArray.length ; i++ ){
            String text = textArray[i];
            
            String textACodi = RotX.xifraRotX(text, 1*2);
            System.out.printf("(%d)%-30s \t => %-30s\n",i*2 , text, textACodi);
        }

        System.out.printf("\nDesxifrat\n---------\n");

        for(int i = 0 ; i < textArray.length ; i++ ){
            String textC = textCArray[i];

            String codiAText = RotX.desxifraRotX(textC, i*2);
            System.out.printf("(%d)%-30s \t => %-30s\n",i*2 , textC, codiAText);
        }

        System.out.printf("\nMissatge xifrat:\n----------------n");

        forcaBrutaRotX(sorpresa);
    }

    public static String xifraRotX(String text, int n) {
        String textCodificat = "";
        for (int i = 0; i < text.length(); i++) {
            boolean trobat = false;
            char lletraText = text.charAt(i);
            for (int e = 0 ; e < min.length ; e++){
                char lletraMin = min[e];
                char lletraMaj = maj[e];
                int index = 0;
                if (lletraMaj == lletraText){
                    textCodificat += maj[(e + n) % maj.length];
                    trobat = true;
                }else if(lletraMin == lletraText){
                    textCodificat +=  min[(e + n) % min.length];
                    trobat = true;
                }
            }
            if(!trobat){textCodificat += lletraText;}
        }
        return textCodificat;
    }
    public static String desxifraRotX(String codi, int n) {
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
                    textDescodificat += maj[(e - n + maj.length ) % maj.length];
                }else if(lletraMin == lletra){
                    trobat = true;
                    textDescodificat +=  min[(e - n + min.length) % min.length];
                }
            }
            if(!trobat){textDescodificat += lletra;}
        }
        return textDescodificat;

    }

    public static void forcaBrutaRotX(String sorpresa){
        String textDescodificat = "";
        for (int i = 0; i < sorpresa.length(); i++) {
            boolean trobat = false;
            char lletra = sorpresa.charAt(i);
            for (int e = 0 ; e < min.length ; e++){
                char lletraMin = min[e];
                char lletraMaj = maj[e];
                int index = 0;
                if (lletraMaj == lletra){
                    trobat = true;
                    textDescodificat += maj[(i + maj.length ) % maj.length];
                }else if(lletraMin == lletra){
                    trobat = true;
                    textDescodificat +=  min[(i + min.length) % min.length];
                }
            }
            if(!trobat){textDescodificat += lletra;}
        }
        for (int i = 0 ; i < min.length; i++){
            System.out.printf("(%d)-> %s\n",i , textDescodificat);

        }

    }

}
