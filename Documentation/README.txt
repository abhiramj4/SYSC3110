Plants Vs. Zombies: Bootleg Version Version 1.2.0 10/29/2018
Authors: Sai Vikranth Desu, Abhi Santhosh, Everett Soldaat, Liam Murphy
Documentation Author: Sai Vikranth Desu
JAVADOCS Author/Creator: Abhi Santhosh

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
- documentation (design choice, user manual, javadocs)


KNOWN ISSUES
------------
- One issue we have right now is that in the real game zombies can "pile up" and attack plants together. Our version of the game kind of have the zombies kind of line up behind each other instead of stacking on the same square. This should be something we can fix when we make the game realtime.
- The commands that you give the game must be pretty accurate, else the game can easily crash. This is something that shouldn't be an issue with the GUI version of the game.
- This isn't really related to the game but we had some trouble using GitHub so our commits aren't a direct representation of the member's work. We ended up having to send one person the code to constantly update on the master branch. This will be fixed for the next milestones!

ROADMAP AHEAD
-------------
- We must design more levels using our JSON object level implementation method.
- We want to make the level implementation a little smarter, and include the "final wave" from the original game.
- Add more types of zombies and plants, having them unlocked as the user passes levels.
- Implement the lawnmowers for each row as the original game
- Make our code more efficient (instantiate fewer objects, maybe design a better controller model).
- GUI version of the game
- Anything else left for the second milestone :)

INSTALLATION 
------------
- Can't really run the game since there isn't a GUI (unless you want to do it on command prompt), either way a runnable JAR file was included
- Folder "code" contains our project and source code
- Folder "documentation" contains all the documentation
