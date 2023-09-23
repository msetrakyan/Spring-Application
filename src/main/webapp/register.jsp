<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="UTF-8">
  <title>Welcome page</title>
</head>
<body>

<h1>Welcome to our shop</h1>

<h3>Register</h3>

<form action="/users/register" method="post">
  Name: <input type="text" name="name"></br>
  LastName: <input type="text" name="lastName"></br>
  Age: <input type="number" name="age"></br>
  Username: <input type="text" name="username"></br>
  Password: <input type="text" name="password"></br>
  Email: <input type="text" name="email"><br>

  <input type="submit" value="register">

</form>

</body>
</html>