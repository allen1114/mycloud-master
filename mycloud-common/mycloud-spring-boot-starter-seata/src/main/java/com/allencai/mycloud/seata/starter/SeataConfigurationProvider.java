package com.allencai.mycloud.seata.starter;

import io.seata.config.Configuration;
import io.seata.config.ExtConfigurationProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class SeataConfigurationProvider implements ExtConfigurationProvider {

    private static final String INTERCEPT_METHOD = "getConfig";

    private static Object config;

    public static void setConfig(Object config) {
        SeataConfigurationProvider.config = config;
    }

    @Override
    public Configuration provide(Configuration configuration) {
        return (Configuration) Enhancer.create(configuration.getClass(), (MethodInterceptor) (proxy, method, args, methodProxy) -> {
            if (method.getName().equals(INTERCEPT_METHOD) && args.length > 0 && args[0] instanceof String) {
                Object result = getConfig(config, (String) args[0]);
                if (null != result) {
                    log.info("get config in ExtConfigurationProvider {} {}", args[0], result);
                    if (method.getReturnType().equals(String.class)) {
                        return String.valueOf(result);
                    } else if (method.getReturnType().isAssignableFrom(result.getClass())) {
                        return result;
                    }
                }
            }
            return method.invoke(configuration, args);
        });
    }

    public Object getConfig(Object obj, String id) {

        if (obj == null || StringUtils.isBlank(id)) {
            return null;
        }
        Object result = getCamelField(obj, id);
        if (result == null && obj instanceof Map) {
            result = getCamelField(obj, id);
        }
        if (result != null) {
            return result;
        }

        String prefix = id;
        while (StringUtils.contains(prefix, ".")) {
            prefix = StringUtils.substringBeforeLast(prefix, ".");
            Object subProperties = getCamelField(obj, prefix);

            if (subProperties == null && obj instanceof Map) {
                subProperties = ((Map) obj).get(prefix);
            }

            if (subProperties != null) {
                return getConfig(subProperties, StringUtils.substringAfter(id, prefix + "."));
            }
        }
        return null;
    }

    public String toCamelCase(String s, char separator) {
        if (s == null) {
            return null;
        }
        String[] tmps = StringUtils.split(s, separator);
        for (int i = 1; i < tmps.length; i++) {
            if (tmps[i].length() > 0) {
                tmps[i] = tmps[i].substring(0, 1).toUpperCase() + tmps[i].substring(1);
            }
        }
        return StringUtils.join(tmps, "");
    }

    public String toCamelCase(String s, String separators) {
        String result = s;
        for (char separator : separators.toCharArray()) {
            result = toCamelCase(result, separator);
        }
        return result;
    }


    public Object getFiledValue(Object o, String name) {
        if (o == null || StringUtils.isBlank(name)) {
            return null;
        }
        Method method;
        try {
            method = o.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
        } catch (NoSuchMethodException e) {
            return null;
        }
        try {
            return method.invoke(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    public Object getCamelField(Object o, String id) {
        if (o == null || StringUtils.isBlank(id)) {
            return null;
        }
        return getFiledValue(o, toCamelCase(id, "-_."));
    }

}
