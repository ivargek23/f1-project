package hr.algebra.backendapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "postgres")
public record DbConnectionInfo(
    String username,
    String database,
    String port,
    String host
) {}
