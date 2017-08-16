package br.com.besche.indice.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.IndiceModel;
import br.com.besche.repository.IndiceRepository;
import br.com.besche.uteis.Uteis;

@Named(value = "consultarIndiceMB")
@ViewScoped
public class ConsultarIndiceMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	transient private IndiceModel indiceModel;
	@Produces
	private List<IndiceModel> indices;
	@Inject
	transient private IndiceRepository indiceRepository;

	/***
	 * CARREGA OS INDICES NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		this.indices = indiceRepository.listar();
	}

	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void salvar() {
		indiceRepository.salvar(this.indiceModel);
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
		this.indices.add(this.indiceModel);
		this.indiceModel = new IndiceModel();
	}
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param indiceModel
	 */
	public void excluir(IndiceModel indiceModel) {
		this.indiceRepository.excluir(indiceModel.getId());
		this.indices.remove(indiceModel);
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM INDICE PARA SER EDITADO
	 * @param indiceModel
	 */
	public void editar(IndiceModel indiceModel) {
		this.indiceModel = indiceModel;
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void alterar() {
		this.indiceRepository.alterar(this.indiceModel);
		this.init();
	}

	public IndiceModel getIndiceModel() {
		return indiceModel;
	}

	public void setIndiceModel(IndiceModel indiceModel) {
		this.indiceModel = indiceModel;
	}

	public List<IndiceModel> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceModel> indices) {
		this.indices = indices;
	}
	
}
