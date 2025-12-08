package com.demo.sd.sn.config;

import com.demo.sd.sn.config.application.JSONPlaceHolderConfig;
import com.demo.sd.sn.config.application.UserConfig;
import com.demo.sd.sn.config.aws.AwsConfig;
import com.demo.sd.sn.config.client.ClientConfig;
import com.demo.sd.sn.config.openapi.OpenApiConfig;
import com.demo.sd.sn.config.persistence.PersistenceConfig;
import com.demo.sd.sn.config.rest.RestHandlerConfig;
import com.demo.sd.sn.config.secrets.SecretsConfig;
import com.demo.sd.sn.infrastructure.rest.router.AppRouter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ClientConfig.class,
        JSONPlaceHolderConfig.class,
        AwsConfig.class,
        SecretsConfig.class,
        RestHandlerConfig.class,
        AppRouter.class,
        UserConfig.class,
        PersistenceConfig.class,
        OpenApiConfig.class
})
public class RootConfig {
}
