package br.com.besche.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "indice")
@NamedQueries({ @NamedQuery(name = "Indice.findAll", query = "SELECT p FROM Indice p") })
public class Indice implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy = "indice", cascade = CascadeType.ALL)
	private List<IndiceDocumento> indexacao;

	public Indice() {
	}

	public Indice(Long id, String nome, List<IndiceDocumento> indexacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.indexacao = indexacao;
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

	public List<IndiceDocumento> getIndexacao() {
		return indexacao;
	}

	public void setIndexacao(List<IndiceDocumento> indexacao) {
		this.indexacao = indexacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Indice other = (Indice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
