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

@Named(value = "consultarIndiceController")
@ViewScoped
public class ConsultarIndiceController implements Serializable {
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
		// RETORNA OS INDICES CADASTRADOS
		this.indices = indiceRepository.GetIndices();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * @param indiceModel
	 */
	public void ExcluirIndice(IndiceModel indiceModel) {
		// EXCLUI O INDICE DO BANCO DE DADOS
		this.indiceRepository.ExcluirRegistro(indiceModel.getId());
		// REMOVENDO O INDICE DA LISTA
		// ASSIM QUE É O INDICE É REMOVIDO DA LISTA O DATATABLE É ATUALIZADO
		this.indices.remove(indiceModel);
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM INDICE PARA SER EDITADO
	 * @param indiceModel
	 */
	public void Editar(IndiceModel indiceModel) {
		/* PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F) 
		indiceModel.setSexo(indiceModel.getSexo().substring(0, 1));*/
		this.indiceModel = indiceModel;
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {
		this.indiceRepository.AlterarRegistro(this.indiceModel);
		this.init(); // RECARREGA OS REGISTROS
	}

	public List<IndiceModel> getIndices() {
		return this.indices;
	}

	public void setIndices(List<IndiceModel> indices) {
		this.indices = indices;
	}

	public IndiceModel getIndiceModel() {
		return indiceModel;
	}

	public void setIndiceModel(IndiceModel indiceModel) {
		this.indiceModel = indiceModel;
	}
}
