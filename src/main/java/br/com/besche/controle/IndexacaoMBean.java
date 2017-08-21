package br.com.besche.controle;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.besche.modelo.Documento;
import br.com.besche.negocio.DocumentoService;
import br.com.besche.uteis.Uteis;

@Named(value = "indexacaoMBean")
@RequestScoped
public class IndexacaoMBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	transient private DocumentoService documentoService;
	private Documento documento = new Documento();
	
	/***
	 * CARREGA AS INFORMAÇÕES DE UM REGISTRO PARA SER EDITADO
	 * @param tipo
	 */
	public String editar(Documento documento) {
		this.documento = documento;
		return "editarDocumento.xhtml";
	}
	
	/***
	 * ATUALIZA O REGISTRO QUE FOI CARREGADO
	 */
	public String alterar() {
		try {
			documentoService.alterar(documento);
			Uteis.MensagemInfo("Documento alterado com sucesso");
		} catch (Exception e) {
			Uteis.MensagemAtencao("Documento com mesmo nome já cadastrado");
		}
		documento = new Documento();
		return "home.xhtml";
	}
	
	public Documento getDocumento() {
		return documento;
	}
	
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
}
