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
					<h1>Resumen de tu compra</h1>
					<table>

						<thead>
							<tr>
								<th>Nombre Producto</th>
								<th>Precio</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="venta" items="${lista_compras}">

								<tr>
									<td>
										<c:out value="${venta.nombre}" />
									</td>

									<td>
										$
										<c:out value="${venta.precio}" />
									</td>


								</tr>

							</c:forEach>

							<tr>
								<td>
									Total de tu compra: $
									<c:out value="${total_venta}" />
								</td>
							</tr>
						</tbody>
					</table>
					<h3></h3>
					<h4>Dirección de despacho:
						<c:out value="${direccion_despacho}" />
					</h4>
					<a href="/venta">finalizar</a>
				</div>
				
			</body>

			</html>