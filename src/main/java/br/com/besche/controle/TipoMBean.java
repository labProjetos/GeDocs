package br.com.besche.controle;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.modelo.Tipo;
import br.com.besche.negocio.TipoService;
import br.com.besche.uteis.Uteis;

@Named(value = "tipoMBean")
@RequestScoped
public class TipoMBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	transient private TipoService tipoService;
	private Tipo tipo = new Tipo();
	
	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void salvar() {
		try {
			tipoService.salvar(tipo);
			Uteis.MensagemInfo("Tipo cadastrado com sucesso");
		} catch (Exception e) {
			Uteis.MensagemAtencao("Tipo com mesmo nome já cadastrado");
		}
		tipo = new Tipo();
	}
	
	/***
	 * CARREGA AS INFORMAÇÕES DE UM REGISTRO PARA SER EDITADO
	 * @param tipo
	 */
	public void editar(Tipo tipo) {
		this.tipo = tipo;
	}
	
	/***
	 * ATUALIZA O REGISTRO QUE FOI CARREGADO
	 */
	public void alterar() {
		try {
			tipoService.alterar(tipo);
			this.getLista();
			Uteis.MensagemInfo("Tipo alterado com sucesso");
		} catch (Exception e) {
			Uteis.MensagemAtencao("Tipo com mesmo nome já cadastrado");
		}
		tipo = new Tipo();
	}
	
	/**
	 * EXCLUINDO UM REGISTRO
	 * @param indiceModel
	 */
	public void excluir(Tipo tipo) {
		try {
			tipoService.excluir(tipo);
			Uteis.MensagemInfo("Tipo excluído com sucesso");
		} catch (Exception e) {
			Uteis.MensagemAtencao("Não é possível excluir tipos vínculados a indices ou com documentos importados");
		}
		tipo = new Tipo();
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return
	 */
	public List<Tipo> getLista() {
		return tipoService.listar();
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
