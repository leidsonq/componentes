package br.com.o.q.leidson.componentes.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Componente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String codigoD;
	private String codComplementar;
	private String descricaoSemAcento;
	private String status;
	private String alocacao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "COMPONENTE_CONJUNTO", joinColumns = @JoinColumn(name = "componente_id"), inverseJoinColumns = @JoinColumn(name = "conjunto_id"))
	private List<Conjunto> conjuntos = new ArrayList<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "COMPONENTE_SUB_CONJUNTO", joinColumns = @JoinColumn(name = "componente_id"), inverseJoinColumns = @JoinColumn(name = "sub_conjunto_id"))
	private List<SubConjunto> subConjuntos = new ArrayList<>();

	public Componente() {

	}

	public Componente(Integer id, String descricao, String codigoD, String codComplementar, String descricaoSemAcento,
			String status, String alocacao, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.codigoD = codigoD;
		this.codComplementar = codComplementar;
		this.descricaoSemAcento = descricaoSemAcento;
		this.status = status;
		this.alocacao = alocacao;
		this.categoria = categoria;
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

	public String getCodComplementar() {
		return codComplementar;
	}

	public void setCodComplementar(String codComplementar) {
		this.codComplementar = codComplementar;
	}

	public String getDescricaoSemAcento() {
		return descricaoSemAcento;
	}

	public void setDescricaoSemAcento(String descricaoSemAcento) {
		this.descricaoSemAcento = descricaoSemAcento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAlocacao() {
		return alocacao;
	}

	public void setAlocacao(String alocacao) {
		this.alocacao = alocacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Conjunto> getConjuntos() {
		return conjuntos;
	}

	public void setConjuntos(List<Conjunto> conjuntos) {
		this.conjuntos = conjuntos;
	}

	public List<SubConjunto> getSubConjuntos() {
		return subConjuntos;
	}

	public void setSubConjuntos(List<SubConjunto> subConjuntos) {
		this.subConjuntos = subConjuntos;
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
		Componente other = (Componente) obj;
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
		builder.append(codigoD);
		builder.append("- ");
		builder.append(descricao);
		return builder.toString();
	}
}
