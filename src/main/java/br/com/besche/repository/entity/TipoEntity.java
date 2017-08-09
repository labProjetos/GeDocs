package br.com.besche.repository.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo")
@NamedQueries({ @NamedQuery(name = "TipoEntity.findAll", query = "SELECT p FROM TipoEntity p") })
public class TipoEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private int temporalidade;
	@ManyToMany
	@JoinTable(name = "tipo_indice", joinColumns = @JoinColumn(name = "tipo_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "indice_id", referencedColumnName = "id"))
	private List<IndiceEntity> indices;
	@OneToMany(mappedBy = "tipo")
	private List<DocumentoEntity> documentos;

	public TipoEntity() {
	}

	public TipoEntity(Long id, String nome, int temporalidade, List<IndiceEntity> indices,
			List<DocumentoEntity> documentos) {
		super();
		this.id = id;
		this.nome = nome;
		this.temporalidade = temporalidade;
		this.indices = indices;
		this.documentos = documentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTemporalidade() {
		return temporalidade;
	}

	public void setTemporalidade(int temporalidade) {
		this.temporalidade = temporalidade;
	}

	public List<IndiceEntity> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceEntity> indices) {
		this.indices = indices;
	}

	public List<DocumentoEntity> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoEntity> documentos) {
		this.documentos = documentos;
	}

}
