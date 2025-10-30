package iticbcn.xifratge;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception{

        try {
            // Fem una instancia de KeyPairGenerator i li asociem el algoritme tipus RSA
            KeyPairGenerator clauGenerada = KeyPairGenerator.getInstance("RSA");
            // Inicialitzem la clau amb 2048 bytes de llarg
            clauGenerada.initialize(2048);
            // Retornem el parell de claus
        return clauGenerada.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception{

        try {
            
            // S'utilitza per xifrar i desxifrar dades en aquest cas xifra
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // S'inicialitza el cipher en mode de encriptar i li pasem la clau publica
            cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
            // Retorna el missatge cifrat
            return cipher.doFinal(msg.getBytes("UTF-8"));

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception{

        try {
            
            // S'utilitza per xifrar i desxifrar dades en aquest cas desxifra
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
            // Genera una array de bytes amb el missatge descifrat
            byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
            // Retorna la array de bytes anterior transformantla en una String per a que sigui llegible
            return new String(msgDesxifrat, "UTF-8");

        }catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
