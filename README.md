I wrote a program that interacts with the API https://jsonplaceholder.typicode.com.
<h2>Task 1</h2>
The program contains methods to implement the following functionality:

- creating a new object in https://jsonplaceholder.typicode.com/users. You may not see the changes on the site immediately. The method works correctly if the same JSON is returned in response to the JSON with the object, but with an id value 1 greater than the largest id on the site.

- updating the object in https://jsonplaceholder.typicode.com/users. You may not see the changes on the site immediately. We believe that the method works correctly if you receive an updated JSON in response (it must be the same as the one you sent).

- deleting the object from https://jsonplaceholder.typicode.com/users. Here we will consider the correct result - a response status from the 2xx group (for example, 200).

- get information about all users https://jsonplaceholder.typicode.com/users

- get information about a user by id https://jsonplaceholder.typicode.com/users/{id}

- get information about a user by username - https://jsonplaceholder.typicode.com/users?username={username}

<h2>Task 2</h2>
Added a method to the program that displays all comments to the last post of a specific user and write them to a file.

https://jsonplaceholder.typicode.com/users/1/posts The last post is considered to be the one with the highest id.

https://jsonplaceholder.typicode.com/posts/10/comments

The file is called user-X-post-Y-comments.json, where X is the user id, Y is the post number.

<h2>Task 3</h2>
Added a method to the program that displays all open tasks for the user with the ID X.

https://jsonplaceholder.typicode.com/users/1/todos.

All tasks in which completed = false are considered open.