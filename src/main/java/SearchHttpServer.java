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
    
    private SearchHttpServer() throws  Exception
    {
        server = HttpServer.create(new InetSocketAddress(9080), 0);
        server.setExecutor(Executors.newFixedThreadPool(8));
        Routes.setup(server);
    }
    
    private void startHttpServerAndWait() throws Exception
    {
        server.start();
    }
}