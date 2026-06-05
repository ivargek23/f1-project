package hr.algebra.backendapi.service;

import java.util.List;

public interface DatabaseService {
    String createBackup();
    void restoreBackup(String filename);

    List<String> getListOfBackups();
}
