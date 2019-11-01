package my.toothpick.application

import androidx.fragment.app.Fragment
import toothpick.Toothpick
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var someInterface: SomeInterface

    override fun onStart() {
        super.onStart()

        Toothpick.openRootScope()
            .inject(this)

        someInterface.doSomethingInActivity()
    }
}
