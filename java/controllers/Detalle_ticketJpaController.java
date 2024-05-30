package controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Excepciones.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Detalle_Ticket;

/**
 *
 * @author Rosío
 */
public class Detalle_ticketJpaController implements Serializable {

    public Detalle_ticketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalle_Ticket detalle_ticket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detalle_ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalle_Ticket detalle_ticket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detalle_ticket = em.merge(detalle_ticket);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalle_ticket.getDetalleId();
                if (findDetalle_ticket(id) == null) {
                    throw new NonexistentEntityException("The detalle_ticket with id " + id + " no longer exists.");
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
            Detalle_Ticket detalle_ticket;
            try {
                detalle_ticket = em.getReference(Detalle_Ticket.class, id);
                detalle_ticket.getDetalleId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalle_ticket with id " + id + " no longer exists.", enfe);
            }
            em.remove(detalle_ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalle_Ticket> findDetalle_ticketEntities() {
        return findDetalle_ticketEntities(true, -1, -1);
    }

    public List<Detalle_Ticket> findDetalle_ticketEntities(int maxResults, int firstResult) {
        return findDetalle_ticketEntities(false, maxResults, firstResult);
    }

    private List<Detalle_Ticket> findDetalle_ticketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalle_Ticket.class));
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

    public Detalle_Ticket findDetalle_ticket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalle_Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalle_ticketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalle_Ticket> rt = cq.from(Detalle_Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
