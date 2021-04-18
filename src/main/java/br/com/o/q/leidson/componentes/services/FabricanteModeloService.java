package br.com.o.q.leidson.componentes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.o.q.leidson.componentes.domain.Componente;
import br.com.o.q.leidson.componentes.domain.Conjunto;
import br.com.o.q.leidson.componentes.domain.FabricanteModelo;
import br.com.o.q.leidson.componentes.dto.FabricanteModeloDTO;
import br.com.o.q.leidson.componentes.repositories.FabricanteModeloRepository;
import br.com.o.q.leidson.componentes.services.exceptions.DataIntegrityException;

@Service
public class FabricanteModeloService {
	
	@Autowired
	private FabricanteModeloRepository repo;
	
	@Autowired
	EmailService emailService;

	public FabricanteModelo find(Integer id) {
		FabricanteModelo fabMod = new FabricanteModelo();
		fabMod.setConjuntos(repo.findById(id).get().getConjuntos());
		fabMod.setFabricante(repo.findById(id).get().getFabricante());
		fabMod.setModelo(repo.findById(id).get().getModelo());
		
		Optional<FabricanteModelo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.o.q.leidson.componentes.services.exceptions.ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + FabricanteModelo.class.getName()));
		
		}
	
	public void EnviarDecomposicao (Integer FabModId, String email) {
		FabricanteModelo fab = find(FabModId);
		emailService.sendDecomposicao(fab, email);
	}
	
	
	public void EnviarEstrategicas (Integer FabModId, String email) {
		FabricanteModelo fab = find(FabModId);
		StringBuilder assunto = new StringBuilder();
		StringBuilder estrategicas = new StringBuilder();
		assunto.append("LISTA DE PEÇAS ESTRATÉGICAS: ");
		assunto.append(fab.getFabricante());
		assunto.append(" - ");
		assunto.append(fab.getModelo());
		
		for (Conjunto conj: fab.getConjuntos()) {
			System.out.println(conj.getComponentes());
			for (Componente comp: conj.getComponentes()) {
				if (comp.isEstrategico()) {
					estrategicas.append(comp.getCodigoD());
					estrategicas.append("- ");
					estrategicas.append(comp.getDescricao());
					estrategicas.append("\n");
				}
				
			}
		}
		
		emailService.sendEstrategicas(assunto, estrategicas, email);
	}

	public FabricanteModelo insert(FabricanteModelo obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public FabricanteModelo update(FabricanteModelo obj) {
		FabricanteModelo newObj = find(obj.getId());
		updateData (newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete (Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Modelo que possui componentes");
			
		}
	}
	
	public List<FabricanteModelo> findAll(){
		return repo.findAll();
	}
	
	public FabricanteModelo fromDTO (FabricanteModeloDTO objDto) {
		return new FabricanteModelo(objDto.getId(), objDto.getFabricante(),objDto.getModelo());
	}
	
	private void updateData (FabricanteModelo newObj, FabricanteModelo obj) {
		newObj.setFabricante(obj.getFabricante());
		newObj.setModelo(obj.getModelo());
	}
}
