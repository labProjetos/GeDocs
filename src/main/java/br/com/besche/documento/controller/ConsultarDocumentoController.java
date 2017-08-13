package br.com.besche.documento.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.DocumentoModel;
import br.com.besche.repository.DocumentoRepository;

@Named(value = "consultarDocumentoController")
@ViewScoped
public class ConsultarDocumentoController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	transient private DocumentoModel documentoModel;
	@Produces
	private List<DocumentoModel> documentos;
	@Inject
	transient private DocumentoRepository documentoRepository;

	/***
	 * CARREGA OS CONTENTORES NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNA OS DOCUMENTOS CADASTRADOS
		this.documentos = documentoRepository.GetDocumentos();
	}
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param documentoModel
	 */
	public void ExcluirDocumento(DocumentoModel documentoModel) {
		// EXCLUI O DOCUMENTO DO BANCO DE DADOS
		this.documentoRepository.ExcluirRegistro(documentoModel.getId());
		// REMOVENDO O DOCUMENTO DA LISTA
		// ASSIM QUE O DOCUMENTO É REMOVIDO DA LISTA O DATATABLE É ATUALIZADO
		this.documentos.remove(documentoModel);
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM DOCUMENTO PARA SER EDITADO
	 * @param documentoModel
	 */
	public void Editar(DocumentoModel documentoModel) {
		this.documentoModel = documentoModel;
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {
		this.documentoRepository.AlterarRegistro(this.documentoModel);
		this.init(); // RECARREGA OS REGISTROS
	}
	
	public List<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentoes(List<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

	public DocumentoModel getDocumentoModel() {
		return documentoModel;
	}

	public void setDocumentoModel(DocumentoModel documentoModel) {
		this.documentoModel = documentoModel;
	}
	
}
