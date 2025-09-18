public class Rot13 {
    static String minuscul = "aàáäbcdeèéëfghiìíïjklmnoòóöpqrstuùúüvwxyz";
    static String majuscul = "AÀÁÄBCDEÈÉËFGHIÌÍÏJKLMNOÒÓÖPQRSTUÙÚÜVWXYZ";

    static char[] min=minuscul.toCharArray();
    static char[] maj=majuscul.toCharArray();

    static String text = "Hola que tal estàs?";

    public static void main(String[] args) {
       String textCo = Rot13.codifica(text);
       System.out.println(textCo);

    }

    public static String codifica(String text) {
        String textCodificat = "";
        for (int i = 0; i < text.length(); i++) {
            char lletraText = text.charAt(i);
            for (int e = 0 ; e < min.length ; e++){
                char lletraMin = min[e];
                char lletraMaj = maj[e];
                int index = 0;
                if (lletraMaj == lletraText){
                    if (e > (maj.length - 13)){
                        index = (e + 13) % maj.length;
                    }else{
                        index = e + 13;
                    }
                    textCodificat += maj[index];
                    break;
                }else if(lletraMin == lletraText){
                    if (e > (min.length - 13)){
                        index = (e + 13) % min.length;
                    }else{
                        index = e + 13;
                    }
                    textCodificat += min[index];
                    break;
                }else{
                    textCodificat += lletraText;
                    break;
                }

            }

        }

        return textCodificat;
    }

    
}

