import java.io.IOException;
import java.io.InputStream;

public class HttpUtils {
    public static String readRequest(InputStream inputStream) throws IOException {
        String httpHeader = "";
        while (!httpHeader.endsWith("\r\n\r\n")){
            httpHeader+=(char)inputStream.read();
        }
        return httpHeader;
    }

    public static String wrapConnectionClose(String s) {
        return null;
    }
}
