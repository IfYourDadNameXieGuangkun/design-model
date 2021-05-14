package exception.aye;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootStrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(BootStrap.class).bannerMode(Banner.Mode.OFF).run(args);
    }
}
