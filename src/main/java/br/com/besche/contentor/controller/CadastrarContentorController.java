package br.com.besche.contentor.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.ContentorModel;
import br.com.besche.repository.ContentorRepository;
import br.com.besche.uteis.Uteis;

@Named(value = "cadastrarContentorController")
@RequestScoped
public class CadastrarContentorController {
	@Inject
	ContentorModel contentorModel;
	@Inject
	ContentorRepository contentorRepository;

	public ContentorModel getContentorModel() {
		return contentorModel;
	}

	public void setContentorModel(ContentorModel contentorModel) {
		this.contentorModel = contentorModel;
	}

	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void SalvarNovoContentor() {
		contentorRepository.SalvarNovoRegistro(this.contentorModel);
		this.contentorModel = null;
		Uteis.MensagemInfo("Registro cadastrado com sucesso");

	}

}
