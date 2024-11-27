# HTTP-Server
HTTP-Server is an application which can start an HTTP server on Windows and Mac.

## Windows
The Windows build uses .NET Core 6.0 (meaning that Windows 7 and below can't run it) and was compiled using Visual Studio 2022 and uses C#. It uses the HTTPListener to start an HTTP server and listen on a port you specify.

## Mac
The Mac build is plain C and ***requires*** **libmicrohttpd** installed for it to work, otherwise it won't. You can install libmicrohttpd using Homebrew by running
`brew install libmicrohttpd`. To install Homebrew, run
`/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"`
It is a Unix executable so it should also work on Linux, but has not been tested there.

# How to open
Most if not all releases of this app _**will be unsigned**_, so here's how to open them on both Windows and Mac.
### On Windows
When you try to open the app, you will come across a SmartScreen warning. Press "More..." and then click "Run Anyway".
### On Mac
When you try to open the app, you will come across a "Developer cannot be verified" warning. Open it normally once, then after the failure, right-click, then click Open, then click Open in the same dialog. 
