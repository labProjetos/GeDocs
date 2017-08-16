package br.com.besche.documento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.DocumentoModel;
import br.com.besche.repository.DocumentoRepository;
import br.com.besche.repository.IndiceDocumentoRepository;
import br.com.besche.repository.entity.DocumentoEntity;
import br.com.besche.repository.entity.IndiceDocumentoEntity;

@Named(value = "consultarDocumentoController")
@ViewScoped
public class ConsultarDocumentoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	transient private DocumentoModel documentoModel;
	@Produces
	private List<DocumentoModel> documentos;
	
	private List<IndiceDocumentoEntity> indiceDoc;
	
	private List<DocumentoEntity> doc;
	
	@Inject
	transient private DocumentoRepository documentoRepository;
	@Inject
	transient private IndiceDocumentoRepository indiceDocumentoRepository;
	private String termos;

	/***
	 * CARREGA OS CONTENTORES NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNA OS DOCUMENTOS CADASTRADOS
		// this.documentos = documentoRepository.GetDocumentos();
	}

	public void buscar() {
		
		//this.doc = documentoRepository.buscaDocumento(this.documentoModel.getTipo().getId(), termos);
		/*this.indiceDoc = indiceDocumentoRepository.getIndicesDocumentoPor(this.documentoModel.getTipo());
		for (IndiceDocumentoEntity indiceDocumento : this.indiceDoc) {
			this.doc.add(indiceDocumento.getDocumento());
		}*/
		//this.doc = documentoRepository.getDocumentoPor(this.documentoModel.getTipo());
		this.doc = new ArrayList<DocumentoEntity>();
		for (DocumentoEntity documento : documentoRepository.getDocumentoPor(this.documentoModel.getTipo())) {
			for (IndiceDocumentoEntity indiceDocumento : documento.getIndexacao()) {
				if (indiceDocumento.getConteudo().contains(termos)) {
					this.doc.add(documento);
				}
			}
		}
		System.out.println(termos);
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param documentoModel
	 */
	public void ExcluirDocumento(DocumentoEntity documentoModel) {
		// EXCLUI O DOCUMENTO DO BANCO DE DADOS
		this.documentoRepository.excluir(documentoModel.getId());
		// REMOVENDO O DOCUMENTO DA LISTA
		// ASSIM QUE O DOCUMENTO É REMOVIDO DA LISTA O DATATABLE É ATUALIZADO
		this.documentos.remove(documentoModel);
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM DOCUMENTO PARA SER EDITADO
	 * 
	 * @param documentoModel
	 */
	/*public void Editar(DocumentoModel documentoModel) {
		this.documentoModel = documentoModel;
	}*/

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	/*
	 * public void AlterarRegistro() {
	 * this.documentoRepository.AlterarRegistro(this.documentoModel);
	 * this.init(); // RECARREGA OS REGISTROS }
	 */

	public List<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

	/*public DocumentoModel getDocumentoModel() {
		return documentoModel;
	}

	public void setDocumentoModel(DocumentoModel documentoModel) {
		this.documentoModel = documentoModel;
	}*/

	public String getTermos() {
		return termos;
	}

	public void setTermos(String termos) {
		this.termos = termos;
	}

	/*public List<DocumentoEntity> getDoc() {
		return doc;
	}

	public void setDoc(List<DocumentoEntity> doc) {
		this.doc = doc;
	}*/

	public DocumentoRepository getDocumentoRepository() {
		return documentoRepository;
	}

	public void setDocumentoRepository(DocumentoRepository documentoRepository) {
		this.documentoRepository = documentoRepository;
	}
	
	public IndiceDocumentoRepository getIndiceDocumentoRepository() {
		return indiceDocumentoRepository;
	}
	
	public void setIndiceDocumentoRepository(IndiceDocumentoRepository indiceDocumentoRepository) {
		this.indiceDocumentoRepository = indiceDocumentoRepository;
	}

	public DocumentoModel getDocumentoModel() {
		return documentoModel;
	}

	public void setDocumentoModel(DocumentoModel documentoModel) {
		this.documentoModel = documentoModel;
	}

	public List<DocumentoEntity> getDoc() {
		return doc;
	}

	public void setDoc(List<DocumentoEntity> doc) {
		this.doc = doc;
	}

}
