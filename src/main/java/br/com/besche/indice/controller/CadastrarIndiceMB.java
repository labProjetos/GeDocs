/*package br.com.besche.indice.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.IndiceModel;
import br.com.besche.repository.IndiceRepository;
import br.com.besche.uteis.Uteis;

@Named(value = "cadastrarIndiceMB")
@RequestScoped
public class CadastrarIndiceMB {
	@Inject
	IndiceModel indiceModel;
	@Inject
	IndiceRepository indiceRepository;

	*//**
	 * SALVA UM NOVO REGISTRO
	 *//*
	public void salvar() {
		indiceRepository.salvar(this.indiceModel);
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
		this.indiceModel = null;
	}
	
	public IndiceModel getIndiceModel() {
		return indiceModel;
	}
	
	public void setIndiceModel(IndiceModel indiceModel) {
		this.indiceModel = indiceModel;
	}

}*/