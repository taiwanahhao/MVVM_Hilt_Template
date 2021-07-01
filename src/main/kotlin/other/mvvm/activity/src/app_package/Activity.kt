package other.mvvm.activity.src.app_package

fun Activity(
        applicationPackage: String?,
        viewClass: String,
        packageName: String
) = """
package $packageName

import android.os.Bundle
import androidx.activity.viewModels
import ${applicationPackage}.R
import ${applicationPackage}.databinding.Activity${viewClass}Binding
import ${applicationPackage}.ui.base.BaseActivity
import ${applicationPackage}.ui.base.BaseViewModel
import ${applicationPackage}.ui.base.ViewEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ${viewClass}Activity : BaseActivity<Activity${viewClass}Binding>() {
    private val viewModel: ${viewClass}ActivityViewModel by viewModels()

    override fun initParam(data: Bundle) {

    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun initViewBinding(): Activity${viewClass}Binding {
        return Activity${viewClass}Binding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.fcv_${viewClass}, ${viewClass}Fragment.newInstance()).safeCommit()
        }
    }

    override fun handleViewEvent(event: ViewEvent) {
        super.handleViewEvent(event)
    }
}
"""