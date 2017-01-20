#Design plan
![alt text](https://github.com/tartiflette1990/FinalProject/blob/master/docs/App_design.png)


##Activities & Classes
Welcome_screen_activity: Ask if user wants to do a new story or continue an existing story

Continue_story_activity: shows a screen with the existing playfiles. Loaded from firebase database.

Story_activity: Loads the story. Either starting from the beginning or fast forwarding to the point the user was if he wants to continue the story where he left off. This will be a scrollview with textviews and buttons. The buttons determine which parts of the story are loaded. Consists of a scrollview with textviews and buttons.

Story class: Story is written down in seperate classes that can be accessed by the story_activity. The story I'm writing has multiple branching points, these different parts are saved in classes and are called upon based on the choices made in the story.

Wiki-API: Users can search wiki. An EditText and requestbutton make a request and the returned JSON is parsed into a textview.

![alt_text](https://github.com/tartiflette1990/Programmeerproject/blob/master/Story_Activity.png)

##LoadFile Class


![alt text](https://github.com/tartiflette1990/FinalProject/blob/master/docs/story_structure.jpg)

The progression-string of numbers is saved as under the username key in sharedpreferences. This string functions for selecting the correct story previously played and selects the right choices from the Story class.



