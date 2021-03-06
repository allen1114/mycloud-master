package com.allencai.mycloud.seata.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.client.undo")
public class SeataClientUndo {

    private String dataValidation;
    private String logSerialization;
    private String logTable;

}
