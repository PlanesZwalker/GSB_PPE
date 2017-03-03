/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.controlleurs;

import ApplicationGSB.controlleurs.exceptions.IllegalOrphanException;
import ApplicationGSB.controlleurs.exceptions.NonexistentEntityException;
import ApplicationGSB.controlleurs.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ApplicationGSB.modeles.Rapportdevisite;
import java.util.ArrayList;
import java.util.Collection;
import ApplicationGSB.modeles.Compose;
import ApplicationGSB.modeles.Echantillon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author passpass
 */
public class EchantillonJpaController implements Serializable {

    public EchantillonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Echantillon echantillon) throws PreexistingEntityException, Exception {
        if (echantillon.getRapportdevisiteCollection() == null) {
            echantillon.setRapportdevisiteCollection(new ArrayList<Rapportdevisite>());
        }
        if (echantillon.getComposeCollection() == null) {
            echantillon.setComposeCollection(new ArrayList<Compose>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Rapportdevisite> attachedRapportdevisiteCollection = new ArrayList<Rapportdevisite>();
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisiteToAttach : echantillon.getRapportdevisiteCollection()) {
                rapportdevisiteCollectionRapportdevisiteToAttach = em.getReference(rapportdevisiteCollectionRapportdevisiteToAttach.getClass(), rapportdevisiteCollectionRapportdevisiteToAttach.getIdrapport());
                attachedRapportdevisiteCollection.add(rapportdevisiteCollectionRapportdevisiteToAttach);
            }
            echantillon.setRapportdevisiteCollection(attachedRapportdevisiteCollection);
            Collection<Compose> attachedComposeCollection = new ArrayList<Compose>();
            for (Compose composeCollectionComposeToAttach : echantillon.getComposeCollection()) {
                composeCollectionComposeToAttach = em.getReference(composeCollectionComposeToAttach.getClass(), composeCollectionComposeToAttach.getComposePK());
                attachedComposeCollection.add(composeCollectionComposeToAttach);
            }
            echantillon.setComposeCollection(attachedComposeCollection);
            em.persist(echantillon);
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisite : echantillon.getRapportdevisiteCollection()) {
                Echantillon oldEchantillonOfRapportdevisiteCollectionRapportdevisite = rapportdevisiteCollectionRapportdevisite.getEchantillon();
                rapportdevisiteCollectionRapportdevisite.setEchantillon(echantillon);
                rapportdevisiteCollectionRapportdevisite = em.merge(rapportdevisiteCollectionRapportdevisite);
                if (oldEchantillonOfRapportdevisiteCollectionRapportdevisite != null) {
                    oldEchantillonOfRapportdevisiteCollectionRapportdevisite.getRapportdevisiteCollection().remove(rapportdevisiteCollectionRapportdevisite);
                    oldEchantillonOfRapportdevisiteCollectionRapportdevisite = em.merge(oldEchantillonOfRapportdevisiteCollectionRapportdevisite);
                }
            }
            for (Compose composeCollectionCompose : echantillon.getComposeCollection()) {
                Echantillon oldEchantillonOfComposeCollectionCompose = composeCollectionCompose.getEchantillon();
                composeCollectionCompose.setEchantillon(echantillon);
                composeCollectionCompose = em.merge(composeCollectionCompose);
                if (oldEchantillonOfComposeCollectionCompose != null) {
                    oldEchantillonOfComposeCollectionCompose.getComposeCollection().remove(composeCollectionCompose);
                    oldEchantillonOfComposeCollectionCompose = em.merge(oldEchantillonOfComposeCollectionCompose);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEchantillon(echantillon.getIdechantillon()) != null) {
                throw new PreexistingEntityException("Echantillon " + echantillon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Echantillon echantillon) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Echantillon persistentEchantillon = em.find(Echantillon.class, echantillon.getIdechantillon());
            Collection<Rapportdevisite> rapportdevisiteCollectionOld = persistentEchantillon.getRapportdevisiteCollection();
            Collection<Rapportdevisite> rapportdevisiteCollectionNew = echantillon.getRapportdevisiteCollection();
            Collection<Compose> composeCollectionOld = persistentEchantillon.getComposeCollection();
            Collection<Compose> composeCollectionNew = echantillon.getComposeCollection();
            List<String> illegalOrphanMessages = null;
            for (Compose composeCollectionOldCompose : composeCollectionOld) {
                if (!composeCollectionNew.contains(composeCollectionOldCompose)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compose " + composeCollectionOldCompose + " since its echantillon field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Rapportdevisite> attachedRapportdevisiteCollectionNew = new ArrayList<Rapportdevisite>();
            for (Rapportdevisite rapportdevisiteCollectionNewRapportdevisiteToAttach : rapportdevisiteCollectionNew) {
                rapportdevisiteCollectionNewRapportdevisiteToAttach = em.getReference(rapportdevisiteCollectionNewRapportdevisiteToAttach.getClass(), rapportdevisiteCollectionNewRapportdevisiteToAttach.getIdrapport());
                attachedRapportdevisiteCollectionNew.add(rapportdevisiteCollectionNewRapportdevisiteToAttach);
            }
            rapportdevisiteCollectionNew = attachedRapportdevisiteCollectionNew;
            echantillon.setRapportdevisiteCollection(rapportdevisiteCollectionNew);
            Collection<Compose> attachedComposeCollectionNew = new ArrayList<Compose>();
            for (Compose composeCollectionNewComposeToAttach : composeCollectionNew) {
                composeCollectionNewComposeToAttach = em.getReference(composeCollectionNewComposeToAttach.getClass(), composeCollectionNewComposeToAttach.getComposePK());
                attachedComposeCollectionNew.add(composeCollectionNewComposeToAttach);
            }
            composeCollectionNew = attachedComposeCollectionNew;
            echantillon.setComposeCollection(composeCollectionNew);
            echantillon = em.merge(echantillon);
            for (Rapportdevisite rapportdevisiteCollectionOldRapportdevisite : rapportdevisiteCollectionOld) {
                if (!rapportdevisiteCollectionNew.contains(rapportdevisiteCollectionOldRapportdevisite)) {
                    rapportdevisiteCollectionOldRapportdevisite.setEchantillon(null);
                    rapportdevisiteCollectionOldRapportdevisite = em.merge(rapportdevisiteCollectionOldRapportdevisite);
                }
            }
            for (Rapportdevisite rapportdevisiteCollectionNewRapportdevisite : rapportdevisiteCollectionNew) {
                if (!rapportdevisiteCollectionOld.contains(rapportdevisiteCollectionNewRapportdevisite)) {
                    Echantillon oldEchantillonOfRapportdevisiteCollectionNewRapportdevisite = rapportdevisiteCollectionNewRapportdevisite.getEchantillon();
                    rapportdevisiteCollectionNewRapportdevisite.setEchantillon(echantillon);
                    rapportdevisiteCollectionNewRapportdevisite = em.merge(rapportdevisiteCollectionNewRapportdevisite);
                    if (oldEchantillonOfRapportdevisiteCollectionNewRapportdevisite != null && !oldEchantillonOfRapportdevisiteCollectionNewRapportdevisite.equals(echantillon)) {
                        oldEchantillonOfRapportdevisiteCollectionNewRapportdevisite.getRapportdevisiteCollection().remove(rapportdevisiteCollectionNewRapportdevisite);
                        oldEchantillonOfRapportdevisiteCollectionNewRapportdevisite = em.merge(oldEchantillonOfRapportdevisiteCollectionNewRapportdevisite);
                    }
                }
            }
            for (Compose composeCollectionNewCompose : composeCollectionNew) {
                if (!composeCollectionOld.contains(composeCollectionNewCompose)) {
                    Echantillon oldEchantillonOfComposeCollectionNewCompose = composeCollectionNewCompose.getEchantillon();
                    composeCollectionNewCompose.setEchantillon(echantillon);
                    composeCollectionNewCompose = em.merge(composeCollectionNewCompose);
                    if (oldEchantillonOfComposeCollectionNewCompose != null && !oldEchantillonOfComposeCollectionNewCompose.equals(echantillon)) {
                        oldEchantillonOfComposeCollectionNewCompose.getComposeCollection().remove(composeCollectionNewCompose);
                        oldEchantillonOfComposeCollectionNewCompose = em.merge(oldEchantillonOfComposeCollectionNewCompose);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = echantillon.getIdechantillon();
                if (findEchantillon(id) == null) {
                    throw new NonexistentEntityException("The echantillon with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Echantillon echantillon;
            try {
                echantillon = em.getReference(Echantillon.class, id);
                echantillon.getIdechantillon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The echantillon with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Compose> composeCollectionOrphanCheck = echantillon.getComposeCollection();
            for (Compose composeCollectionOrphanCheckCompose : composeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Echantillon (" + echantillon + ") cannot be destroyed since the Compose " + composeCollectionOrphanCheckCompose + " in its composeCollection field has a non-nullable echantillon field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Rapportdevisite> rapportdevisiteCollection = echantillon.getRapportdevisiteCollection();
            for (Rapportdevisite rapportdevisiteCollectionRapportdevisite : rapportdevisiteCollection) {
                rapportdevisiteCollectionRapportdevisite.setEchantillon(null);
                rapportdevisiteCollectionRapportdevisite = em.merge(rapportdevisiteCollectionRapportdevisite);
            }
            em.remove(echantillon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Echantillon> findEchantillonEntities() {
        return findEchantillonEntities(true, -1, -1);
    }

    public List<Echantillon> findEchantillonEntities(int maxResults, int firstResult) {
        return findEchantillonEntities(false, maxResults, firstResult);
    }

    private List<Echantillon> findEchantillonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Echantillon.class));
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

    public Echantillon findEchantillon(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Echantillon.class, id);
        } finally {
            em.close();
        }
    }

    public int getEchantillonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Echantillon> rt = cq.from(Echantillon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
