package br.com.besche.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	 * EXCLUI UM REGISTRO PELO ID
	 * @param id
	 */
	public void excluir(Documento objeto) {
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
	
}
