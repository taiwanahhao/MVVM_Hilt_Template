package other.mvvm.activity.src.app_package

fun ActivityViewModel(
        applicationPackage: String?,
        viewClass: String,
        packageName: String
) = """
package $packageName

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import ${applicationPackage}.ui.base.BaseViewModel

class ${viewClass}ActivityViewModel @ViewModelInject constructor(
@Assisted private val state: SavedStateHandle
) : BaseViewModel(state) {

}
"""