package br.com.besche.contentor.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.ContentorModel;
import br.com.besche.repository.ContentorRepository;

@Named(value = "consultarContentorController")
@ViewScoped
public class ConsultarContentorController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	transient private ContentorModel contentorModel;
	@Produces
	private List<ContentorModel> contentores;
	@Inject
	transient private ContentorRepository contentorRepository;

	/***
	 * CARREGA OS CONTENTORES NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNA OS CONTENTORES CADASTRADOS
		this.contentores = contentorRepository.GetContentores();
	}
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param contentorModel
	 */
	public void ExcluirContentor(ContentorModel contentorModel) {
		// EXCLUI O INDICE DO BANCO DE DADOS
		this.contentorRepository.ExcluirRegistro(contentorModel.getId());
		// REMOVENDO O INDICE DA LISTA
		// ASSIM QUE É O INDICE É REMOVIDO DA LISTA O DATATABLE É ATUALIZADO
		this.contentores.remove(contentorModel);
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM CONTENTOR PARA SER EDITADO
	 * @param contentorModel
	 */
	public void Editar(ContentorModel contentorModel) {
		this.contentorModel = contentorModel;
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {
		this.contentorRepository.AlterarRegistro(this.contentorModel);
		this.init(); // RECARREGA OS REGISTROS
	}
	
	public List<ContentorModel> getContentores() {
		return contentores;
	}

	public void setContentores(List<ContentorModel> contentores) {
		this.contentores = contentores;
	}

	public ContentorModel getContentorModel() {
		return contentorModel;
	}

	public void setContentorModel(ContentorModel contentorModel) {
		this.contentorModel = contentorModel;
	}
	
}
