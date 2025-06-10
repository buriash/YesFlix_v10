<%@page import="com.ipartek.modelo.dto.Usuario"%>
<%@page import="com.ipartek.modelo.I_Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Cancion"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

	Usuario usuarioSession= new Usuario(); 
	if(session.getAttribute(I_Constantes.S_ATR_USUARIO)!=null){
		usuarioSession=(Usuario)session.getAttribute(I_Constantes.S_ATR_USUARIO);
	}
	
	//solo entra si es 1 o 2
	if(usuarioSession.getFK_rol()!= 1 && usuarioSession.getFK_rol()!= 2 ){
		response.sendRedirect(I_Constantes.JSP_INDEX);
	}


	List<Cancion> listaCanciones= new ArrayList<>();
	if(request.getAttribute(I_Constantes.ATR_LISTA_CANCIONES)!=null){
		listaCanciones=(List<Cancion>)request.getAttribute(I_Constantes.ATR_LISTA_CANCIONES);
	}

%>


<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>EuskoDenda</title>
<link rel="stylesheet" href="styles/style_multimedia.css">
<link rel="stylesheet" href="styles/style.css">
</head>
<body>

	<%@include file="includes/cabecera.jsp"%>

	<%@include file="includes/menu.jsp"%>

	<main>
		<section>
			<h2>Listado de Películas</h2>

			<table border="1">
			    <thead>
			        <tr>
			            <th>ID</th>
			            <th>Película</th>
			            <th>Duración</th>
			            <th>Descripción</th>
			            <th>Estilo</th>
			            <th>Usuario</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<% for(Cancion elem:listaCanciones){%>
			        <tr>
			            <td><%=elem.getId_cancion()%></td>
			            <td><%=elem.getTitulo()%></td>
			            <td><%=elem.getEnlace()%></td>
			            <td><%=elem.getDescripcion_cancion()%></td>
			            <td><%=elem.getEstilo_cancion()%></td>
			            <td><%=elem.getUsuario()%></td>
			        </tr>
			        <%}%>
			    </tbody>
			</table>
			
			
		</section>
	</main>

	<%@include file="includes/pie.jsp"%>

</body>
</html>