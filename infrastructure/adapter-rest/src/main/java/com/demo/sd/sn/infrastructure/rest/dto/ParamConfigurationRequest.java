package com.demo.sd.sn.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParamConfigurationRequest {
    private String op;
    private String correlationId;
    private String country;
    private String domain;
    private String name;
    private String version;
}
