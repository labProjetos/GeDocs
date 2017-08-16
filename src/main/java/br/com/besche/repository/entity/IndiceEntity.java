package br.com.besche.repository.entity;

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
@NamedQueries({ @NamedQuery(name = "IndiceEntity.findAll", query = "SELECT p FROM IndiceEntity p") })
public class IndiceEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy = "indice", cascade = CascadeType.ALL)
	private List<IndiceDocumentoEntity> indexacao;

	public IndiceEntity() {
	}

	public IndiceEntity(Long id, String nome, List<IndiceDocumentoEntity> indexacao) {
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

	public List<IndiceDocumentoEntity> getIndexacao() {
		return indexacao;
	}

	public void setIndexacao(List<IndiceDocumentoEntity> indexacao) {
		this.indexacao = indexacao;
	}

}
