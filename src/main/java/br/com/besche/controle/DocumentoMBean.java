package br.com.besche.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.besche.model.DocumentoModel;
import br.com.besche.model.IndiceDocumentoModel;
import br.com.besche.model.IndiceModel;
import br.com.besche.modelo.DocumentoEntity;
import br.com.besche.modelo.IndiceDocumentoEntity;
import br.com.besche.repository.DocumentoRepository;
import br.com.besche.repository.IndiceDocumentoRepository;

@Named(value = "documentoMBean")
@ViewScoped
public class DocumentoMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	transient private DocumentoModel documentoModel;
	@Produces
	private List<DocumentoModel> documentos;
	private List<DocumentoEntity> doc;
	@Inject
	transient private DocumentoRepository documentoRepository;
	//@Inject
	//transient private IndiceDocumentoRepository indiceDocumentoRepository;
	private String termos;
	private List<IndiceModel> indices;
    //private DocumentoModel documentoModel;
    private String PATH = "/home/wander/Arquivo/";
	private List<IndiceDocumentoModel> indicesDocumentoModel;
	private IndiceDocumentoRepository indiceDocumentoRepository;

	/***
	 * CARREGA OS CONTENTORES NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		documentoModel = new DocumentoModel();
    	indices = new ArrayList<IndiceModel>();
    	indicesDocumentoModel = new ArrayList<IndiceDocumentoModel>();
    	indiceDocumentoRepository = new IndiceDocumentoRepository();
	}
	
	public void salvarNovoDocumento(UploadedFile uploadedFile) throws Exception {
		try {
			File file = new File(PATH, this.documentoModel.getTipo().getNome() +" - "+ uploadedFile.getFileName());
		    OutputStream out = new FileOutputStream(file);
		    out.write(uploadedFile.getContents());
		    out.close();
		    
		    this.documentoModel.setUrl(PATH + file.getName()); 
            indiceDocumentoRepository.salvar(this.indicesDocumentoModel, this.documentoModel);
    		this.documentoModel = null;
    		this.indicesDocumentoModel = null;
    		FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch(IOException e) {
		  		FacesContext.getCurrentInstance().addMessage(
		  				null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}
 
    public void tipoSelecionado() {
        if(this.documentoModel.getTipo() !=null) {
            indices = documentoModel.getTipo().getIndices();
            indicesDocumentoModel.clear();
            IndiceDocumentoModel indiceDocumentoModel = null;
            for (IndiceModel indiceModel : indices) {
            	indiceDocumentoModel = new IndiceDocumentoModel();
            	indiceDocumentoModel.setDocumentoModel(this.documentoModel);
            	indiceDocumentoModel.setIndiceModel(indiceModel);
            	indicesDocumentoModel.add(indiceDocumentoModel);
            }
        } else {
            indices = new ArrayList<IndiceModel>();
        }
    }

	public void buscar() {
		this.doc = new ArrayList<DocumentoEntity>();
		for (DocumentoEntity documento : documentoRepository.getDocumentoPor(this.documentoModel.getTipo())) {
			for (IndiceDocumentoEntity indiceDocumento : documento.getIndexacao()) {
				if (indiceDocumento.getConteudo().contains(termos) && !this.doc.contains(documento)) {
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

	public String getTermos() {
		return termos;
	}

	public void setTermos(String termos) {
		this.termos = termos;
	}

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

	public List<IndiceModel> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceModel> indices) {
		this.indices = indices;
	}

	public String getPATH() {
		return PATH;
	}

	public void setPATH(String pATH) {
		PATH = pATH;
	}

	public List<IndiceDocumentoModel> getIndicesDocumentoModel() {
		return indicesDocumentoModel;
	}

	public void setIndicesDocumentoModel(List<IndiceDocumentoModel> indicesDocumentoModel) {
		this.indicesDocumentoModel = indicesDocumentoModel;
	}
	

}
