package br.com.besche.usuario.controller;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.IndiceModel;
import br.com.besche.repository.IndiceRepository;
import br.com.besche.uteis.Uteis;

// classe responsável por salvar índices

@Named(value="cadastrarIndiceController")
@RequestScoped
public class IndiceController {

	@Inject
	IndiceModel indiceModel;
	
	@Inject
	IndiceRepository indiceRepository;
	
	public void SalvarNovoIndice() {
		indiceRepository.SalvarNovoIndice(this.indiceModel);
		this.indiceModel = null;
		 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
	}
	
}
