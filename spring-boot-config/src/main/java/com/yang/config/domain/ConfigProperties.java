package com.yang.config.domain;

import java.util.List;
import java.util.Map;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;


//@Component
@ConfigurationProperties(prefix = "mail")
@ToString
@Setter
public class ConfigProperties {

  private String hostName;
  private int port;
  private String from;

  private List<String> defaultRecipients;
  private Map<String, String> additionalHeaders;

}
