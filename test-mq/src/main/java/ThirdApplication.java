import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lizhen created on 2021-10-13 15:15
 */
@ComponentScan(basePackages = {"com.mq"})
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class ThirdApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ThirdApplication.class);


    public static void main(String[] args) {
        Stopwatch sw = Stopwatch.createStarted();
        SpringApplication.run(ThirdApplication.class, args);
        LOG.info("##################################################");
        LOG.info("edu-archive-callback service Start Success. Cost " + sw.stop());
        LOG.info("##################################################");
    }
}
