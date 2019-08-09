package ${package};

import io.github.kgress.scaffold.environment.config.ScaffoldConfiguration;
import io.github.kgress.scaffold.webdriver.ScaffoldBaseTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = { EnvironmentConfig.class, ScaffoldConfiguration.class }
)
public abstract class BaseTest extends ScaffoldBaseTest {

    @Autowired
    protected Navigation navigation;
}