# UIAutomatorDemo

1. install genymotion emulator
2. choose and open Google Nexus 10 - 4.4.4, API 19, 2560X1600 emulator, unlock the screen
3. check android target (run next command in a command line - android list targets) - in my case it's 1, 2, 3, 4, 5, 6, 7
4. please drug in Music, People Widget (create Contact A widget), Calendar and Power control widgets on Home page 
5. here is a sample command to put a music file on your running emulator: adb push 'path to mp3 file' '/storage/sdcard0/'
6. install Android sdk
7. set next paths in environment variables: 

export ANDROID_HOME=path to .../Library/Android/sdk

export PATH=$PATH:$ANDROID_HOME/bin

export PATH=$PATH:path to .../Library/Android/sdk/platform-tools>

export PATH=$PATH:path to .../Library/Android/sdk/tools

8. Please run the following command in a command line:
android create uitest-project -n AutomatorUIDemo -t 5 -p 'workspace path'
number 5 in the command is a target from step 3
=> build.xml, local.properties and project.properties files are created
9. open 'workspace path' folder and drag and drop build.xml, local.properties and project.properties files into AutomatorUIDemo folder
10. go to eclipse, refresh the whole project
11. run build.xml as ant build, check -jar checkbox, press Run button
12. To push jar into device please run the following command in a command line:
adb push 'proejct path'/bin/AutomatorUIDemo.jar /data/local/tmp/
13. To run tests please run the following command in a command line:
adb shell uiautomator runtest AutomatorUIDemo.jar -c com.company.WidgetSuite

Click on the emulator and observe the tests running! Isn't that cool?! 
