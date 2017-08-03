package br.com.besche.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.IndiceModel;
import br.com.besche.repository.entity.IndiceEntity;
import br.com.besche.uteis.Uteis;

// classe responsável por persistir o índice
public class IndiceRepository {
	@Inject
	IndiceEntity indiceEntity;
	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM INDICE
	 * @param indiceModel
	 */
	public void SalvarNovoIndice(IndiceModel indiceModel) {
		entityManager = Uteis.JpaEntityManager();
		indiceEntity = new IndiceEntity();
		indiceEntity.setNome(indiceModel.getNome());
		entityManager.persist(indiceEntity);
	}

	/***
	 * MÉTODO PARA CONSULTAR O INDICE
	 * @return
	 */
	public List<IndiceModel> GetIndices() {
		List<IndiceModel> indicesModel = new ArrayList<IndiceModel>();
		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("IndiceEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<IndiceEntity> indicesEntity = (Collection<IndiceEntity>) query.getResultList();

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
	 * CONSULTA UM INDICE CADASTRADO PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	public IndiceEntity GetIndice(Long codigo) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(IndiceEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param indiceModel
	 */
	public void AlterarRegistro(IndiceModel indiceModel) {
		entityManager = Uteis.JpaEntityManager();
		IndiceEntity indiceEntity = this.GetIndice(indiceModel.getId());
		indiceEntity.setNome(indiceModel.getNome());
		entityManager.merge(indiceEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(Long codigo) {
		entityManager = Uteis.JpaEntityManager();
		IndiceEntity indiceEntity = this.GetIndice(codigo);
		entityManager.remove(indiceEntity);
	}
	
}
