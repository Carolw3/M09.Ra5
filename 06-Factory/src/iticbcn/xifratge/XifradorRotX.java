package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    String minuscul = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz";
    String majuscul = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ";

    char[] min=minuscul.toCharArray();
    char[] maj=majuscul.toCharArray();

    public String xifraRotX(String text, int n) {
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
    public String desxifraRotX(String codi, int n) {
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

    public void forcaBrutaRotX(String sorpresa){
        for(int x = 0 ; x < min.length ; x++ ){
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
                        textDescodificat += maj[(e - x + maj.length ) % maj.length];
                    }else if(lletraMin == lletra){
                        trobat = true;
                        textDescodificat +=  min[(e - x + min.length) % min.length];
                    }
                }
                if(!trobat){textDescodificat += lletra;}
            }
            System.out.printf("(%d) -> %s\n", x, textDescodificat );
        }
    }
}