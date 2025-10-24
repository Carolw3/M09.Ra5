package iticbcn.xifratge;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORITME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Lalalalala";

    public byte[] xifraAES(String msg, String clau) throws Exception{
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

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception{

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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        
            if (clau == null || clau.isEmpty()) {
                throw new ClauNoSuportada("La clau no pot estar buida");
            }
        try {
            byte[] resultat = xifraAES(msg, clau);
            return new TextXifrat(resultat);

        } catch (ClauNoSuportada e) {
            throw e;
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el xifrat: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        
        if (clau == null||clau.isEmpty()) {
            throw new ClauNoSuportada("La clau no pot estar buida");
        }
        try {
            return desxifraAES(xifrat.getBytes(), clau);

        } catch (ClauNoSuportada e) {
            throw e;
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el desxifrat: " + e.getMessage());
        }
    }

}
