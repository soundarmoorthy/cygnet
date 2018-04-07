import com.google.common.base.Strings;


/**
 * Created by dakshins on 07/04/18.
 */
public class CoreNLPHttpResponse
{
    private static final String invalidResponseBody = "The input search text is not valid. Please try again with " +
            "valid text";
    private static final int invalidHttpResponseCode = 422;
    private static final int validHttpResponseCode = 200;
    int httpResponseCode = validHttpResponseCode;
    String responseBody  = "OK";
    
    public static CoreNLPHttpResponse ERROR = new CoreNLPHttpResponse(invalidResponseBody, invalidHttpResponseCode);
    
    public CoreNLPHttpResponse(String body, int code)
    {
        responseBody = body;
        httpResponseCode = code;
    }
    
    public int httpCode()
    {
        return httpResponseCode;
    }
    
    public int length()
    {
        return responseBody.length();
    }
    
    public String body()
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
}
