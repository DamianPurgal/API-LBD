package lbd.fissst.api_lbd;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "API - LBD",
        description = "Some long and useful description :)",
        version = "v1",
        license = @License(
                name = "Apache 2.0",
                url = "https://www.apache.org/licenses/LICENSE-2.0")
        )
)
public class ApiLbdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiLbdApplication.class, args);
    }

}
