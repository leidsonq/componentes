package br.com.o.q.leidson.componentes.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FabricanteModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fabricante;
	private String Modelo;

	@OneToMany(mappedBy = "fabricanteModelo")
	private List<Conjunto> conjuntos = new ArrayList<>();

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "decomposicao_id")
	private Decomposicao decomposicao;

	public FabricanteModelo() {

	}

	public FabricanteModelo(Integer id, String fabricante, String modelo, Decomposicao decomposicao) {
		super();
		this.id = id;
		this.fabricante = fabricante;
		Modelo = modelo;
		this.decomposicao = decomposicao;
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
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public List<Conjunto> getConjuntos() {
		return conjuntos;
	}

	public void setConjuntos(List<Conjunto> conjuntos) {
		this.conjuntos = conjuntos;
	}

	public Decomposicao getDecomposicao() {
		return decomposicao;
	}

	public void setDecomposicao(Decomposicao decomposicao) {
		this.decomposicao = decomposicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FabricanteModelo other = (FabricanteModelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
