package br.com.besche.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DocumentoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String url;
	private LocalDateTime upload;
	private TipoModel tipo;
	private ContentorModel contentor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public LocalDateTime getUpload() {
		return upload;
	}
	
	public void setUpload(LocalDateTime upload) {
		this.upload = upload;
	}

	public TipoModel getTipo() {
		return tipo;
	}

	public void setTipo(TipoModel tipo) {
		this.tipo = tipo;
	}
	
	public ContentorModel getContentor() {
		return contentor;
	}
	
	public void setContentor(ContentorModel contentor) {
		this.contentor = contentor;
	}

}
