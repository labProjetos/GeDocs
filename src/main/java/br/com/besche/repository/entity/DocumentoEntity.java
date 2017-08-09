package br.com.besche.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
@NamedQueries({ @NamedQuery(name = "DocumentoEntity.findAll", query = "SELECT d FROM TipoEntity d") })
public class DocumentoEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String url;
	private LocalDateTime upload;
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private TipoEntity tipo;
	@ManyToOne
	@JoinColumn(name = "contentor_id")
	private ContentorEntity contentor;

	public DocumentoEntity() {
	}

	public DocumentoEntity(Long id, String url, LocalDateTime upload, TipoEntity tipo, ContentorEntity contentor) {
		super();
		this.id = id;
		this.url = url;
		this.upload = upload;
		this.tipo = tipo;
		this.contentor = contentor;
	}

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

	public TipoEntity getTipo() {
		return tipo;
	}

	public void setTipo(TipoEntity tipo) {
		this.tipo = tipo;
	}

	public ContentorEntity getContentor() {
		return contentor;
	}

	public void setContentor(ContentorEntity contentor) {
		this.contentor = contentor;
	}

}
