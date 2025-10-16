/* Aquest codi xifrará i desxifrara la bateria de text que se li doni amb el metode de xifrat AES
 */

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES{

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORITME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Lalalalala";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                                + e.getLocalizedMessage());
            }

            System.out.println("----------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception{
        //Obtenir els bytes
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        //Genera IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        //Genera hash
        MessageDigest digest = MessageDigest.getInstance(ALGORITME_HASH);
        byte[] keyBytes = digest.digest(clau.getBytes("UTF-8"));
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        //Encrypt.
        byte[] xifrat = cipher.doFinal(msg.getBytes("UTF-8"));

        //Combinar IV i part xifrada
        byte[] combinat = new byte[iv.length + xifrat.length];
        System.arraycopy(iv, 0, combinat, 0, iv.length);
        System.arraycopy(xifrat, 0, combinat, iv.length, xifrat.length);

        //return el combinat
        return combinat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception{

        //Extreu l'IV
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);

        //Extreu la part xifrada
        byte[] xifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        //Fes el hash de la clau
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = digest.digest(clau.getBytes("UTF-8"));
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        
        //Desxifra.
        byte[] desxifrat = cipher.doFinal(xifrat);

        //Return String desxifrat
        return new String(desxifrat, "UTF-8");
    }

}