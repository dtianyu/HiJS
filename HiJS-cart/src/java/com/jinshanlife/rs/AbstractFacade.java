/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.rs;

import com.jinshanlife.comm.SuperEJB;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author C0160
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    private SuperEJB superEJB;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        getSuperEJB().persist(entity);
    }

    public void edit(T entity) {
        getSuperEJB().update(entity);
    }

    public void remove(T entity) {
        getSuperEJB().delete(entity);
    }

    public T find(Object id) {
        return (T)getSuperEJB().findById(id);
    }

    public List<T> findAll() {
        return getSuperEJB().findAll();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getSuperEJB().getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getSuperEJB().getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getSuperEJB().getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getSuperEJB().getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getSuperEJB().getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * @return the superEJB
     */
    public SuperEJB getSuperEJB() {
        return superEJB;
    }

    /**
     * @param superEJB the superEJB to set
     */
    public void setSuperEJB(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

}
