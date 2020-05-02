<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>QVeo web</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materialize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/general.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/tabla.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/icon.css">
</head>
<body>
	<header>
		<%@include file="/WEB-INF/views/layout/header.jsp"%>
	</header>
	<main>
		<div class="row">
			<div class="col s12">
				<a href="/qveo/usuario/form"><i
					class="waves-effect waves-light btn agregar-usuario">Nuevo
						Usuario</i></a>
			</div>
		</div>
		<table class="centered highlight responsive-table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>Toda la información</th>
					<th>Editar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="usuario">
					<tr>
						<td>${usuario.nombre}</td>
						<td>${usuario.apellidos}</td>
						<%-- 						<td><fmt:formatDate pattern="dd/MM/yyyy" --%>
						<%-- 								value="${usuario.fechaNacimiento}" /></td> --%>

						<td><a id="${usuario.id}"
							class="waves-effect waves-light btn modal-trigger mostrar-usuario"
							href="#modal1">Cargar datos de usuarios</a></td>
						<td><a href="/qveo/usuario/edit/${usuario.id}"><i
								class="material-icons editar">edit</i></a></td>
						<td><a href="/qveo/usuario/delete/${usuario.id}"><i
								class="material-icons eliminar">delete</i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- Modal Structure -->
		<div id="modal1" class="modal modal-fixed-footer">
			<div id="contenido" class="modal-content"></div>
			<div class="modal-footer">
				<a href="#" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
			</div>
		</div>
	</main>
	<footer>
		<%@include file="/WEB-INF/views/layout/footer.jsp"%>
	</footer>

</body>
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/materialize.js"></script>
<script>
	document.addEventListener('DOMContentLoaded', function() {
	    var elems = document.querySelectorAll('.modal');
	    var instances = M.Modal.init(elems);
	  });
</script>
<script>
	$(document).ready(function() {

		$(".mostrar-usuario").click(function(event) {
			
			let id = event.target.getAttribute('id');
			console.log(event.target.getAttribute('id'));
			mostrarUsuario(id);

		});

	});

	function mostrarUsuario(id) {

		$.ajax({
			url : '/qveo/ajax/usuario/'+id,
			success : function(data) {
				
				console.log(data.foto);
				let rutaFoto = data.foto;
				let contenido = document.getElementById('contenido');
				
				contenido.innerHTML = '<h4>Datos del usuario:'+data.nombre+'</h4>';
				
				for(const prop in data){
					contenido.innerHTML += prop+':'+ data[prop]+'<br>';
				}
				
				//let imagen = document.createTextNode('<img src="'+ruta+data.foto+'">');
				//contenido.appendChild(imagen);
				contenido.innerHTML += '<img src="${pageContext.request.contextPath}'+data.foto+'">';
				console.log(imagen);
				console.log("SUCCESS : ", data);

			},
			error : function(e) {

				console.log("ERROR : ", e);

			}
		});

	}
</script>

</html>