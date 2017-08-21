package br.com.besche.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.DocumentoModel;
import br.com.besche.model.IndiceDocumentoModel;
import br.com.besche.model.TipoModel;
import br.com.besche.modelo.DocumentoEntity;
import br.com.besche.modelo.IndiceDocumentoEntity;
import br.com.besche.modelo.Indice;
import br.com.besche.modelo.Tipo;
import br.com.besche.uteis.Uteis;

public class IndiceDocumentoRepository {
	@Inject
	IndiceDocumentoEntity indiceDocumentoEntity;
	EntityManager entityManager;
	
	/**
	 * SALVA UM NOVO REGISTRO
	 * @param registro
	 */
	public void salvar(List<IndiceDocumentoModel> indicesDocumentoModel, DocumentoModel documentoModel) {
		List<IndiceDocumentoEntity> indexacao = new ArrayList<IndiceDocumentoEntity>();
		entityManager = Uteis.JpaEntityManager();
		
		DocumentoEntity documentoEntity = new DocumentoEntity();
		documentoEntity.setUpload(LocalDateTime.now());
		documentoEntity.setUrl(documentoModel.getUrl());
		documentoEntity.setPrivado(documentoModel.isPrivado());
		
		Tipo tipo = entityManager.find(Tipo.class, 
				documentoModel.getTipo().getId());
		documentoEntity.setTipo(tipo);
		
		for (IndiceDocumentoModel indiceDocumentoModel : indicesDocumentoModel) {
			indiceDocumentoEntity = new IndiceDocumentoEntity();
			indiceDocumentoEntity.setConteudo(indiceDocumentoModel.getConteudo());
			indiceDocumentoEntity.setDocumento(documentoEntity);
			Indice indice = 
					entityManager.find(Indice.class, indiceDocumentoModel.getIndiceModel().getId());
			indiceDocumentoEntity.setIndice(indice);
			
			indexacao.add(indiceDocumentoEntity);
		}
		
		documentoEntity.setIndexacao(indexacao);
		entityManager.persist(documentoEntity);
	}

	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return 
	 */
	public List<IndiceDocumentoEntity> getIndicesDocumento() {
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("IndiceDocumento.findAll");

		@SuppressWarnings("unchecked")
		List<IndiceDocumentoEntity> registros = (List<IndiceDocumentoEntity>) query.getResultList();
		return registros;
	}
	
	/**
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
	}*/

	/**
	 * RETORNA UM REGISTRO PELO ID
	 * @param id
	 * @return
	 */
	private IndiceDocumentoEntity getIndiceDocumentoPor(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(IndiceDocumentoEntity.class, id);
	}

	/**
	 * ALTERA UM REGISTRO
	 * @param registro
	 */
	public void alterar(IndiceDocumentoEntity registro) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(this.getIndiceDocumentoPor(registro.getId()));
	}

	/**
	 * EXCLUI UM REGISTRO PELO ID
	 * @param id
	 */
	public void excluirPor(Long id) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(this.getIndiceDocumentoPor(id));
	}
	
}
