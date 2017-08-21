package br.com.besche.negocio;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import br.com.besche.modelo.Documento;
import br.com.besche.modelo.IndiceDocumento;
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
	
/*       
	Documento documento = new Documento();
	documento.setUpload(LocalDateTime.now());
	documento.setUrl(documentoModel.getUrl());
	documento.setPrivado(documentoModel.isPrivado());
	
	Tipo tipo = entityManager.find(Tipo.class, 
			documentoModel.getTipo().getId());
	documento.setTipo(tipo);
	
	for (IndiceDocumentoModel indiceDocumentoModel : indicesDocumentoModel) {
		indiceDocumento = new IndiceDocumento();
		indiceDocumento.setConteudo(indiceDocumentoModel.getConteudo());
		indiceDocumento.setDocumento(documento);
		Indice indice = 
				entityManager.find(Indice.class, indiceDocumentoModel.getIndiceModel().getId());
		indiceDocumento.setIndice(indice);
		
		indexacao.add(indiceDocumento);
	}
	
	documento.setIndexacao(indexacao);
	entityManager.persist(documento);
}*/
}	
