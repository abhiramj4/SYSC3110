Plants Vs. Zombies: Bootleg Version Version 2.1.0 11/16/2018
Authors: Sai Vikranth Desu, Abhi Santhosh, Everett Soldaat, Liam Murphy
Documentation Author: Typed by Sai Vikranth Desu, but the voice of the whole team

GENERAL USAGE NOTES
-------------------
- This text based version of plants vs. zombies is turn-based. Each turn you can choose between various commands and advance turns accordingly.
- There is only one level, good luck though it's not easy

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
- There is a good amount of smelly code. We can easily make our code more efficient by instantiating fewer objects. Our goal was to get the barebones functionality to work for the game, we will make the code clean and beautiful for the next milestone.
- Saving and loading sometimes freeze the gamescreen and don't allow for additional inputs

ChangeLog
-------------
- Added undo and redo functionality (Abhi)
- Added ability to create and play custom levels (Liam)
- Added save and load features (Sai)
- Bug fixing involving entities (Everett)

AUTHORS
-------
Sai worked on the on the Save and Load features. Abhi implemented the undo and redo functions. Liam completed the custom level features. Everett worked on documentation and did lots of bug fixing. Everyone worked on the commenting and documentation, whoever made the method/wrote the code would comment it. 
INSTALLATION 
------------
- Run the Game.java class to run the GUI and play the game.
- Folder "code" contains our project and source code
- Folder "documentation" contains all the documentation
