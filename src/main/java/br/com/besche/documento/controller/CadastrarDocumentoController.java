package br.com.besche.documento.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.besche.model.DocumentoModel;
import br.com.besche.repository.DocumentoRepository;

@Named(value = "cadastrarDocumentoController")
@RequestScoped
public class CadastrarDocumentoController {
	private String PATH = "/home/wander/Arquivo/";
	private UploadedFile uploadedFile;
	
	@Inject
	DocumentoModel documentoModel;
	@Inject
	DocumentoRepository documentoRepository;

	/**
	 * SALVA UM NOVO REGISTRO
	 * @return 
	 */
	public void salvarNovoDocumento() throws Exception {
		try {
			File file = new File(PATH, this.documentoModel.getTipo().getNome() +" - "+ uploadedFile.getFileName());
		 
		    OutputStream out = new FileOutputStream(file);
		    out.write(uploadedFile.getContents());
		    out.close();
		 
		    /*FacesContext.getCurrentInstance().addMessage(
		               	null, new FacesMessage("Upload completo", 
		               	"O arquivo " + uploadedFile.getFileName() + " foi salvo!"));*/
		    
		    this.documentoModel.setUrl(PATH + file.getName()); 
            documentoRepository.SalvarNovoDocumento(this.documentoModel);
    		/*Uteis.MensagemInfo("Registro cadastrado com sucesso");*/
    		FacesContext.getCurrentInstance().getExternalContext().redirect("indexarDocumento.xhtml");
		    
    		this.documentoModel = null;
		} catch(IOException e) {
		  		FacesContext.getCurrentInstance().addMessage(
		  				null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
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
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
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
