### This is not a Game: [The Depression Game](https://depressionga.me)

## What is this?
The purpose of this software is to destroy itself! To be more specific, every time the core executable of the software is executed, one of it's byte get's corrupted. Repeat it enough times and eventually a core portion is damaged that doesn't allow to execute anymore, causing it to crash in many different ways and with various effects. 

This is a project I built mainly for fun, but I decided to use to raise awareness for a good cause. It is licensed under the MIT License, so everything is free to use, reproduce or adapt in any way.

It consists of two main elements:
- The core executable binary software which self destructs
- A web interface that allows to interact with it and displays the depressive quotes it replies to you when it executes successfully

## Core Software

The source code and compiled executables are located under `/src/main/webapp/core/`

You can execute it with the following format:

```shell
areyoudepressed -p [outputfile] [-d[d][d]]
```

The `-p outputfile` is **required** and defines where the new executable after one byte is corrupted will be stored

The `-d[d][d]` is **optional** and can be used for debugging. There are three levels of debugging to chose from, defined by the number of `-d`s

An example execution is the following:

```shell
areyoudepressed -p ./_areyoudepressed.o -dd
```` 

The `quotes.h` file contains a static list of quotes about depression. The executable chooses one of these quotes randomly to reply to you.

## Web Interface

The Web interface is developed using Grails 3, and is visualizing the replies of the executable as well as the bytes that got corrupted.
After the executable cannot finish its execution successfully it allows you to restart the process all over again.

In addition, all iterrations are stored in a database in case future retrieval is required.

Every time a new session is initiated a new folder with the session ID is created under `/src/main/webapp/core/sessions`. For example:

```
/src/main/webapp/core/sessions/1AA5706EE591D3E1FE7EAC9344FC495C/
```

in that folder will be copied the intact areyoudepressed executable and it's corrupted copy _areyoudepressed file. After every execution, a new _areyoudepressed file is created.

## The Good Cause

This project is hoping to raise awareness about depression. If you enjoyed using it, learning about it or you were just moved, consider donating to one of the listed established foundations that help fight depression:

- [European Alliance Against Depression (EAAD)](http://www.eaad.net/mainmenu/about/support-us/)
- [Anxiety and Depression Association of America (ADAA)](https://adaa.org/donate)

**Do you want to see another organization listed as well? Please email me at bigappledev@gmail.com or create a GitHub issue**