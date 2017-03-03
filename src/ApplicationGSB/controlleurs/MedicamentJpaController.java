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
import ApplicationGSB.modeles.Compose;
import ApplicationGSB.modeles.Medicament;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author passpass
 */
public class MedicamentJpaController implements Serializable {

    public MedicamentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medicament medicament) throws PreexistingEntityException, Exception {
        if (medicament.getComposeCollection() == null) {
            medicament.setComposeCollection(new ArrayList<Compose>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Compose> attachedComposeCollection = new ArrayList<Compose>();
            for (Compose composeCollectionComposeToAttach : medicament.getComposeCollection()) {
                composeCollectionComposeToAttach = em.getReference(composeCollectionComposeToAttach.getClass(), composeCollectionComposeToAttach.getComposePK());
                attachedComposeCollection.add(composeCollectionComposeToAttach);
            }
            medicament.setComposeCollection(attachedComposeCollection);
            em.persist(medicament);
            for (Compose composeCollectionCompose : medicament.getComposeCollection()) {
                Medicament oldMedicamentOfComposeCollectionCompose = composeCollectionCompose.getMedicament();
                composeCollectionCompose.setMedicament(medicament);
                composeCollectionCompose = em.merge(composeCollectionCompose);
                if (oldMedicamentOfComposeCollectionCompose != null) {
                    oldMedicamentOfComposeCollectionCompose.getComposeCollection().remove(composeCollectionCompose);
                    oldMedicamentOfComposeCollectionCompose = em.merge(oldMedicamentOfComposeCollectionCompose);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicament(medicament.getIdmedicament()) != null) {
                throw new PreexistingEntityException("Medicament " + medicament + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medicament medicament) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medicament persistentMedicament = em.find(Medicament.class, medicament.getIdmedicament());
            Collection<Compose> composeCollectionOld = persistentMedicament.getComposeCollection();
            Collection<Compose> composeCollectionNew = medicament.getComposeCollection();
            List<String> illegalOrphanMessages = null;
            for (Compose composeCollectionOldCompose : composeCollectionOld) {
                if (!composeCollectionNew.contains(composeCollectionOldCompose)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compose " + composeCollectionOldCompose + " since its medicament field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Compose> attachedComposeCollectionNew = new ArrayList<Compose>();
            for (Compose composeCollectionNewComposeToAttach : composeCollectionNew) {
                composeCollectionNewComposeToAttach = em.getReference(composeCollectionNewComposeToAttach.getClass(), composeCollectionNewComposeToAttach.getComposePK());
                attachedComposeCollectionNew.add(composeCollectionNewComposeToAttach);
            }
            composeCollectionNew = attachedComposeCollectionNew;
            medicament.setComposeCollection(composeCollectionNew);
            medicament = em.merge(medicament);
            for (Compose composeCollectionNewCompose : composeCollectionNew) {
                if (!composeCollectionOld.contains(composeCollectionNewCompose)) {
                    Medicament oldMedicamentOfComposeCollectionNewCompose = composeCollectionNewCompose.getMedicament();
                    composeCollectionNewCompose.setMedicament(medicament);
                    composeCollectionNewCompose = em.merge(composeCollectionNewCompose);
                    if (oldMedicamentOfComposeCollectionNewCompose != null && !oldMedicamentOfComposeCollectionNewCompose.equals(medicament)) {
                        oldMedicamentOfComposeCollectionNewCompose.getComposeCollection().remove(composeCollectionNewCompose);
                        oldMedicamentOfComposeCollectionNewCompose = em.merge(oldMedicamentOfComposeCollectionNewCompose);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medicament.getIdmedicament();
                if (findMedicament(id) == null) {
                    throw new NonexistentEntityException("The medicament with id " + id + " no longer exists.");
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
            Medicament medicament;
            try {
                medicament = em.getReference(Medicament.class, id);
                medicament.getIdmedicament();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicament with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Compose> composeCollectionOrphanCheck = medicament.getComposeCollection();
            for (Compose composeCollectionOrphanCheckCompose : composeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medicament (" + medicament + ") cannot be destroyed since the Compose " + composeCollectionOrphanCheckCompose + " in its composeCollection field has a non-nullable medicament field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(medicament);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medicament> findMedicamentEntities() {
        return findMedicamentEntities(true, -1, -1);
    }

    public List<Medicament> findMedicamentEntities(int maxResults, int firstResult) {
        return findMedicamentEntities(false, maxResults, firstResult);
    }

    private List<Medicament> findMedicamentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medicament.class));
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

    public Medicament findMedicament(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medicament.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicamentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medicament> rt = cq.from(Medicament.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
