package br.com.besche.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "indice_documento")
@NamedQueries({ @NamedQuery(name = "IndiceDocumento.findAll", query = "SELECT id FROM IndiceDocumento id"),
	@NamedQuery(name = "IndiceDocumento.porTipo", 
		query = "SELECT indiceDoc FROM IndiceDocumento indiceDoc WHERE indiceDoc.documento.tipo.id = :idDoTipo")})
public class IndiceDocumento {
	@Id
	@GeneratedValue
	private Long id;
	private String conteudo;
	@ManyToOne
	@JoinColumn(name = "documento_id")
	private Documento documento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "indice_id")
	private Indice indice;

	public IndiceDocumento() {
	}

	public IndiceDocumento(Long id, String conteudo, Documento documento, Indice indice) {
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

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Indice getIndice() {
		return indice;
	}

	public void setIndice(Indice indice) {
		this.indice = indice;
	}

}
