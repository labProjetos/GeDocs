package br.com.besche.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.DocumentoModel;
import br.com.besche.model.TipoModel;
import br.com.besche.modelo.Documento;
import br.com.besche.modelo.Tipo;
import br.com.besche.uteis.Uteis;


public class DocumentoRepository {
	@Inject
	Documento documento;
	EntityManager entityManager;
	
	/**
	 * SALVA UM NOVO REGISTRO
	 * @param registro
	 */
	public void salvar(Documento documento) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.persist(documento);
	}
	
	/**
	 * ALTERA UM REGISTRO
	 * @param indice
	 */
	public void alterar(Documento objeto) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(objeto);
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return 
	 */
	public List<Documento> findAll() {
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("Documento.findAll");
		
		@SuppressWarnings("unchecked")
		List<Documento> documentos = query.getResultList();
		return documentos;
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS POR TIPO
	 * @return 
	 */
	public List<Documento> findPor(Tipo tipo) {
		entityManager = Uteis.JpaEntityManager();
		Query query = Uteis.JpaEntityManager().createNamedQuery("Documento.porTipo");
		query.setParameter("idDoTipo", tipo.getId());
		@SuppressWarnings("unchecked")
		List<Documento> registros = (List<Documento>) query.getResultList();
		return registros;
	}
	
	
	/**
	 * RETORNA UM REGISTRO PELO ID
	 * @param id
	 * @return
	 */
	private Documento getDocumento(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(Documento.class, id);
	}
	
	/**
	 * SALVA UM NOVO REGISTRO
	 * @param registro
	 */
	public void salvar(DocumentoModel documentoModel) {
		entityManager = Uteis.JpaEntityManager();
		documento = new Documento();
		documento.setUpload(LocalDateTime.now());
		documento.setUrl(documentoModel.getUrl());
		documento.setPrivado(documentoModel.isPrivado());
		
		Tipo tipo = entityManager.find(Tipo.class, documentoModel.getTipo().getId());
		documento.setTipo(tipo);
		
		entityManager.persist(documento);
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
		List<Documento> documentosEntity = (List<Documento>) query.getResultList();
		DocumentoModel documentoModel = null;
		for (Documento documento : documentosEntity) {
			documentoModel = new DocumentoModel();
			documentoModel.setId(documento.getId());
			documentoModel.setUpload(documento.getUpload());
			documentoModel.setUrl(documento.getUrl());
			documentoModel.setPrivado(documento.isPrivado());
			
			TipoModel tipoModel = new TipoModel();
			tipoModel.setNome(documento.getTipo().getNome());
			
			documentoModel.setTipo(tipoModel);
			documentosModel.add(documentoModel);
		}
		return documentosModel;
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS POR TIPO
	 * @return 
	 */
	public List<Documento> getDocumentoPor(TipoModel tipo) {
		entityManager = Uteis.JpaEntityManager();
		//Query query = entityManager.createNamedQuery("IndiceDocumento.findAll");
		
		Query query = Uteis.JpaEntityManager().createNamedQuery("DocumentoEntity.porTipo");

		// PARÂMETROS DA QUERY
		query.setParameter("idDoTipo", tipo.getId());
		//query.setParameter("termos", termos);

		@SuppressWarnings("unchecked")
		List<Documento> registros = (List<Documento>) query.getResultList();
		return registros;
	}

	public List<Documento> buscaDocumento(Long idTipoDocumento, String termo) {
		entityManager = Uteis.JpaEntityManager();
		
		String str= "SELECT doc FROM DocumentoEntity doc LEFT JOIN FETCH DocumentoEntity.indexacao AS index Where index.conteudo LIKE :conteudo AND doc.tipo.id=:idTipoDocumento"; 
		
		Query query = entityManager.createQuery(str).setParameter("idTipoDocumento", idTipoDocumento).setParameter("conteudo", "%"+ termo + "%");
		
		@SuppressWarnings("unchecked")
		List<Documento> registros = (List<Documento>) query.getResultList();
		return registros;
	}
	
	/**
	 * ALTERA UM REGISTRO
	 * @param documento
	 */
	public void alterar(DocumentoModel documentoModel) {
		entityManager = Uteis.JpaEntityManager();
		Documento documento = this.getDocumento(documentoModel.getId());
		//
		entityManager.merge(documento);
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
