package br.com.besche.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.modelo.Indice;
import br.com.besche.uteis.Uteis;

public class IndiceRepository {
	@Inject
	Indice indice;
	EntityManager entityManager;
	
	/**
	 * SALVA UM NOVO REGISTRO
	 * @param indice
	 */
	public void salvar(Indice objeto) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.persist(objeto);
	}
	
	/**
	 * ALTERA UM REGISTRO
	 * @param indice
	 */
	public void alterar(Indice objeto) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.merge(objeto);
	}
	
	/**
	 * EXCLUI UM REGISTRO PELO ID
	 * @param id
	 */
	public void excluir(Indice objeto) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(objeto);
	}
	
	/**
	 * RETORNA UM REGISTRO PELO ID
	 * @param id
	 * @return
	 */
	public Indice findBy(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(Indice.class, id);
	}
	
	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return 
	 */
	public List<Indice> findAll() {
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("Indice.findAll");
		
		@SuppressWarnings("unchecked")
		List<Indice> objetos = query.getResultList();
		return objetos;
	}
	
}
