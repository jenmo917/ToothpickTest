# ToothpickTest
Test project to highlight a NoFactoryFoundException

MainActivity:
```
package my.toothpick.application

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

```
SomeInterface:
```
package my.toothpick.application

interface SomeInterface {
    fun doSomething()
}
```

Exception:
```
2019-11-01 09:43:50.424 11720-11720/? E/AndroidRuntime: FATAL EXCEPTION: main
    Process: is.defaul.myapplication, PID: 11720
    java.lang.RuntimeException: Unable to start activity ComponentInfo{is.defaul.myapplication/my.toothpick.application.MainActivity}: toothpick.locators.NoFactoryFoundException: No factory could be found for class my.toothpick.application.SomeInterface. Check that the class has either a @Inject annotated constructor or contains @Inject annotated members.
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2913)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3048)
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:78)
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1808)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:193)
        at android.app.ActivityThread.main(ActivityThread.java:6669)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858)
     Caused by: toothpick.locators.NoFactoryFoundException: No factory could be found for class my.toothpick.application.SomeInterface. Check that the class has either a @Inject annotated constructor or contains @Inject annotated members.
        at toothpick.locators.FactoryLocator.getFactory(FactoryLocator.java:37)
        at toothpick.InternalProvider.get(InternalProvider.java:125)
        at toothpick.InternalScopedProvider.get(InternalScopedProvider.java:80)
        at toothpick.ScopeImpl.getInstance(ScopeImpl.java:93)
        at toothpick.ScopeImpl.getInstance(ScopeImpl.java:84)
        at my.toothpick.application.MainActivity__MemberInjector.inject(MainActivity__MemberInjector.java:10)
        at my.toothpick.application.MainActivity__MemberInjector.inject(MainActivity__MemberInjector.java:7)
        at toothpick.InjectorImpl.inject(InjectorImpl.java:37)
        at toothpick.Toothpick.inject(Toothpick.java:243)
        at toothpick.ScopeImpl.inject(ScopeImpl.java:140)
        at my.toothpick.application.MainActivity.onCreate(MainActivity.kt:24)
        at android.app.Activity.performCreate(Activity.java:7136)
        at android.app.Activity.performCreate(Activity.java:7127)
        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1271)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2893)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3048) 
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:78) 
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108) 
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68) 
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
        at my.toothpick.application.MainActivity__MemberInjector.inject(MainActivity__MemberInjector.java:10) 
        at my.toothpick.application.MainActivity__MemberInjector.inject(MainActivity__MemberInjector.java:7) 
        at toothpick.InjectorImpl.inject(InjectorImpl.java:37) 
        at toothpick.Toothpick.inject(Toothpick.java:243) 
        at toothpick.ScopeImpl.inject(ScopeImpl.java:140) 
        at my.toothpick.application.MainActivity.onCreate(MainActivity.kt:24) 
        at android.app.Activity.performCreate(Activity.java:7136) 
        at android.app.Activity.performCreate(Activity.java:7127) 
        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1271) 
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2893) 
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3048) 
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:78) 
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108) 
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68) 
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1808) 
        at android.os.Handler.dispatchMessage(Handler.java:106) 
        at android.os.Looper.loop(Looper.java:193) 
        at android.app.ActivityThread.main(ActivityThread.java:6669) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858) 
     Caused by: java.lang.ClassNotFoundException: Didn't find class "my.toothpick.application.SomeInterface__Factory" on path: DexPathList[[zip file "/data/app/is.defaul.myapplication-f3VGzw5Mx_vlhd_3_oJXmQ==/base.apk"],nativeLibraryDirectories=[/data/app/is.defaul.myapplication-f3VGzw5Mx_vlhd_3_oJXmQ==/lib/x86, /system/lib]]
        at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:134)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:379)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
        ```
