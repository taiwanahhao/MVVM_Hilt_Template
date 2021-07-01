package other.mvvm.fragment.src.app_package

fun ViewModel(
        applicationPackage: String?,
        viewClass: String,
        packageName: String
) = """
package ${packageName}

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import ${applicationPackage}.ui.base.BaseViewModel

class ${viewClass}ViewModel @ViewModelInject constructor(
@Assisted private val state: SavedStateHandle
) : BaseViewModel(state) {

}
"""