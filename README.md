# workmanager-foreground-worker
Simple Android app with foreground worker

Start worker from command line:
```
adb shell am broadcast -n com.github.otbinary.fgworker/.StartSleepingWorkerBroadcastReceiver
```