<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="blue">먼저 로그인을 해주세요.</div>
    <form action="${pageContext.request.contextPath}/member/login" method="post">
    <input type="text" name="id" placeholder="아이디">
    <input type="text" name="password" placeholder="비밀번호">
    <input type="submit" value="로그인">
</form>
</body>
</html>