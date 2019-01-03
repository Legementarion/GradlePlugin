package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import service.YalantisPluginService;
import service.YalantisPluginServiceImpl;
import utils.PluginIcons;

public class SyncAction extends AnAction {

    private YalantisPluginServiceImpl service = ((YalantisPluginServiceImpl) YalantisPluginService.getInstance());

    public SyncAction() {
        super("Hello", "Easy description", PluginIcons.BASE_ACTION);
    }

    public void actionPerformed(@NotNull AnActionEvent event) {
        service.isActionPerformed = true;
        if (service.PATH == null || service.PATH.isEmpty()) {
            PathAction action = new PathAction();
            action.actionPerformed(event);
        } else {
            sync(event);
        }

    }

    private void sync(AnActionEvent event) {
        Project project = event.getProject();
        Messages.showMessageDialog(project, "Loading...", "Sync", null);
    }

    public void performActionFromService(@NotNull AnActionEvent event) {
        if (!service.isActionPerformed) {
            service.isActionPerformed = true;
            actionPerformed(event);
        }
    }

}
