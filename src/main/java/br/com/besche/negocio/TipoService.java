package br.com.besche.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.besche.modelo.Indice;
import br.com.besche.modelo.Tipo;
import br.com.besche.repository.TipoRepository;

public class TipoService {
	@Inject
	TipoRepository repository;
	
	public void salvar(Tipo objeto) throws Exception {
		for (Tipo t : repository.findAll()) {
			if (t.getNome().equalsIgnoreCase(objeto.getNome())) {
				throw new Exception();
			}
		}
		objeto.setIndices(new ArrayList<Indice>());
		repository.salvar(objeto);
	}
	
	public void alterar(Tipo objeto) throws Exception {
		for (Tipo p : repository.findAll()) {
			if (p.getNome().equalsIgnoreCase(objeto.getNome()) && !p.getId().equals(objeto.getId())) {
				throw new Exception();
			}
		}
		Tipo tipo = new Tipo();
		tipo.setId(objeto.getId());
		tipo.setNome(objeto.getNome());
		tipo.setTemporalidade(objeto.getTemporalidade());
		tipo.setIndices(objeto.getIndices());
		repository.alterar(tipo);
	}
	
	public void excluir(Tipo objeto) throws Exception {
		if (objeto.getId() < 1 || !objeto.getIndices().isEmpty() || !objeto.getDocumentos().isEmpty()) {
			throw new Exception();
		} else {
			repository.excluir(objeto);
		}
	}
	
	public List<Tipo> listar() {
		return repository.findAll();
	}
}
