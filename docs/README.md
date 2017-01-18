# Programmeerproject
Final app project for the minor programmeren

I want to make an interactive story app.
It tells a story through text and images and provides the user with choices along the way. 
![alt_text](https://github.com/tartiflette1990/Programmeerproject/blob/master/Story_Activity.png)


In the above picture you can see the basic idea, the different background colours signify changes in the UI affected by choices made in the story.

Programming-wise it will not be very difficult so I thought about some extra features: including an unlockable story/sidestory choice, adding sounds and/or animations to the story. Perhaps introducing a save-file system so that users can safe their progress and continue their story on a later moment.

#Design plan
I will list the activities I plan to make, and what they will do.

Welcome_screen_activity: Ask if user wants to do a new story or continue an existing story

Continue_story_activity: shows a screen with the existing playfiles. Loaded from firebase database. 

Story_activity: Loads the story. Either starting from the beginning or fast forwarding to the point the user was if he wants to continue the story where he left off. This will be a scrollview with textviews and buttons. The buttons determine which parts of the story are loaded.

Story class: Story is written down in seperate classes that can be accessed by the story_activity.

Interaction with Firebase: How do I want to save the story progress in firebase? I can label the choices with numbers and save these numbers seperated by commas under a username, that way it's clear from the length of the string of numbers how many choices the user has made and the numbers that the string is made up of will symbolize the choices made.








