/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.controlleurs;

import ApplicationGSB.controlleurs.exceptions.NonexistentEntityException;
import ApplicationGSB.controlleurs.exceptions.PreexistingEntityException;
import ApplicationGSB.modeles.Compose;
import ApplicationGSB.modeles.ComposePK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ApplicationGSB.modeles.Echantillon;
import ApplicationGSB.modeles.Medicament;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author passpass
 */
public class ComposeJpaController implements Serializable {

    public ComposeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compose compose) throws PreexistingEntityException, Exception {
        if (compose.getComposePK() == null) {
            compose.setComposePK(new ComposePK());
        }
        compose.getComposePK().setIdechantillon(compose.getEchantillon().getIdechantillon());
        compose.getComposePK().setIdmedicament(compose.getMedicament().getIdmedicament());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Echantillon echantillon = compose.getEchantillon();
            if (echantillon != null) {
                echantillon = em.getReference(echantillon.getClass(), echantillon.getIdechantillon());
                compose.setEchantillon(echantillon);
            }
            Medicament medicament = compose.getMedicament();
            if (medicament != null) {
                medicament = em.getReference(medicament.getClass(), medicament.getIdmedicament());
                compose.setMedicament(medicament);
            }
            em.persist(compose);
            if (echantillon != null) {
                echantillon.getComposeCollection().add(compose);
                echantillon = em.merge(echantillon);
            }
            if (medicament != null) {
                medicament.getComposeCollection().add(compose);
                medicament = em.merge(medicament);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompose(compose.getComposePK()) != null) {
                throw new PreexistingEntityException("Compose " + compose + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compose compose) throws NonexistentEntityException, Exception {
        compose.getComposePK().setIdechantillon(compose.getEchantillon().getIdechantillon());
        compose.getComposePK().setIdmedicament(compose.getMedicament().getIdmedicament());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compose persistentCompose = em.find(Compose.class, compose.getComposePK());
            Echantillon echantillonOld = persistentCompose.getEchantillon();
            Echantillon echantillonNew = compose.getEchantillon();
            Medicament medicamentOld = persistentCompose.getMedicament();
            Medicament medicamentNew = compose.getMedicament();
            if (echantillonNew != null) {
                echantillonNew = em.getReference(echantillonNew.getClass(), echantillonNew.getIdechantillon());
                compose.setEchantillon(echantillonNew);
            }
            if (medicamentNew != null) {
                medicamentNew = em.getReference(medicamentNew.getClass(), medicamentNew.getIdmedicament());
                compose.setMedicament(medicamentNew);
            }
            compose = em.merge(compose);
            if (echantillonOld != null && !echantillonOld.equals(echantillonNew)) {
                echantillonOld.getComposeCollection().remove(compose);
                echantillonOld = em.merge(echantillonOld);
            }
            if (echantillonNew != null && !echantillonNew.equals(echantillonOld)) {
                echantillonNew.getComposeCollection().add(compose);
                echantillonNew = em.merge(echantillonNew);
            }
            if (medicamentOld != null && !medicamentOld.equals(medicamentNew)) {
                medicamentOld.getComposeCollection().remove(compose);
                medicamentOld = em.merge(medicamentOld);
            }
            if (medicamentNew != null && !medicamentNew.equals(medicamentOld)) {
                medicamentNew.getComposeCollection().add(compose);
                medicamentNew = em.merge(medicamentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ComposePK id = compose.getComposePK();
                if (findCompose(id) == null) {
                    throw new NonexistentEntityException("The compose with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ComposePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compose compose;
            try {
                compose = em.getReference(Compose.class, id);
                compose.getComposePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compose with id " + id + " no longer exists.", enfe);
            }
            Echantillon echantillon = compose.getEchantillon();
            if (echantillon != null) {
                echantillon.getComposeCollection().remove(compose);
                echantillon = em.merge(echantillon);
            }
            Medicament medicament = compose.getMedicament();
            if (medicament != null) {
                medicament.getComposeCollection().remove(compose);
                medicament = em.merge(medicament);
            }
            em.remove(compose);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compose> findComposeEntities() {
        return findComposeEntities(true, -1, -1);
    }

    public List<Compose> findComposeEntities(int maxResults, int firstResult) {
        return findComposeEntities(false, maxResults, firstResult);
    }

    private List<Compose> findComposeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compose.class));
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

    public Compose findCompose(ComposePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compose.class, id);
        } finally {
            em.close();
        }
    }

    public int getComposeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compose> rt = cq.from(Compose.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
