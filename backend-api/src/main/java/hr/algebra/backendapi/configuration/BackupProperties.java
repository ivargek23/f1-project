package hr.algebra.backendapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

@ConfigurationProperties(prefix = "backup")
public record BackupProperties(
        Path directory,
        String pgDumpPath,
        String prefix,
        String extension
) {
}
