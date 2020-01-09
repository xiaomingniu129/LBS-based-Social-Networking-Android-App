# LBS-based-Social-Networking-Android-App
Developed an Android App for users to report events and search nearby events.

# Description
* Developed an Android App for users to report events and search nearby events.
* Designed a view to report new events with title, description, image and location.
* Designed a view to show the details of clicked event and make comments or a like to it.
* Integrated Google Map API to track the current user’s location and display nearby hot events as well as navigating to the event.
* Used Google Firebase to store and control UGC including titles, descriptions, images, comments and locations.
* Implemented in-app advertising service (Google AdMob) to show Google advertisers and interact with users.

# Activity and Fragment Design
* MainActivity: for login and register.
* EventActivity: show events in two ways, one for all events list, the other for nearby hot events in Google Map.
* EventsFragment: show all events in list view.
* EventListAdapter: get data of events and ads, and distribute them to corresponding positions in RecyclerView.
* EventMapFragment: show nearby hot events in Google Map with markers and navigate to the event.
* CommentActivity: show detail information of each specific event clicked by users, including title, username, time, description, comments, etc. and also make new comment on it.
* CommentAdapter: get data of event and comments, and distribute them to corresponding positions in RecyclerView.
* EventReportActivity: report new event with title, description, location, image, etc.
* User: create instance when registering with attributes of each user.
* Event: create instance when reporting a new event with attributes like title, like, comment, etc.
* Comment: create instance when making comments to a specific event with attributes like description, username, eventId, like, etc.
* Utils: some helper method like encryption of password, calculation of distance between two locations, time transformation, etc.
* LocationTracker: get permission from users to use Android system GPS or network to get current location.
