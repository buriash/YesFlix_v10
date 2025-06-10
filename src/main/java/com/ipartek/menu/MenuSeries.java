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
import com.ipartek.modelo.dto.Serie;


@WebServlet("/MenuSeries")
public class MenuSeries extends HttpServlet implements I_Constantes {
	private static final long serialVersionUID = 1L;
       

    public MenuSeries() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DB_Helper  db = new DB_Helper();
		Connection con = db.conectar();
		
		List<Serie> listaSeries= db.obtenerTodasSeries(con);
		
		db.desconectar(con);
		
		request.setAttribute(ATR_LISTA_SERIES, listaSeries);
		
		request.getRequestDispatcher(JSP_SERIES).forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
