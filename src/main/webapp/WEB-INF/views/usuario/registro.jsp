
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Registro nuevo usuario</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materialize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/general.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/registro.css">
</head>
<body>
	<header>
		<%@include file="/WEB-INF/views/layout/header.jsp"%>
	</header>
	<main>
	<div class="container">
			<c:choose>
				<c:when test="${edit}">
					<c:set var="action" scope="session"
						value="/qveo/usuario/update/${nuevoUsuario.id}" />
				</c:when>
				<c:otherwise>
					<c:set var="action" scope="session" value="/qveo/usuario/form/add" />
				</c:otherwise>
			</c:choose>
			<form:form method="POST" action="${action}"
				modelAttribute="nuevoUsuario" enctype="multipart/form-data"
				class="col s12 m12 l6 offset-l3 white-text">

				<div class="row">
					<div class="input-field col s12 m6 l6 offset-l3">
						<form:input path="nombre" id="first_name" class="validate" />
						<form:label for="first_name" path="nombre">Nombre</form:label>
						<form:errors path="nombre" style="color:red"></form:errors>
					</div>
					<div class="input-field col s12 m6 l6 offset-l3">
						<form:input path="apellidos" id="last_name" class="validate" />
						<form:label for="last_name" path="apellidos">Apellidos</form:label>
						<form:errors path="apellidos" style="color:red"></form:errors>
					</div>
				</div>
				
				<div class="row">
					<div class="input-field col s12 m6 l6 offset-l3">
						<form:input id="email" type="email" path="email" class="validate" />
						<form:label for="email" path="email">Email</form:label>
						<span class="helper-text" data-error="email inválido"
							data-success="Perfecto!"></span>
						<form:errors path="email" style="color:red"></form:errors>
					</div>
					<div class="input-field col s12 m6 l6 offset-l3">
						<form:password id="password" path="password" class="validate" />
						<form:label for="password" path="password">Password</form:label>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m12 l6 offset-l3">
						<form:label path="sexo">Género</form:label>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m12 l6 offset-l3">
						<form:label for="mas" path="sexo">
							<form:radiobutton id="mas" path="sexo" value="M" />
							<span>Masculino</span>
						</form:label>
						<form:label for="fem" path="sexo">
							<form:radiobutton id="fem" path="sexo" value="F" />
							<span>Femenino</span>
						</form:label>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m12 l6 offset-l3">
						<form:label for="date" path="fechaNacimiento">Fecha de Nacimiento</form:label>
						<form:input id="date" class="datepicker" path="fechaNacimiento"/>
						<form:errors path="fechaNacimiento" style="color:red"></form:errors>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m12 l6 offset-l3">
						<form:select path="pais" multiple="false">
							<form:option value="">Pais de residencia</form:option>
							<form:options items="${paises}" itemLabel="nombre" />
						</form:select>
					</div>
				</div>
				<!-- plataformas de usuario -->
				<div class="row">
					<div class="col s12 m12 l6 offset-l3">
						<form:label path="plataformas">Plataformas</form:label>
					</div>
				</div>
				<div class="row">
					<div class="chips chips-autocomplete">
					</div>
<!-- 					<div class="col s12 m12 l6 offset-l3"> -->
<!-- 						<div class="chips chips-initial"> -->
							<c:forEach items="${plataformas}" var="plataforma">
<%-- 								<form:label for="${plataforma.nombre}" path="plataformas"> --%>
									<div id="${plataforma.nombre}" class="plataformas" style="display: none">${plataforma.id}</div>
<%-- 								</form:label> --%>
							</c:forEach>
<!-- 						</div> -->
					</div>

				<!-- Imagen de usuario -->

				<c:if test="${edit}">
					<div class="row">
						<div class="col s12 m8 offset-m2 l6 offset-l3">
							<div class="row valign-wrapper">
								<div class="col s6">
									<img width="200px"
										src="${pageContext.request.contextPath}${nuevoUsuario.foto}"
										alt="" class="circle responsive-img">
								</div>
								<div class="col s6">
									<span> Imagen de ${nuevoUsuario.nombre} con id:
										${nuevoUsuario.id} </span>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<!-- foto -->
				<div class="row">
					<div class="col s12 m12 l6 offset-l3">
						<div class="file-field input-field">
							<div class="btn">
								<form:label path="foto">
									<span>Imagen de perfil</span>
								</form:label>
								<input type="file" name="file" />
								<form:errors path="foto" style="color:red"></form:errors>
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text"
									placeholder="Suba aqui su foto">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m12 l6 offset-l3">
						<c:choose>
							<c:when test="${edit}">
								<button class="btn waves-effect waves-light" type="submit"
									name="action">
									Actualizar <i class="material-icons right">send</i>
								</button>
							</c:when>
							<c:otherwise>
								<button id="boton" class="btn waves-effect waves-light" type="submit"
									name="action">
									Registrarse <i class="material-icons right">send</i>
								</button>

							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</form:form>
		</div>
	</main>
	<footer>
		<%@include file="/WEB-INF/views/layout/footer.jsp"%>
	</footer>

</body>
<script
	src="${pageContext.request.contextPath}/resources/js/materialize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/usuario/registro.js"></script>
</body>
</html>
