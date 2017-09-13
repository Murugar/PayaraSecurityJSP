

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payara Security JSP</title>
    </head>
    <body>
        <h1>Sign On</h1>
        <form name="form" action="" method="post">
            <p>
                <label id="name">Name:</label>
                <input type="text" name="name" value="" />
            </p>
            <p>
                <label id="password">Password:</label>
                <input type="password" name="password" value=""/>
            </p>
            <p>
                <input type="submit" value="Login" name="button"/>
            </p>
            ${msg}
        </form>
    </body>
</html>
