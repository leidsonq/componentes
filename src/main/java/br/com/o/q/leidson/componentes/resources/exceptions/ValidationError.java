package br.com.o.q.leidson.componentes.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();



	public ValidationError(Long timestamp, Integer status, String msg, String message, String path) {
		super(timestamp, status, msg, message, path);
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldNome, String message) {
		erros.add(new FieldMessage(fieldNome, message));
	}

}
