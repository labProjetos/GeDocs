package br.com.besche.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@NamedQueries({ @NamedQuery(name = "Tipo.findAll", query = "SELECT p FROM Tipo p"),
				@NamedQuery(name = "TipoIndices", query = "SELECT p FROM Tipo p JOIN FETCH p.indices WHERE p.id = :id")})
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private int temporalidade;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tipo_indice", 
		joinColumns = @JoinColumn(name = "tipo_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "indice_id", referencedColumnName = "id"))
	private List<Indice> indices;
	
	@OneToMany(mappedBy = "tipo")
	private List<DocumentoEntity> documentos;

	public Tipo() {
	}

	public Tipo(Long id, String nome, int temporalidade, List<Indice> indices, List<DocumentoEntity> documentos) {
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

	public List<Indice> getIndices() {
		return indices;
	}

	public void setIndices(List<Indice> indices) {
		this.indices = indices;
	}

	public List<DocumentoEntity> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoEntity> documentos) {
		this.documentos = documentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documentos == null) ? 0 : documentos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indices == null) ? 0 : indices.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + temporalidade;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tipo other = (Tipo) obj;
		if (documentos == null) {
			if (other.documentos != null)
				return false;
		} else if (!documentos.equals(other.documentos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indices == null) {
			if (other.indices != null)
				return false;
		} else if (!indices.equals(other.indices))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (temporalidade != other.temporalidade)
			return false;
		return true;
	}
	

}
