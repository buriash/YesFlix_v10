package com.ipartek.canciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Constantes;
import com.ipartek.modelo.dto.Cancion;
import com.ipartek.modelo.dto.Usuario;

@WebServlet("/InsertarCancion")
public class InsertarCancion extends HttpServlet implements I_Constantes {
	private static final long serialVersionUID = 1L;

	public InsertarCancion() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recibir los datos
		String titulo = "";
		if (request.getParameter("p_titulo") != null) {
			titulo = request.getParameter("p_titulo");
		}

		String enlace = "";
		if (request.getParameter("p_enlace") != null) {
			enlace = request.getParameter("p_enlace");
		}
		
		String estilo = "";
		if (request.getParameter("p_estilo_cancion") != null) {
			estilo = request.getParameter("p_estilo_cancion");
		}
		
		String descripcion = "";
		if (request.getParameter("p_descripcion_cancion") != null) {
			descripcion = request.getParameter("p_descripcion_cancion");
		}
		
		HttpSession session = request.getSession();
		int id_usuario_logueado=0;
		if (session.getAttribute(S_ATR_USUARIO)!=null) {
			id_usuario_logueado=((Usuario)session.getAttribute(S_ATR_USUARIO)).getId_usuario();
		}	
		
		Cancion cancion = new Cancion(0, titulo, enlace, estilo, descripcion, id_usuario_logueado, "");
		
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		db.insertarCancion(cancion, con);
		
		db.desconectar(con);
		
		request.getRequestDispatcher("MenuGestion").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
