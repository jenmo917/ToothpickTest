package my.toothpick.application

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import toothpick.Toothpick
import toothpick.config.Module

class MainActivity : AppCompatActivity(), SomeInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toothpick.openRootScope()
            .installModules(object : Module() {
                init {
                    bind(SomeInterface::class.java) to this@MainActivity
                }
            })

        supportFragmentManager.beginTransaction()
            .add(R.id.layout_fragment, MainFragment())
            .commit()
    }

    override fun doSomethingInActivity() {
        Toast.makeText(this, "Do something called", Toast.LENGTH_SHORT)
            .show()
    }
}
