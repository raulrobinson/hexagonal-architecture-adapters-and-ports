package com.demo.sd.sn.infrastructure.rest.router;

import com.demo.sd.sn.infrastructure.rest.handler.JSONPlaceHolderHandler;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class JSONPlaceHolderRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/jsonplaceholder/posts",
                    beanClass = JSONPlaceHolderHandler.class,
                    beanMethod = "getPosts",
                    operation = @Operation(
                            operationId = "getPosts",
                            summary = "Obtener posts desde JSONPlaceholder",
                            description = "Consulta posts de JSONPlaceholder"
                    )
            )
    })
    public RouterFunction<ServerResponse> jsonPlaceHolderRoutes(
            JSONPlaceHolderHandler handler
    ) {
        return RouterFunctions.route(
                GET("/jsonplaceholder/posts"),
                handler::getPosts
        );
    }
}
