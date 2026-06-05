package hr.algebra.backendapi.service.impl;

import hr.algebra.backendapi.configuration.BackupProperties;
import hr.algebra.backendapi.configuration.DbConnectionInfo;
import hr.algebra.backendapi.exception.DatabaseOperationException;
import hr.algebra.backendapi.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostgresDatabaseService implements DatabaseService {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private final DbConnectionInfo dbConnectionInfo;
    private final BackupProperties backupProperties;


    @Override
    public String createBackup() {
        try {
            // create directory
            Files.createDirectories(backupProperties.directory());

            // generate filename
            String filename = generateFilename();
            // execute pg_dump
            ProcessBuilder builder = createBackupProcess(resolveBackupPath(filename).toString());
            execute(builder);
            // return filename
            return filename;
        } catch (IOException e) {
            throw new DatabaseOperationException("Failed to create backup directory", e);
        }
    }

    private String generateFilename() {
        return backupProperties.prefix()
                + LocalDateTime.now().format(TIMESTAMP_FORMATTER)
                + backupProperties.extension();
    }

    private Path resolveBackupPath(String filename) {
        return backupProperties.directory().resolve(filename);
    }

    private ProcessBuilder createBackupProcess(String filepath) {
        return new ProcessBuilder(
                "pg_dump",
                "-h", dbConnectionInfo.host(),
                "-p", dbConnectionInfo.port(),
                "-U", dbConnectionInfo.username(),
                "-d", dbConnectionInfo.database(),
                "-f", filepath);
    }

    @Override
    public void restoreBackup(String filename) {
        if (!filename.endsWith(backupProperties.extension())) {
            throw new DatabaseOperationException("Invalid backup filename");
        }

        Path backupPath = resolveBackupPath(filename);
        // validate file exists
        if (!Files.isRegularFile(backupPath)) {
            throw new DatabaseOperationException("Backup file " + backupPath + " does not exist");
        }
        // execute pg_restore
        execute(createRestoreProcess(backupPath.toString()));
    }

    @Override
    public List<String> getListOfBackups() {
        try (Stream<Path> files = Files.list(backupProperties.directory())) {
            return files.map(Path::getFileName)
                    .map(Path::toString)
                    .filter(filename -> filename.startsWith(backupProperties.prefix()))
                    .toList();
        } catch (IOException e) {
            throw new DatabaseOperationException("Failed to list backup files", e);
        }
    }

    private ProcessBuilder createRestoreProcess(String filepath) {
        return new ProcessBuilder(
                "pg_dump",
                "--clean",
                "--if-exists",
                "-h", dbConnectionInfo.host(),
                "-p", dbConnectionInfo.port(),
                "-U", dbConnectionInfo.username(),
                "-d", dbConnectionInfo.database(),
                "-f", filepath
        );
    }

    private void execute(ProcessBuilder builder) {
        try {
            Process process = builder.start();

            String error = new String(process.getErrorStream().readAllBytes());
            int exitCode = process.waitFor();

            System.out.println(error);
            if (exitCode != 0) {
                throw new DatabaseOperationException("Failed to execute command: " + builder.command() + " with exit code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DatabaseOperationException("Failed to execute command: " + builder.command(), e);
        }
    }
}
