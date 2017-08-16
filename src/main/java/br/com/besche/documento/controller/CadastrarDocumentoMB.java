/*package br.com.besche.documento.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.besche.repository.DocumentoRepository;
import br.com.besche.repository.entity.DocumentoEntity;

@Named(value = "cadastrarDocumentoController")
@RequestScoped
public class CadastrarDocumentoMB {
	private String PATH = "/home/wander/Arquivo/";
	private UploadedFile uploadedFile;

	@Inject
	DocumentoEntity documentoModel;
	@Inject
	DocumentoRepository documentoRepository;

	*//**
	 * SALVA UM NOVO REGISTRO
	 * 
	 * @return
	 *//*
	public void salvarNovoDocumento() throws Exception {
		try {
			File file = new File(PATH, this.documentoModel.getTipo().getNome() + " - " + uploadedFile.getFileName());

			OutputStream out = new FileOutputStream(file);
			out.write(uploadedFile.getContents());
			out.close();

			
			 * FacesContext.getCurrentInstance().addMessage( null, new
			 * FacesMessage("Upload completo", "O arquivo " +
			 * uploadedFile.getFileName() + " foi salvo!"));
			 

			this.documentoModel.setUrl(PATH + file.getName());
			documentoRepository.salvar(this.documentoModel);

			this.documentoModel = null;
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public DocumentoEntity getDocumentoModel() {
		return documentoModel;
	}

	public void setDocumentoModel(DocumentoEntity documentoModel) {
		this.documentoModel = documentoModel;
	}

	public DocumentoRepository getDocumentoRepository() {
		return documentoRepository;
	}

	public void setDocumentoRepository(DocumentoRepository documentoRepository) {
		this.documentoRepository = documentoRepository;
	}

}
*/