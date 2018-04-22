/**
 * Created by DakshinS on 4/19/2018.
 */


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CygnetBoot {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NativeHttpServer.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);
    }
}
