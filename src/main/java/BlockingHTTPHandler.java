



import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.concurrent.Callable;

public class BlockingHTTPHandler implements Callable<Void> {
    private final Socket socket;

    public BlockingHTTPHandler(Socket socket) {
        this.socket = socket;
    }

    public Void call() throws Exception {
        //читаем запрос
        String httpRequest = HttpUtils.readRequest(socket.getInputStream());
        System.out.println("--------------------");
        System.out.println(httpRequest);

        //идет какой то процесс
        String httpResponce = HttpUtils.wrapConnectionClose("Hello!");
        System.out.println(httpResponce);

        //пишем
        Writer writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write(httpResponce);
        writer.flush();
        return null;
    }
}
