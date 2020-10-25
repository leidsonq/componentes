package br.com.o.q.leidson.componentes.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.o.q.leidson.componentes.domain.Categoria;
import br.com.o.q.leidson.componentes.domain.Componente;

@Repository
public interface ComponenteRepository extends JpaRepository<Componente, Integer>{

	@Transactional (readOnly =true)
	@Query("SELECT DISTINCT obj FROM Componente obj INNER JOIN obj.categoria cat WHERE obj.descricao LIKE %:descricao% AND cat IN :categoria")
	Page<Componente> search (@Param("descricao") String descricao, @Param("categoria") List<Categoria> categorias, Pageable pageRequest);
	
	
}
