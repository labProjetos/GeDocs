package br.com.besche.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class DocumentoEntity {
	@Id
	@GeneratedValue
	private int id;
	private String url_nome; // endereço local do arquivo
	private Date data_upload;
	private Date data_criacao;	
	@ManyToOne // um documento tem um tipo de documento
	private TipoEntity tipo;

	// DocumentoEntity tem apenas um contentor
	@ManyToOne
	private ContentorEntity contentor;

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

	public Date getData_upload() {
		return data_upload;
	}

	public void setData_upload(Date data_upload) {
		this.data_upload = data_upload;
	}

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
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
