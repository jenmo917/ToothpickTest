# ToothpickTest
Test project to highlight a NoFactoryFoundException

MainActivity:
```
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

```
SomeInterface:
```
package my.toothpick.application

interface SomeInterface {
    fun doSomethingInActivity()
}
```

MainFragment:
```
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

```

Exception:
```
2019-11-01 15:20:37.121 5814-5814/is.defaul.myapplication E/AndroidRuntime: FATAL EXCEPTION: main
    Process: is.defaul.myapplication, PID: 5814
    toothpick.locators.NoFactoryFoundException: No factory could be found for class my.toothpick.application.SomeInterface. Check that the class has either a @Inject annotated constructor or contains @Inject annotated members.
        at toothpick.locators.FactoryLocator.getFactory(FactoryLocator.java:37)
        at toothpick.InternalProvider.get(InternalProvider.java:125)
        at toothpick.InternalScopedProvider.get(InternalScopedProvider.java:80)
        at toothpick.ScopeImpl.getInstance(ScopeImpl.java:93)
        at toothpick.ScopeImpl.getInstance(ScopeImpl.java:84)
        at my.toothpick.application.MainFragment__MemberInjector.inject(MainFragment__MemberInjector.java:10)
        at my.toothpick.application.MainFragment__MemberInjector.inject(MainFragment__MemberInjector.java:7)
        at toothpick.InjectorImpl.inject(InjectorImpl.java:37)
        at toothpick.Toothpick.inject(Toothpick.java:243)
        at toothpick.ScopeImpl.inject(ScopeImpl.java:140)
        at my.toothpick.application.MainFragment.onStart(MainFragment.kt:16)
        at androidx.fragment.app.Fragment.performStart(Fragment.java:2632)
        at androidx.fragment.app.FragmentManagerImpl.moveToState(FragmentManagerImpl.java:915)
        at androidx.fragment.app.FragmentManagerImpl.moveFragmentToExpectedState(FragmentManagerImpl.java:1238)
        at androidx.fragment.app.FragmentManagerImpl.moveToState(FragmentManagerImpl.java:1303)
        at androidx.fragment.app.FragmentManagerImpl.dispatchStateChange(FragmentManagerImpl.java:2659)
        at androidx.fragment.app.FragmentManagerImpl.dispatchStart(FragmentManagerImpl.java:2619)
        at androidx.fragment.app.FragmentController.dispatchStart(FragmentController.java:257)
        at androidx.fragment.app.FragmentActivity.onStart(FragmentActivity.java:551)
        at androidx.appcompat.app.AppCompatActivity.onStart(AppCompatActivity.java:201)
        at android.app.Instrumentation.callActivityOnStart(Instrumentation.java:1391)
        at android.app.Activity.performStart(Activity.java:7157)
        at android.app.ActivityThread.handleStartActivity(ActivityThread.java:2937)
        at android.app.servertransaction.TransactionExecutor.performLifecycleSequence(TransactionExecutor.java:180)
        at android.app.servertransaction.TransactionExecutor.cycleToPath(TransactionExecutor.java:165)
        at android.app.servertransaction.TransactionExecutor.executeLifecycleState(TransactionExecutor.java:142)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:70)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1808)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:193)
        at android.app.ActivityThread.main(ActivityThread.java:6669)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858)
     Caused by: java.lang.ClassNotFoundException: my.toothpick.application.SomeInterface__Factory
        at java.lang.Class.classForName(Native Method)
        at java.lang.Class.forName(Class.java:453)
        at java.lang.Class.forName(Class.java:378)
        at toothpick.locators.FactoryLocator.getFactory(FactoryLocator.java:34)
        at toothpick.InternalProvider.get(InternalProvider.java:125) 
        at toothpick.InternalScopedProvider.get(InternalScopedProvider.java:80) 
        at toothpick.ScopeImpl.getInstance(ScopeImpl.java:93) 
        at toothpick.ScopeImpl.getInstance(ScopeImpl.java:84) 
        at my.toothpick.application.MainFragment__MemberInjector.inject(MainFragment__MemberInjector.java:10) 
        at my.toothpick.application.MainFragment__MemberInjector.inject(MainFragment__MemberInjector.java:7) 
        at toothpick.InjectorImpl.inject(InjectorImpl.java:37) 
        at toothpick.Toothpick.inject(Toothpick.java:243) 
        at toothpick.ScopeImpl.inject(ScopeImpl.java:140) 
        at my.toothpick.application.MainFragment.onStart(MainFragment.kt:16) 
        at androidx.fragment.app.Fragment.performStart(Fragment.java:2632) 
        at androidx.fragment.app.FragmentManagerImpl.moveToState(FragmentManagerImpl.java:915) 
        at androidx.fragment.app.FragmentManagerImpl.moveFragmentToExpectedState(FragmentManagerImpl.java:1238) 
        at androidx.fragment.app.FragmentManagerImpl.moveToState(FragmentManagerImpl.java:1303) 
        at androidx.fragment.app.FragmentManagerImpl.dispatchStateChange(FragmentManagerImpl.java:2659) 
        at androidx.fragment.app.FragmentManagerImpl.dispatchStart(FragmentManagerImpl.java:2619) 
        at androidx.fragment.app.FragmentController.dispatchStart(FragmentController.java:257) 
        at androidx.fragment.app.FragmentActivity.onStart(FragmentActivity.java:551) 
        at androidx.appcompat.app.AppCompatActivity.onStart(AppCompatActivity.java:201) 
        at android.app.Instrumentation.callActivityOnStart(Instrumentation.java:1391) 
        at android.app.Activity.performStart(Activity.java:7157) 
        at android.app.ActivityThread.handleStartActivity(ActivityThread.java:2937) 
        at android.app.servertransaction.TransactionExecutor.performLifecycleSequence(TransactionExecutor.java:180) 
        at android.app.servertransaction.TransactionExecutor.cycleToPath(TransactionExecutor.java:165) 
        at android.app.servertransaction.TransactionExecutor.executeLifecycleState(TransactionExecutor.java:142) 
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:70) 
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1808) 
        at android.os.Handler.dispatchMessage(Handler.java:106) 
        at android.os.Looper.loop(Looper.java:193) 
        at android.app.ActivityThread.main(ActivityThread.java:6669) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858) 
     Caused by: java.lang.ClassNotFoundException: Didn't find class "my.toothpick.application.SomeInterface__Factory" on path: DexPathList[[zip file "/data/app/is.defaul.myapplication-BHOkhVm7A5jL7kWZIg0Vdg==/base.apk"],nativeLibraryDirectories=[/data/app/is.defaul.myapplication-BHOkhVm7A5jL7kWZIg0Vdg==/lib/x86, /system/lib]]
        at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:134)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:379)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
        ```
