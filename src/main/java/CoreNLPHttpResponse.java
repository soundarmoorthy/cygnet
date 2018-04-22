import com.google.common.base.Strings;
import edu.stanford.nlp.util.ArrayMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Created by dakshins on 07/04/18.
 */
public class CoreNLPHttpResponse
{
    private static final String invalidResponseBody = "The input search text is not valid. Please try again with " +
            "valid text";
    private static final int invalidHttpResponseCode = 422;

    private int httpResponseCode;
    private String responseBody;
    private Map<String,String> headers;

    public static CoreNLPHttpResponse ERROR = new CoreNLPHttpResponse()
            .withBody(invalidResponseBody)
            .withHttpCode(invalidHttpResponseCode);


    public static final CoreNLPHttpResponse NEW = new CoreNLPHttpResponse();

    private CoreNLPHttpResponse()
    {
    }

    public CoreNLPHttpResponse withBody(String body)
    {
        this.responseBody = body;
        return this;
    }

    public CoreNLPHttpResponse withHeaders(Map<String,String> headers)
    {
        this.headers = headers;
        return this;
    }

    public CoreNLPHttpResponse withHttpCode(int code)
    {
        this.httpResponseCode = code;
        return this;
    }
    
    public int length()
    {
        return responseBody.length();
    }
    
    public String getBody()
    {
        if(Strings.isNullOrEmpty(responseBody))
        {
            return "";
        }
        else
        {
            return responseBody;
        }
    }

    public int getHttpCode()
    {
        return httpResponseCode;
    }

    public Map<String, String> getHeaders()
    {
        return headers;
    }
}
