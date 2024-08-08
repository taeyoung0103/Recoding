<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>main</title>
  <link href="front/basic.css?after" rel="stylesheet" type="text/css">
</head>
<body>
<div class="blue">${sessionScope.id} 님 환영합니다.</div>
<button onclick="update()">내정보 수정하기</button>
<button onclick="logout()">로그아웃</button>
<button class="blue" onclick="findAll()">리스트</button>
<button class="blue" onclick="gomap()">지도로 이동</button>

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
  const gomap = () => {
    location.href = `/member/gomap`;
  }
</script>
</html>