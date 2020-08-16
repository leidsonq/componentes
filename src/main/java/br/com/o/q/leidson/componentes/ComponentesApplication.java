package br.com.o.q.leidson.componentes;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.o.q.leidson.componentes.domain.Categoria;
import br.com.o.q.leidson.componentes.domain.Componente;
import br.com.o.q.leidson.componentes.domain.Conjunto;
import br.com.o.q.leidson.componentes.domain.SubConjunto;
import br.com.o.q.leidson.componentes.repositories.CategoriaRepository;
import br.com.o.q.leidson.componentes.repositories.ComponenteRepository;
import br.com.o.q.leidson.componentes.repositories.ConjuntoRepository;
import br.com.o.q.leidson.componentes.repositories.SubConjuntoRepository;

@SpringBootApplication
public class ComponentesApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ComponenteRepository componenteRepository;

	@Autowired
	ConjuntoRepository conjuntoRepository;

	@Autowired
	SubConjuntoRepository subConjuntoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ComponentesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Elétrico");
		Categoria cat2 = new Categoria(null, "Mecânico");

		Componente comp1 = new Componente(null, "FUSO ESFÉRICO EIXO X", null, null, null, null, null, cat2);
		Componente comp2 = new Componente(null, "SERVOMOTOR FANUC A06B-0269-B400", null, null, null, null, null, cat1);
		Componente comp3 = new Componente(null, "SERVOMOTOR SIEMENS", null, null, null, null, null, cat1);
		Componente comp4 = new Componente(null, "ROLAMENTO DE CONTATO ANGULAR", null, null, null, null, null, cat2);

		cat1.getComponentes().addAll(Arrays.asList(comp2, comp3));
		cat2.getComponentes().addAll(Arrays.asList(comp1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		Conjunto conj1 = new Conjunto(null, "Eixo X", null);
		Conjunto conj2 = new Conjunto(null, "Magazine de Ferramentas", null);
		Conjunto conj3 = new Conjunto(null, "Eixo Árvore", null);

		conj1.getComponentes().addAll(Arrays.asList(comp1, comp2));
		conj2.getComponentes().addAll(Arrays.asList(comp3));
		
		SubConjunto subConj1 = new SubConjunto(null, "Fuso Árvore", "531.01.7006");
		
		subConj1.getConjuntos().addAll(Arrays.asList(conj3));
		subConj1.getComponentes().addAll(Arrays.asList(comp4));
		conj3.getSubConjunto().addAll(Arrays.asList(subConj1));

		conjuntoRepository.saveAll(Arrays.asList(conj1, conj2, conj3));
		subConjuntoRepository.saveAll(Arrays.asList(subConj1));
		
		comp1.getConjuntos().addAll(Arrays.asList(conj1));
		comp2.getConjuntos().addAll(Arrays.asList(conj1));
		comp3.getConjuntos().addAll(Arrays.asList(conj2));
		comp4.getSubConjuntos().addAll(Arrays.asList(subConj1));

		componenteRepository.saveAll(Arrays.asList(comp1, comp2, comp3, comp4));

		

	}

}
