/**
 * Created by dakshins on 06/04/18.
 */

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;

public class SearchHttpServer
{
    private final int port = 9080;
    public static void main(String[] args)
    {
        try
        {
            new SearchHttpServer().startHttpServerAndWait();
        }
        catch (Exception ex)
        {
            System.out.println("******** The server crashed ********* " );
            ex.printStackTrace();
        }
    }
    
    private HttpServer server;
    
    private SearchHttpServer() throws  Exception
    {
        System.out.println("Beging server initialization...");
        initializeRoutine();
        System.out.println("Initialization sequence complete. Server up and running");
        System.out.println("Browse to http://localhost:"+port);
    }
    
    private void initializeRoutine() throws Exception
    {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newFixedThreadPool(8));
        Routes.setup(server);
        CoreNLPWrapper.initialize();
    }
    
    private void startHttpServerAndWait() throws Exception
    {
        server.start();
    }
}