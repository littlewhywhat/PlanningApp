PlanningApp
===========
Handy app for planning your day's events for Android

####Build
Gradle Wrapper

locate your Android sdk tools by creating local.properties file with line 
```
sdk.dir=path\:\\to\\your\\sdk\\folder
```

####Debug
run adb
```
adb devices
```

run to install debug version on your device
```
gradlew installDebug
```
run to uninstall
```
gradlew uninstallDebug
```
####Test
run these commands  
```
gradlew assembleDebugAndroidTest
gradlew connectedAndroidTest
```
