Plants Vs. Zombies: Bootleg Version Version 2.1.0 11/16/2018
Authors: Sai Vikranth Desu, Abhi Santhosh, Everett Soldaat, Liam Murphy
Documentation Author: Typed by Sai Vikranth Desu, but the voice of the whole team

GENERAL USAGE NOTES
-------------------
- This text based version of plants vs. zombies is turn-based. Each turn you can choose between various commands and advance turns accordingly.
- There is only one level, good luck though it's not easy
- Once the game is run, there will be a lot of text before the game itself. This text will instruct you on how to play the game and give you some background information (although the manual is a better resource).

DELIVERABLES
------------
- readme file 
- code
- UML diagram(s)
- JUnit tests
- documentation (design choice, user manual, javadocs)

KNOWN ISSUES
------------
- The main issue we have right now is that in the real game zombies can "pile up" and attack plants together. Our version of the game kind of have the zombies kind of line up behind each other instead of stacking on the same square. This should be something we can fix when we make the game realtime.
- We were also not able to implement the cooldown timer for the plant cards. Right now, you can spam the plants and there will be nothing to stop you unfortunately.
- There is a good amount of smelly code. We can easily make our code more efficient by instantiating fewer objects. Our goal was to get the barebones functionality to work for the game, we will make the code clean and beautiful for the next milestone.

ROADMAP AHEAD
-------------
Must haves:
- Code needs to be cleaned up a lot.
- More levels must be added, perhaps a bar that shows you how far into the current level you are
- All requriements for milestone 3
- A lot of hardcoded stuff needs to be automated (plants/zombies)
- We want to have the sun and peas (from the peashooter) to be their own objects that show up on the screen

Nice to haves:
- More formatting on the UI (buttons/gameboard, etc)
- A menu with play/about/controls
- Lawnmowers
- Nicer plant cards
- Sound effects
- Realtime version

AUTHORS
-------
We didn't have as organized work distribution amongst our group members as we did for the first milestone. Sai worked on the initial barebones GUI conversion of the game. Abhi and Liam then started modifying all the entities (zombies/plants/sun/turns) to work with the GUI and everyone was participating in debugging and small tasks. Everett and Liam worked on the JUnit tests, Abhi and Sai implemented the turns and action listeners. Liam is our JSON guy so he implemented his Level class within our new GUI based Game.java class to spawn zombies. Everyone worked on the commenting and documentation, whoever made the method/wrote the code would comment it. Everett also updated our previous UML diagram and everyone worked on the sequence diagrams.

INSTALLATION 
------------
- Run the Game.java class to run the GUI and play the game.
- Folder "code" contains our project and source code
- Folder "documentation" contains all the documentation
