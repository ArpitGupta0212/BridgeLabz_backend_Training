<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Greeting Result</title>

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
            background:#fff;
            padding:40px;
            border-radius:20px;
            box-shadow:0 10px 25px rgba(0,0,0,0.15);
            text-align:center;
        }

        h1{
            color:#d81b60;
            margin-bottom:20px;
        }

        .message{
            font-size:24px;
            color:#444;
            margin:25px 0;
            font-weight:bold;
        }

        a{
            display:inline-block;
            margin-top:20px;
            padding:12px 25px;
            background:#ff5c8d;
            color:white;
            text-decoration:none;
            border-radius:10px;
            transition:.3s;
        }

        a:hover{
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

    <h1>💖 Greeting Result</h1>

    <div class="message">
        ${greeting.message}
    </div>

    <a href="index.jsp">⬅ Back</a>

    <div class="footer">
        ❤️ Made by Arpit ❤️
    </div>

</div>

</body>
</html>