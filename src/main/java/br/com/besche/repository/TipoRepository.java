package br.com.besche.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.IndiceModel;
import br.com.besche.model.TipoModel;
import br.com.besche.repository.entity.IndiceEntity;
import br.com.besche.repository.entity.TipoEntity;
import br.com.besche.uteis.Uteis;

//classe responsável por persistir o tipo
public class TipoRepository {
	@Inject
	TipoEntity tipoEntity;
	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM TIPO
	 * @param tipoModel
	 */
	public void SalvarNovoTipo(TipoModel tipoModel) {
		entityManager = Uteis.JpaEntityManager();
		tipoEntity = new TipoEntity();
		tipoEntity.setNome(tipoModel.getNome());
		tipoEntity.setTemporalidade(tipoModel.getTemporalidade());
		tipoEntity.setIndices(new ArrayList<IndiceEntity>());
		entityManager.persist(tipoEntity);
	}
	
	/**
	 * MÉTODO QUE RETORNA A LISTA DE INDICES DO TIPO
	 * @param tipoEntity
	 * @return
	 */
	private List<IndiceModel> GetIndicesModelDo(TipoEntity tipoEntity) {
		Collection<IndiceEntity> indicesEntity = (Collection<IndiceEntity>) tipoEntity.getIndices();
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

	/***
	 * MÉTODO PARA CONSULTAR O TIPO
	 * @return
	 */
	public List<TipoModel> GetTipos() {
		List<TipoModel> tiposModel = new ArrayList<TipoModel>();
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("TipoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<TipoEntity> tiposEntity = (Collection<TipoEntity>) query.getResultList();
		TipoModel tipoModel = null;
		for (TipoEntity tipoEntity : tiposEntity) {
			tipoModel = new TipoModel();
			tipoModel.setId(tipoEntity.getId());
			tipoModel.setNome(tipoEntity.getNome());
			tipoModel.setTemporalidade(tipoEntity.getTemporalidade());
			tipoModel.setIndices(GetIndicesModelDo(tipoEntity));
			tiposModel.add(tipoModel);
		}
		return tiposModel;
	}

	/***
	 * CONSULTA UM TIPO CADASTRADO PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	public TipoEntity GetTipo(Long codigo) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(TipoEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param tipoModel
	 */
	public void AlterarRegistro(TipoModel tipoModel) {
		entityManager = Uteis.JpaEntityManager();
		TipoEntity tipoEntity = this.GetTipo(tipoModel.getId());
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

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(Long codigo) {
		entityManager = Uteis.JpaEntityManager();
		TipoEntity tipoEntity = this.GetTipo(codigo);
		entityManager.remove(tipoEntity);
	}
}
