/*package br.com.besche.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.DocumentoModel;
import br.com.besche.model.IndiceDocumentoModel;
import br.com.besche.model.TipoModel;
import br.com.besche.modelo.Documento;
import br.com.besche.modelo.IndiceDocumento;
import br.com.besche.modelo.Indice;
import br.com.besche.modelo.Tipo;
import br.com.besche.uteis.Uteis;

public class IndiceDocumentoRepository {
	@Inject
	IndiceDocumento indiceDocumento;
	EntityManager entityManager;
	
	*//**
	 * SALVA UM NOVO REGISTRO
	 * @param registro
	 *//*
	public void salvar(List<IndiceDocumentoModel> indicesDocumentoModel, Documento documentoModel) {
		List<IndiceDocumento> indexacao = new ArrayList<IndiceDocumento>();
		entityManager = Uteis.JpaEntityManager();
		
		Documento documento = new Documento();
		documento.setUpload(LocalDateTime.now());
		documento.setUrl(documentoModel.getUrl());
		documento.setPrivado(documentoModel.isPrivado());
		
		Tipo tipo = entityManager.find(Tipo.class, 
				documentoModel.getTipo().getId());
		documento.setTipo(tipo);
		
		for (IndiceDocumentoModel indiceDocumentoModel : indicesDocumentoModel) {
			indiceDocumento = new IndiceDocumento();
			indiceDocumento.setConteudo(indiceDocumentoModel.getConteudo());
			indiceDocumento.setDocumento(documento);
			Indice indice = 
					entityManager.find(Indice.class, indiceDocumentoModel.getIndiceModel().getId());
			indiceDocumento.setIndice(indice);
			
			indexacao.add(indiceDocumento);
		}
		
		documento.setIndexacao(indexacao);
		entityManager.persist(documento);
	}

	*//**
	 * RETORNA TODOS OS REGISTROS
	 * @return 
	 *//*
	public List<IndiceDocumento> getIndicesDocumento() {
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("IndiceDocumento.findAll");

		@SuppressWarnings("unchecked")
		List<IndiceDocumento> registros = (List<IndiceDocumento>) query.getResultList();
		return registros;
	}
	
	*//**
	 * RETORNA TODOS OS REGISTROS POR TIPO
	 * @return 
	 *//*
	public List<IndiceDocumentoEntity> getIndicesDocumentoPor(TipoModel tipo) {
		entityManager = Uteis.JpaEntityManager();
		//Query query = entityManager.createNamedQuery("IndiceDocumento.findAll");
		
		Query query = Uteis.JpaEntityManager().createNamedQuery("IndiceDocumentoEntity.porTipo");

		// PARÂMETROS DA QUERY
		query.setParameter("idDoTipo", tipo.getId());

		@SuppressWarnings("unchecked")
		List<IndiceDocumentoEntity> registros = (List<IndiceDocumentoEntity>) query.getResultList();
		return registros;
	}

	*//**
	 * RETORNA UM REGISTRO PELO ID
	 * @param id
	 * @return
	 *//*
	private IndiceDocumento getIndiceDocumentoPor(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(IndiceDocumento.class, id);
	}

	*//**
	 * ALTERA UM REGISTRO
	 * @param registro
	 *//*
	public void alterar(IndiceDocumento registro) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(this.getIndiceDocumentoPor(registro.getId()));
	}

	*//**
	 * EXCLUI UM REGISTRO PELO ID
	 * @param id
	 *//*
	public void excluirPor(Long id) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(this.getIndiceDocumentoPor(id));
	}
	
}
*/