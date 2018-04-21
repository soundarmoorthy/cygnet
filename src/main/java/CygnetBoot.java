/**
 * Created by DakshinS on 4/19/2018.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.ImageBanner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class CygnetBoot {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SearchHttpServer.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);
    }
}
