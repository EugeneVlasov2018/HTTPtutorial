import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BlockingHTTPServer_multi{
    public static void main(String[] args) throws IOException {
        /*
        * Конструктор ThreadPoolExecutor
        * corePoolSize - мы стартуем с 0 потоков
        * maximumPoolSize - максимальное к-во потоков
        * keepAliveTime - ждем 60 секунд(TimeUnit.SECONDS) и убиваем все потоки до corePoolSize (nen - lj 0)
        * иными словами каждому потоку мы дадим 60 секунд на работу
        */
        ExecutorService executor = new ThreadPoolExecutor(0,100,60L,
                TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        ServerSocket serverSocket = new ServerSocket(80,256);

        while (true){
            Socket socket = serverSocket.accept();
            executor.submit(new BlockingHTTPHandler(socket));
        }
    }
}
