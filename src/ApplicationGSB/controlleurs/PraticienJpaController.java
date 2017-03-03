/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.controlleurs;

import ApplicationGSB.controlleurs.exceptions.NonexistentEntityException;
import ApplicationGSB.controlleurs.exceptions.PreexistingEntityException;
import ApplicationGSB.modeles.Praticien;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ApplicationGSB.modeles.Rapportdevisite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author passpass
 */
public class PraticienJpaController implements Serializable {

    public PraticienJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Praticien praticien) throws PreexistingEntityException, Exception {
        if (praticien.getRapportdevisiteCollection() == null) {
            praticien.setRapportdevisiteCollection(new ArrayList<Rapportdevisite>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Rapportdevisite> attachedRapportdevisiteCollection = new ArrayList<Rapportdevisite>();
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisiteToAttach : praticien.getRapportdevisiteCollection()) {
                rapportdevisiteCollectionRapportdevisiteToAttach = em.getReference(rapportdevisiteCollectionRapportdevisiteToAttach.getClass(), rapportdevisiteCollectionRapportdevisiteToAttach.getIdrapport());
                attachedRapportdevisiteCollection.add(rapportdevisiteCollectionRapportdevisiteToAttach);
            }
            praticien.setRapportdevisiteCollection(attachedRapportdevisiteCollection);
            em.persist(praticien);
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisite : praticien.getRapportdevisiteCollection()) {
                Praticien oldPraticienOfRapportdevisiteCollectionRapportdevisite = rapportdevisiteCollectionRapportdevisite.getPraticien();
                rapportdevisiteCollectionRapportdevisite.setPraticien(praticien);
                rapportdevisiteCollectionRapportdevisite = em.merge(rapportdevisiteCollectionRapportdevisite);
                if (oldPraticienOfRapportdevisiteCollectionRapportdevisite != null) {
                    oldPraticienOfRapportdevisiteCollectionRapportdevisite.getRapportdevisiteCollection().remove(rapportdevisiteCollectionRapportdevisite);
                    oldPraticienOfRapportdevisiteCollectionRapportdevisite = em.merge(oldPraticienOfRapportdevisiteCollectionRapportdevisite);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPraticien(praticien.getIdpraticien()) != null) {
                throw new PreexistingEntityException("Praticien " + praticien + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Praticien praticien) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Praticien persistentPraticien = em.find(Praticien.class, praticien.getIdpraticien());
            Collection<Rapportdevisite> rapportdevisiteCollectionOld = persistentPraticien.getRapportdevisiteCollection();
            Collection<Rapportdevisite> rapportdevisiteCollectionNew = praticien.getRapportdevisiteCollection();
            Collection<Rapportdevisite> attachedRapportdevisiteCollectionNew = new ArrayList<Rapportdevisite>();
            for (Rapportdevisite rapportdevisiteCollectionNewRapportdevisiteToAttach : rapportdevisiteCollectionNew) {
                rapportdevisiteCollectionNewRapportdevisiteToAttach = em.getReference(rapportdevisiteCollectionNewRapportdevisiteToAttach.getClass(), rapportdevisiteCollectionNewRapportdevisiteToAttach.getIdrapport());
                attachedRapportdevisiteCollectionNew.add(rapportdevisiteCollectionNewRapportdevisiteToAttach);
            }
            rapportdevisiteCollectionNew = attachedRapportdevisiteCollectionNew;
            praticien.setRapportdevisiteCollection(rapportdevisiteCollectionNew);
            praticien = em.merge(praticien);
            for (Rapportdevisite rapportdevisiteCollectionOldRapportdevisite : rapportdevisiteCollectionOld) {
                if (!rapportdevisiteCollectionNew.contains(rapportdevisiteCollectionOldRapportdevisite)) {
                    rapportdevisiteCollectionOldRapportdevisite.setPraticien(null);
                    rapportdevisiteCollectionOldRapportdevisite = em.merge(rapportdevisiteCollectionOldRapportdevisite);
                }
            }
            for (Rapportdevisite rapportdevisiteCollectionNewRapportdevisite : rapportdevisiteCollectionNew) {
                if (!rapportdevisiteCollectionOld.contains(rapportdevisiteCollectionNewRapportdevisite)) {
                    Praticien oldPraticienOfRapportdevisiteCollectionNewRapportdevisite = rapportdevisiteCollectionNewRapportdevisite.getPraticien();
                    rapportdevisiteCollectionNewRapportdevisite.setPraticien(praticien);
                    rapportdevisiteCollectionNewRapportdevisite = em.merge(rapportdevisiteCollectionNewRapportdevisite);
                    if (oldPraticienOfRapportdevisiteCollectionNewRapportdevisite != null && !oldPraticienOfRapportdevisiteCollectionNewRapportdevisite.equals(praticien)) {
                        oldPraticienOfRapportdevisiteCollectionNewRapportdevisite.getRapportdevisiteCollection().remove(rapportdevisiteCollectionNewRapportdevisite);
                        oldPraticienOfRapportdevisiteCollectionNewRapportdevisite = em.merge(oldPraticienOfRapportdevisiteCollectionNewRapportdevisite);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = praticien.getIdpraticien();
                if (findPraticien(id) == null) {
                    throw new NonexistentEntityException("The praticien with id " + id + " no longer exists.");
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
            Praticien praticien;
            try {
                praticien = em.getReference(Praticien.class, id);
                praticien.getIdpraticien();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The praticien with id " + id + " no longer exists.", enfe);
            }
            Collection<Rapportdevisite> rapportdevisiteCollection = praticien.getRapportdevisiteCollection();
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisite : rapportdevisiteCollection) {
                rapportdevisiteCollectionRapportdevisite.setPraticien(null);
                rapportdevisiteCollectionRapportdevisite = em.merge(rapportdevisiteCollectionRapportdevisite);
            }
            em.remove(praticien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Praticien> findPraticienEntities() {
        return findPraticienEntities(true, -1, -1);
    }

    public List<Praticien> findPraticienEntities(int maxResults, int firstResult) {
        return findPraticienEntities(false, maxResults, firstResult);
    }

    private List<Praticien> findPraticienEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Praticien.class));
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

    public Praticien findPraticien(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Praticien.class, id);
        } finally {
            em.close();
        }
    }

    public int getPraticienCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Praticien> rt = cq.from(Praticien.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
