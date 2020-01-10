package com.allencai.mycloud.seata.config.properties;

import com.allencai.mycloud.seata.config.SeataConfigurationProvider;
import org.junit.Assert;
import org.junit.Test;

public class SeataConfigurationProviderTest {

    @Test
    public void getFieldTest() {
        FieldTestClass fieldTestClass = new FieldTestClass("aaaa");
        SeataConfigurationProvider provider = new SeataConfigurationProvider();
        Assert.assertEquals(provider.getFiledValue(fieldTestClass, "field"), "aaaa");
    }

    public static class FieldTestClass {
        private String field;

        public FieldTestClass(String field) {
            this.field = field;
        }
    }
}
