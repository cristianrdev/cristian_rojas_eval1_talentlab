
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

					


					<h3>Registrese acá:</h3>
					<form:form action="/usuario/crear" method="post" modelAttribute="usuario" class="forms">

						<form:label path="nombre">Nombre:</form:label>
						<form:input type="text" path="nombre" /><br>

						<form:label path="apellido">Apellido:</form:label>
						<form:input type="text" path="apellido" /><br>

						<form:label path="email">Email:</form:label>
						<form:input type="text" path="email" /><br>

						<form:label path="password">Contraseña:</form:label>
						<form:input type="password" path="password" /><br>

						<form:label type="password" path="passwordConfirmation">Confirme Contraseña:</form:label>
						<form:input type="password" path="passwordConfirmation" /><br>

						<input type="submit" value="Crear Usuario">
						<p>
							<div class="text-danger"><c:out value="${error}" /></div>
							
						</p>
					</form:form>

				</div>
			</body>

			</html>