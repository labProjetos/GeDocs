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
	
	public IndiceModel getIndiceModel() {
		return indiceModel;
	}
 
	public void setIndiceModel(IndiceModel indiceModel) {
		this.indiceModel = indiceModel;
	}
 
	public void SalvarNovoIndice() {
		try {
			System.out.println("TESTANDO PEGA DE VALOR," + indiceModel.getNome() );
			indiceRepository.SalvarNovoIndice(this.indiceModel);
			Uteis.MensagemInfo("Registro cadastrado com sucesso");
			this.indiceModel = null;
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
