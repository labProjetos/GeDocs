/*package br.com.besche.tipo.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.TipoModel;
import br.com.besche.repository.TipoRepository;
import br.com.besche.uteis.Uteis;

@Named(value = "cadastrarTipoMB")
@RequestScoped
public class CadastrarTipoMB {
	@Inject
	TipoModel tipoModel;
	@Inject
	TipoRepository tipoRepository;

	public void salvar() {
		tipoRepository.salvar(this.tipoModel);
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
		this.tipoModel = null;
	}
	
	public TipoModel getTipoModel() {
		return tipoModel;
	}
	
	public void setTipoModel(TipoModel tipoModel) {
		this.tipoModel = tipoModel;
	}
	
}
*/