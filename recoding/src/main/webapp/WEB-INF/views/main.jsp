<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>main</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<h2>${sessionScope.loginEmail} 님 환영합니다.</h2>
<button onclick="update()">내정보 수정하기</button>
<button onclick="logout()">로그아웃</button>
<button onclick="findAll()">리스트</button>

</body>
<script>
  const update = () => {
    location.href = "/member/update";
  }
  const logout = () => {
    location.href = "/member/logout";
  }
  const findAll = () => {
    location.href = "/member/";
  }
</script>
</html>