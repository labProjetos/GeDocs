package br.com.besche.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.besche.model.ContentorModel;
import br.com.besche.repository.entity.ContentorEntity;
import br.com.besche.uteis.Uteis;

public class ContentorRepository {
	@Inject
	ContentorEntity contentorEntity;
	EntityManager entityManager;
 
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * @param contentorModel
	 */
	public void SalvarNovoRegistro(ContentorModel contentorModel){
		entityManager =  Uteis.JpaEntityManager();
		contentorEntity = new ContentorEntity();
		contentorEntity.setNome(contentorModel.getNome());
		entityManager.persist(contentorEntity);
	}
}
