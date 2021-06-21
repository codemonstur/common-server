[![Build Status](https://travis-ci.org/codemonstur/common-server.svg?branch=master)](https://travis-ci.org/codemonstur/common-server)
[![GitHub Release](https://img.shields.io/github/release/codemonstur/common-server.svg)](https://github.com/codemonstur/common-server/releases) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.codemonstur/common-server/badge.svg)](http://mvnrepository.com/artifact/com.github.codemonstur/common-server)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)

# Common-Server

A blocking server in Java.
Uses plain old IO.

The server listens on a port and assigns a separate thread to each incoming connection.
The server can de both start()ed and stop()ped.

This obviously doesn't scale and should not be used in production.
My hope is that this code will be easy to adapt to use Loom's fibers when it comes.
At that point the simplicity of the code will make it possible to build all kinds of simple request-response services.
