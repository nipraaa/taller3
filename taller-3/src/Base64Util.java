import java.util.Base64;

public class Base64Util {

    public static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    public static String decode(String base64Text) {
         byte[] array = Base64.getDecoder().decode(base64Text);

         return new String(array);
    }

}
