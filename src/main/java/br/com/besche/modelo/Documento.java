package br.com.besche.modelo;

import java.io.Serializable;
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
@NamedQueries({ @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d WHERE d.ativo = true"),
	@NamedQuery(name = "Documento.porTipo", 
	query = "SELECT d FROM Documento d WHERE d.tipo.id = :idDoTipo AND d.ativo = true") })

public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String url;
	private LocalDateTime upload;
	private boolean privado;
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private Tipo tipo;
	
	
	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<IndiceDocumento> indexacao;

	public Documento() {
		this.ativo = true;
	}

	public Documento(Long id, String url, LocalDateTime upload, Tipo tipo) {
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<IndiceDocumento> getIndexacao() {
		return indexacao;
	}

	public void setIndexacao(List<IndiceDocumento> indexacao) {
		this.indexacao = indexacao;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
