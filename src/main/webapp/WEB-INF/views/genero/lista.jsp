<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <title>QVeo web</title>
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
                <div class="container">
                    <div class="row">
                        <div class="input-field col s12 m6 l6 boton">
                            <a href="/qveo/genero/form"><i
						class="waves-effect waves-light btn agregar-usuario">Nuevo
							Genero</i></a>
                        </div>

                    </div>

                    <div class="row cabeceras">
                        <div class="col s3">Nombre Genero</div>
                        <div class="col s3">Opciones</div>
                    </div>
                    <c:forEach items="${generos}" var="genero">
                        <div id="${genero.id}" class="row cuerpo">
                            <div class="col s3">${genero.nombre}</div>
                            <div id="${genero.id}" class="col s3">

                                <div class="col s12 m3 l4">
                                    <a href="/qveo/genero/edit/${genero.id}" class="btn-floating btn-large"><i
								class="material-icons editar">edit</i></a>
                                </div>
                                <div class="col s12 m6 l4">
                                    <a href="#modal2-genero-${genero.id}" class="btn-floating btn-large red modal-trigger"><i
								class="material-icons eliminar">delete</i></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <c:forEach items="${generos}" var="genero">
                    <div id="modal2-genero-${genero.id}" class="modal confirmar">
                        <div class="modal-content">
                            <h4>Por favor debes confirmar</h4>
                            <p>¿Estás seguro de querer eliminar a este Actor?</p>
                        </div>
                        <div class="modal-footer">
                            <a href="#!" class="waves-effect waves-green btn-flat modal-close">Cancelar</a>
                            <a href="/qveo/genero/delete/${genero.id}" class="waves-effect waves-green btn-flat modal-close eliminar-usuario">Aceptar</a>
                        </div>
                    </div>
                </c:forEach>
            </main>


            <footer>
                <%@include file="/WEB-INF/views/layout/footer.jsp"%>
            </footer>

        </body>
        <script src="${pageContext.request.contextPath}/resources/vendor/js/jquery-3.4.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/js/materialize.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/header.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/genero/genero.js"></script>

        </html>