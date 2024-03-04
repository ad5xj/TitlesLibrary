<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T">
 </head>

 <body>
   <div class="container">
     <div class="row text-center" style="color: tomato;">
       <h2>User Login Registration</h2>
     </div>
     <hr>
     <div class="row col-md-10 col-md-offset-3">
        <div class="card card-body">
          <h2>User Register Form</h2>
            <div class="col-md-8 col-md-offset-3">
              <form action="<%=request.getContextPath()%>/registerform" method="post">
                <div class="form-group">
                  <label for="uid">Login ID:</label>
                  <input type="text"
                         class="form-control" id="uid"
                         placeholder="Login ID"
                         name="login" required>
                </div>
                <div class="form-group">
                  <label for="username">User Name:</label>
                  <input type="text"
                         class="form-control"
                         id="username"
                         placeholder="User Name"
                         name="username" required>
                </div>
                <div class="form-group">
                  <label for="pwd">Password:</label>
                  <input type="password"
                         class="form-control"
                         id="pwd"
                         placeholder="Password"
                         name="password" required>
                </div>
                  <div class="form-group">
                      <label for="acesslvl">"Access Level: "
                          <select id="acesslvl" size="20" name="acesslvl">
                              <option value="0" />
                              <option value="1" />
                              <option value="2" />
                              <option value="9" />
                          </select>
                      </label>
                  </div>
                <button type="submit" class="btn btn-primary">Submit</button>
              </form>
            </div>
        </div>
      </div>
   </div>
 </body>
</html>