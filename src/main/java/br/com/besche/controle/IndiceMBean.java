package br.com.besche.controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.modelo.Indice;
import br.com.besche.negocio.IndiceService;
import br.com.besche.uteis.Uteis;

@Named(value = "indiceMBean")
@ViewScoped
public class IndiceMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	transient private IndiceService indiceService;
	private Indice indice = new Indice();
	
	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void salvar() {
		try {
			indiceService.salvar(indice);
			Uteis.MensagemInfo("Indice cadastrado com sucesso");
		} catch (Exception e) {
			Uteis.MensagemAtencao("Indice com mesmo nome já cadastrado");
		}
		indice = new Indice();
	}
	
	/***
	 * CARREGA AS INFORMAÇÕES DE UM REGISTRO PARA SER EDITADO
	 * @param indice
	 */
	public void editar(Indice indice) {
		this.indice = indice;
	}
	
	/***
	 * ATUALIZA O REGISTRO QUE FOI CARREGADO
	 */
	public void alterar() {
		try {
			indiceService.alterar(indice);
			this.getLista();
			Uteis.MensagemInfo("Indice alterado com sucesso");
		} catch (Exception e) {
			Uteis.MensagemAtencao("Indice com mesmo nome já cadastrado");
		}
		indice = new Indice();
	}
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param indiceModel
	 */
	public void excluir(Indice indice) {
		try {
			indiceService.excluir(indice);
			Uteis.MensagemInfo("Indice excluído com sucesso");
		} catch (Exception e) {
			Uteis.MensagemAtencao("Indice já usado numa indexação");
		}
		indice = new Indice();
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return
	 */
	public List<Indice> getLista() {
		return indiceService.listar();
	}
	
	public Indice getIndice() {
		return indice;
	}
	
	public void setIndice(Indice indice) {
		this.indice = indice;
	}
	
}
