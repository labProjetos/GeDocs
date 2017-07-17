package br.com.besche.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DocumentoEntity {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String url_nome; //endereço local do arquivo
	private int upload;
	
	//DocumentoEntity tem apenas um tipo
	@ManyToOne
	private TipoDocumentoEntity tipo;

	//DocumentoEntity tem apenas um contentor
	@ManyToOne
	private ContentorEntity contentor;
		
	
	
	//construtor padrão
	public DocumentoEntity() {
		
	}

	public DocumentoEntity(int id, String url_nome, int upload) {
		super();
		this.id = id;
		this.url_nome = url_nome;
		this.upload = upload;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl_nome() {
		return url_nome;
	}

	public void setUrl_nome(String url_nome) {
		this.url_nome = url_nome;
	}

	public int getUpload() {
		return upload;
	}

	public void setUpload(int upload) {
		this.upload = upload;
	}

	public TipoDocumentoEntity getTipo() {
		return tipo;
	}

	public void setTipo(TipoDocumentoEntity tipo) {
		this.tipo = tipo;
	}

	public ContentorEntity getContentor() {
		return contentor;
	}

	public void setContentor(ContentorEntity contentor) {
		this.contentor = contentor;
	}
	
	
	
}
