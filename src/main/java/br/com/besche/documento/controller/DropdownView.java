package br.com.besche.documento.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.besche.model.DocumentoModel;
import br.com.besche.model.IndiceDocumentoModel;
import br.com.besche.model.IndiceModel;
import br.com.besche.repository.IndiceDocumentoRepository;

@Named(value = "dropdownView")
@ViewScoped
public class DropdownView implements Serializable {
	private static final long serialVersionUID = 1L;
    private List<IndiceModel> indices;
    private DocumentoModel documentoModel;
    private String PATH = "/home/wander/Arquivo/";
	private List<IndiceDocumentoModel> indicesDocumentoModel;
	private IndiceDocumentoRepository indiceDocumentoRepository;
     
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
            indiceDocumentoRepository.SalvarNovaIndexacao(this.indicesDocumentoModel, this.documentoModel);
    		this.documentoModel = null;
    		this.indicesDocumentoModel = null;
    		FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch(IOException e) {
		  		FacesContext.getCurrentInstance().addMessage(
		  				null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}
    
    public DocumentoModel getDocumentoModel() {
		return documentoModel;
	}
    
    public void setDocumentoModel(DocumentoModel documentoModel) {
		this.documentoModel = documentoModel;
	}
 
    public List<IndiceModel> getIndices() {
		return indices;
	}
    
    public void setIndices(List<IndiceModel> indices) {
		this.indices = indices;
	}
    
    public List<IndiceDocumentoModel> getIndicesDocumentoModel() {
		return indicesDocumentoModel;
	}
    
    public void setIndicesDocumentoModel(List<IndiceDocumentoModel> indicesDocumentoModel) {
		this.indicesDocumentoModel = indicesDocumentoModel;
	}
    
    public IndiceDocumentoRepository getIndiceDocumentoRepository() {
		return indiceDocumentoRepository;
	}
    
    public void setIndiceDocumentoRepository(IndiceDocumentoRepository indiceDocumentoRepository) {
		this.indiceDocumentoRepository = indiceDocumentoRepository;
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
    
}
