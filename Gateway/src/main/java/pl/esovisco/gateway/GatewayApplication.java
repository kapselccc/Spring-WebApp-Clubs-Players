package pl.esovisco.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("clubs", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/clubs/{id}", "/clubs", "")
                        .uri("http://localhost:8081"))
                .route("players", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/players", "/players/{id}","/players/{id}/**","/clubs/{id}/players")
                        .uri("http://localhost:8082"))
                .build();
    }

}
