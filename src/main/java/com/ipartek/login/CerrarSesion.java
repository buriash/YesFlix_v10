package com.ipartek.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ipartek.modelo.I_Constantes;

@WebServlet("/CerrarSesion")
public class CerrarSesion extends HttpServlet implements I_Constantes{
	private static final long serialVersionUID = 1L;
 
    public CerrarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		
		session.invalidate();
		
		request.getRequestDispatcher(JSP_INDEX).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
