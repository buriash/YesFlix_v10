<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>EuskoDenda</title>
<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/style_login.css">

</head>
<body>

	<%@include file="includes/cabecera.jsp"%>

	<main>
		<section>
			
			<form method="get" action="ValidarUsuario">
				<div>
					<label>Usuario</label>
					<input type="text" name ="p_usuario">
				</div>
				
				<div>
					<label>Password</label>
					<input type="text" name ="p_pass">
				</div>
				
				<div id="controles_login">
					<a href="#">Registrarse</a>
					<input type="submit" value="Entrar"> 
				</div>
				
				
			</form>
			
		</section>
	</main>

	<%@include file="includes/pie.jsp"%>

</body>
</html>