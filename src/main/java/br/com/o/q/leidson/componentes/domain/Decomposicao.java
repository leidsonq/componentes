package br.com.o.q.leidson.componentes.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Decomposicao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id
	private Integer Id;
	@JsonFormat (pattern = "dd/MM/yyyy HH:mm")
	private Date dataCricao;
	@JsonFormat (pattern = "dd/MM/yyyy HH:mm")
	private Date dataRevisao;
	private String titulo;
	
	@OneToOne (mappedBy = "decomposicao")
	private FabricanteModelo fabricanteModelo;
	
	public Decomposicao () {
		
	}

	public Decomposicao(Integer id, Date dataCricao, Date dataRevisao, String titulo,
			FabricanteModelo fabricanteModelo) {
		super();
		Id = id;
		this.dataCricao = dataCricao;
		this.dataRevisao = dataRevisao;
		this.titulo = titulo;
		this.fabricanteModelo = fabricanteModelo;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Date getDataCricao() {
		return dataCricao;
	}

	public void setDataCricao(Date dataCricao) {
		this.dataCricao = dataCricao;
	}

	public Date getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Decomposicao other = (Decomposicao) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	
	
}
