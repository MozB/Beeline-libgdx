# Beeline-libgdx

_Beeline is currently going through a process of being prepared for public consumption, after living the last two years in a private repository.  Until the first release it should be treated as experimental at best._

Beeline is a library utilising Scene2D framework in libgdx that is intended to take some of the pain out of game development.  It's easy to use Beeline to create games from the simple to the complex.  Beeline is ideal for bringing a project to life quickly without the worry of complex libgdx configuration.

I have personally used Beeline with libgdx to make the following games.

* [Contract Chef](https://play.google.com/store/apps/details?id=com.moz.chef)
* [Pixel Pirates](https://play.google.com/store/apps/details?id=com.moz.pixelpirates)
* [Big Politics Inc](https://play.google.com/store/apps/details?id=com.moz.politics)

## Key Features

* Automated generation of sprite sheet from source images
* Automated generation of libgdx font data from a source font
* Easy to use [nine patches](https://github.com/libgdx/libgdx/wiki/Ninepatches) for speedy UI development
* Save & load game states
* A default libgdx configuration for high performance

## Install
[Create a new libgdx project](https://github.com/libgdx/libgdx), then add jitpack.io to your repositories.
```
repositories {
  maven { url 'https://jitpack.io' }
}
```
And import your desired version of Beeline.
```
implementation 'com.github.MozB:Beeline-libgdx:master-SNAPSHOT'
```
## Create your first Beeline game

_Coming soon_
