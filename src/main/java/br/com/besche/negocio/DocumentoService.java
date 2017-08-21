package br.com.besche.negocio;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import br.com.besche.modelo.Documento;
import br.com.besche.modelo.IndiceDocumento;
import br.com.besche.modelo.Tipo;
import br.com.besche.repository.DocumentoRepository;

public class DocumentoService {
	@Inject
	DocumentoRepository repository;
	
	public void salvar(Documento documento, List<IndiceDocumento> indexacao, String PATH, File file) 
			throws Exception {
		documento.setUrl(PATH + file.getName());
		documento.setUpload(LocalDateTime.now());
		documento.setIndexacao(indexacao);
		repository.salvar(documento);
	}
	
	public List<Documento> listar() {
		return repository.findAll();
	}

	public List<Documento> listarPor(Tipo tipo) {
		return repository.findPor(tipo);
	}
	
	public void alterar(Documento objeto) throws Exception {
		Documento documento = new Documento();
		documento.setId(objeto.getId());
		documento.setPrivado(objeto.isPrivado());
		documento.setIndexacao(objeto.getIndexacao());
		repository.alterar(documento);
	}
	
	public void excluir(Documento objeto) {
		objeto.setAtivo(false);
		repository.excluir(objeto);
	}
}	
