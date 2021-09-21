<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<!DOCTYPE html>
			<html>

			<head>
				<!-- CSS only -->
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
					crossorigin="anonymous">



				<meta charset="UTF-8">
				<title>Panel de Ventas</title>
			</head>

			<body>
				<div class="container">


					<h1>Registrar Venta ${usuario_activo.nombre} ${usuario_activo.apellido}</h1>


					<ul class="nav">
						<li class="nav-item">
							<a class="nav-link" href="/producto">Gestionar Productos</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/usuario/actualizar/${usuario_activo.id}">Editar mis datos</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="usuario/logout">Cerrar Sesión</a>
						</li>

					</ul>



					<div class="row">


						<div class="col-4">


							<form:form action="/venta/crear" method="post" modelAttribute="venta" class="form">
								<form:input type="hidden" path="usuario" value="${usuario_activo.id}" />
								<form:label path="direccion_envio">Dirección Envío:</form:label>
								<form:input type="text" path="direccion_envio" /><br>
								<label for="productos">Seleccione Los Productos:</label>
								<select class="form-select" name="productos" multiple>
									<c:forEach var="producto" items="${lista_productos}">
										<option value="<c:out value=" ${producto.id}" />">
										<c:out value="${producto.nombre}" /> $
										<c:out value="${producto.precio}" />
										</option>
									</c:forEach>
								</select>

								<input class="m-2" type="submit" value="Generar Venta">
							</form:form>
						</div>
					</div>
					<c:out value="${error}"></c:out>



					<h1>Mis compras</h1>
					<table class="table">

						<thead>
							<tr>
								<th>ID Venta</th>
								<th>Dirección envío</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="venta" items="${lista_de_compras}">

								<tr>
									<td>
										<c:out value="${venta.id}" />
									</td>

									<td>
										<c:out value="${venta.direccion_envio}" />
									</td>



								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</body>

			</html>