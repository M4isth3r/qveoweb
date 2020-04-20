<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Registro de una serie</title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--     <link rel="stylesheet" -->
<!--           href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/general.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/serieRegistro.css">

</head>
<body>
	<header>
		<%--     <%@include file="/WEB-INF/views/layout/header.jsp" %> --%>
	</header>

	<main>
		<section>
			<div class="row">
				<c:choose>
					<c:when test="${editar}">
						<c:set var="action" scope="session"
							value="/qveo/serie/update/${serieNueva.id}" />
					</c:when>
					<c:otherwise>
						<c:set var="action" scope="session" value="/qveo/serie/form/add" />
					</c:otherwise>
				</c:choose>

				<form:form method="POST" action="${action}"
					modelAttribute="serieNueva" enctype="multipart/form-data"
					class="col s12">
					<div class="row">
						<div class="col s2"></div>
						<div class="input-field col s8">
							<form:label path="titulo">Titulo</form:label>
							<form:input path="titulo" id="titulo" />
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<form:label path="fechaInicio">Fecha de lanzamiento</form:label>
							<form:input type="date" id="fechaInicio" path="fechaInicio" />
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="input-field col s8">
							<form:label path="temporadas">Temporada</form:label>
							<form:input path="temporadas" id="temporadas" />
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="input-field col s8">
							<form:label path="capitulos">Capitulos</form:label>
							<form:input path="capitulos" id="capitulos" />
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<form:label path="poster">Poster</form:label>
							<%-- 							<form:input path="poster" name="poster" type="file"/> --%>
							<input type="file" name="posters">
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<form:label path="sinopsis">Sinopsis</form:label>
							<form:textarea path="sinopsis" id="sinopsis" rows="4" cols="20" />
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<form:label path="generos">Género</form:label>
						</div>
						<div class="col s2"></div>
					</div>
					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">

							<c:choose>
								<c:when test="${editar}">
									<c:forEach items="${generos}" var="genero">
										<form:label path="generos">
											<c:forEach items="${serieNueva.generos}" var="generoS">
												<c:choose>
													<c:when test="${genero.id == generoS.id}">
														<form:checkbox path="generos" value="${genero.id}"
															checked="checked" /> ${genero.nombre}
												</c:when>
													<c:otherwise>
												S<form:checkbox path="generos" value="${genero.id}" /> ${genero.nombre}
												</c:otherwise>
												</c:choose>


											</c:forEach>
										</form:label>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach items="${generos}" var="genero">
										<form:label path="generos">E
											<form:checkbox path="generos" value="${genero.id}" /> ${genero.nombre}
										</form:label>
									</c:forEach>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col s2"></div>
					</div>


					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<form:label path="pais">Pais de produccion</form:label>
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<form:select path="pais">
								<c:choose>
									<c:when test="${editar}">
										<form:option value="0">Elija un pais</form:option>
										<c:forEach items="${paises}" var="pais">
											<c:choose>
												<c:when test="${pais.id == serieNueva.pais.id}">
													<form:option value="${pais.id}" selected="selected">${pais.nombre}</form:option>
												</c:when>
												<c:otherwise>
													<form:option value="${pais.id}">${pais.nombre}</form:option>
												</c:otherwise>
											</c:choose>
										</c:forEach>

									</c:when>
									<c:otherwise>

										<form:option value="0" selected="selected">Elija un pais</form:option>
										<c:forEach items="${paises}" var="pais">
											<form:option value="${pais.id}">${pais.nombre}</form:option>
										</c:forEach>

									</c:otherwise>

								</c:choose>
							</form:select>
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<form:label path="directores">Director</form:label>
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">

							<c:choose>
								<c:when test="${editar}">
									<c:forEach items="${directores}" var="director">
										<form:label path="directores">
											<c:forEach items="${serieNueva.directores}" var=" directorS">
												<c:choose>
													<c:when test="${director.id == directorS.id}">N
														<form:checkbox path="directores" value="${director.id}"
															checked="checked" />${director.nombre}
													</c:when>
													<c:otherwise>S
														<form:checkbox path="directores" value="${director.id}" />${director.nombre}
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:label>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach items="${directores}" var="director">
										<form:label path="directores">
											<form:checkbox path="directores" value="${director.id}" />${director.nombre}
							</form:label>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<form:label path="plataformas">Plataformas</form:label>
						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<c:choose>
								<c:when test="${editar}">
									<c:forEach items="${plataformas}" var="plataforma">
										<form:label path="plataformas">
											<c:forEach items="${serieNueva.plataformas}"
												var="plataformaS">
												<c:choose>
													<c:when test="${plataforma.id == plataformaS.id}">
														<form:checkbox path="plataformas" value="${plataforma.id}"
															checked="checked" /> ${plataforma.nombre}
													</c:when>
													<c:otherwise>
														<form:checkbox path="plataformas" value="${plataforma.id}" /> ${plataforma.nombre}
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:label>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach items="${plataformas}" var="plataforma">
										<form:label path="plataformas">
											<form:checkbox path="plataformas" value="${plataforma.id}" /> ${plataforma.nombre}
								</form:label>
									</c:forEach>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col s2"></div>
					</div>

					<div class="row">
						<div class="col s2"></div>
						<div class="col s8">
							<c:choose>
								<c:when test="${editar}">
									<button class="btn waves-effect waves-light" type="submit"
										name="action">
										Actualizar <i class="material-icons right">send</i>
									</button>
								</c:when>
								<c:otherwise>
									<button class="btn waves-effect waves-light" type="submit"
										name="action">
										Registrarse <i class="material-icons right">send</i>
									</button>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col s2"></div>
					</div>
				</form:form>

			</div>
		</section>
	</main>

	<footer>
		<%@include file="/WEB-INF/views/layout/footer.jsp"%>
	</footer>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>