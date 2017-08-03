package br.com.besche.tipo.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.TipoModel;
import br.com.besche.repository.TipoRepository;
import br.com.besche.uteis.Uteis;

//classe responsável por salvar índices
@Named(value = "cadastrarTipoController")
@RequestScoped
public class CadastrarTipoController {
	@Inject
	TipoModel tipoModel;
	@Inject
	TipoRepository tipoRepository;

	public TipoModel getTipoModel() {
		return tipoModel;
	}

	public void setTipoModel(TipoModel tipoModel) {
		this.tipoModel = tipoModel;
	}

	public void SalvarNovoTipo() {
		tipoRepository.SalvarNovoTipo(this.tipoModel);
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
		this.tipoModel = null;
	}
}
