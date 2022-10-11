# README #

**Name:**	Nicholas Wang

**Period:**	1

**Game Title:** Shoot 'Em Up: Bullet Chaos

## Game Proposal ##

I wish to make a shoot 'em up style game where the player controls a small spaceship and fires bullets at enemy ships while constantly dodging 
projectiles enemies fire at you. The player will go through levels surviving waves of different enemies and trying to destroy as many as they can.
Enemies will appear for a set amount of time. Smaller ones pop in and out quickly, while bigger, deadlier "boss" ones stay for longer. Levels get
progressively harder, with the player having to react quicklier to different attacks and having less space to avoid getting hit.

Game Controls:

+ Ship follows mouse cursor
+ Pressing 'Z' will cause the ship to fire a bullet. You can hold 'Z' to continuously fire, but each shot has a short interval before another fires.
+ Pressing 'X' will cause the ship to fire a laser, causing all bullets inside the laser to disappear
+ Pressing the space bar pauses the game and allows the user to restart a level, resume, or exit to main menu

Game Elements:

+ There is no "gravity" existing in this game, bullets and ships follow their own paths
+ Two levels
	+ Levels are based on waves of different enemies
+ You have a certain number of lasers you can use to give yourself a temporary immunity. If you get hit, you automatically fire one laser and it counts as a laser fire. 
+ After your lasers are used up and you get hit, you lose and have to restart the level.
+ Different types of enemies and projectiles that they fire
	+ Basic enemies that are destroyed very quickly and fires small, slow moving projectiles, but can accumlate in large numbers before gradually leaving the screen
	+ Small, orange colored ships that can either home into your current position, or travel in a set direction
	+ Relavtively larger enemy ship that stays on screen for longer and take many more bullets to destroy, fires faster moving projectiles and lasers

How to Win:

+ Make it through the level without getting hit and for the third level, defeat the boss by firing enough bullets at it until its health reaches zero
+ Possibly scoring system based on how many enemies were destroyed and using less lasers

## Link Examples ##
Provide links to examples of your game idea.  This can be a playable online game, screenshots, YouTube videos of gameplay, etc.

+ [Bullet Hell Monday](https://www.youtube.com/watch?v=Gc2Kav-EM1Y&t=334s) Inspiration for the game, and what it will be based on

## Teacher Response ##

**Approved**

If your game has little to no animation and enemies just spawn randomly but increase as levels go, this won't be a great game.
However, if your enemies do interesting things and you have some animations for fire/hit then you're game will look and feel good.
I would suggest skipping the Boss idea and just try to make some solid levels that feel like more than just random enemies popping up.

## Class Design and Brainstorm ##
+ The base of my game will be similar to the Breakout Group project one, which is mostly different actors in a single world all managed by another GameController class.
+ Game Controller class
	+ Manages each different scene classes (Main menu, game over, levels, etc.)
		+ Allows for a GameController class to control scene switching by having a borderpane set a different center for each scene
			+ Each different scene class will have a reference to the GameController
	+ Getter methods that return references to different root nodes of different scenes
		+ Other scenes like main menu can use these getter methods to switch scenes when a button is pressed
+ Movable interface
	+ Enemies and bullet will implement a movable interface that contains methods to move the actor coordinates
		+ Ship will not because it moves through mouse movement rather than set directions and speeds
+ Resettable interface
	+ Used by levels and tutorial
	+ Has a reset method which classes need to implement
+ Menu abstract class
	+ Menu screens will be subclasses of this since they will all have the same layout
+ Main Menu class
+ Level Selection Menu class
+ High Scores class
+ Tutorial class
	+ Subclass of World
	+ Listeners for keyboard inputs and mouse movement
		+ If 'Z' is pressed, will call the ship's fire bullet method
+ Score class
	+ Levels will have references to their own score class
	+ Used to keep track of score
+ Ship Actor
	+ Ship that the player controls
	+ Subclass of Actor
	+ Fires bullet method
	+ Animation Timer to control the delay between each bullet fired
+ Enemy Actor
	+ Multiple enemies, so a new subclass class for every variant
	+ Animation Timer that each different enemy subclass uses
		+ Different enemies will have different firing speeds, so uses animation timer to delay each bullet
+ Bullet Actor
	+ Each individual bullet fired by you and enemies are individual actors
	+ Different bullets that are subclasses
		+ Each different bullet has a different image and path
			+ Basic ones follow straight lines, others home in on player, some follow curved paths, etc.
+ Player Bullet
	+ Bullet that the player fires

## Development Journal ##

Every day you work, keep track of it here.

**Friday May 22 (3 hours)**

Goal:  Get the game project base going, make the gameController and the other world classes (main menu, levels). Will add the basic buttons and title but no effects/animation for main menu

Work accomplished:  Got the basics of the game project going with the main menu, level selection, high score list, and scene switching working correctly. 
Added reading from files from high score list.

**Saturday May 23 (2 hours)**

Goal:  Get the bare minimum tutorials and levels working, get the basic level mechanics (ship, enemies, firing bullets, collision detection) working

Work accomplished:  Added tutorial scene and ship actor. Changed ship movement from arrow keys to following mouse. Arrow keys felt too clunky, slow, and lacked diagonal movement. 
Mouse movement feels better and smoother. Change to mouse movement means deleting holding the shift key to slow down the ship.

**Sunday May 24 (1.5 hours)**

Goal: Implement bullet firing from player

Work accomplished: Added basic bullet firing from the player

**Tuesday May 26 (2.5 hours)**

Goal: Implement laser firing, immunity, and absorbing bullets, and finish tutorial

Work accomplished: Added laser firing and completed the tutorial

**Saturday May 30 (4 hours)**

Goal: Implement a basic enemy class, implement pixel perfect bullet detection for player (not needed for enemies), implement basic bullet detection for enemies, 
implement collision with player and enemies, implement pausing and resetting in levels, finish level 1

Work Accomplished: Added enemy classes, did not implement pixel perfect collision for player as I felt it would be too time consuming and not worth it to try and resize the actor so it is the exact same size as the image it uses, 
and then find a way to loop through every pixel running through different conditions of whatever an x,y coordinate is shared amongst two actors. Implemented bullet/laser detection for enemies, bullet detection for player ship, and started a bit of level 1.

**Sunday May 31 (8.5 hours)**

Goal: Finish levels 1 and 2, add high score system

Work Accomplished: Deleted scoring system, too much trouble to work with as files would only succesfully be read once and after that, file reading wouldn't work and would just return nothing, unless i physically changed the contents of that file, which it would then only work once and then never again. Only kept scores for each individual game. Added
win, pause, and game over screens, as well as pausing, resetting, and quitting levels. Finished level 1, 1/2 of the way to level 2. Added a variety of enemies.


**Monday June 1 (7 hours)**

Goal: Finish level 2

Work Accomplished: Finished level 2 and entire game except for polishing

**Tuesday June 2**

Goal: Add special effects to menus, make a laser count indicator, add animations for ship fire and hit, as well as enemy fire and hit. Add an indicator of health being depleted from enemies. Add better images for ship, enemies, and bullets. Add backgrounds to menus and levels. Add sound effects and music.
***
***
