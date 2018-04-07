import com.sun.net.httpserver.HttpServer;

/**
 * Created by dakshins on 07/04/18.
 */
public final class Routes
{
    public static void setup(HttpServer server)
    {
        server.createContext("/", new IndexHandler());
        server.createContext("/search", new SearchHandler());
    }
}
