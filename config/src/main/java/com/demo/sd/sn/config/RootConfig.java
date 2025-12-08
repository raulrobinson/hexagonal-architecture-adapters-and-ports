package com.demo.sd.sn.config;

import com.demo.sd.sn.config.application.JSONPlaceHolderConfig;
import com.demo.sd.sn.config.application.UserConfig;
import com.demo.sd.sn.config.aws.AwsConfig;
import com.demo.sd.sn.config.client.ClientConfig;
import com.demo.sd.sn.config.lambda.LambdaConfig;
import com.demo.sd.sn.config.openapi.OpenApiConfig;
import com.demo.sd.sn.config.persistence.PersistenceConfig;
import com.demo.sd.sn.config.rest.RestHandlerConfig;
import com.demo.sd.sn.infrastructure.rest.router.AppRouter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AwsConfig.class,
        LambdaConfig.class,
        ClientConfig.class,
        JSONPlaceHolderConfig.class,
        RestHandlerConfig.class,
        AppRouter.class,
        UserConfig.class,
        PersistenceConfig.class,
        OpenApiConfig.class
})
public class RootConfig {
}
