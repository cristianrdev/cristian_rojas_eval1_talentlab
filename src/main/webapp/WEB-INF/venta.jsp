<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

<style>
table, th, td {
  border: 1px solid black;
</style>

<meta charset="ISO-8859-1">
<title>Panel de Ventas</title>
</head>
<body>
<h1>AGREGAR VENTA</h1>
					<a href="/">Atrás</a>
				<form:form action="/venta/crear" method="post" modelAttribute="venta">
					<form:label path="id_usuario">ID Usuario:</form:label>
					<form:input type="text" path="id_usuario" /><br>
					
					<form:label path="id_producto">ID Producto:</form:label>
					<form:input type="text" path="id_producto" /><br>
					
					<form:label path="direccion_envio">Dirección Envío:</form:label>
					<form:input type="text" path="direccion_envio" /><br>

					<form:label path="cantidad">Cantidad:</form:label>
					<form:input type="text" path="cantidad" /><br>

					<form:label path="total">Total:</form:label>
					<form:input type="text" path="total" /><br>


					<input type="submit" value="Generar Venta">
				</form:form>
				<br>
				<c:out value="${error}"></c:out>
				<br>
				
				
				<a href="/">Atrás</a>
				<h1>Lista Productos</h1>
				<table>

					<thead>
						<tr>
							<th>#</th>
							<th>ID Usuario</th>
							<th>ID Producto</th>
							<th>Dirección envío</th>
							<th>Cantidad</th>
							<th>Total</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="venta" items="${lista_ventas}">

							<tr>
								<td>
									<c:out value="${venta.id}" />
								</td>
							
								<td>
									<c:out value="${venta.id_usuario}" />
								</td>
								<td>
									<c:out value="${venta.id_producto}" />
								</td>
								<td>
									<c:out value="${venta.direccion_envio}" />
								</td>
								<td>
									<c:out value="${venta.cantidad}" />
								</td>
								<td>
									<c:out value="${venta.total}" />
								</td>


								<td> <a href="/venta/actualizar/${venta.id}">Editar</a>								
									<form action="/venta/eliminar/${venta.id}" method="POST">
										<input type="hidden" name="_method" value="delete" >
										<input type="submit" value="Eliminar">
									</form>	
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
</body>
</html>