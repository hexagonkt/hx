
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.wm.ToolWindowManager

val popupFactory: JBPopupFactory = JBPopupFactory.getInstance()
val projectManager: ProjectManager = ProjectManager.getInstance()
val windowProject: Project = projectManager.openProjects[0]
val fileEditorManager: FileEditorManager = FileEditorManager.getInstance(windowProject)
val toolWindowManager = ToolWindowManager.getInstance(windowProject)

popupFactory
    .createPopupChooserBuilder(projectManager.openProjects.map { it.name })
    .createPopup()
    .showInFocusCenter()

popupFactory
    .createPopupChooserBuilder(fileEditorManager.allEditors.map { it.file })
    .createPopup()
    .showInFocusCenter()

popupFactory
    .createPopupChooserBuilder(toolWindowManager.toolWindowIds.toList())
    .createPopup()
    .showInFocusCenter()
