# Navigation Crash Reproduction

This project exists to showcase how to reproduce a crash in the `navigation-compose` artifact. To reproduce the crash,
follow these steps:

1. Open the app in debug mode and click the button to open the nested screen.
2. Pause the app using the debugger (click the “Pause Program” button) in the debugger UI.
3. Click the “Navigate Up” button once, then perform a Back gesture.
4. Resume the program from the debugger UI.
5. The app now crashes with the following exception:

    ```
    FATAL EXCEPTION: main
    Process: dev.sindrenm.repro.backnavigation, PID: 21646
    java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 1
  	  at jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
  	  at jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
  	  at jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
  	  at java.util.Objects.checkIndex(Objects.java:359)
  	  at java.util.ArrayList.get(ArrayList.java:434)
  	  at androidx.navigation.compose.NavHostKt$NavHost$25$1.invokeSuspend(NavHost.kt:518)
  	  at androidx.navigation.compose.NavHostKt$NavHost$25$1.invoke(Unknown Source:8)
  	  at androidx.navigation.compose.NavHostKt$NavHost$25$1.invoke(Unknown Source:4)
  	  at androidx.activity.compose.OnBackInstance$job$1.invokeSuspend(PredictiveBackHandler.kt:121)
  	  at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
  	  at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
  	  at androidx.compose.ui.platform.AndroidUiDispatcher.performTrampolineDispatch(AndroidUiDispatcher.android.kt:81)
  	  at androidx.compose.ui.platform.AndroidUiDispatcher.access$performTrampolineDispatch(AndroidUiDispatcher.android.kt:41)
  	  at androidx.compose.ui.platform.AndroidUiDispatcher$dispatchCallback$1.run(AndroidUiDispatcher.android.kt:57)
  	  at android.os.Handler.handleCallback(Handler.java:959)
  	  at android.os.Handler.dispatchMessage(Handler.java:100)
  	  at android.os.Looper.loopOnce(Looper.java:232)
  	  at android.os.Looper.loop(Looper.java:317)
  	  at android.app.ActivityThread.main(ActivityThread.java:8705)
  	  at java.lang.reflect.Method.invoke(Native Method)
  	  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:580)
  	  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:886)
    ```

This crash is reproducible with the following artifact versions:

- androidx.activity:activity-compose:1.9.3
- androidx.activity:activity-ktx:1.9.3
- androidx.navigation:navigation-compose:2.8.3
