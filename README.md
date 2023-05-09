# CSC1004-Java-Java-Movie-Management-System
My program is a movie managemet system. It includes three parts, Login part, Admin part and Commonuser part.

# The login and Sign up part

New user can sign up through the sign_up page to sign up.
Then, the user can log in though the username and password and enter the commonuser part.

Some of Admin has been added into the MySql. The admin can log in through the username and password.
Then, they can enter the Admin part.

# The Admin part

1. Admins can view the movies and update/delete/add movies. They can upload image of the movie.

2. Admin can change the admin's and commonusers' username and password.

3. Admin can view commonusers' comments on movies and view the rates on movies.
4. Admin see some statistics of rates/ages/movietypes through chart plots.

# The commonuser part

1. Commonuser can view the users' statistics.
2. Commonuser can view all movies' contents and comment and rate the movies.

# Implementation

1. Prepare the classes of Admin,CommonUser,Movie,MovieClass,Rate,Comment,and User.
2. Write the JdbcUtils.java to connect the program with mysql.
3. Write three .fxml files: Login.fxml,AdminView.fxml,CommonUser.fxml.
   1. Login.fxml covers two stages: login stage and sign up stage.
   2. AdminView.fxml covers five stages: Movie,Admin,CommonUser,Comment,Statistics(rate,age,movieClass).
   3. commonUserView.fxml covers two stages: User page,Movie page(view movie contents and rate and comment the movie).
4. Write three controller.java for each .fxml.
5. Finish the main.java
# How to run the program

1. Download Java 8, MySql, and Intellij.
2. Add mysql-connector and controlsfx into the project.
3. Run src/main/resources/org/example/moviemanagement.sql.
4. In JdbcUtils.java, change the code in 14 and 15 lines to your MySql's username and password.
5. Run the main.java.

