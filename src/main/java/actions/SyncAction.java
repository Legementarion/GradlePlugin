package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import service.YalantisPluginServiceImpl;
import utils.PluginIcons;

public class SyncAction extends AnAction {

    public SyncAction() {
        super("Hello", "Easy description", PluginIcons.BASE_ACTION);
    }

    public void actionPerformed(@NotNull AnActionEvent event) {
        YalantisPluginServiceImpl.isActionPerformed = true;
        if (YalantisPluginServiceImpl.PATH == null || YalantisPluginServiceImpl.PATH.isEmpty()) {
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
        if (!YalantisPluginServiceImpl.isActionPerformed) {
            YalantisPluginServiceImpl.isActionPerformed = true;
            actionPerformed(event);
        }
    }


}
