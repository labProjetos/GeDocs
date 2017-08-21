package br.com.besche.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "indice_documento")
@NamedQueries({ @NamedQuery(name = "IndiceDocumentoEntity.findAll", query = "SELECT id FROM IndiceDocumentoEntity id"),
	@NamedQuery(name = "IndiceDocumentoEntity.porTipo", 
		query = "SELECT indiceDoc FROM IndiceDocumentoEntity indiceDoc WHERE indiceDoc.documento.tipo.id = :idDoTipo")})
public class IndiceDocumentoEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String conteudo;
	@ManyToOne
	@JoinColumn(name = "documento_id")
	private DocumentoEntity documento;
	@ManyToOne
	@JoinColumn(name = "indice_id")
	private Indice indice;

	public IndiceDocumentoEntity() {
	}

	public IndiceDocumentoEntity(Long id, String conteudo, DocumentoEntity documento, Indice indice) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.documento = documento;
		this.indice = indice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public DocumentoEntity getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoEntity documento) {
		this.documento = documento;
	}

	public Indice getIndice() {
		return indice;
	}

	public void setIndice(Indice indice) {
		this.indice = indice;
	}

}
