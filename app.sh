export PATH=/opt/homebrew/bin:$PATH
./gradlew assembleDevelopmentDebug && ./gradlew installDevelopmentDebug
adb shell am start -n com.mayburger.sneakers/.ui.main.MainActivity