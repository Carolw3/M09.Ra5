package iticbcn.xifratge;

import java.util.Arrays;

public class TextXifrat {
    private byte[] bytes;

    public TextXifrat(byte[] bytesArribats) {
        this.bytes = bytesArribats;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String toString(){
        try {
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            return Arrays.toString(bytes);
        }
    }
    
    
}
