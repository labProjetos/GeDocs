package br.com.besche.model;

import java.io.Serializable;
import java.util.Date;

public class DocumentoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String url_nome;
	private Date data_upload;
	private Date data_criacao;
	private ContentorModel contentor;
	private TipoModel tipo;
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
	public ContentorModel getContentor() {
		return contentor;
	}
	public void setContentor(ContentorModel contentor) {
		this.contentor = contentor;
	}
	public TipoModel getTipo() {
		return tipo;
	}
	public void setTipo(TipoModel tipo) {
		this.tipo = tipo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	
}
