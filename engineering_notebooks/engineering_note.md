#09/15/2016
1. There is a lot of music out these days. Especially with the internet, that makes music easily available, how do people decide what new music to listen to? We want to create a mobile app platform that allows users to rate albums of music, and discuss its contents; kind of like a rotten tomatoes for music. Instead of having long, formal reviews, the discussion of the music will be in a more mobile-friendly environment and look more like a youtube comment section.

2. Music fans, people who want to find something new to listen to, vinyl collectors, appreciators of art.

3.

4. The platform:
OS/Hosting: ?
Data Storage: SQL, Amazon cloud?
Application Server: Java

5. The core functionality:
    - Store the information of the albums in the database that is easy to fetch.
    - Search the information of a specific album based on the database. You can search by the artist name,
    -  Provide a rating scale for each album in the database. You can give 1.0, 1.5, 2.0...4.5 5.0 rating. Provide user login to prevent repeat rating (ideally use the facebook API)
    - Allow using to thumbs up or thumbs down an artist
    - Provide a discussion board allowing users to write "mini-reviews" of an album and allowing other users  to like and/or comment on these reviews. Think of a youtube comment section
    - Have a discover (search) page. In discover page, there is three regions in the page. The first one is the search bar on the top which allow people to search by the name of album and/or the artists. The second one is the predefined genre region. The third one is the self-defined tag region, this tag can be a subgenre, or some user defined characteristic of the album.
In the second and third region, people can select the tags and the genre they want to listen together and hit the search button in the search bar. After that, it will redirect to a page with a list of albums sorted by the ratings.

6. The sencondary functionality:
    - Search the cover of the album from the internet automatically and the users can report the incorrect cover of the albums.
    - Search the bio of the artist and the introduction of the album from the internet. The user should also be able to report the incorrect information of them as well.
    - Give the user a home page with stats and preferences where people can track to the other user's main page easily
    - Store the information of the users including the listening record, comments, ratings, and tags used for the liked songs.
    - Recommendation system: Based on the tags and the specific artists, provide the recommended albums for the specific user.
    - Provide the link to itunes or amazon link for the albums that users can buy the album of interest
    - Interaction with other music app like spotify, pandora, youtube

7. The features that the mobile device will use:
    - internet
    - facebook for login

8. Limitations:
    - Set up the database for albums, artists, and the users.
    - web crawler for searching the images and the words.
    - Interaction with other apps like itunes, spotify, etc.

9. The market plan:
    - We can put up advertisements for the app around music shows
    - We can recommend our apps in some musical forum and distribute it on the google store as a free app.
    - Invite(or hire?) someone to write some good music review with some popular albums according to the trends(?)
    - Or we can pay for some good music critics from the forum and put it in our app at the specific album section.
