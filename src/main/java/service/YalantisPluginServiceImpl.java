package service;

import actions.SyncAction;
import com.google.common.base.Charsets;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class YalantisPluginServiceImpl implements YalantisPluginService {

    public static volatile String PATH = null;
    public static volatile boolean isActionPerformed = false;

    private SyncAction action = new SyncAction();

    YalantisPluginServiceImpl() {
        try {
            Path dirPath = Files.exists(Paths.get("cache")) ? Paths.get("cache") : Files.createDirectory(Paths.get("cache"));
            Path file;
            if (!Files.exists(Paths.get(dirPath + "/config.txt"))) {
                Files.createFile(Paths.get(dirPath + "/config.txt"));
            } else {
                file = Paths.get(dirPath + "/config.txt");
                PATH = Files.readAllLines(file, Charsets.UTF_8).size() == 0 ? null : Files.readAllLines(file, Charsets.UTF_8).get(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPath(String newPath, AnActionEvent event) {
        PATH = newPath; //todo validation
        savePathToCache(newPath);
        action.performActionFromService(event);
    }

    private void savePathToCache(String newPath) {
        try {
            Path dirPath = Paths.get("cache");
            Path filePath;
            filePath = Paths.get(dirPath + "/config.txt");
            Files.write(filePath, newPath.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
