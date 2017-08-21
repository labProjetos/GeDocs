package br.com.besche.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.modelo.Tipo;
import br.com.besche.uteis.Uteis;

public class TipoRepository {
	@Inject
	Tipo tipo;
	EntityManager entityManager;
	
	/**
	 * SALVA UM NOVO REGISTRO
	 * @param indice
	 */
	public void salvar(Tipo objeto) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.persist(objeto);
	}
	
	/**
	 * ALTERA UM REGISTRO
	 * @param indice
	 */
	public void alterar(Tipo objeto) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(objeto);
	}
	
	/**
	 * EXCLUI UM REGISTRO PELO ID
	 * @param id
	 */
	public void excluir(Tipo objeto) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(objeto);
	}
	
	/**
	 * RETORNA UM REGISTRO PELO ID
	 * @param id
	 * @return
	 */
	public Tipo findby(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(Tipo.class, id);
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return 
	 */
	public List<Tipo> findAll() {
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("Tipo.findAll");
		
		@SuppressWarnings("unchecked")
		List<Tipo> tipos = query.getResultList();
		return tipos;
	}
	
}
