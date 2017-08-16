package br.com.besche.tipo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.IndiceModel;
import br.com.besche.model.TipoModel;
import br.com.besche.repository.TipoRepository;
import br.com.besche.uteis.Uteis;

@Named(value = "consultarTipoMB")
@ViewScoped
public class ConsultarTipoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	transient private TipoModel tipoModel;
	@Inject
	private List<IndiceModel> indices;
	@Produces
	private List<TipoModel> tipos;
	@Inject
	transient private TipoRepository tipoRepository;

	/**
	 * CARREGA OS TIPOS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		this.tipos = tipoRepository.listar();
	}
	
	public void salvar() {
		tipoRepository.salvar(this.tipoModel);
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
		this.tipos.add(this.tipoModel);
		this.tipoModel = new TipoModel();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * @param tipoModel
	 */
	public void excluir(TipoModel tipoModel) {
		this.tipoRepository.excluir(tipoModel.getId());
		this.tipos.remove(tipoModel);
	}
	
	/***
	 * CARREGA INFORMAÇÕES DE UM TIPO PARA SER EDITADO
	 * @param tipoModel
	 */
	public void editar(TipoModel tipoModel) {
		tipoModel.setIndices(tipoModel.getIndices());
		this.tipoModel = tipoModel;
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void alterar() {
		this.tipoRepository.alterar(this.tipoModel);
		this.init();
	}

	public TipoModel getTipoModel() {
		return tipoModel;
	}

	public void setTipoModel(TipoModel tipoModel) {
		this.tipoModel = tipoModel;
	}

	public List<IndiceModel> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceModel> indices) {
		this.indices = indices;
	}

	public List<TipoModel> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoModel> tipos) {
		this.tipos = tipos;
	}
	
}
