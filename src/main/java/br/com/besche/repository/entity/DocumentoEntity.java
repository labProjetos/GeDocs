package br.com.besche.repository.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
@NamedQueries({ @NamedQuery(name = "DocumentoEntity.findAll", query = "SELECT d FROM DocumentoEntity d"),
	@NamedQuery(name = "DocumentoEntity.porTipo", 
	query = "SELECT d FROM DocumentoEntity d WHERE d.tipo.id = :idDoTipo") })
public class DocumentoEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String url;
	private LocalDateTime upload;
	private boolean privado;
	
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private TipoEntity tipo;
	
	
	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<IndiceDocumentoEntity> indexacao;

	public DocumentoEntity() {
	}

	public DocumentoEntity(Long id, String url, LocalDateTime upload, TipoEntity tipo) {
		super();
		this.id = id;
		this.url = url;
		this.upload = upload;
		this.tipo = tipo;
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

	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}

	public TipoEntity getTipo() {
		return tipo;
	}

	public void setTipo(TipoEntity tipo) {
		this.tipo = tipo;
	}

	public List<IndiceDocumentoEntity> getIndexacao() {
		return indexacao;
	}

	public void setIndexacao(List<IndiceDocumentoEntity> indexacao) {
		this.indexacao = indexacao;
	}

}
