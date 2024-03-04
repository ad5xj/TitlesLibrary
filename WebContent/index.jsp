<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>Library Login</title>
     <link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei" rel="stylesheet">
     <link href="CSS/basestyle.css" rel="stylesheet" type="text/css">
 </head>

 <body>
  <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <h1>Library Login Form</h1>
    <form action="<%=request.getContextPath()%>/LoginController" method="post">
      <div class="form-group">
        <label for="username">User Name:</label>
        <input type="text"
               class="form-control"
               id="username"
               placeholder="User Name"
               name="username" required />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password"
               class="form-control"
               id="password"
               placeholder="Password"
               name="password" required />
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
 </body>
 <footer>&copy; Copyright 2024 AD5XJ ad5xj@qso.com</footer>
</html>