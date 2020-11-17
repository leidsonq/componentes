package br.com.o.q.leidson.componentes.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.o.q.leidson.componentes.domain.Categoria;
import br.com.o.q.leidson.componentes.domain.Componente;
import br.com.o.q.leidson.componentes.domain.Conjunto;
import br.com.o.q.leidson.componentes.domain.FabricanteModelo;
import br.com.o.q.leidson.componentes.domain.SubConjunto;
import br.com.o.q.leidson.componentes.domain.Usuario;
import br.com.o.q.leidson.componentes.repositories.CategoriaRepository;
import br.com.o.q.leidson.componentes.repositories.ComponenteRepository;
import br.com.o.q.leidson.componentes.repositories.ConjuntoRepository;
import br.com.o.q.leidson.componentes.repositories.FabricanteModeloRepository;
import br.com.o.q.leidson.componentes.repositories.SubConjuntoRepository;
import br.com.o.q.leidson.componentes.repositories.UsuarioRepository;
@Service
public class DBService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ComponenteRepository componenteRepository;

	@Autowired
	ConjuntoRepository conjuntoRepository;

	@Autowired
	SubConjuntoRepository subConjuntoRepository;

	@Autowired
	FabricanteModeloRepository fabricanteModeloRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public void instantiateTestDatabase() {

		Categoria cat1 = new Categoria(null, "ELÉTRICO");
		Categoria cat2 = new Categoria(null, "MECÂNICO");

		Componente comp1 = new Componente(null, "FUSO ESFÉRICO EIXO X", null, null, null, null, null, cat2);
		Componente comp2 = new Componente(null, "SERVOMOTOR FANUC A06B-0269-B400", null, null, null, null, null, cat1);
		Componente comp3 = new Componente(null, "SERVOMOTOR SIEMENS", null, null, null, null, null, cat1);
		Componente comp4 = new Componente(null, "ROLAMENTO DE CONTATO ANGULAR", null, null, null, null, null, cat2);

		cat1.getComponentes().addAll(Arrays.asList(comp2, comp3));
		cat2.getComponentes().addAll(Arrays.asList(comp1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		Conjunto conj1 = new Conjunto(null, "EIXO X", null);
		Conjunto conj2 = new Conjunto(null, "MAGAZINE DE FERRAMENTAS", null);
		Conjunto conj3 = new Conjunto(null, "EIXO ÁRVORE", null);

		conj1.getComponentes().addAll(Arrays.asList(comp1, comp2));
		conj2.getComponentes().addAll(Arrays.asList(comp3));
		conj3.getComponentes().addAll(Arrays.asList(comp2));

		SubConjunto subConj1 = new SubConjunto(null, "FUSO ÁRVORE", "531.01.7006");

		subConj1.getConjuntos().addAll(Arrays.asList(conj3));
		subConj1.getComponentes().addAll(Arrays.asList(comp4));
		conj3.getSubConjunto().addAll(Arrays.asList(subConj1));

		FabricanteModelo fabMod1 = new FabricanteModelo(null, "HELLER", "MCI28.1");

		fabMod1.getConjuntos().addAll(Arrays.asList(conj1, conj2, conj3));

		conj1.setFabricanteModelo(fabMod1);
		conj2.setFabricanteModelo(fabMod1);
		conj3.setFabricanteModelo(fabMod1);

		fabricanteModeloRepository.saveAll(Arrays.asList(fabMod1));
		conjuntoRepository.saveAll(Arrays.asList(conj1, conj2, conj3));
		subConjuntoRepository.saveAll(Arrays.asList(subConj1));

		comp1.getConjuntos().addAll(Arrays.asList(conj1));
		comp2.getConjuntos().addAll(Arrays.asList(conj1, conj3));
		comp3.getConjuntos().addAll(Arrays.asList(conj2));
		comp4.getSubConjuntos().addAll(Arrays.asList(subConj1));

		componenteRepository.saveAll(Arrays.asList(comp1, comp2, comp3, comp4));
		
		Usuario usu = new Usuario(null, "Leidson Quirino de Oliveira", "leidsonoliveira@yahoo.com.br");
		usu.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		usuarioRepository.saveAll(Arrays.asList(usu));

	}

}
