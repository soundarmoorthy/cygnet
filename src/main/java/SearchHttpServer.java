/**
 * Created by dakshins on 06/04/18.
 */

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class SearchHttpServer
{
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
    
    private void initialize() throws Exception
    {
        server = HttpServer.create(new InetSocketAddress(9080), 0);
        server.createContext("/", new IndexHandler());
        server.createContext("/search", new SearchHandler());
        server.setExecutor(Executors.newFixedThreadPool(8));
        System.out.println("Server started");
    }
    
    private void startHttpServerAndWait() throws Exception
    {
        initialize();
        server.start();
    }
}