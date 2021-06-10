
[![Build Status](https://travis-ci.org/codemonstur/common-server.svg?branch=master)](https://travis-ci.org/codemonstur/common-server)
[![GitHub Release](https://img.shields.io/github/release/codemonstur/common-server.svg)](https://github.com/codemonstur/common-server/releases) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.codemonstur/common-server/badge.svg)](http://mvnrepository.com/artifact/com.github.codemonstur/common-server)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)

# Common-Server

A simple blocking server in Java.
Uses plain old IO.

Right now it spawns a new Thread for each connection.
This obviously doesn't scale and shouldn't be used in production.

My hope is that this code will be easy to adapt to use Loom's fibers when it comes.
At that point the simplicity of the code will make it possible to build all kinds of simple request-response services.