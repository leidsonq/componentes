package br.com.o.q.leidson.componentes.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.o.q.leidson.componentes.domain.Categoria;
import br.com.o.q.leidson.componentes.domain.Componente;
import br.com.o.q.leidson.componentes.domain.Conjunto;
import br.com.o.q.leidson.componentes.domain.FabricanteModelo;
import br.com.o.q.leidson.componentes.domain.SubConjunto;
import br.com.o.q.leidson.componentes.domain.Usuario;
import br.com.o.q.leidson.componentes.domain.enums.Perfil;
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
	
	@Autowired
	BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() {

		Categoria cat1 = new Categoria(null, "ELÉTRICO");
		Categoria cat2 = new Categoria(null, "MECÂNICO");

		Componente comp1 = new Componente(null, "FUSO ESFÉRICO EIXO X", "531.01.3066", null, null, null, null, cat2, true);
		Componente comp2 = new Componente(null, "SERVOMOTOR FANUC A06B-0269-B400", "531.02.3325", null, null, null, null, cat1, false);
		Componente comp3 = new Componente(null, "SERVOMOTOR SIEMENS", "531.02.2575", null, null, null, null, cat1, true);
		Componente comp4 = new Componente(null, "ROLAMENTO DE CONTATO ANGULAR", "531.01.5175", null, null, null, null, cat2, false);

		cat1.getComponentes().addAll(Arrays.asList(comp2, comp3));
		cat2.getComponentes().addAll(Arrays.asList(comp1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		Conjunto conj1 = new Conjunto(null, "EIXO X", null);
		Conjunto conj2 = new Conjunto(null, "MAGAZINE DE FERRAMENTAS", null);
		Conjunto conj3 = new Conjunto(null, "EIXO ÁRVORE", null);
		Conjunto conj4 = new Conjunto(null, "EIXO X", null);
		Conjunto conj5 = new Conjunto(null, "EIXO Y", null);

		conj1.getComponentes().addAll(Arrays.asList(comp1, comp2));
		conj2.getComponentes().addAll(Arrays.asList(comp3));
		conj3.getComponentes().addAll(Arrays.asList(comp2));
		conj4.getComponentes().addAll(Arrays.asList(comp1, comp2));
		conj5.getComponentes().addAll(Arrays.asList(comp1, comp2, comp3));

		SubConjunto subConj1 = new SubConjunto(null, "FUSO ÁRVORE", "531.01.7006");
		SubConjunto subConj2 = new SubConjunto(null, "ACIONAMENTO FANUC", "531.02.2323");

		subConj1.getConjuntos().addAll(Arrays.asList(conj3));
		subConj1.getComponentes().addAll(Arrays.asList(comp4));
		conj3.getSubConjunto().addAll(Arrays.asList(subConj1));
		
		subConj2.getConjuntos().addAll(Arrays.asList(conj5));
		subConj2.getComponentes().addAll(Arrays.asList(comp4, comp3));
		conj5.getSubConjunto().addAll(Arrays.asList(subConj2));

		FabricanteModelo fabMod1 = new FabricanteModelo(null, "HELLER", "MCI28.1");
		FabricanteModelo fabMod2 = new FabricanteModelo(null, "HELLER", "MCI16.1");

		fabMod1.getConjuntos().addAll(Arrays.asList(conj1, conj2, conj3, conj4, conj5));
		fabMod2.getConjuntos().addAll(Arrays.asList(conj4, conj5));

		conj1.setFabricanteModelo(fabMod1);
		conj2.setFabricanteModelo(fabMod1);
		conj3.setFabricanteModelo(fabMod1);
		conj4.setFabricanteModelo(fabMod2);
		conj5.setFabricanteModelo(fabMod2);

		fabricanteModeloRepository.saveAll(Arrays.asList(fabMod1, fabMod2));
		conjuntoRepository.saveAll(Arrays.asList(conj1, conj2, conj3, conj4, conj5));
		subConjuntoRepository.saveAll(Arrays.asList(subConj1, subConj2));

		comp1.getConjuntos().addAll(Arrays.asList(conj1, conj4, conj5));
		comp2.getConjuntos().addAll(Arrays.asList(conj1, conj3, conj4, conj5));
		comp3.getConjuntos().addAll(Arrays.asList(conj2, conj5));
		comp3.getSubConjuntos().addAll(Arrays.asList(subConj2));
		comp4.getSubConjuntos().addAll(Arrays.asList(subConj1, subConj2));

		componenteRepository.saveAll(Arrays.asList(comp1, comp2, comp3, comp4));
		
		Usuario usu = new Usuario(null, "Leidson Quirino de Oliveira", "leidsonoliveira@yahoo.com.br", pe.encode("123"));
		usu.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Usuario usu1 = new Usuario(null, "Michele Oliveira", "michele_gomes7l@hotmail.com", pe.encode("321"));
		usu1.getTelefones().addAll(Arrays.asList("274343434", "98383934"));
		usu1.addPerfil(Perfil.ADMIN);
		usuarioRepository.saveAll(Arrays.asList(usu, usu1));

	}

}
