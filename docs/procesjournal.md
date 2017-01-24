09-01: Thought of an idea I would like to make into an app. An interactive story, like a text-based-game.
10-01: The app is going to have a safe-file system and a single screen into which the story is displayed, Martijn gives the OK for this.
11-01: Started seeing of the basic functionality is possible, meaning a single screen with scrolling text and a button to make more text appear.
12-01: I have the scrolling text ready and made a welcome screen in some free time, the welcome screen has a custom dialog to prompt the user for his name which serves a the name for the file under which user story-progress is saved.
13-01: tried to implement firebase but not sure if I want to use firebase for this app
16-01: Im using sharedprefences now for saving the users progress, users can select their file and their progress is saved under a key-value pair, users create their key and the saved progress is the value.
17-01: Sharedpreferences keys now show up in a listview for the user to select his/her name from.
18-01: SharedPrefs hold filename + value, value corresponds to progress in story. Story_activity no longer works with scrollview and multiple textviews but with a scrollable textview. Work on buttons: they are mutually exclusive and clickable only once. Still needs work: they have to become clickable again when the next choice has to be made, and they have to have a different text and effect on the story.
23-01: I worked on file IO and how to combine it with my code. It turned out to be kind of easy but it took me a while to figure it out. The story.txt file has the text for the story, the text for the choicebuttons and the codes for the text. These are splitted using the split function and can thus be processed and put in the right place.
