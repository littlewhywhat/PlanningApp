PlanningApp
===========
Handy app for planning your day's events for Android

####Build
Gradle Wrapper

locate your Android sdk tools by creating local.properties file with line 
```
sdk.dir=path\:\\to\\your\\sdk\\folder
```
Android SDK requirements:

- Android SDK Tools
- Android SDK Platform-Tools
- Android SDK Build-Tools
- SDK Platform 22
- Android Support Repository - for dependencies
- Google USB Driver - for device usb debugging

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
