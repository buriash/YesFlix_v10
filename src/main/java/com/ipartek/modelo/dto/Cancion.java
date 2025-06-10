package com.ipartek.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {

	private int id_cancion;
	private String titulo;
	private String enlace;
	private String estilo_cancion;
	private String descripcion_cancion;
	private int FK_usuario;
	
	private String usuario;
	
}
