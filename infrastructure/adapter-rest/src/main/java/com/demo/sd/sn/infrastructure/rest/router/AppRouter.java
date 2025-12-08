package com.demo.sd.sn.infrastructure.rest.router;

import com.demo.sd.sn.infrastructure.rest.dto.CreateUserRequest;
import com.demo.sd.sn.infrastructure.rest.handler.JSONPlaceHolderHandler;
import com.demo.sd.sn.infrastructure.rest.handler.LambdaHandler;
import com.demo.sd.sn.infrastructure.rest.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class AppRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/jsonplaceholder/posts",
                    method = RequestMethod.GET,
                    beanClass = JSONPlaceHolderHandler.class,
                    beanMethod = "getPosts",
                    operation = @Operation(
                            operationId = "getPosts",
                            summary = "Obtener posts desde JSONPlaceholder",
                            description = "Consulta posts de JSONPlaceholder"
                    )
            ),
            @RouterOperation(
                    path = "/users",
                    method = RequestMethod.POST,
                    beanClass = UserHandler.class,
                    beanMethod = "create",
                    operation = @Operation(
                            operationId = "createUser",
                            summary = "Crear un usuario",
                            description = "Crea un nuevo usuario",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    description = "Datos del usuario a crear",
                                    required = true,
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                                    implementation = CreateUserRequest.class
                                            )
                                    )
                            )
                    )
            )
    })
    public RouterFunction<ServerResponse> routes(
            JSONPlaceHolderHandler jsonPlaceHolderHandler,
            UserHandler userHandler,
            LambdaHandler lambdaHandler
    ) {
        return RouterFunctions.route()
                .GET("/jsonplaceholder/posts", jsonPlaceHolderHandler::getPosts)
                .POST("/users", userHandler::create)
                .POST("/lambda/invoke", lambdaHandler::getInvokeLambda)
                .build();
    }
}
