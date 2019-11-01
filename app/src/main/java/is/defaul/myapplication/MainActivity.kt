package `is`.defaul.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SomeInterface {

    @Inject
    lateinit var someInterface: SomeInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toothpick.openRootScope()
                .installModules(object : Module() {
                    init {
                        bind(SomeInterface::class.java) to this@MainActivity
                    }
                })
                .inject(this)
    }

    override fun doSomething() {

    }
}
