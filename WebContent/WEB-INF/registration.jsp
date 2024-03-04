<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register Account</title>
    <link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei"
          rel="stylesheet">
    <link href="../CSS/regstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container">
    <div class="regbox box">
        <img class="avatar" src="../Images/collaboration.png">
        <h1>Register Account</h1>
        <form action="UserController" method="post">
            <p>Login ID</p>
            <input type="text" placeholder="Login ID" name="login" required>
            <p>User Full Name</p>
            <input type="text" placeholder="UserName" name="usderName" required>
            <p>Password</p>
            <input type="password" placeholder="Password" name="password"
                   required> <input type="submit" value="Register"> <a
                href="../index.jsp">Already have Account?</a>
        </form>
    </div>
</div>
</body>
</html>