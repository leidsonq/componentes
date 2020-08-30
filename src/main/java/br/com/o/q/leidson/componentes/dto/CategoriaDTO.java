package br.com.o.q.leidson.componentes.dto;

import java.io.Serializable;

import br.com.o.q.leidson.componentes.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	
	public CategoriaDTO () {
	}
	
	public CategoriaDTO (Categoria obj) {
		id = obj.getId();
		descricao = obj.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	
	
}
