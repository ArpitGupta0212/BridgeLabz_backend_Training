<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Greeting Application</title>

    <style>
        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:Arial, Helvetica, sans-serif;
        }

        body{
            background:#ffdbe7;
            display:flex;
            justify-content:center;
            align-items:center;
            height:100vh;
        }

        .container{
            width:450px;
            background:white;
            padding:40px;
            border-radius:20px;
            box-shadow:0 10px 25px rgba(0,0,0,0.15);
            text-align:center;
        }

        h1{
            color:#d81b60;
            margin-bottom:15px;
        }

        p{
            color:#666;
            margin-bottom:30px;
        }

        label{
            display:block;
            text-align:left;
            margin-bottom:8px;
            font-weight:bold;
            color:#444;
        }

        input[type=text]{
            width:100%;
            padding:12px;
            border:2px solid #ff8fb1;
            border-radius:10px;
            font-size:16px;
            outline:none;
            margin-bottom:20px;
        }

        input[type=text]:focus{
            border-color:#e91e63;
        }

        input[type=submit]{
            width:100%;
            padding:13px;
            background:#ff5c8d;
            color:white;
            border:none;
            border-radius:10px;
            font-size:18px;
            cursor:pointer;
            transition:.3s;
        }

        input[type=submit]:hover{
            background:#e91e63;
        }

        .footer{
            margin-top:30px;
            color:#777;
            font-size:15px;
        }
    </style>

</head>
<body>

<div class="container">

    <h1>💖 Greeting Application</h1>

    <p>Enter your name to get a personalized greeting.</p>

    <form action="greeting" method="post">

        <label>Name</label>

        <input
                type="text"
                name="name"
                placeholder="Enter your name"
                required>

        <input type="submit" value="Submit">

    </form>

    <div class="footer">
        ❤️ Made by Arpit ❤️
    </div>

</div>

</body>
</html>