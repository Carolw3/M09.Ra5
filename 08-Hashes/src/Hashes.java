import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public long npass = 0;

    private final char[] CHARSET = "abcdefABCDEF1234567890!".toCharArray();

    private final int MAX_LEN = 6;

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                            h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algoritmes = {"SHA-512", "PBKDF2"};

        for(int i=0; i< aHashes.length; i++){
            System.out.printf("=============================\n");
            System.out.printf("Algorisme: %s\n", algoritmes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("-----------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algoritmes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("-----------------------------\n\n");
        }
    }

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] input = (pw + salt).getBytes(StandardCharsets.UTF_8);
        byte[] digest = md.digest(input);
        return HexFormat.of().formatHex(digest);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iterations = 10000;
        int keyLength = 128;
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = skf.generateSecret(spec).getEncoded();
        return HexFormat.of().formatHex(key);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
    // contador de intentos
    this.npass = 0;

    for (int len = MAX_LEN; len >= 1; len--) {
        int[] indices = new int[len];

        while (true) {
            // construir candidate a partir de indices
            char[] candidateChars = new char[len];
            for (int i = 0; i < len; i++) {
                candidateChars[i] = CHARSET[indices[i]];
            }
            String candidate = new String(candidateChars);

            this.npass++;
            String computed;
            if ("SHA-512".equalsIgnoreCase(alg)) {
                computed = getSHA512AmbSalt(candidate, salt);
            } else if ("PBKDF2".equalsIgnoreCase(alg)) {
                computed = getPBKDF2AmbSalt(candidate, salt);
            } else {
                return null;
            }

            if (computed.equalsIgnoreCase(hash)) {
                return candidate;
            }

            int pos = len - 1;
            while (pos >= 0) {
                indices[pos]++;
                if (indices[pos] < CHARSET.length) {
                    break;
                } else {
                    indices[pos] = 0;
                    pos--;
                }
            }

            if (pos < 0) break;
        }
    }

    // si no troba res retorna null
    return null;
}


    public String getInterval(long t1, long t2){
        long diff = Math.max(0, t2 - t1);
        long millis = diff % 1000;
        long secondsTotal = diff / 1000;
        long seconds = secondsTotal % 60;
        long minutesTotal = secondsTotal / 60;
        long minutes = minutesTotal % 60;
        long hoursTotal = minutesTotal / 60;
        long hours = hoursTotal % 24;
        long days = hoursTotal / 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d mills",
                days, hours, minutes, seconds, millis);
    }
}
