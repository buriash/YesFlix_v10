package com.ipartek.login;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Constantes;
import com.ipartek.modelo.dto.Cancion;
import com.ipartek.modelo.dto.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ValidarUsuario")
public class ValidarUsuario extends HttpServlet implements I_Constantes {
	private static final long serialVersionUID = 1L;

	public ValidarUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Funcion para validacion del usuario
	 * 
	 * funciona por el metodo get, pero esta adaptada a que funcione con el post tambien
	 * 
	 * Esta funcion recibira dos parametros del formulario:
	 * <ul>
	 * 		<li>p_usuario: el nombre del usuario</li>
	 * 		<li>p_pass: el password</li>
	 * </ul>
	 * 
	 * funcionamiento:
	 * comprobaremos si el usuario y la contraseña son correctos y el usuario tiene
	 * permiso para poder entrar a la siguiente pagina. solo podran entrar usuarios 
	 * con Rol de usuario y de admin. los baneados y los bloqueados no podran
	 * 
	 * si un usuario existe en la BD y se introduce el pass 3 veces mal se le bloquea.
	 * solo se le bloqueara si no esta baneado
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String ruta = "";
		
		// recibir los datos
		String usuario = "";
		if (request.getParameter("p_usuario") != null) {
			usuario = request.getParameter("p_usuario");
		}

		String pass = "";
		if (request.getParameter("p_pass") != null) {
			pass = request.getParameter("p_pass");
		}

		Usuario usu = new Usuario(0, usuario, pass, 0);

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		// comprobar en la BD si ese usuario con esa contraseña existe
		//  me devuelva el usuario vacio si no esta validado,
		// relleno menos el pass si existe, null si algo fallo
		Usuario UsuarioReal = db.comprobarSiExisteUsuario(usu, con);

		// comprobacion de bloqueo
		HashMap<String, Integer> listaPosiblesBloqueos = new HashMap<String, Integer>();
		
		if (session.getAttribute(S_LISTA_BLOQUEOS)!=null) {
			listaPosiblesBloqueos=(HashMap<String, Integer>) session.getAttribute("s_lista_posibles_bloqueos");
		}

		//aqui entra si el usuario no existe, o si el usuario y el pass no coinciden, o si 
		//coincide, pero esta bloqueado o baneado
		if (UsuarioReal.getId_usuario() == 0) {
			if (listaPosiblesBloqueos.containsKey(usuario)) {
				int intentos = listaPosiblesBloqueos.get(usuario);

				listaPosiblesBloqueos.put(usuario, intentos + 1);
			} else {
				listaPosiblesBloqueos.put(usuario, 1);
			}
		}

		session.setAttribute(S_LISTA_BLOQUEOS, listaPosiblesBloqueos);
		
		if (listaPosiblesBloqueos.get(usuario)!=null) {
			if (   listaPosiblesBloqueos.get(usuario) == 3    ) {
				// bloquear el usuario
				// seleccionar si existe en la BD un usuario con ese nombre, obtener su id,
				// nombre y FK
				Usuario usutemp = db.obtenerIdyFkPorNombre(usuario, con);
	
				System.out.println("entra a bloquear");
				
				// si no esta baneado, se bloquea
				if(usutemp.getFK_rol()!=3) {
					db.bloquearUsuarioPorId(usutemp.getId_usuario(), con);
					
					System.out.println("bloquea");
				}
	
			}
		}
		
		if (listaPosiblesBloqueos.get(usuario)==null) {
			ruta= JSP_INDEX;
		}

		List<Cancion> listaCanciones= new ArrayList<>();
		if (UsuarioReal.getId_usuario()>0) {
			listaCanciones=db.obtenerCancionesUsuario(UsuarioReal.getId_usuario(), con);
		}
		

		db.desconectar(con);

		// si el usuarioreal tiene id>0 comprobar su FK:
		// si el FK es 1 ir a pagina de admin: aun no lo tenemos
		// si el FK es 2 ir a la pagina de gestion de ese usuario
		// si es 3 ir otra vez al login
		// si es 4 ir otra vez al login
		// si el usuarioreal tiene id= 0, mandamos a index. es que no lo encontro
		
		if (UsuarioReal.getId_usuario() > 0) {

			switch (UsuarioReal.getFK_rol()) {
			case 1:
				ruta = JSP_GESTION;
				session.setAttribute(S_ATR_USUARIO, UsuarioReal);
				session.removeAttribute(S_LISTA_BLOQUEOS);
				break;
			case 2:
				ruta = JSP_GESTION;
				session.setAttribute(S_ATR_USUARIO, UsuarioReal);
				session.removeAttribute(S_LISTA_BLOQUEOS);
				break;
			case 3:
				ruta = JSP_INDEX;
				break;
			case 4:
				ruta = JSP_INDEX;
				break;
			default:
				ruta = JSP_INDEX;
				break;
			}

		} else {
			ruta = JSP_INDEX;
		}

		request.setAttribute(ATR_LISTA_CANCIONES, listaCanciones);

		request.getRequestDispatcher(ruta).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
