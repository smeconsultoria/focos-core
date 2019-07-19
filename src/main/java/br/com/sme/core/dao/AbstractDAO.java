package br.com.sme.core.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sme.core.entity.DefaultEntity;


public abstract class AbstractDAO<T extends Serializable>
{

  private Class<T> persistentClass;

  @PersistenceContext(name = "focosPU")
  protected EntityManager em;

  public AbstractDAO()
  {

  }

  public void setEm(EntityManager em)
  {

    this.em = em;

  }

  public T mergeNoFlush(T entity)
  {

    if (entity == null)
    {
      throw new IllegalArgumentException("entity nao pode ser null");
    }

    T entityMerged = em.merge(entity);

    return entityMerged;

  }

  public T mergeWithFlush(T entity)
  {

    T entityMerged = this.mergeNoFlush(entity);

    em.flush();

    return entityMerged;

  }

  public void persist(T entity) throws Exception
  {

    if (entity == null)
    {

      throw new IllegalArgumentException("entity năo pode ser null");

    }

    em.persist(entity);

  }

  public T persistWithFlush(T entity) throws Exception
  {

    persist(entity);

    em.flush();

    return entity;

  }

  public T find(Long id)
  {

    if (id == null)
    {
      throw new IllegalArgumentException("id năo pode ser null");
    }

    return em.find(this.persistentClass, id);

  }

  public T getReference(Long id)
  {

    if (id == null)
    {
      throw new IllegalArgumentException("id năo pode ser null");
    }

    return em.getReference(this.persistentClass, id);

  }

  public T refresh(Long id)
  {

    T t = (T) em.find(persistentClass, id);

    if (t != null)
    {
      em.refresh(t);
    }

    return t;

  }

  public void remove(T entity)
  {

    Long id = ((DefaultEntity) entity).getId();
    entity = getReference(id);
    em.remove(entity);

  }

  @SuppressWarnings("unchecked")
  public List<T> findAll() throws Exception
  {
    return em.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
  }

  public Class<T> getPersistentClass()
  {
    return persistentClass;
  }

  public void setPersistentClass(Class<T> persistentClass)
  {
    this.persistentClass = persistentClass;
  }

}
