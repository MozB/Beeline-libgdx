# Beeline-libgdx

Beeline-libgdx is a library to aid flattening the learning curve of libgdx, utilising Scene2D framework in libgdx it is intended to speed up early development of games and code that can be often boiler plate in game development.  It's easy to use Beeline to create games from the simple to the complex.  Beeline is ideal for bringing a project to life quickly and will also protect you from common pitfalls I faced myself and learnt to protect myself from over time, such as performance and organisation of my code, and time consuming manual processes.

I have personally used Beeline-libgdx to make the following games.

* [Tennis Superstars](https://play.google.com/store/apps/details?id=com.moz.tennis)
* [Big Politics Inc: UK](https://play.google.com/store/apps/details?id=com.moz.politics)
* [Big Politics Inc: USA](https://play.google.com/store/apps/details?id=com.moz.politics.us)
* [Contract Chef](https://play.google.com/store/apps/details?id=com.moz.chef)
* [Pixel Pirates](https://play.google.com/store/apps/details?id=com.moz.pixelpirates)

## Key Features

* Automatically creates spritesheets when source sprites change, with a simple API to load them
* Automatically builds a provided font into the same spritesheet whenever it changes
* Automatically manage consistency between actor & model by linking the two in a contract
* Simple API for loading sounds and music into your game
* Simple API for creation of Actors, Buttons, NinePatches and more, each with easily customisable styles in Java, no JSON
* Simple API for loading and saving game states
* Much more!

## Tutorials
[Beeline-libgdx: Tutorial 1, Hello world!](http://trailblaze.games/2020/03/22/beeline-libgdx-tutorial-1-hello-world/)

## Examples
See https://github.com/MozB/Beeline-libgdx-examples for a full set of working example projects with Beeline-libgdx.

## Installation

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
...
project(":core") {
    apply plugin: "java"

    dependencies {
        //compile "com.badlogicgames.gdx:gdx:$gdxVersion"
        compile "com.github.MozB:Beeline-libgdx:0.2.6"
    }
}
```
