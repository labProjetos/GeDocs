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
		if (documento == null || indexacao == null) {
			throw new Exception();
		}
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
	
	public List<Documento> listarPublico(Tipo tipo) {
		return repository.findPublico(tipo);
	}
	
	public void excluir(Documento objeto) throws Exception {
		if (objeto.getId() < 1) {
			throw new Exception();
		}
		objeto.setAtivo(false);
		repository.excluir(objeto);
	}
}	
