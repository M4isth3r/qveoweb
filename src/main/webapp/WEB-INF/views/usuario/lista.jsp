<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>QVeo web</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materialize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/general.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/tabla.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/usuario/lista.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/icon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>
	<header>
		<%@include file="/WEB-INF/views/layout/header.jsp"%>
	</header>
	<main>
		<div class="container">
			<div class="row">
				<div class="col s12 boton">
					<a href="/qveo/usuario/form"><i
						class="waves-effect waves-light btn agregar-usuario">Nuevo
							Usuario</i></a>
				</div>
			</div>
			<div class="row cabeceras">
				<div class="col s4">Nombre</div>
				<div class="col s4">Apellidos</div>
				<div class="col s4">Opciones</div>
			</div>
			<c:forEach items="${usuarios}" var="usuario">
				<div class="row cuerpo">
					<div class="col s4">${usuario.nombre}</div>
					<div class="col s4">${usuario.apellidos}</div>
					<div class="col s4">
						<div id="${usuario.id}" class="row opciones">
							<div class="col s12 m3 l4">
								<a class="modal-trigger mostrar-usuario material-icons"
									href="#modal1">visibility</a>
							</div>
							<div class="col s12 m3 l4">
								<a href="/qveo/usuario/edit/${usuario.id}"><i
									class="material-icons editar">edit</i></a>
							</div>
							<div class="col s12 m6 l4">
								<a href="#" class="material-icons eliminar-usuario">delete</a>
							</div>
						</div>



					</div>
				</div>
			</c:forEach>
			<!-- Modal Structure -->
			<div id="modal1" class="modal">
				<div id="contenido" class="modal-content">
					<div class="row">
						<div class="col s12 m12 l5"></div>
						<div class="col s12 m12 l7"></div>
					</div>
				</div>
				<!-- 				<div class="modal-footer"> -->
				<!-- 					<a href="#" class="modal-close waves-effect waves-green btn-flat">Cerrar</a> -->
				<!-- 				</div> -->
			</div>
		</div>
	</main>
	<footer class="page-footer">
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

			let id = event.target.parentNode.parentNode.getAttribute('id');
			console.log(event.target.parentNode.parentNode.getAttribute('id'));
			mostrarUsuario(id);

		});
		
		$(".eliminar-usuario").click(function(event) {

			let id = event.target.parentNode.parentNode.getAttribute('id');
			console.log(event.target.parentNode.parentNode.getAttribute('id'));
			event.target.parentNode.parentNode.parentNode.parentNode.remove();
			borrarUsuario(id);

		});

	});

	function mostrarUsuario(id) {

		$
				.ajax({
					url : '/qveo/ajax/usuario/' + id,
					success : function(data) {

						console.log(data.foto);
						let rutaFoto = data.foto;
						let contenido = document.getElementById('contenido');
						let cajas = document.getElementById('contenido').childNodes[1]
								.querySelectorAll('div');

						cajas[0].innerHTML = '';
						cajas[0].innerHTML = '<img width="100%" src="${pageContext.request.contextPath}'
								+ data.foto + '">';

						//Formateamos fechas
						let fechaNa = new Date(data.fechaNacimiento);
						let fechaAl = new Date(data.fechaAlta);

						let datos = {
							identificador : data.id,
							nombre : data.nombre,
							apellidos : data.apellidos,
							email : data.email,
							nacimiento : fechaNa.toLocaleDateString(),
							password : data.password,
							sexo : data.sexo,
							alta : fechaAl.toLocaleDateString()
						};

						cajas[1].innerHTML = '';
						let fila = document.createElement('div');
						fila.classList.add('row');
						//let titulo = document.createElement('h3');
						//let textoTitulo = document.createTextNode('Usuario con id: '+data.id);
						//titulo.appendChild(textoTitulo);
						//fila.appendChild(titulo);

						for ( const prop in datos) {
							let contenedor = document.createElement('div');
							contenedor.classList.add('col', 's4', 'cabeceras');
							let encabezado = document
									.createTextNode(prop + ':');
							contenedor.appendChild(encabezado);
							let contenedor2 = document.createElement('div');
							contenedor2.classList.add('col', 's8', 'cuerpo');
							let valor = document.createTextNode(datos[prop]);
							contenedor2.appendChild(valor);
							fila.appendChild(contenedor);
							fila.appendChild(contenedor2);

						}
						//Agregamos todo al segundo row
						cajas[1].appendChild(fila);

						console.log("SUCCESS : ", data);

					},
					error : function(e) {

						console.log("ERROR : ", e);

					}
				});

	}
	

	function borrarUsuario(id) {
		$
			.ajax({
			url : '/qveo/ajax/usuario/delete/' + id,
			success : function(data) {
		
				console.log("SUCCESS : ", data);
			},
			error : function(e) {

				console.log("ERROR : ", e);
			}
		});

	}
</script>

</html>