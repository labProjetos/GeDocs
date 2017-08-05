package br.com.besche.documento.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.besche.model.ContentorModel;
import br.com.besche.model.DocumentoModel;
import br.com.besche.model.IndiceModel;
import br.com.besche.repository.ContentorRepository;
import br.com.besche.repository.DocumentoRepository;
import br.com.besche.repository.IndiceRepository;
 
@Named(value = "cadastrarDocumentoController")
@RequestScoped
public class CadastrarDocumentoController {
	private String PATH = "/home/jackson/Arquivos/";
	
	@Inject
	private DocumentoModel documentoModel;
	@Inject
	private DocumentoRepository  documentoRepository;
	
	//lista de índices, contentores, para mostrar no xhtml;
	private List<IndiceModel> indices;
	private List<ContentorModel> contentores;
	
	@Inject
	private IndiceRepository indiceRepository;
	@Inject
	private ContentorRepository contendorRespository;
	
	//carregamento dos índices e os contentores;
	public void prepara() {
		this.indices = indiceRepository.GetIndices();
		this.contentores = contendorRespository.GetContentores();
	}
	
    public void salvar(FileUploadEvent event) throws Exception {
    	UploadedFile file = event.getFile();
    	//1)  setar os valores no documentoModel
    	//2)  setar documentoModel in respository;
    	 
    	System.out.println(file.getFileName());
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            file.write(PATH + file.getFileName());
        }
    }

	public List<IndiceModel> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceModel> indices) {
		this.indices = indices;
	}

	public List<ContentorModel> getContentores() {
		return contentores;
	}

	public void setContentores(List<ContentorModel> contentores) {
		this.contentores = contentores;
	}

	public DocumentoModel getDocumentoModel() {
		return documentoModel;
	}

	public void setDocumentoModel(DocumentoModel documentoModel) {
		this.documentoModel = documentoModel;
	}

	public DocumentoRepository getDocumentoRepository() {
		return documentoRepository;
	}

	public void setDocumentoRepository(DocumentoRepository documentoRepository) {
		this.documentoRepository = documentoRepository;
	}
    
	
    
    
    
}