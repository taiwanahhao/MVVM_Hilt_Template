package other.mvvm.activity

import com.android.tools.idea.npw.assetstudio.toLowerCamelCase
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.ThemeData
import com.android.tools.idea.wizard.template.camelCaseToUnderlines
import com.android.tools.idea.wizard.template.impl.activities.common.androidManifestXml
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.intellij.openapi.roots.ProjectRootManager
import com.github.taiwanahhao.mvvmhilttemplate.listeners.MyProjectManagerListener
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.idea.KotlinLanguage
import other.mvvm.AndroidManifest
import other.mvvm.activity.res.layout.ActivityXml
import other.mvvm.activity.src.app_package.Activity
import other.mvvm.activity.src.app_package.ActivityViewModel
import other.mvvm.fragment.res.layout.FragmentXml
import other.mvvm.fragment.src.app_package.Fragment
import other.mvvm.fragment.src.app_package.ViewModel

fun RecipeExecutor.mvvmHiltRecipe(
        moduleData: ModuleTemplateData,
        viewClass: String,
        shouldGenActivity: Boolean,
        shouldGenFragment: Boolean,
        packageName: String
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    val project = MyProjectManagerListener.projectInstance ?: return

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.first { it.path.contains("src") }
    val virtRes = virtualFiles.first { it.path.contains("res") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

    if (shouldGenActivity) {
        Activity(projectData.applicationPackage, viewClass, packageName)
                .save(directorySrc, packageName, "${viewClass}Activity.${ktOrJavaExt}")
        ActivityViewModel(projectData.applicationPackage, viewClass, packageName)
                .save(directorySrc, packageName, "${viewClass}ActivityViewModel.${ktOrJavaExt}")
        ActivityXml(viewClass)
                .save(directoryRes, "layout", "activity_${viewClass.toLowerCase()}.xml")
    }

    if (shouldGenFragment) {
        Fragment(projectData.applicationPackage, viewClass, packageName)
                .save(directorySrc, packageName, "${viewClass}Fragment.${ktOrJavaExt}")
        ViewModel(projectData.applicationPackage, viewClass, packageName)
                .save(directorySrc, packageName, "${viewClass}ViewModel.${ktOrJavaExt}")
        FragmentXml(viewClass)
                .save(directoryRes, "layout", "fragment_${viewClass.toLowerCase()}.xml")
    }
    androidManifestXml(
            isNewModule = false,
            hasNoActionBar = true,
            packageName = packageName,
            activityClass = "${viewClass}Activity",
            isLauncher = false,
            isLibraryProject = false,
            mainTheme = ThemeData("", false),
            hasNoActionBarTheme = ThemeData("", false),
            generateActivityTitle = true,
            requireTheme = false,
            hasApplicationTheme = false
    )
}

fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    try {
        val destDir = subDirPath.split(".").toDir(srcDir)
        val psiFile = PsiFileFactory
                .getInstance(srcDir.project)
                .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        destDir.add(psiFile)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}