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
import ApplicationGSB.modeles.Echantillon;
import ApplicationGSB.modeles.Praticien;
import ApplicationGSB.modeles.Rapportdevisite;
import ApplicationGSB.modeles.Visiteurmedical;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author passpass
 */
public class RapportdevisiteJpaController implements Serializable {

    public RapportdevisiteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rapportdevisite rapportdevisite) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Echantillon echantillon = rapportdevisite.getEchantillon();
            if (echantillon != null) {
                echantillon = em.getReference(echantillon.getClass(), echantillon.getIdechantillon());
                rapportdevisite.setEchantillon(echantillon);
            }
            Praticien praticien = rapportdevisite.getPraticien();
            if (praticien != null) {
                praticien = em.getReference(praticien.getClass(), praticien.getIdpraticien());
                rapportdevisite.setPraticien(praticien);
            }
            Visiteurmedical visiteurmedical = rapportdevisite.getVisiteurmedical();
            if (visiteurmedical != null) {
                visiteurmedical = em.getReference(visiteurmedical.getClass(), visiteurmedical.getIdvisiteur());
                rapportdevisite.setVisiteurmedical(visiteurmedical);
            }
            em.persist(rapportdevisite);
            if (echantillon != null) {
                echantillon.getRapportdevisiteCollection().add(rapportdevisite);
                echantillon = em.merge(echantillon);
            }
            if (praticien != null) {
                praticien.getRapportdevisiteCollection().add(rapportdevisite);
                praticien = em.merge(praticien);
            }
            if (visiteurmedical != null) {
                visiteurmedical.getRapportdevisiteCollection().add(rapportdevisite);
                visiteurmedical = em.merge(visiteurmedical);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRapportdevisite(rapportdevisite.getIdrapport()) != null) {
                throw new PreexistingEntityException("Rapportdevisite " + rapportdevisite + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rapportdevisite rapportdevisite) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rapportdevisite persistentRapportdevisite = em.find(Rapportdevisite.class, rapportdevisite.getIdrapport());
            Echantillon echantillonOld = persistentRapportdevisite.getEchantillon();
            Echantillon echantillonNew = rapportdevisite.getEchantillon();
            Praticien praticienOld = persistentRapportdevisite.getPraticien();
            Praticien praticienNew = rapportdevisite.getPraticien();
            Visiteurmedical visiteurmedicalOld = persistentRapportdevisite.getVisiteurmedical();
            Visiteurmedical visiteurmedicalNew = rapportdevisite.getVisiteurmedical();
            if (echantillonNew != null) {
                echantillonNew = em.getReference(echantillonNew.getClass(), echantillonNew.getIdechantillon());
                rapportdevisite.setEchantillon(echantillonNew);
            }
            if (praticienNew != null) {
                praticienNew = em.getReference(praticienNew.getClass(), praticienNew.getIdpraticien());
                rapportdevisite.setPraticien(praticienNew);
            }
            if (visiteurmedicalNew != null) {
                visiteurmedicalNew = em.getReference(visiteurmedicalNew.getClass(), visiteurmedicalNew.getIdvisiteur());
                rapportdevisite.setVisiteurmedical(visiteurmedicalNew);
            }
            rapportdevisite = em.merge(rapportdevisite);
            if (echantillonOld != null && !echantillonOld.equals(echantillonNew)) {
                echantillonOld.getRapportdevisiteCollection().remove(rapportdevisite);
                echantillonOld = em.merge(echantillonOld);
            }
            if (echantillonNew != null && !echantillonNew.equals(echantillonOld)) {
                echantillonNew.getRapportdevisiteCollection().add(rapportdevisite);
                echantillonNew = em.merge(echantillonNew);
            }
            if (praticienOld != null && !praticienOld.equals(praticienNew)) {
                praticienOld.getRapportdevisiteCollection().remove(rapportdevisite);
                praticienOld = em.merge(praticienOld);
            }
            if (praticienNew != null && !praticienNew.equals(praticienOld)) {
                praticienNew.getRapportdevisiteCollection().add(rapportdevisite);
                praticienNew = em.merge(praticienNew);
            }
            if (visiteurmedicalOld != null && !visiteurmedicalOld.equals(visiteurmedicalNew)) {
                visiteurmedicalOld.getRapportdevisiteCollection().remove(rapportdevisite);
                visiteurmedicalOld = em.merge(visiteurmedicalOld);
            }
            if (visiteurmedicalNew != null && !visiteurmedicalNew.equals(visiteurmedicalOld)) {
                visiteurmedicalNew.getRapportdevisiteCollection().add(rapportdevisite);
                visiteurmedicalNew = em.merge(visiteurmedicalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rapportdevisite.getIdrapport();
                if (findRapportdevisite(id) == null) {
                    throw new NonexistentEntityException("The rapportdevisite with id " + id + " no longer exists.");
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
            Rapportdevisite rapportdevisite;
            try {
                rapportdevisite = em.getReference(Rapportdevisite.class, id);
                rapportdevisite.getIdrapport();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rapportdevisite with id " + id + " no longer exists.", enfe);
            }
            Echantillon echantillon = rapportdevisite.getEchantillon();
            if (echantillon != null) {
                echantillon.getRapportdevisiteCollection().remove(rapportdevisite);
                echantillon = em.merge(echantillon);
            }
            Praticien praticien = rapportdevisite.getPraticien();
            if (praticien != null) {
                praticien.getRapportdevisiteCollection().remove(rapportdevisite);
                praticien = em.merge(praticien);
            }
            Visiteurmedical visiteurmedical = rapportdevisite.getVisiteurmedical();
            if (visiteurmedical != null) {
                visiteurmedical.getRapportdevisiteCollection().remove(rapportdevisite);
                visiteurmedical = em.merge(visiteurmedical);
            }
            em.remove(rapportdevisite);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rapportdevisite> findRapportdevisiteEntities() {
        return findRapportdevisiteEntities(true, -1, -1);
    }

    public List<Rapportdevisite> findRapportdevisiteEntities(int maxResults, int firstResult) {
        return findRapportdevisiteEntities(false, maxResults, firstResult);
    }

    private List<Rapportdevisite> findRapportdevisiteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rapportdevisite.class));
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

    public Rapportdevisite findRapportdevisite(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rapportdevisite.class, id);
        } finally {
            em.close();
        }
    }

    public int getRapportdevisiteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rapportdevisite> rt = cq.from(Rapportdevisite.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
