public class Rot13 {
    static String minuscul = "aàáäbcdeèéëfghiìíïjklmnoòóöpqrstuùúüvwxyz";
    static String majuscul = "AÀÁÄBCDEÈÉËFGHIÌÍÏJKLMNOÒÓÖPQRSTUÙÚÜVWXYZ";

    static char[] min=minuscul.toCharArray();
    static char[] maj=majuscul.toCharArray();

    static String text = "Hola que tal estàs?";

    public static void main(String[] args) {
       String textCo = Rot13.codifica(text);

    }

    public static String codifica(String text) {
        String textCodificat = "";
        for (int i = 0; i < text.length(); i++) {
            char lletraText = text.charAt(i);
            for (int e = 0 ; e < min.length ; e++){
                char lletraMin = min[e];
                char lletraMaj = maj[e];
                if (lletraMaj == lletraText){
                    int index = (e + 13) % maj.length;
                }else if(lletraMin == lletraText){
                    
                }

            }

        }

        return textCodificat;
    }

    
}

