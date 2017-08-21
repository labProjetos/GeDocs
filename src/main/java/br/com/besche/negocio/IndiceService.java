package br.com.besche.negocio;

import java.util.List;

import javax.inject.Inject;

import br.com.besche.modelo.Indice;
import br.com.besche.repository.IndiceRepository;

public class IndiceService {
	@Inject
	IndiceRepository repository;
	
	public void salvar(Indice objeto) throws Exception {
		for (Indice i : repository.findAll()) {
			if (i.getNome().equalsIgnoreCase(objeto.getNome())) {
				throw new Exception();
			}
		}
		repository.salvar(objeto);
	}
	
	public void alterar(Indice objeto) throws Exception {
		for (Indice i : repository.findAll()) {
			if (i.getNome().equalsIgnoreCase(objeto.getNome())) {
				throw new Exception();
			}
		}
		repository.alterar(objeto);
	}
	
	public void excluir(Indice objeto) throws Exception {
		if (objeto.getId() < 1 || !objeto.getIndexacao().isEmpty()) {
			throw new Exception();
		} else {
			repository.excluir(objeto);
		}
	}
	
	public List<Indice> listar() {
		return repository.findAll();
	}
	
}
