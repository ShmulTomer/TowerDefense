# TowerDefense

## M2
1. Starting the application will open to a welcome screen for the application which:
    * Has some way to start the game (e.g., a start button).
    * Has some way to quit the game within the application (e.g., a quit button).
        * The user closing the window does not count as a method to quit.
2. Starting the game should take the player to an initial configuration screen which:
    * Requires input for the player to enter their name.
        * Players should not be allowed to pass in an empty, null, or white-space only name.
    * Requires the player to choose their difficulty from at least 3 different options.
    * Allows the player to continue to the game screen after they input both a valid name and chosen
    a difficulty.
        * The player must be able to visually see the name and difficulty they have chosen before
        they are allowed to move to the game screen
3. The game screen should display textually or graphically the first map the player will see. This
is where most of the functionality in later milestones is implemented. For now, the screen must:
    * Display starting money.
        * Starting money should vary based on difficulty.
    * Display/describe a distinct path over which enemies will travel in future milestones.
    * Display/describe a monument at the end of the path which in future milestones will be attacked
    by monsters and protected by towers.
    * Display/describe monument health.
        * Monument health should vary based on difficulty.

## M2 Issues / To-Do
1. When I run the emulator on my computer, it goes from the activity_main screen to the initial_config
screen back to the activity_main screen. It should be activity_main --> initial_config --> game_activity,
can someone check my code and see what is wrong -Helen
2. Since I can't run the emulator for game_activity idk if my code to resize the path works
3. Still need to set monument health and starting money based on difficulty.

## Notes
1. Instead of using System.out.println to test if things are working, use Log.d("tag", "message")
and the output will be in Logcat; search up the tag name
2. I also copied the .gitignore file [here](https://github.gatech.edu/gtobdes/M1_Android/blob/master/.gitignore)
and added it to what already exists in my .gitignore file