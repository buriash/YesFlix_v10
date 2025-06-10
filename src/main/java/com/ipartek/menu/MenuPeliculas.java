package com.ipartek.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Constantes;
import com.ipartek.modelo.dto.Pelicula;


@WebServlet("/MenuPeliculas")
public class MenuPeliculas extends HttpServlet implements I_Constantes {
	private static final long serialVersionUID = 1L;
       

    public MenuPeliculas() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DB_Helper  db = new DB_Helper();
		Connection con = db.conectar();
		
		List<Pelicula> listaPeliculas= db.obtenerTodasPeliculas(con);
			
		db.desconectar(con);
		
		request.setAttribute(ATR_LISTA_PELICULAS, listaPeliculas);

		request.getRequestDispatcher(JSP_PELICULAS).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
