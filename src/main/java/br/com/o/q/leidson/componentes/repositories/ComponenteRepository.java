package br.com.o.q.leidson.componentes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.o.q.leidson.componentes.domain.Componente;

@Repository
public interface ComponenteRepository extends JpaRepository<Componente, Integer>{

	
	
	
}
