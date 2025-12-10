package com.demo.sd.sn.domain.port.out;

public interface OutParameterPort {
    <T> T getParameter(String parameterName, Class<T> clazz);
}
