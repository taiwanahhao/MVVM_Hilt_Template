package other.mvvm

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import other.mvvm.activity.mvvmHiltRecipe

val mvvmHiltTemplate
    get() = template {
        revision = 1
        name = "MVVM_Hilt Template"
        description = "Create new MVVM classes based on Hilt"
        minApi = 16
        minBuildApi = 16
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)
        val packageNameParam = defaultPackageNameParameter

        val viewClass = stringParameter {
            name = "View Name"
            default = ""
            constraints = listOf(Constraint.CLASS, Constraint.UNIQUE, Constraint.NONEMPTY)
        }

        val shouldGenActivity = booleanParameter {
            name = "Generate activity class"
            default = true
            help = "If true, an activity class will be generated."
        }

        val shouldGenFragment = booleanParameter {
            name = "Generate fragment class"
            default = true
            help = "If true, a fragment class will be generated."
        }

        widgets(
                TextFieldWidget(viewClass),
                CheckBoxWidget(shouldGenActivity),
                CheckBoxWidget(shouldGenFragment),
                PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            mvvmHiltRecipe(
                    data as ModuleTemplateData,
                    viewClass.value,
                    shouldGenActivity.value,
                    shouldGenFragment.value,
                    packageNameParam.value)
        }
    }

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.mycompany.myapp"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }