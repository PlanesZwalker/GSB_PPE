/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.controlleurs;

import ApplicationGSB.controlleurs.exceptions.NonexistentEntityException;
import ApplicationGSB.controlleurs.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ApplicationGSB.modeles.Rapportdevisite;
import ApplicationGSB.modeles.Visiteurmedical;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author passpass
 */
public class VisiteurmedicalJpaController implements Serializable {

    public VisiteurmedicalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Visiteurmedical visiteurmedical) throws PreexistingEntityException, Exception {
        if (visiteurmedical.getRapportdevisiteCollection() == null) {
            visiteurmedical.setRapportdevisiteCollection(new ArrayList<Rapportdevisite>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Rapportdevisite> attachedRapportdevisiteCollection = new ArrayList<Rapportdevisite>();
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisiteToAttach : visiteurmedical.getRapportdevisiteCollection()) {
                rapportdevisiteCollectionRapportdevisiteToAttach = em.getReference(rapportdevisiteCollectionRapportdevisiteToAttach.getClass(), rapportdevisiteCollectionRapportdevisiteToAttach.getIdrapport());
                attachedRapportdevisiteCollection.add(rapportdevisiteCollectionRapportdevisiteToAttach);
            }
            visiteurmedical.setRapportdevisiteCollection(attachedRapportdevisiteCollection);
            em.persist(visiteurmedical);
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisite : visiteurmedical.getRapportdevisiteCollection()) {
                Visiteurmedical oldVisiteurmedicalOfRapportdevisiteCollectionRapportdevisite = rapportdevisiteCollectionRapportdevisite.getVisiteurmedical();
                rapportdevisiteCollectionRapportdevisite.setVisiteurmedical(visiteurmedical);
                rapportdevisiteCollectionRapportdevisite = em.merge(rapportdevisiteCollectionRapportdevisite);
                if (oldVisiteurmedicalOfRapportdevisiteCollectionRapportdevisite != null) {
                    oldVisiteurmedicalOfRapportdevisiteCollectionRapportdevisite.getRapportdevisiteCollection().remove(rapportdevisiteCollectionRapportdevisite);
                    oldVisiteurmedicalOfRapportdevisiteCollectionRapportdevisite = em.merge(oldVisiteurmedicalOfRapportdevisiteCollectionRapportdevisite);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVisiteurmedical(visiteurmedical.getIdvisiteur()) != null) {
                throw new PreexistingEntityException("Visiteurmedical " + visiteurmedical + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Visiteurmedical visiteurmedical) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Visiteurmedical persistentVisiteurmedical = em.find(Visiteurmedical.class, visiteurmedical.getIdvisiteur());
            Collection<Rapportdevisite> rapportdevisiteCollectionOld = persistentVisiteurmedical.getRapportdevisiteCollection();
            Collection<Rapportdevisite> rapportdevisiteCollectionNew = visiteurmedical.getRapportdevisiteCollection();
            Collection<Rapportdevisite> attachedRapportdevisiteCollectionNew = new ArrayList<Rapportdevisite>();
            for (Rapportdevisite rapportdevisiteCollectionNewRapportdevisiteToAttach : rapportdevisiteCollectionNew) {
                rapportdevisiteCollectionNewRapportdevisiteToAttach = em.getReference(rapportdevisiteCollectionNewRapportdevisiteToAttach.getClass(), rapportdevisiteCollectionNewRapportdevisiteToAttach.getIdrapport());
                attachedRapportdevisiteCollectionNew.add(rapportdevisiteCollectionNewRapportdevisiteToAttach);
            }
            rapportdevisiteCollectionNew = attachedRapportdevisiteCollectionNew;
            visiteurmedical.setRapportdevisiteCollection(rapportdevisiteCollectionNew);
            visiteurmedical = em.merge(visiteurmedical);
            for (Rapportdevisite rapportdevisiteCollectionOldRapportdevisite : rapportdevisiteCollectionOld) {
                if (!rapportdevisiteCollectionNew.contains(rapportdevisiteCollectionOldRapportdevisite)) {
                    rapportdevisiteCollectionOldRapportdevisite.setVisiteurmedical(null);
                    rapportdevisiteCollectionOldRapportdevisite = em.merge(rapportdevisiteCollectionOldRapportdevisite);
                }
            }
            for (Rapportdevisite rapportdevisiteCollectionNewRapportdevisite : rapportdevisiteCollectionNew) {
                if (!rapportdevisiteCollectionOld.contains(rapportdevisiteCollectionNewRapportdevisite)) {
                    Visiteurmedical oldVisiteurmedicalOfRapportdevisiteCollectionNewRapportdevisite = rapportdevisiteCollectionNewRapportdevisite.getVisiteurmedical();
                    rapportdevisiteCollectionNewRapportdevisite.setVisiteurmedical(visiteurmedical);
                    rapportdevisiteCollectionNewRapportdevisite = em.merge(rapportdevisiteCollectionNewRapportdevisite);
                    if (oldVisiteurmedicalOfRapportdevisiteCollectionNewRapportdevisite != null && !oldVisiteurmedicalOfRapportdevisiteCollectionNewRapportdevisite.equals(visiteurmedical)) {
                        oldVisiteurmedicalOfRapportdevisiteCollectionNewRapportdevisite.getRapportdevisiteCollection().remove(rapportdevisiteCollectionNewRapportdevisite);
                        oldVisiteurmedicalOfRapportdevisiteCollectionNewRapportdevisite = em.merge(oldVisiteurmedicalOfRapportdevisiteCollectionNewRapportdevisite);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = visiteurmedical.getIdvisiteur();
                if (findVisiteurmedical(id) == null) {
                    throw new NonexistentEntityException("The visiteurmedical with id " + id + " no longer exists.");
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
            Visiteurmedical visiteurmedical;
            try {
                visiteurmedical = em.getReference(Visiteurmedical.class, id);
                visiteurmedical.getIdvisiteur();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The visiteurmedical with id " + id + " no longer exists.", enfe);
            }
            Collection<Rapportdevisite> rapportdevisiteCollection = visiteurmedical.getRapportdevisiteCollection();
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisite : rapportdevisiteCollection) {
                rapportdevisiteCollectionRapportdevisite.setVisiteurmedical(null);
                rapportdevisiteCollectionRapportdevisite = em.merge(rapportdevisiteCollectionRapportdevisite);
            }
            em.remove(visiteurmedical);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Visiteurmedical> findVisiteurmedicalEntities() {
        return findVisiteurmedicalEntities(true, -1, -1);
    }

    public List<Visiteurmedical> findVisiteurmedicalEntities(int maxResults, int firstResult) {
        return findVisiteurmedicalEntities(false, maxResults, firstResult);
    }

    private List<Visiteurmedical> findVisiteurmedicalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Visiteurmedical.class));
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

    public Visiteurmedical findVisiteurmedical(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Visiteurmedical.class, id);
        } finally {
            em.close();
        }
    }

    public int getVisiteurmedicalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Visiteurmedical> rt = cq.from(Visiteurmedical.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
