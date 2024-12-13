# HTTP-Server
[![latest release](https://a-star100.github.io/images/http-server-release.svg)](https://github.com/A-Star100/HTTP-Server)

HTTP-Server is an application which can start an HTTP server on Windows, Mac, Android, and with a bit of extra configuration, Linux.

### Windows
The Windows build uses .NET Core 6.0 (meaning that Windows 7 and below can't run it) and was compiled using Visual Studio 2022 and uses C#. It uses the HTTPListener to start an HTTP server and listen on a port you specify.

### Mac
The Mac build is plain C and ***requires*** **libmicrohttpd** installed for it to work, otherwise it won't. You can install libmicrohttpd using Homebrew by running
`brew install libmicrohttpd`. 
To install Homebrew, run
`/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"`
It is a Unix executable so it should also work on Linux, but has not been tested there.

### Android
The Android build was created and compiled with [MIT App Inventor](https://appinventor.mit.edu) and is experimental (so it may not work as seamlessly as the Windows and Mac versions), but still does something nonetheless. It (like 99% of all Android apps) uses Java and since this app is bigger than the Mac and Windows apps, it primarily uses Java, butting out other languages. 
****-*The Android build can only serve raw text data.*-****
## Compile from source
If you want to compile from source and create modified versions, follow the instructions below:
### Windows
Install [Visual Studio](https://visualstudio.microsoft.com/thank-you-downloading-visual-studio/?sku=Community&channel=Release&version=VS2022&source=VSLandingPage&cid=2030&passive=false) or any IDE that supports creating C# programs that use .NET Core.
Open the *Program.cs* file, then in your editor modify the C# however you please. Once done, compile the app (in Visual Studio, click on the Build dropdown, then choose Release, then click Build Solution). Once done, you should see a folder with your compiled result.
### Mac
Use TextEdit (or any IDE) and open *file_server.c* and edit the C however you please!
Then use clang to compile into an ARM64 binary by running
`clang -arch arm64 -o MyApplication-ARM file_server.c -I/opt/homebrew/include -L/opt/homebrew/lib -lmicrohttpd`
then compile into an Intel (x86_64) binary by running
`clang -arch x86_64 -o MyApplication-x86_64 file_server.c -I/opt/homebrew/include -L/opt/homebrew/lib -lmicrohttpd`
then use lipo to compile both into a universal (x86_64 and ARM64 compatible binary) by running
`lipo -create -output MyApplication-Universal MyApplication-x86_64 MyApplication-ARM`
and you have created a universal binary of the program by yourself! Congratulations!

(The command above utilizes a ***Homebrew*** installation of *libmicrohttpd*, so users need libmicrohttpd installed from Homebrew if you use the command above. You can modify it to your liking).
You can also use CMake by running
`cmake -E chdir . /usr/bin/gcc main.c -o main` (this example assumes *main.c* is your C app) 
### Android
You can use [Android Studio](https://developer.android.com/studio) to compile the decompiled source code or compile with *just* Gradle [by using this method](https://stackoverflow.com/questions/39538021/compiling-android-source-code). 

## How to open
Most if not all releases of this app _**will be unsigned**_, so here's how to open them Windows, Mac, and Android
### On Windows
When you try to open the app, you will come across a SmartScreen warning. Press "More..." and then click "Run Anyway".
### On Mac
#### MacOS Sonoma and below:
When you try to open the app, you will come across a "Developer cannot be verified" warning. Open it normally once, then after the failure, right-click, then click Open, then click Open in the new warning dialog. 
#### MacOS Sequoia and above:
Open the app normally once. Then click Done, then go to Settings, Privacy, and you will see a box that says [App name] was not opened because it is an unknown app. Click Open Anyway under that box, enter your password or use Touch ID, and the app should open.
### On Android
When you install the app itself, you will be warned that "[App name] cannot install apps from unknown sources". Go to settings, then Apps & Notifications, then Special App Access, then Install unknown apps and select the browser you want to use to download APK files from. 
(this process varies depending on what phone you use).
