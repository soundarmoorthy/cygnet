import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

import java.io.*;
import java.util.Base64;

/**
 * Created by DakshinS on 4/21/2018.
 */
public class TransientCache
{
    private static RedisStringCommands command;
    private static boolean initialized = false;

    private static void initialize()
    {
        RedisClient client = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = client.connect();
        command = connection.sync();
        initialized = true;
    }

    public static <T> T Load(Class<T> type) throws Exception
    {
        if(!initialized)
            initialize();

        String key = type.getTypeName();
        String value = (String)command.get(key);
        return value == null ? null : decode(value);
    }

    public static void Store(Object object) throws  Exception
    {
        if (!initialized)
            initialize();

        if (object == null)
        {
            throw new NullPointerException("Cannot store a null object in Cache");
        }

        String key = object.getClass().getName();
        String value = encode(object);
        if (value == null)
            throw new Exception("Unable to serailize object. One or more of the objects in the object hierarchy doesn't support serialization.");
        command.set(key, value);
    }

    private static String encode(Object obj)
    {
        if(obj == null)
            throw new NullPointerException("parameter : obj");
        try
        {
            Base64.Encoder encoder = java.util.Base64.getEncoder();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            String encoded = encoder.encodeToString(byteArrayOutputStream.toByteArray());
            return encoded;
        }
        catch(NotSerializableException ex)
        {
            ex.printStackTrace();
            System.out.println("Cannot serialize object " + obj.getClass().getName());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T decode(String value)
    {
        try
        {
            Base64.Decoder decoder = java.util.Base64.getDecoder();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decoder.decode(value));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            T object = (T) objectInputStream.readObject();
            objectInputStream.close();
            return object;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
