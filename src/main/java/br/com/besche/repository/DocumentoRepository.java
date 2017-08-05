package br.com.besche.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.besche.model.ContentorModel;
import br.com.besche.model.DocumentoModel;
import br.com.besche.repository.entity.ContentorEntity;
import br.com.besche.repository.entity.DocumentoEntity;
import br.com.besche.uteis.Uteis;

public class DocumentoRepository{
	@Inject
	DocumentoEntity documentoEntity;
	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR REGISTRA O SALVAMENTO DO ARQUIVO
	 * @param documentoModel
	 */
	
	public void SalvarDocumento(DocumentoModel documentoModel) {
	
	
	}
	
	
	public List<ContentorModel> GetContentores(){
		List<ContentorModel> contentoresModel = new ArrayList<ContentorModel>();
		entityManager =  Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("ContentorEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<ContentorEntity> contentoresEntity = (Collection<ContentorEntity>)query.getResultList();
 
		ContentorModel contentorModel = null;
		for (ContentorEntity contentorEntity : contentoresEntity) {
			contentorModel = new ContentorModel();
			contentorModel.setId(contentorEntity.getId());
			contentorModel.setNome(contentorEntity.getNome());
			
			contentoresModel.add(contentorModel);
		}
		return contentoresModel;
	}
	
	/***
	 * CONSULTA UM CONTENTOR CADASTRADO PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	private ContentorEntity GetContentor(int codigo) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(ContentorEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param contentorModel
	 */
	public void AlterarRegistro(ContentorModel contentorModel) {
		entityManager = Uteis.JpaEntityManager();
		ContentorEntity contentorEntity = this.GetContentor(contentorModel.getId());
		contentorEntity.setNome(contentorModel.getNome());
		entityManager.merge(contentorEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {
		entityManager = Uteis.JpaEntityManager();
		ContentorEntity contentorEntity = this.GetContentor(codigo);
		entityManager.remove(contentorEntity);
	}
}
