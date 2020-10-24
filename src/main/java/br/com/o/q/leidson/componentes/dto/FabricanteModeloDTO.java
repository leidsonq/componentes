package br.com.o.q.leidson.componentes.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.o.q.leidson.componentes.domain.FabricanteModelo;

public class FabricanteModeloDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "O campo fabricante não pode ser vazio!")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String fabricante;
	@NotEmpty(message = "O campo modelo não pode ser vazio!")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String modelo;

	public FabricanteModeloDTO() {

	}

	public FabricanteModeloDTO(FabricanteModelo obj) {
		id = obj.getId();
		fabricante = obj.getFabricante();
		modelo = obj.getModelo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
