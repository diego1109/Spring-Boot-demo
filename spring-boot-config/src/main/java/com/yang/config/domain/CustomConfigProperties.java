package com.yang.config.domain;

import java.util.List;
import java.util.Map;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:/customConfigProperties.properties")
@ConfigurationProperties(prefix = "custom")
@Component
@ToString
@Setter
public class CustomConfigProperties {

  private String hostName;
  private int port;
  private String from;

  private List<String> defaultRecipients;
  private Map<String, String> additionalHeaders;
}
