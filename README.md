# KaGameAPI
Open-source library for mini-game plugins.

## Goal
* Provide developers a simpler way of creating mini-game plugins based on arenas.
* Allow auto-mode plugins to work flawlesly over any plugin supporting this library. An auto-mode plugin is capable of running arenas infinitely without requiring players' to join manually, allowing servers on Bungee to be dedicated to a mini-game easily.

## Current state
* Basic implementation done.
* Somewhat documented. Methods in interfaces without "public" declarator are subject of change anytime, unannounced.

## How to Use
1. Make your mini-game plugin extend 'ArenaPlugin' (not so much of work) and their arenas extend 'Arena' (a little bit of more work).
2. Register each of you arena manually in 'ArenaRegistry'.
3. Done! Now you have access to your arenas from anywhere, and you can also listen to events in the event package.
