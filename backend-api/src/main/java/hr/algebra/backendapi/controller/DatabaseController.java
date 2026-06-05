package hr.algebra.backendapi.controller;

import hr.algebra.backendapi.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database")
@RequiredArgsConstructor
public class DatabaseController {
    private final DatabaseService databaseService;

    @GetMapping
    public ResponseEntity<List<String>> getAvailableBackups() {
        return ResponseEntity.ok(databaseService.getListOfBackups());
    }

    @PostMapping("/backup")
    public ResponseEntity<String> backupDatabase() {
        return ResponseEntity.ok(databaseService.createBackup());
    }

    @PostMapping("/restore")
    public ResponseEntity<Void> restoreDatabase(@RequestParam String filename) {
        databaseService.restoreBackup(filename);
        return ResponseEntity.noContent().build();
    }
}
