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

@Named(value = "consultarTipoController")
@ViewScoped
public class ConsultarTipoController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	transient private TipoModel tipoModel;
	@Inject
	private List<IndiceModel> indicesModel;
	@Produces
	private List<TipoModel> tipos;
	@Inject
	transient private TipoRepository tipoRepository;

	/***
	 * CARREGA OS TIPOS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNA OS TIPOS CADASTRADOS
		this.tipos = tipoRepository.GetTipos();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * @param tipoModel
	 */
	public void ExcluirTipo(TipoModel tipoModel) {
		// EXCLUI O TIPO DO BANCO DE DADOS
		this.tipoRepository.ExcluirRegistro(tipoModel.getId());
		// REMOVENDO O TIPO DA LISTA
		// ASSIM QUE O TIPO É REMOVIDO DA LISTA O DATATABLE É ATUALIZADO
		this.tipos.remove(tipoModel);
	}
	
	/***
	 * CARREGA INFORMAÇÕES DE UM TIPO PARA SER EDITADO
	 * @param tipoModel
	 */
	public void Editar(TipoModel tipoModel) {
		/* PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F) 
		tipoModel.setSexo(tipoModel.getSexo().substring(0, 1));*/
		tipoModel.setIndices(tipoModel.getIndices());
		this.tipoModel = tipoModel;
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {
		this.tipoRepository.AlterarRegistro(this.tipoModel);
		/* RECARREGA OS REGISTROS */
		this.init();
	}
	
	public List<TipoModel> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoModel> tipos) {
		this.tipos = tipos;
	}

	public TipoModel getTipoModel() {
		return tipoModel;
	}

	public void setTipoModel(TipoModel tipoModel) {
		this.tipoModel = tipoModel;
	}
	
	public List<IndiceModel> getIndicesModel() {
		return indicesModel;
	}
	
	public void setIndicesModel(List<IndiceModel> indicesModel) {
		this.indicesModel = indicesModel;
	}
}
