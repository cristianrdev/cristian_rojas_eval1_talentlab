<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>TIENDA VIRTUAL</title>
				<!-- CSS only -->
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
					crossorigin="anonymous">

			</head>

			<body>
				<div class="container">
					<h1>BIENVENIDOS A LA TIENDA VIRTUAL</h1>

					<h1>Ingrese Acá</h1>




					<form action="/usuario/ingresar" method="POST" class="form col-4 m-3">
						<div class="row m-2">
							<label for="email" class="col-4">Email :</label>
							<input class="col-8" type="text" id="email" name="email" />

						</div>

						<div class="row m-2">
							<label for="password" class="col-4">Password :</label>
							<input class="col-8" type="password" id="password" name="password" />

						</div>

						<div class="row m-2">
							<div class="col-5"></div>
							<input class="col-3" type="submit" value="Entrar" />

						</div>




						<c:out value="${errorlogin}" />


					</form>

					<h4>Si no tiene una cuenta puede <a href="/usuario/registro">crearla acá</a>:</h4>

					<div class="row">
						<div class="col">
							<h1>Listado de ventas globales</h1>
							<table class="table">

								<thead>
									<tr>
										<th>ID Venta</th>
										<th>Nombre Cliente</th>
										<th>ID Cliente</th>
										<th>Dirección envío</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="venta" items="${lista_ventas}">

										<tr>
											<td>
												<c:out value="${venta.id}" />
											</td>
											<td>
												<c:out value="${venta.usuario.nombre}  ${venta.usuario.apellido}" />
											</td>
											<td>
												<c:out value="${venta.usuario.id}" />
											</td>


											<td>
												<c:out value="${venta.direccion_envio}" />
											</td>



										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="col">
							<h1>Lista Productos Disponibles</h1>
							<table class="table">

								<thead>
									<tr>
										<th>ID Producto</th>
										<th>Nombre</th>
										<th>Precio</th>
										<th>Caracteristica</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="producto" items="${lista_productos}">

										<tr>
											<td>
												<c:out value="${producto.id}" />
											</td>
											<td>
												<c:out value="${producto.nombre}" />
											</td>
											<td>
												<c:out value="${producto.precio}" />
											</td>
											<td>
												<c:out value="${producto.caracteristica}" />
											</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>








				</div>
			</body>

			</html>