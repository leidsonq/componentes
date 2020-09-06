package br.com.o.q.leidson.componentes.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fieldDescricao;
	private String message;
	
	public FieldMessage () {
	}

	public FieldMessage(String fieldDescricao, String message) {
		super();
		this.fieldDescricao = fieldDescricao;
		this.message = message;
	}

	public String getFieldDescricao() {
		return fieldDescricao;
	}

	public void setFieldDescricao(String fieldDescricao) {
		this.fieldDescricao = fieldDescricao;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
