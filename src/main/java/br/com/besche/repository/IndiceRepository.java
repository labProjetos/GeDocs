package br.com.besche.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.IndiceModel;
import br.com.besche.repository.entity.IndiceEntity;
import br.com.besche.uteis.Uteis;

public class IndiceRepository {
	@Inject
	IndiceEntity indiceEntity;
	EntityManager entityManager;

	/**
	 * RETORNA UM REGISTRO PELO ID
	 * @param id
	 * @return
	 */
	private IndiceEntity getIndice(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(IndiceEntity.class, id);
	}
	
	/**
	 * SALVA UM NOVO REGISTRO
	 * @param registro
	 */
	public void salvar(IndiceModel indiceModel) {
		entityManager = Uteis.JpaEntityManager();
		indiceEntity = new IndiceEntity();
		indiceEntity.setNome(indiceModel.getNome());
		
		entityManager.persist(indiceEntity);
	}

	/**
	 * RETORNA TODOS OS REGISTROS
	 * @return 
	 */
	public List<IndiceModel> listar() {
		List<IndiceModel> indicesModel = new ArrayList<IndiceModel>();
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("IndiceEntity.findAll");

		@SuppressWarnings("unchecked")
		List<IndiceEntity> indicesEntity = (List<IndiceEntity>) query.getResultList();

		IndiceModel indiceModel = null;
		for (IndiceEntity indiceEntity : indicesEntity) {
			indiceModel = new IndiceModel();
			indiceModel.setId(indiceEntity.getId());
			indiceModel.setNome(indiceEntity.getNome());

			indicesModel.add(indiceModel);
		}
		return indicesModel;
	}

	/**
	 * ALTERA UM REGISTRO
	 * @param registro
	 */
	public void alterar(IndiceModel indiceModel) {
		entityManager = Uteis.JpaEntityManager();
		IndiceEntity indiceEntity = this.getIndice(indiceModel.getId());
		indiceEntity.setNome(indiceModel.getNome());
		entityManager.merge(indiceEntity);
	}

	/**
	 * EXCLUI UM REGISTRO PELO ID
	 * @param id
	 */
	public void excluir(Long id) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(this.getIndice(id));
	}
	
}
