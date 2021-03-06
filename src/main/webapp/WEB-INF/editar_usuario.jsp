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
				<title>Editar Usuario</title>
			</head>

			<body>
				<div class="container">
					<h3>Edite sus datos</h3>

					<h4 class="text-danger">
						<c:out value="${error}"></c:out>
					</h4>




					<form:form action="/usuario/modificar" method="post" modelAttribute="usuario">
						<input type="hidden" name="_method" value="put">
						<form:hidden path="id" />

						<form:label path="nombre">Nombre:</form:label>
						<form:input type="text" path="nombre" /><br>

						<form:label path="apellido">Apellido:</form:label>
						<form:input type="text" path="apellido" /><br>

						<form:label path="email">Email:</form:label>
						<form:input type="text" path="email" /><br>


						<input type="submit" value="Modificar">
					</form:form>





				</div>
			</body>

			</html>