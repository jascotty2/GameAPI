# GameAPI
Open-source library for mini-game plugins. This is a full plugin that shall be placed onto your server. It does nothing on its own, but allows other plugins to inter-communicate and it makes it easier for you to create event/minigame plugins.

## Goal
* Provide developers a simpler way of creating mini-game plugins based on arenas.
* Allow plugins to work flawlesly over any plugin supporting this library. For example, an AutoPlay plugin is capable of running arenas infinitely without requiring players' to join manually, allowing servers on Bungee to be dedicated to a mini-game easily.

## Current state
* Basic and extended implementation done.
* Partially documented. Methods in interfaces without "public" declarator are subject of change anytime, unannounced.

## How to Use
0. Install GameAPI on your server.
1. Make your mini-game plugin extend 'ArenaPlugin' (not so much of work) and their arenas extend 'Arena' (a little bit of more work).
2. Register each of you arena manually in 'ArenaRegistry'.
3. Done! Now you have access to your arenas from anywhere, and you can also listen to events in the event package.

© Copyright 2017 kangarko | All Rights Reserved.
