# Beeline-libgdx

_Beeline-libgdx is currently going through a process of being prepared for public consumption, after living the last two years in a private repository.  Until the first release it should be treated as experimental at best._

Beeline-libgdx is a library utilising Scene2D framework in libgdx that is intended to take some of the pain out of game development.  It's easy to use Beeline to create games from the simple to the complex.  Beeline is ideal for bringing a project to life quickly without the worry of complex libgdx configuration.

I have personally used Beeline-libgdx to make the following games.

* [Tennis Superstars](https://play.google.com/store/apps/details?id=com.moz.tennis)
* [Contract Chef](https://play.google.com/store/apps/details?id=com.moz.chef)
* [Pixel Pirates](https://play.google.com/store/apps/details?id=com.moz.pixelpirates)
* [Big Politics Inc: UK](https://play.google.com/store/apps/details?id=com.moz.politics)
* [Big Poltiics Inc: USA](https://play.google.com/store/apps/details?id=com.moz.politics.us)

## Key Features

* Automatically creates spritesheets when source sprites change, with a simple API to load them
* Automatically builds a provided font into the same spritesheet whenever it changes
* Automatically manage the consistency between models and actors in your project when either are removed or added
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
        compile "com.github.MozB:Beeline-libgdx:0.2.1"
    }
}
```
