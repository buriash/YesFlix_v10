package com.ipartek.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.modelo.dto.Cancion;
import com.ipartek.modelo.dto.Pelicula;
import com.ipartek.modelo.dto.Serie;
import com.ipartek.modelo.dto.Usuario;

public class DB_Helper implements I_Constantes {

	public Connection conectar() {
		Connection con = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(CONEXION, USUARIO, PASS);
			System.out.println("BASE DE DATOS CONECTADA");
		} catch (ClassNotFoundException e) {
			System.out.println("NO SE ENCONTRO EL DRIVER");
		} catch (SQLException e) {
			System.out.println("ERROR AL CONECTAR A LA BD");
		}

		return con;
	}

	public void desconectar(Connection con) {
		try {
			con.close();
			System.out.println("BASE DE DATOS DESCONECTADA");
		} catch (SQLException e) {
			System.out.println("NO SE PUDO DESCONECTAR");
		}
	}

	public List<Pelicula> obtenerTodasPeliculas(Connection con) {
		try {
			List<Pelicula> lista = new ArrayList<>();
			CallableStatement cstmt = con.prepareCall(SP_PELIS_OBTENER_TODAS);
			cstmt.execute();

			ResultSet rs = cstmt.getResultSet();

			while (rs.next()) {
				Pelicula peli = new Pelicula();

				peli.setId_pelicula(rs.getInt(PELICULAS_ID_PELICULA));
				peli.setPelicula(rs.getString(PELICULAS_PELICULA));
				peli.setDuracion(rs.getString(PELICULAS_DURACION));
				peli.setDescripcion_pelicula(rs.getString(PELICULAS_DESCRIPCION_PELICULA));
				peli.setEstilo_pelicula(rs.getString(PELICULAS_ESTILO_PELICULA));
				peli.setFK_usuario(rs.getInt(PELICULAS_FK_USUARIO));
				peli.setUsuario(rs.getString(PELICULAS_USUARIO));				
				lista.add(peli);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Serie> obtenerTodasSeries(Connection con) {
		try {
			List<Serie> lista = new ArrayList<>();
			CallableStatement cstmt = con.prepareCall(SP_SERIES_OBTENER_TODAS);
			cstmt.execute();

			ResultSet rs = cstmt.getResultSet();

			while (rs.next()) {
				Serie serie = new Serie();
			
				serie.setId_serie(rs.getInt(SERIES_ID_SERIE));
				serie.setSerie(rs.getString(SERIES_SERIE));
				serie.setNum_temporadas(rs.getInt(SERIES_NUM_TEMP));
				serie.setDescripcion_serie(rs.getString(SERIES_DESCRIPCION_SERIE));
				serie.setFK_usuario(rs.getInt(SERIES_FK_USUARIO));
				serie.setUsuario(rs.getString(SERIES_USUARIO));
				
				lista.add(serie);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Cancion> obtenerTodasCanciones(Connection con) {
		try {
			List<Cancion> lista = new ArrayList<>();
			CallableStatement cstmt = con.prepareCall(SP_CANCIONES_OBTENER_TODAS);
			cstmt.execute();

			ResultSet rs = cstmt.getResultSet();

			while (rs.next()) {
				Cancion canci = new Cancion();
				
				canci.setId_cancion(rs.getInt(CANCIONES_ID_CANCION));
				canci.setTitulo(rs.getString(CANCIONES_TITULO));
				canci.setEnlace(rs.getString(CANCIONES_ENLACE));
				canci.setEstilo_cancion(rs.getString(CANCIONES_ESTILO_CANCION));
				canci.setDescripcion_cancion(rs.getString(CANCIONES_DESCRIPCION_CANCION));
				canci.setUsuario(rs.getString(CANCIONES_USUARIO));
				
				lista.add(canci);
				
				System.out.println(canci);
			}
			
			System.out.println(lista);
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario comprobarSiExisteUsuario(Usuario usu, Connection con) {
		try {
			Usuario usuario= new Usuario();
														
			CallableStatement cstmt = con.prepareCall(SP_USUARIOS_VALIDAR);
				cstmt.setString(1, usu.getUsuario());
				cstmt.setString(2, usu.getPass());
			
			cstmt.execute();

			ResultSet rs = cstmt.getResultSet();

			int contador=0;
			while (rs.next()) {
				
				usuario.setId_usuario(rs.getInt(USUARIO_ID_USUARIO));
				usuario.setUsuario(rs.getString(USUARIO_USUARIO));
				usuario.setPass("");
				usuario.setFK_rol(rs.getInt(USUARIO_FK_ROL));
				contador++;
			}
			
			if(contador<2) {
				return usuario;
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario obtenerIdyFkPorNombre(String usu, Connection con) {
		try {
			Usuario usuario= new Usuario();
														
			CallableStatement cstmt = con.prepareCall(SP_USUARIOS_OBTENER_ID_POR_NOMBRE);
				cstmt.setString(1, usu);
			
			cstmt.execute();

			ResultSet rs = cstmt.getResultSet();

			int contador=0;
			while (rs.next()) {
				
				usuario.setId_usuario(rs.getInt(USUARIO_ID_USUARIO));
				usuario.setUsuario("");
				usuario.setPass("");
				usuario.setFK_rol(rs.getInt(USUARIO_FK_ROL));
				contador++;
			}
			
			if(contador<2) {
				return usuario;
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void bloquearUsuarioPorId(int id_usuario, Connection con) {
		try {
			CallableStatement cstmt = con.prepareCall(SP_USUARIOS_BLOQUEAR);
				cstmt.setInt(1, id_usuario);

			cstmt.execute();	
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

	public List<Cancion> obtenerCancionesUsuario(int id_usuario, Connection con) {

		try {
			List<Cancion> lista = new ArrayList<>();
			CallableStatement cstmt = con.prepareCall(SP_CANCIONES_POR_ID_USUARIO);
				cstmt.setInt(1, id_usuario);
			cstmt.execute();

			ResultSet rs = cstmt.getResultSet();

			while (rs.next()) {
				Cancion canci = new Cancion();
				
				canci.setId_cancion(rs.getInt(CANCIONES_ID_CANCION));
				canci.setTitulo(rs.getString(CANCIONES_TITULO));
				canci.setEnlace(rs.getString(CANCIONES_ENLACE));
				canci.setEstilo_cancion(rs.getString(CANCIONES_ESTILO_CANCION));
				canci.setDescripcion_cancion(rs.getString(CANCIONES_DESCRIPCION_CANCION));
				canci.setUsuario(rs.getString(CANCIONES_USUARIO));
				
				lista.add(canci);
				
				System.out.println(canci);
			}
			
			System.out.println(lista);
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	public void insertarCancion(Cancion cancion, Connection con) {

		try {
//			call sp_canciones_insertar('t', 'enkl', 'est', 'descr', fk_usu);
			CallableStatement cstmt = con.prepareCall(SP_CANCIONES_INSERTAR);
			
			cstmt.setString(1, cancion.getTitulo());
			cstmt.setString(2, cancion.getEnlace());
			cstmt.setString(3, cancion.getEstilo_cancion());
			cstmt.setString(4, cancion.getDescripcion_cancion());
			cstmt.setInt(5, cancion.getFK_usuario());
				
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

	}

	
}
