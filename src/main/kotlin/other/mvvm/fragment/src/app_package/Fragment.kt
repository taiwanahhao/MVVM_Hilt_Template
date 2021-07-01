package other.mvvm.fragment.src.app_package

fun Fragment(
        applicationPackage: String?,
        viewClass: String,
        packageName: String
) = """
package $packageName

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import ${applicationPackage}.databinding.Fragment${viewClass}Binding
import ${applicationPackage}.R
import ${applicationPackage}.ui.base.BaseFragment
import ${applicationPackage}.ui.base.BaseViewModel
import ${applicationPackage}.ui.base.ViewEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ${viewClass}Fragment : BaseFragment<Fragment${viewClass}Binding>() {
    private val viewModel: ${viewClass}ViewModel by viewModels()

    override fun initParam(data: Bundle) {

    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun bindFragmentListener(context: Context) {

    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Fragment${viewClass}Binding {
        return Fragment${viewClass}Binding.inflate(inflater, container, false)
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun addOnBackPressedCallback(): OnBackPressedCallback? {
        return super.addOnBackPressedCallback()
    }

    override fun handleViewEvent(event: ViewEvent) {
        super.handleViewEvent(event)
    }

    companion object {
        fun newInstance(): ${viewClass}Fragment {
            val args = Bundle().apply { }
            return ${viewClass}Fragment().apply { arguments = args }
        }
    }
}
"""