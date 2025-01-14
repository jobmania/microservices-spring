package se.magnus.microservices.composite.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import static java.util.Collections.emptyList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableSwagger2WebFlux
@SpringBootApplication
@ComponentScan("se.magnus")
public class ProductCompositeServiceApplication {

    @Value("${api.common.version}")           String apiVersion;
    @Value("${api.common.title}")             String apiTitle;
    @Value("${api.common.description}")       String apiDescription;
    @Value("${api.common.termsOfServiceUrl}") String apiTermsOfServiceUrl;
    @Value("${api.common.license}")           String apiLicense;
    @Value("${api.common.licenseUrl}")        String apiLicenseUrl;
    @Value("${api.common.contact.name}")      String apiContactName;
    @Value("${api.common.contact.url}")       String apiContactUrl;
    @Value("${api.common.contact.email}")     String apiContactEmail;

	/**
	 * Will exposed on $HOST:$PORT/swagger-ui.html
	 * -> localhost:8080/swagger-ui/index.html 로 접근해보세요
	 * @return
	 */
	@Bean
	public Docket apiDocumentation() {

		return new Docket(DocumentationType.OAS_30)
			.select()
			.apis(basePackage("se.magnus.microservices.composite.product"))
			.paths(PathSelectors.any())
			.build()
                .globalResponseMessage(GET, emptyList())
				.apiInfo(new ApiInfo(
                    apiTitle,
                    apiDescription,
                    apiVersion,
                    apiTermsOfServiceUrl,
                    new Contact(apiContactName, apiContactUrl, apiContactEmail),
                    apiLicense,
                    apiLicenseUrl,
                    emptyList()
                ));
    }

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductCompositeServiceApplication.class, args);
	}
}
