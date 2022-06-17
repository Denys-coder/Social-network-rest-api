<pre>
Very simple REST API for social network


/register POST
You can register new user
{
  "name":"name1",
  "surname":"surname1",
  "age":10,
  "aboutMe":"about1",
  "password":"pass1",
  "email":"email1"
}


/login POS
You can login
{
  "username":"email1",
  "password":pass1"
}
In response you get JWT token in Authentication HTTP header with "Bearer " prefix


/user/{userId} GET
You can obtain user's profile
{
  "id": 1,
  "name": "name1",
  "surname": "surname1",
  "age": 10,
  "aboutMe": "about1",
  "email": "email1"
}


/user/{userId}/posts GET
You can obtain all user's posts
[
  {
    "id": 1,
    "header": "header1",
    "text": "text1"
  }
]


/post/{postId} GET
You can obtain post data
{
  "id": 1,
  "header": "header1",
  "text": "text1"
}


/post POST
You can create a new post. You must send JWT token in "Authentication" HTTP header with "Bearer " prefix.
{
  "header":"header1",
  "text":"text1"
}


/post/{postId} DELETE
You can delete post. You must send JWT token in "Authentication" HTTP header with "Bearer " prefix.
If you try to delete another user's post you will get 403 Unauthenticated.


/post/{postId} PUT
You can update (send new post and swap it with old one) post.
You must send JWT token in "Authentication" HTTP header with "Bearer " prefix.
If you try to update another user's post you will get 403 Unauthenticated.
{
  "header":"header1",
  "text":"text1"
}
</pre>