package br.com.besche.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.DocumentoModel;
import br.com.besche.model.TipoModel;
import br.com.besche.modelo.DocumentoEntity;
import br.com.besche.modelo.IndiceDocumentoEntity;
import br.com.besche.modelo.Tipo;
import br.com.besche.uteis.Uteis;


public class DocumentoRepository {
	@Inject
	DocumentoEntity documentoEntity;
	EntityManager entityManager;
	
	/**
	 * RETORNA UM REGISTRO PELO ID
	 * @param id
	 * @return
	 */
	private DocumentoEntity getDocumento(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(DocumentoEntity.class, id);
	}
	
	/**
	 * SALVA UM NOVO REGISTRO
	 * @param registro
	 */
	public void salvar(DocumentoModel documentoModel) {
		entityManager = Uteis.JpaEntityManager();
		documentoEntity = new DocumentoEntity();
		documentoEntity.setUpload(LocalDateTime.now());
		documentoEntity.setUrl(documentoModel.getUrl());
		documentoEntity.setPrivado(documentoModel.isPrivado());
		
		Tipo tipo = entityManager.find(Tipo.class, documentoModel.getTipo().getId());
		documentoEntity.setTipo(tipo);
		
		entityManager.persist(documentoEntity);
	}

	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return 
	 */
	public List<DocumentoModel> listar() {
		List<DocumentoModel> documentosModel = new ArrayList<DocumentoModel>();
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("Documento.findAll");

		@SuppressWarnings("unchecked")
		List<DocumentoEntity> documentosEntity = (List<DocumentoEntity>) query.getResultList();
		DocumentoModel documentoModel = null;
		for (DocumentoEntity documentoEntity : documentosEntity) {
			documentoModel = new DocumentoModel();
			documentoModel.setId(documentoEntity.getId());
			documentoModel.setUpload(documentoEntity.getUpload());
			documentoModel.setUrl(documentoEntity.getUrl());
			documentoModel.setPrivado(documentoEntity.isPrivado());
			
			TipoModel tipoModel = new TipoModel();
			tipoModel.setNome(documentoEntity.getTipo().getNome());
			
			documentoModel.setTipo(tipoModel);
			documentosModel.add(documentoModel);
		}
		return documentosModel;
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS POR TIPO
	 * @return 
	 */
	public List<DocumentoEntity> getDocumentoPor(TipoModel tipo) {
		entityManager = Uteis.JpaEntityManager();
		//Query query = entityManager.createNamedQuery("IndiceDocumento.findAll");
		
		Query query = Uteis.JpaEntityManager().createNamedQuery("DocumentoEntity.porTipo");

		// PARÂMETROS DA QUERY
		query.setParameter("idDoTipo", tipo.getId());
		//query.setParameter("termos", termos);

		@SuppressWarnings("unchecked")
		List<DocumentoEntity> registros = (List<DocumentoEntity>) query.getResultList();
		return registros;
	}

	public List<DocumentoEntity> buscaDocumento(Long idTipoDocumento, String termo) {
		entityManager = Uteis.JpaEntityManager();
		
		String str= "SELECT doc FROM DocumentoEntity doc LEFT JOIN FETCH DocumentoEntity.indexacao AS index Where index.conteudo LIKE :conteudo AND doc.tipo.id=:idTipoDocumento"; 
		
		Query query = entityManager.createQuery(str).setParameter("idTipoDocumento", idTipoDocumento).setParameter("conteudo", "%"+ termo + "%");
		
		@SuppressWarnings("unchecked")
		List<DocumentoEntity> registros = (List<DocumentoEntity>) query.getResultList();
		return registros;
	}
	
	/**
	 * ALTERA UM REGISTRO
	 * @param documento
	 */
	public void alterar(DocumentoModel documentoModel) {
		entityManager = Uteis.JpaEntityManager();
		DocumentoEntity documentoEntity = this.getDocumento(documentoModel.getId());
		//
		entityManager.merge(documentoEntity);
	}

	/**
	 * EXCLUI UM REGISTRO PELO ID
	 * @param id
	 */
	public void excluir(Long id) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(this.getDocumento(id));
	}
	
}
