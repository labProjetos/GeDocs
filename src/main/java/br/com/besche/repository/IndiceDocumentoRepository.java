package br.com.besche.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.besche.model.DocumentoModel;
import br.com.besche.model.IndiceDocumentoModel;
import br.com.besche.repository.entity.ContentorEntity;
import br.com.besche.repository.entity.DocumentoEntity;
import br.com.besche.repository.entity.IndiceDocumentoEntity;
import br.com.besche.repository.entity.IndiceEntity;
import br.com.besche.repository.entity.TipoEntity;
import br.com.besche.uteis.Uteis;

public class IndiceDocumentoRepository {
	@Inject
	IndiceDocumentoEntity indiceDocumentoEntity;
	EntityManager entityManager;
	
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM DOCUMENTO
	 * @param documentoModel
	 */
	public void SalvarNovaIndexacao(List<IndiceDocumentoModel> indicesDocumentoModel, DocumentoModel documentoModel) {
		List<IndiceDocumentoEntity> indexacao = new ArrayList<IndiceDocumentoEntity>();
		entityManager = Uteis.JpaEntityManager();
		
		DocumentoEntity documentoEntity = new DocumentoEntity();
		documentoEntity.setUpload(LocalDateTime.now());
		documentoEntity.setUrl(documentoModel.getUrl());
		ContentorEntity contentorEntity = entityManager.find(ContentorEntity.class,
				documentoModel.getContentor().getId());
		documentoEntity.setContentor(contentorEntity);
		TipoEntity tipoEntity = entityManager.find(TipoEntity.class, 
				documentoModel.getTipo().getId());
		documentoEntity.setTipo(tipoEntity);
		
		for (IndiceDocumentoModel indiceDocumentoModel : indicesDocumentoModel) {
			indiceDocumentoEntity = new IndiceDocumentoEntity();
			indiceDocumentoEntity.setConteudo(indiceDocumentoModel.getConteudo());
			indiceDocumentoEntity.setDocumentoEntity(documentoEntity);
			IndiceEntity indiceEntity = 
					entityManager.find(IndiceEntity.class, indiceDocumentoModel.getIndiceModel().getId());
			indiceDocumentoEntity.setIndiceEntity(indiceEntity);
			
			indexacao.add(indiceDocumentoEntity);
		}
		
		documentoEntity.setIndexacao(indexacao);
		entityManager.persist(documentoEntity);
	}
}
