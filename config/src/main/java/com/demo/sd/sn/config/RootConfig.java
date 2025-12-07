package com.demo.sd.sn.config;

import com.demo.sd.sn.infrastructure.rest.router.JSONPlaceHolderRouter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ClientConfig.class,
        JSONPlaceHolderConfig.class,
        RestHandlerConfig.class,
        JSONPlaceHolderRouter.class,
        OpenApiConfig.class
})
public class RootConfig {
}
