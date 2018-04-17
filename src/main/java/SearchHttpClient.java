import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dakshins on 16/04/18.
 */
public class SearchHttpClient
{
    public static void main(String []args) throws Exception
    {
        URL url = new URL("http://localhost:9080/search?searchText=MA+sean+canary+FL");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
    
        BufferedReader in = new BufferedReader = new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
        {
            content.append(inputLine);
        }
        
        in.close();
        conn.disconnect();
        
    }
}
