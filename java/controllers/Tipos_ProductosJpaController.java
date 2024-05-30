/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import Excepciones.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.TiposProductos;

/**
 *
 * @author Rosío
 */
public class Tipos_ProductosJpaController implements Serializable {

    public Tipos_ProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposProductos tipos_Productos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipos_Productos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposProductos tipos_Productos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipos_Productos = em.merge(tipos_Productos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipos_Productos.getTipoId();
                if (findTipos_Productos(id) == null) {
                    throw new NonexistentEntityException("The tipos_Productos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposProductos tipos_Productos;
            try {
                tipos_Productos = em.getReference(TiposProductos.class, id);
                tipos_Productos.getTipoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipos_Productos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipos_Productos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposProductos> findTipos_ProductosEntities() {
        return findTipos_ProductosEntities(true, -1, -1);
    }

    public List<TiposProductos> findTipos_ProductosEntities(int maxResults, int firstResult) {
        return findTipos_ProductosEntities(false, maxResults, firstResult);
    }

    private List<TiposProductos> findTipos_ProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TiposProductos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TiposProductos findTipos_Productos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposProductos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipos_ProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TiposProductos> rt = cq.from(TiposProductos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
