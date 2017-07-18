package br.com.besche.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.besche.model.IndiceModel;
import br.com.besche.repository.entity.IndiceEntity;
import br.com.besche.uteis.Uteis;

// classe responsável por persistir o índice
public class IndiceRepository {
	
	@Inject
	IndiceEntity indiceEntity;
	
	EntityManager entityManager;
	
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM ÍNDICE
	 * @param indiceModel
	 */

	public void SalvarNovoIndice(IndiceModel indiceModel) {
		entityManager = Uteis.JpaEntityManager();
		
		indiceEntity = new IndiceEntity();
		indiceEntity.setNome(indiceModel.getNome());
		
		entityManager.persist(indiceEntity);	 
		
	}
}
