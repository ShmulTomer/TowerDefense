# TowerDefense

I created a template for this project using [this video](https://www.youtube.com/watch?v=kMI2jy-WlGM).

I'm not sure if the virtual device is still on the project, but I created a Pixel 4 XL Virtual Device
titled "Virtual Device Pixel 4 XL".

I think it'd be helpful to have this template on the main branch but for future commits can we each
create our own branch and have other people approve merge requests to the main branch?

## M2
1. Starting the application will open to a welcome screen for the application which:
* Has some way to start the game (e.g., a start button).
* Has some way to quit the game within the application (e.g., a quit button).
    * The user closing the window does not count as a method to quit.
2. Starting the game should take the player to an initial configuration screen which:
* Requires input for the player to enter their name.
    * Players should not be allowed to pass in an empty, null, or white-space only
name.
* Requires the player to choose their difficulty from at least 3 different options.
* Allows the player to continue to the game screen after they input both a valid name and
chosen a difficulty.
    * The player must be able to visually see the name and difficulty they have chosen
before they are allowed to move to the game screen
3. The game screen should display textually or graphically the first map the player will see. This is
where most of the functionality in later milestones is implemented. For now, the screen must:
* Display starting money.
    * Starting money should vary based on difficulty.
* Display/describe a distinct path over which enemies will travel in future milestones.
* Display/describe a monument at the end of the path which in future milestones will be
attacked by monsters and protected by towers.
* Display/describe monument health.
    * Monument health should vary based on difficulty.