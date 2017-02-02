#Description:
I've created a text-based story app with riddles. The story has a
linear progression and when a riddle is encountered the story loops and gives hints
until the correct answer is given. The riddles can be solved by searching the hints
in wikipedia, for which there is a button in the actionbar.
The story is not shown at once but by clicking on one
of two buttons that have a setText that changes everytime the story is progressed.

#Technical design:
Apart from the welcome screen there is one activity where the
work is done and one class that supports this activity in the background. 
Theres some technical work in the textfile aswell, which has text for the storyTextView
and text for the Buttons. These have to be split apart using the string-split function and returns the outcome to the story_activity.

![alt text](https://github.com/tartiflette1990/FinalProject/blob/master/docs/designAPp.jpg)


The textfile: Lines are ordered in a fixed manner: first are two 'choices' seperated
by a "#" (hashtag) and followed by a "|" character. After the "|" follows text to append to
the storyTextView. The 'choices' further serve as identifiers. A line can have certain
identifiers that determine whether the scanner should stay on the current line or whether a line is to be skipped, or if the scanner has to go back
to an earlier point in the textfile or whether a dialog venster has to be opened for the user
to input an answer. With a lack of such identifiers there is a 'normal linear' progression.

The lineselection-class: Gets lines from the storytext as input and seperates them accordingly using the split() function
Lines are split on the "|" character and subsequently on the "#" character and given back to the story_activity.

The story_activity: opens the scanner and on a buttonclick feeds the outputline to the lineselection class.
Most work is done in the story_activity (unfortunately). It checks the identifiers and progresses through if and if/else statements
accordingly while manipulating the scanner accordingly.

#Challenges

##1.
The biggest challenge I've faced had to do with an unclear idea of what I wanted to create. At first when I had the idea of an
interactive story app it seemed clear enough to start coding. After finishing the code for the first idea it couldn't exactly do
what I wanted. Then I adjusted my idea a little making it both easier and unfortunately harder. It became easier in the sense that the story
became linear rather then branched. It became harder in the sense that I wanted to maintain an interactive aspect and now had to newly
implement those. At first I wanted to maintain my old code while implementing these new ideas but this was untenable so I had to change
the structure of the textfile and how lines were identified.

###processjournal
**13-01**: tried to implement firebase but not sure if I want to use firebase for this app.
**16-01**: Im using sharedprefences now for saving the users progress, users can select their file and their progress is saved under a key-value pair, users create their key and the saved progress is the value.
**01-02**: [Big Changes!] I worked on a story and implementing it for the user. I struggled with the way the correct lines show up and kind of
changed my idea/method to do this. At first every choice was symbolized by either a 1 or a 2, these were added to a string which would
keep track of the progress and what text to display. The problem I faced was that I did not want every choice to lead to a different
story-branch, as it would mean a huge branch of different stories. I tried to make different branches merge again but this lead to
problems for my line-identification code.

##2
The second biggest challenge with implementing the file IO, at first I had the whole story written in a different class, but I found out later that
this was not proper procedure. A lot of time was spent uniting the file IO method with my current code that was geared towards the Story class.
In the end I didn't unite them but it took me a lot of days  before I got off the idea that I wanted to preserve my old strategy with file IO. The journal says
that it was easy but that's what I thought back then..

###processjournal
**23-01**: I worked on file IO and how to combine it with my code. It turned out to be kind of easy but it took me a while to figure it out.
The story.txt file has the text for the story, the text for the choicebuttons and the codes for the text.
These are splitted using the split function and can thus be processed and put in the right place. 

##Further challenges
They had to do with how I worked, which wasn't really organized, this is reflected in the code I'm sure.
I've had plenty of bugs due to wrong variable names or stupid errors that could have been prevented if only I was more alert or paid closer
attention, bugs that I was stuck on for hours sometimes. I've tried to make my code more organized but this didn't really work out.

###processjournal
**30-01**: Worked on implementing the whole functionality while keeping my main-activity clean.. I failed to pass the scanner object to a different class so I now open the scanner function in the mainclass.. This means that a lot of work has to be done in the main class unfortunately and the code looks really ugly as a result. Still don't have the functionality working, there are more exceptions than I had thought in the beginning and they cause for a lot of if-statements. 

##Some smaller challenge
Failing hardware: from some point on my phone had to be completely rebooted each time the app crashed while testing.
The emulator was not an option since it would be even slower then rebooting my phone.

###processjournal
**24-01**: Phone crashed constantly today, took ages to fix a bug... 

#Decisions
I'll list the relevant decisions made chronologically and will go into them briefly

##Changing the idea
At first I had the idea that each buttonclick would create a new branch of the story. I thought I could make branches merge later so that I wouldn't have to write out all the story branches. This turned out to be difficult with how I had my code organized and then
I realized that that other aspects were more important. These were at the time the replayability of the story, so with multiple endings
it would be nice to replay the story. The interactive aspect of the app, I wanted the user to go on wikipedia to find the answer to 
certain riddles. I had to make a decision because at this point I couldn't implement both of them. I choose to go with the riddles idea
since that preserved a lot of the interactive qualities I wanted my app to have. (like a game) This led me to discared my loadfile system in the final app because it relied on my old code. A difficult decision but also a feature thats not part of the core-idea.

##Go for the cleanest code-architecture
This was difficult for me, I had time reserved to make my code look more organized. This has to do with opening the scanner object but handling it in a different class. I researched this for more then a day and tried different approaches but couldn't get it to work. With time pressure I had to go for the sloppy solution of handling the scanner in the main activity. Its too bad I was researching this on friday and over the weekend so I couldn't get the TA's input on this. Monday morning I made the decision to continue on since it took me too long otherwise. But when I talked to Renske later she said it wouldn't have been too difficult to handle the scanner in a different class.





