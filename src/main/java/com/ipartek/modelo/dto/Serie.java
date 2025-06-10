package com.ipartek.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Serie {
	
	private int id_serie;
	private String serie;
	private int num_temporadas;
	private String descripcion_serie;
	private int FK_usuario;
	
	private String usuario;
	
}
