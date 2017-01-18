#Design plan
![alt text](https://github.com/tartiflette1990/Programmeerproject/blob/master/App_design.png)


##Activities & Classes
Welcome_screen_activity: Ask if user wants to do a new story or continue an existing story

Continue_story_activity: shows a screen with the existing playfiles. Loaded from firebase database.

Story_activity: Loads the story. Either starting from the beginning or fast forwarding to the point the user was if he wants to continue the story where he left off. This will be a scrollview with textviews and buttons. The buttons determine which parts of the story are loaded. Consists of a scrollview with textviews and buttons.

Story class: Story is written down in seperate classes that can be accessed by the story_activity. The story I'm writing has multiple branching points, these different parts are saved in classes and are called upon based on the choices made in the story.

![alt_text](https://github.com/tartiflette1990/Programmeerproject/blob/master/Story_Activity.png)

##Database and Interaction
How do I want to save the story progress in firebase? I can label the choices with numbers and save these numbers seperated by commas under a username, that way it's clear from the length of the string of numbers how many choices the user has made and the numbers that the string is made up of will symbolize the choices made.
Database entry consists of the username and the choices made during the story. User is presented with two choices so they can be saved as 1 and 2. Based on 1 or 2 the relevant parts of the stories are displayed in the story activity.
![alt text](https://github.com/tartiflette1990/Programmeerproject/blob/master/StoryAppDatabase.png)
