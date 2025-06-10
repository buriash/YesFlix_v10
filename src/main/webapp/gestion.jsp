<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Cancion"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.modelo.dto.Usuario"%>
<%@page import="com.ipartek.modelo.I_Constantes"%>
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
<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/style_gestion.css">
</head>
<body>

	<%@include file="includes/cabecera.jsp"%>

	<%@include file="includes/menu.jsp"%>

	<main>
		<section id="cabecera_forms">
			
			<form action="InsertarCancion" method="get">
				<label for="p_titulo">Título:</label>
				<input type="text" id="p_titulo" name="p_titulo" required><br>
				
				<label for="p_enlace">Enlace:</label>
				<input type="text" id="p_enlace" name="p_enlace" required><br>
				
				<label for="p_estilo_cancion">Estilo de Canción:</label>
				<input type="text" id="p_estilo_cancion" name="p_estilo_cancion" required><br>
				
				<label for="p_descripcion_cancion">Descripción:</label>
				<input type="text" id="p_descripcion_cancion" name="p_descripcion_cancion" required><br>
			
				<input type="submit" value="Insertar canción">
			</form>
			
			<form action="" method="get">
			
			    <label for="serie">Serie:</label>
			    <input type="text" id="serie" name="serie" required><br>
			
			    <label for="num_temporadas">Número de Temporadas:</label>
			    <input type="number" id="num_temporadas" name="num_temporadas" min="1" required><br>
			
			    <label for="descripcion_serie">Descripción:</label>
			    <input type="text" id="descripcion_serie" name="descripcion_serie" required><br>
			
			    <input type="submit" value="Insertar serie">

			</form>
			
			<form action="" method="get">
			    <label for="pelicula">Película:</label>
			    <input type="text" id="pelicula" name="pelicula" required><br>
			
			    <label for="duracion">Duración:</label>
			    <input type="text" id="duracion" name="duracion" required><br>
			
			    <label for="descripcion_pelicula">Descripción:</label>
			    <input type="text" id="descripcion_pelicula" name="descripcion_pelicula" required><br>
			
			    <label for="estilo_pelicula">Estilo:</label>
			    <input type="text" id="estilo_pelicula" name="estilo_pelicula" required><br>

			    <input type="submit" value="Insertar película">
			</form>
			
		</section>
		
		
		
		<section id="seccion tablas">
			<h2>Canciones</h2>
			<table border="1">
				<thead>
					<tr>
					    <th>ID</th>
					    <th>Título</th>
					    <th>Enlace</th>
					    <th>Estilo</th>
					    <th>Descripción</th>
					    <th>Opciones</th>
					</tr>
				</thead>
				<tbody>
				
					<% for(Cancion elem:listaCanciones){%>
			        <tr>
			            <td><%=elem.getId_cancion()%></td>
			            <td><%=elem.getTitulo()%></td>
			            <td><%=elem.getEnlace()%></td>
			            <td><%=elem.getEstilo_cancion()%></td>
			            <td><%=elem.getDescripcion_cancion()%></td>
			            <td>
					          <a href="/FrmCancionModificar?id=1">Modificar</a> |
					          <a href="/CancionBorrar?id=1">Eliminar</a>
					     </td>
			        </tr>
			        <%}%>
	
				</tbody>
			</table>
			
			<br>
			<hr>
			<br>	
			
			<h2>Listado de Series</h2>

			<table border="1">
			    <thead>
			        <tr>
			            <th>ID</th>
			            <th>Serie</th>
			            <th>Nº Temporadas</th>
			            <th>Descripción</th>
			            <th>Opciones</th>
			        </tr>
			    </thead>
			    <tbody>

			        <tr>
			            <td>1</td>
			            <td>Breaking Bad</td>
			            <td>5</td>
			            <td>Serie sobre un profesor de química convertido en narcotraficante.</td>
			            <td>
			                <a href="/FrmSerieModificar?id=1">Modificar</a> |
			                <a href="/SerieBorrar?id=1">Eliminar</a>
			            </td>
			        </tr>

			    </tbody>
			</table>
			
			<br>
			<hr>
			<br>
			
			<h2>Listado de Películas</h2>

			<table border="1">
			    <thead>
			        <tr>
			            <th>ID</th>
			            <th>Película</th>
			            <th>Duración</th>
			            <th>Descripción</th>
			            <th>Estilo</th>
			            <th>Opciones</th>
			        </tr>
			    </thead>
			    <tbody>
			        <!-- Ejemplo de fila -->
			        <tr>
			            <td>1</td>
			            <td>Inception</td>
			            <td>2h 28m</td>
			            <td>Un ladrón que roba secretos del subconsciente a través de los sueños.</td>
			            <td>Ciencia ficción</td>
			            <td>
			                <a href="/FrmPeliculaModificar?id=1">Modificar</a> |
			                <a href="/PeliculaBorrar?id=1">Eliminar</a>
			            </td>
			        </tr>
			        <!-- Más filas aquí -->
			    </tbody>
			</table>
			
			
			
			
		</section>
	</main>

	<%@include file="includes/pie.jsp"%>

</body>
</html>