package br.com.besche.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.DocumentoModel;
import br.com.besche.repository.entity.ContentorEntity;
import br.com.besche.repository.entity.DocumentoEntity;
import br.com.besche.repository.entity.TipoEntity;
import br.com.besche.uteis.Uteis;

public class DocumentoRepository {
	@Inject
	DocumentoEntity documentoEntity;
	EntityManager entityManager;
	
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM DOCUMENTO
	 * @param documentoModel
	 */
	public void SalvarNovoDocumento(DocumentoModel documentoModel) {
		entityManager = Uteis.JpaEntityManager();
		documentoEntity = new DocumentoEntity();
		documentoEntity.setUpload(LocalDateTime.now());
		documentoEntity.setUrl(documentoModel.getUrl());
		TipoEntity tipoEntity = entityManager.find(TipoEntity.class, documentoModel.getTipo().getId());
		documentoEntity.setTipo(tipoEntity);
		ContentorEntity contentorEntity = entityManager.find(ContentorEntity.class,
				documentoModel.getContentor().getId());
		documentoEntity.setContentor(contentorEntity);
		entityManager.persist(documentoEntity);
	}

	/***
	 * MÉTODO PARA CONSULTAR O INDICE
	 * @return
	 */
	public List<DocumentoModel> GetDocumentos() {
		List<DocumentoModel> documentosModel = new ArrayList<DocumentoModel>();
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("DocumentoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<DocumentoEntity> documentosEntity = (Collection<DocumentoEntity>) query.getResultList();
		DocumentoModel documentoModel = null;
		for (DocumentoEntity documentoEntity : documentosEntity) {
			documentoModel = new DocumentoModel();
			documentoModel.setId(documentoEntity.getId());
			documentoModel.setUpload(documentoEntity.getUpload());
			documentoModel.setUrl(documentoEntity.getUrl());
			//documentoModel.setTipo(documentoEntity.getTipo());
			documentosModel.add(documentoModel);
		}
		return documentosModel;
	}

	/***
	 * CONSULTA UM INDICE CADASTRADO PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	private DocumentoEntity GetDocumento(Long codigo) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(DocumentoEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param documentoModel
	 */
	public void AlterarRegistro(DocumentoModel documentoModel) {
		entityManager = Uteis.JpaEntityManager();
		DocumentoEntity documentoEntity = this.GetDocumento(documentoModel.getId());
		//documentoEntity.setNome(documentoModel.getNome());
		entityManager.merge(documentoEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(Long codigo) {
		entityManager = Uteis.JpaEntityManager();
		DocumentoEntity documentoEntity = this.GetDocumento(codigo);
		entityManager.remove(documentoEntity);
	}
}
