/**
 * Created by dakshins on 06/04/18.
 */

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.CommandLineRunner;


public class SearchHttpServer implements CommandLineRunner
{
    private HttpServer server;
    private final int port = 9080;

    public SearchHttpServer() throws Exception
    {
        server = HttpServer.create(new InetSocketAddress(port), 0);
    }
    
    private void initializeRoutine() throws Exception
    {
        server.setExecutor(Executors.newFixedThreadPool(8));
        Routes.setup(server);
        CoreNLPWrapper.initialize();
    }
    
    @Override
    public void run(String... strings) throws Exception
    {
        System.out.println("Beging server initialization...");
        initializeRoutine();
        System.out.println("Initialization sequence complete. Server up and running");
        System.out.println("Browse to http://localhost:"+port);
        server.start();
    }
}