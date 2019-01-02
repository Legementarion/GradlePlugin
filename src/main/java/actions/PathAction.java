package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import service.YalantisPluginService;
import service.YalantisPluginServiceImpl;
import utils.PluginIcons;

public class PathAction extends AnAction {

    PathAction() {
        super(null, null, PluginIcons.BASE_ACTION);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        YalantisPluginServiceImpl service = (YalantisPluginServiceImpl) YalantisPluginService.getInstance();
        String path = Messages.showInputDialog("Set git path to the template project", "Settings", null);
        service.setPath(path, event);
    }

}
