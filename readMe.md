# Web Services For Movie Search And Save Movie to Favorite List  :movie_camera:  :notebook:
- You can search movie on imdb by movieName.
- You can save your favorite movie to your favorite  list with imdb id.
- You can search for movie in your favorite list with imdb id.
- You can get all the movies in your favorite  list. 
#
    ** If you want to use your own api key ,you can change it in application.properties.(imdb.authorization)
# 
-   Search movie by name  :mag:
    ```
    api/movies/search?<movie_name>

    ```

-   Save movie to your favorite list :floppy_disk:
    ```
    api/movies/saveToList/{imdbId}

    ```
-   Get movie details from your favorite list or imdb api with imdbId  :notebook: :eyes:
    ```
    api/movies/saveToList/{imdbId}

    ```

-   Get all the movies in your favorite list  :notebook: :eyes:
    ```
    api/movies/getAllMovieList

    ```
#

#


###### CollectAPI is used in this project -> (https://collectapi.com/api/imdb/imdb-api)

#

<img src="https://raw.githubusercontent.com/github/explore/5b3600551e122a3277c2c5368af2ad5725ffa9a1/topics/java/java.png" align="left" height="30" width="30" />
<img src= "https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/spring-boot/spring-boot.png" align="left" height="30" width="30">