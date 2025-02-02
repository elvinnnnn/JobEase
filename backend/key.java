import java.security.SecureRandom;
import java.util.Base64;

public class key {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
                                    // the 256 required bits 
        random.nextBytes(bytes);
        var encoder = Base64.getUrlEncoder().withoutPadding();
        String secretKey = encoder.encodeToString(bytes);
        System.out.println(secretKey);
    }
}
