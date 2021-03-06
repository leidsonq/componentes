package br.com.o.q.leidson.componentes.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conjunto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String codigoD;

	@ManyToMany(mappedBy = "conjuntos")
	private List<Componente> componentes = new ArrayList<>();

	@ManyToMany(mappedBy = "conjuntos")
	private List<SubConjunto> subConjunto = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn (name="fabricanteModelo_id")
	private FabricanteModelo fabricanteModelo;

	public Conjunto() {

	}

	public Conjunto(Integer id, String descricao, String codigoD) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.codigoD = codigoD;
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

	public String getCodigoD() {
		return codigoD;
	}

	public void setCodigoD(String codigoD) {
		this.codigoD = codigoD;
	}

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

	public List<SubConjunto> getSubConjunto() {
		return subConjunto;
	}

	public void setSubConjunto(List<SubConjunto> subConjunto) {
		this.subConjunto = subConjunto;
	}
	
	

	public FabricanteModelo getFabricanteModelo() {
		return fabricanteModelo;
	}

	public void setFabricanteModelo(FabricanteModelo fabricanteModelo) {
		this.fabricanteModelo = fabricanteModelo;
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
		Conjunto other = (Conjunto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (codigoD!=null) {
			builder.append(codigoD);
			builder.append("- ");
		}	

		builder.append(descricao);
		builder.append("\n");
		for (Componente cp: getComponentes()) {
			builder.append("   ");
			builder.append(cp.toString());
			builder.append("\n");
		}
		for (SubConjunto sub : getSubConjunto()) {
			builder.append("   ");
			builder.append(sub.toString());
			builder.append("\n");
		}
		return builder.toString();
		
	}
}
