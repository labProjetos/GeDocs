package br.com.besche.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.besche.modelo.Documento;
import br.com.besche.modelo.Indice;
import br.com.besche.modelo.IndiceDocumento;
import br.com.besche.negocio.DocumentoService;
import br.com.besche.uteis.Uteis;

@Named(value = "documentoMBean")
@ViewScoped
public class DocumentoMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	transient private DocumentoService documentoService;
	private Documento documento = new Documento();
	private List<Documento> documentos = new ArrayList<Documento>();
	private List<IndiceDocumento> indexacao = new ArrayList<IndiceDocumento>();
	private String PATH = "/home/wander/workspace/teste-de-software/GeDocs/src/main/webapp/resources/repositorio/";
	private String termos;
	private String pdf = "/resources/repositorio/Pontofrio.pdf";
	
	/**
	 * SETA OS REGISTROS DE UMA INDEXAÇÂO PELO TIPO SELECIONADO
	*/
	public void tipoSelecionado() {
		List<Indice> indices = null;
        if(documento.getTipo() != null) {
        	indices = documento.getTipo().getIndices();
            indexacao.clear();
            IndiceDocumento indiceDocumento = null;
            for (Indice indice : indices) {
            	indiceDocumento = new IndiceDocumento();
            	indiceDocumento.setDocumento(documento);
            	indiceDocumento.setIndice(indice);
            	indexacao.add(indiceDocumento);
            }
        } else {
        	indices = new ArrayList<Indice>();
        }
    }
	
	/**
	 * SALVA UM NOVO REGISTRO
	*/
	public void salvar(UploadedFile uploadedFile) throws Exception {
		try {
			File file = new File(PATH, uploadedFile.getFileName());
		    OutputStream out = new FileOutputStream(file);
		    out.write(uploadedFile.getContents());
		    out.close();
		     
            documentoService.salvar(documento, indexacao, PATH, file);
            Uteis.MensagemInfo("Documento importado com sucesso");
		} catch(IOException e) {
		  		FacesContext.getCurrentInstance().addMessage(null, 
		  				new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao importar", 
		  						e.getMessage()));
		} catch (Exception e) {
			Uteis.MensagemAtencao("Erro ao importar");
		}
		documento = new Documento();
		indexacao = new ArrayList<IndiceDocumento>();
	}
	
	/**
	 * BUSCANDO UM REGISTRO
	 */
	public void buscar() {
		documentos = new ArrayList<Documento>();
		for (Documento documento : documentoService.listarPor(documento.getTipo())) {
			for (IndiceDocumento indiceDocumento : documento.getIndexacao()) {
				if (indiceDocumento.getConteudo().contains(termos) && !documentos.contains(documento)) {
					documentos.add(documento);
				}
			}
		}
	}
	
	/**
	 * BUSCANDO UM REGISTRO
	 */
	public void buscarPublico() {
		documentos = new ArrayList<Documento>();
		for (Documento documento : documentoService.listarPublico(documento.getTipo())) {
			for (IndiceDocumento indiceDocumento : documento.getIndexacao()) {
				if (indiceDocumento.getConteudo().contains(termos) && !documentos.contains(documento)) {
					documentos.add(documento);
				}
			}
		}
	}
	
	/**
	 * EXCLUINDO UM REGISTRO
	 * @param documento
	 */
	public void excluir(Documento documento) {
		try {
			documentoService.excluir(documento);
			documentos.remove(documento);
			Uteis.MensagemInfo("Documento removido com sucesso");
		} catch (Exception e) {
			Uteis.MensagemAtencao("Erro ao excluir");
		}
		this.documento = new Documento();
	}
	
	public void carregarPDF(Documento documento) {
		Path caminho = Paths.get(documento.getUrl());
		String nome = caminho.getFileName().toString();
		pdf = "/resources/repositorio/"+ nome;
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return
	 */
	public List<Documento> getLista() {
		return documentoService.listar();
	}
	
	public Documento getDocumento() {
		return documento;
	}
	
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public List<IndiceDocumento> getIndexacao() {
		return indexacao;
	}
	
	public void setIndexacao(List<IndiceDocumento> indexacao) {
		this.indexacao = indexacao;
	}
	
	public List<Documento> getDocumentos() {
		return documentos;
	}
	
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
	public String getTermos() {
		return termos;
	}
	
	public void setTermos(String termos) {
		this.termos = termos;
	}
	
	public String getPdf() {
		return pdf;
	}
	
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

}
