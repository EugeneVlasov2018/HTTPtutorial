import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingHTTPServer_single {
    public static void main(String[] args) throws Exception {
        /*
        * serverSocket будет слушать 80й порт, ожидая приход пакета именно на него
        * backlog - максимальное одномомментное к-во соединений
        * по умолчанию HTTP-протокол юзает 80й порт
        * веб-браузеры по умолчанию подставляют 8080
        * */
        ServerSocket serverSocket = new ServerSocket(80,256);
        while (true){
            /*
            При каждом получении пакета мы создаем новый socket
             */
            Socket socket = serverSocket.accept();
            /*
            и запускаем новый Хєндлер, передавая в него сокет, - типа нового потока
             */
            new BlockingHTTPHandler(socket).call();
        }
    }
}
