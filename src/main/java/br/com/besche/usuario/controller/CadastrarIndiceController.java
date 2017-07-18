package br.com.besche.usuario.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.model.IndiceModel;
import br.com.besche.repository.IndiceRepository;
import br.com.besche.uteis.Uteis;

// classe responsável por salvar índices

@Named(value="cadastrarIndiceController")
@RequestScoped
public class CadastrarIndiceController {

	@Inject
	IndiceModel indiceModel;
	
	@Inject
	IndiceRepository indiceRepository;
	
	public IndiceModel getIndiceModel() {
		return indiceModel;
	}
 
	public void setIndiceModel(IndiceModel indiceModel) {
		this.indiceModel = indiceModel;
	}
 
	public void SalvarNovoIndice() {
			indiceRepository.SalvarNovoIndice(this.indiceModel);
			Uteis.MensagemInfo("Registro cadastrado com sucesso");
			this.indiceModel = null;
		 
				
		
		
	}
	
}