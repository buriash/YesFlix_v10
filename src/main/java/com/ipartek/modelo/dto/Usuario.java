package com.ipartek.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	private int id_usuario;
	private String usuario;
	private String pass;
	private int FK_rol;
		
}
