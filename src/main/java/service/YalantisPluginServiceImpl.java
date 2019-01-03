package service;

import actions.SyncAction;
import com.google.common.base.Charsets;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class YalantisPluginServiceImpl implements YalantisPluginService {

    public String PATH = null;
    public boolean isActionPerformed = false;

    YalantisPluginServiceImpl() {
        try {
            Path file = Paths.get("cache", "config.txt");
            if (!Files.exists(file)) {
                Files.createDirectory(Paths.get("cache"));
                Files.createFile(file);
            }
            PATH = Files.readAllLines(file, Charsets.UTF_8).size() == 0 ? null : Files.readAllLines(file, Charsets.UTF_8).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPath(String newPath, AnActionEvent event) {
        PATH = newPath; //todo validation
        savePathToCache(newPath);
        new SyncAction().performActionFromService(event);
    }

    private void savePathToCache(String newPath) {
        try {
            Files.write(Paths.get("cache", "config.txt"), newPath.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
