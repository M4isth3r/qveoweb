<%@ page contentType="text/html;charset=UTF-8" language="java"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="ISO-8859-1">
                    <meta charset="ISO-8859-1">
                    <c:choose>
                        <c:when test="${editar}">
                            <title>Actualizar de una genero</title>
                        </c:when>
                        <c:otherwise>
                            <title>Registro de una genero</title>
                        </c:otherwise>
                    </c:choose>


                    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
                    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/general.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/icon.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/genero/genero.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal.css">
                </head>

                <body>
                    <header>
                        <%@include file="/WEB-INF/views/layout/header.jsp"%>
                    </header>
                    <main>
                        <section>
                            <div class="row">
                                <div class="col s2"></div>
                                <div class="input-field col s8">
                                    <form:form method="POST" action="/qveo/genero/form" modelAttribute="generoNuevo" class="col s12">
                                        <form:label path="nombre">Nombre del Genero</form:label>
                                        <form:input path="nombre" id="nombre" />
                                        <form:errors path="nombre" style="color:red"></form:errors>
                                        <div style="color: red">${errorNombre}</div>
                                        <c:choose>
                                            <c:when test="${editar}">
                                                <form:hidden path="id" />
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${editar}">
                                                <button class="btn waves-effect waves-light" type="submit" name="action">
									Actualizar <i class="material-icons right">send</i>
								</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn waves-effect waves-light" type="submit" name="action">
									Registrarse <i class="material-icons right">send</i>
								</button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form:form>
                                </div>
                                <div class="col s2"></div>
                            </div>
                        </section>
                    </main>
                    <footer>
                        <%@include file="/WEB-INF/views/layout/footer.jsp"%>
                    </footer>
                    <script src="${pageContext.request.contextPath}/resources/vendor/js/jquery-3.4.1.min.js"></script>
                    <script src="${pageContext.request.contextPath}/resources/vendor/js/materialize.js"></script>
                    <script src="${pageContext.request.contextPath}/resources/js/header.js"></script>
                </body>

                </html>