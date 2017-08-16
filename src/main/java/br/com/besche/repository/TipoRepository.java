package br.com.besche.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.IndiceModel;
import br.com.besche.model.TipoModel;
import br.com.besche.repository.entity.IndiceEntity;
import br.com.besche.repository.entity.TipoEntity;
import br.com.besche.uteis.Uteis;

public class TipoRepository {
	@Inject
	TipoEntity tipoEntity;
	EntityManager entityManager;

	/**
	 * RETORNA UM REGISTRO PELO ID
	 * @param id
	 * @return
	 */
	private TipoEntity getTipo(Long id) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(TipoEntity.class, id);
	}
	
	/**
	 * SALVA UM NOVO REGISTRO
	 * @param registro
	 */
	public void salvar(TipoModel tipoModel) {
		entityManager = Uteis.JpaEntityManager();
		tipoEntity = new TipoEntity();
		tipoEntity.setNome(tipoModel.getNome());
		tipoEntity.setTemporalidade(tipoModel.getTemporalidade());
		tipoEntity.setIndices(new ArrayList<IndiceEntity>());
		
		entityManager.persist(tipoEntity);
	}
	
	/**
	 * RETORNA OS INDICES DE UM REGISTRO
	 * @param tipoEntity
	 * @return
	 */
	private List<IndiceModel> getIndices(TipoEntity tipoEntity) {
		List<IndiceEntity> indicesEntity = (List<IndiceEntity>) tipoEntity.getIndices();
		
		List<IndiceModel> indicesModel = new ArrayList<IndiceModel>();
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
	 * RETORNA TODOS OS REGISTROS
	 * @return 
	 */
	public List<TipoModel> listar() {
		List<TipoModel> tiposModel = new ArrayList<TipoModel>();
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("TipoEntity.findAll");

		@SuppressWarnings("unchecked")
		List<TipoEntity> tiposEntity = (List<TipoEntity>) query.getResultList();
		TipoModel tipoModel = null;
		for (TipoEntity tipoEntity : tiposEntity) {
			tipoModel = new TipoModel();
			tipoModel.setId(tipoEntity.getId());
			tipoModel.setNome(tipoEntity.getNome());
			tipoModel.setTemporalidade(tipoEntity.getTemporalidade());
			tipoModel.setIndices(getIndices(tipoEntity));
			tiposModel.add(tipoModel);
		}
		return tiposModel;
	}

	/**
	 * ALTERA UM REGISTRO
	 * @param registro
	 */
	public void alterar(TipoModel tipoModel) {
		entityManager = Uteis.JpaEntityManager();
		TipoEntity tipoEntity = this.getTipo(tipoModel.getId());
		tipoEntity.setNome(tipoModel.getNome());
		tipoEntity.setTemporalidade(tipoModel.getTemporalidade());
		
		List<IndiceEntity> indicesEntity = new ArrayList<IndiceEntity>();
		IndiceEntity indiceEntity = null;
		for (IndiceModel indiceModel : tipoModel.getIndices()) {
			indiceEntity = new IndiceEntity();
			indiceEntity.setId(indiceModel.getId());
			indiceEntity.setNome(indiceModel.getNome());
			indicesEntity.add(indiceEntity);
		}
		tipoEntity.setIndices(indicesEntity);
		
		entityManager.merge(tipoEntity);
	}

	/**
	 * EXCLUI UM REGISTRO PELO ID
	 * @param id
	 */
	public void excluir(Long id) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(this.getTipo(id));
	}
	
}
