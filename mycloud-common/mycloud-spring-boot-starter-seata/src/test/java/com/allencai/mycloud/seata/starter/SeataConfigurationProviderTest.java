package com.allencai.mycloud.seata.starter;


import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SeataConfigurationProviderTest {

    @Test
    public void getFiledValueTest() {
        SeataConfigurationProvider provider = new SeataConfigurationProvider();
        CamelFieldTestClass camelFieldTestClass = new CamelFieldTestClass();
        camelFieldTestClass.setRecoveryAsynCommittingRetryPeriod("10000");
        Assert.assertEquals(provider.getFiledValue(camelFieldTestClass, "recoveryAsynCommittingRetryPeriod"), "10000");
    }

    @Test
    public void getCamelFieldTest() {
        SeataConfigurationProvider provider = new SeataConfigurationProvider();
        CamelFieldTestClass camelFieldTestClass = new CamelFieldTestClass();
        camelFieldTestClass.setRecoveryAsynCommittingRetryPeriod("10000");
        Assert.assertEquals(provider.getCamelField(camelFieldTestClass, "recovery.asyn_committing-retry-period"), "10000");
    }

    @Data
    private static class CamelFieldTestClass {
        private String recoveryAsynCommittingRetryPeriod;
    }

    @Test
    public void toCamelCaseTest() {
        SeataConfigurationProvider provider = new SeataConfigurationProvider();
        Assert.assertEquals(provider.toCamelCase("recovery.asyn.committing.retry.period", '.'), "recoveryAsynCommittingRetryPeriod");
        Assert.assertEquals(provider.toCamelCase("recovery.asyn_committing-retry.period", "-_."), "recoveryAsynCommittingRetryPeriod");

    }

    @Test
    public void getPropertiesWithSeparatorInObj() {
        SeataConfigurationProvider provider = new SeataConfigurationProvider();
        SubProperties subProperties = new SubProperties();
        subProperties.setName("allen");
        subProperties.setUserName("allen");
        FatherProperties fatherProperties = new FatherProperties();
        fatherProperties.setSub(subProperties);
        Assert.assertEquals(provider.getConfig(fatherProperties, "sub.name"), "allen");
        Assert.assertEquals(provider.getConfig(fatherProperties, "sub.user.name"), "allen");
        Assert.assertEquals(provider.getConfig(fatherProperties, "sub.user-name"), "allen");
        Assert.assertEquals(provider.getConfig(fatherProperties, "sub.user_name"), "allen");
    }

    @Test
    public void getPropertiesInMap() {
        SeataConfigurationProvider provider = new SeataConfigurationProvider();
        MapProperties mapProperties = new MapProperties();
        SubProperties subProperties = new SubProperties();
        subProperties.setName("allen");
        subProperties.setUserName("allen");
        mapProperties.setMapSub(new HashMap<>());
        mapProperties.getMapSub().put("sub", subProperties);
        Assert.assertEquals(provider.getConfig(mapProperties, "mapSub.sub.name"), "allen");
        Assert.assertEquals(provider.getConfig(mapProperties, "mapSub.sub.user.name"), "allen");
        Assert.assertEquals(provider.getConfig(mapProperties, "mapSub.sub.user-name"), "allen");
        Assert.assertEquals(provider.getConfig(mapProperties, "mapSub.sub.user_name"), "allen");
    }

    @Data
    public static class FatherProperties {
        SubProperties sub;
    }

    @Data
    public static class SubProperties {
        private String name;
        private String userName;
    }

    @Data
    public static class MapProperties {
        private Map<String, SubProperties> mapSub;
    }

}
