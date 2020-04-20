
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QVeo web</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
</head>
<body>
<header>
    <%@include file="/WEB-INF/views/layout/header.jsp" %>
</header>
<main>
    <h1>Hola QVeo!</h1>

</main>
<footer>
    <%@include file="/WEB-INF/views/layout/footer.jsp" %>
</footer>

</body>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</html>