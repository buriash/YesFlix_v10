package com.ipartek.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {
	
	private int id_pelicula;
	private String pelicula;
	private String duracion;
	private String descripcion_pelicula;
	private String estilo_pelicula;
	private int FK_usuario;
	
	private String usuario;
	
}
