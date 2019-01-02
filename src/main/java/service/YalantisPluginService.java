package service;

import com.intellij.openapi.components.ServiceManager;

public interface YalantisPluginService {
    static YalantisPluginService getInstance() {
        return ServiceManager.getService(YalantisPluginService.class);
    }
}
