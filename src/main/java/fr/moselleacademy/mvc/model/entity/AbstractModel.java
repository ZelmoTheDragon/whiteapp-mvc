package fr.moselleacademy.mvc.model.entity;

import java.io.Serializable;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Classe mère pour toutes les entités persistantes.
 *
 * @author MOSELLE Maxime
 */
public abstract class AbstractModel implements Serializable {

    /**
     * Numéro de série.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    protected AbstractModel() {
        // Ne pas appeler explicitement
    }

    /**
     * Sauvegarder l'entité courante.
     */
    public void save() {
        var em = getEntityManager();
        if (contains()) {
            em.merge(this);
        } else {
            em.persist(this);
        }
    }

    /**
     * Supprimer l'entité courante.
     */
    public void remove() {
        var em = getEntityManager();
        var pk = getIdentifier();
        var attachedEntity = em.getReference(getClass(), pk);
        em.remove(attachedEntity);
    }

    /**
     * Vérifier que l'entité courante existe.
     *
     * @return La valeur <code>true</code> si l'entité est persistée sinon la
     * valeur <code>false</code> est retournée
     */
    public boolean contains() {
        var em = getEntityManager();
        boolean exists;
        if (em.contains(this)) {
            exists = true;
        } else {
            var cb = em.getCriteriaBuilder();
            var query = cb.createQuery(Long.class);
            var root = query.from(getClass());
            query.select(cb.count(root));

            var pk = getIdentifier();
            var pkType = getIdentifierType();
            var predicate = cb.equal(root.get(pkType), pk);
            query.where(predicate);
            var result = em.createQuery(query).getSingleResult();
            exists = result == 1L;
        }
        return exists;
    }

    /**
     * Obtenir une instance du gestinnaire d'entité.
     *
     * @return Une instance du gestionnaire d'entité
     */
    protected EntityManager getEntityManager() {
        // Ici la classe n'est pas dans un contexte
        // d'injection de dépendance.
        // Il faut donc récupérer le gestionnaire d'entité
        // ce cette manière.
        return CDI.current().select(EntityManager.class).get();
    }

    /**
     * Obtenir la clef primaire de l'entité courante.
     *
     * @return La clef primaire
     */
    protected Object getIdentifier() {
        var em = getEntityManager();
        return em
                .getEntityManagerFactory()
                .getPersistenceUnitUtil()
                .getIdentifier(this);
    }

    /**
     * Obtenir le type de la clef primaire selon le méta-modèle <i>JPA</i>.
     *
     * @return Le type de la clef primaire
     */
    protected SingularAttribute<? super AbstractModel, ?> getIdentifierType() {
        var em = getEntityManager();
        var pk = getIdentifier();
        var pkClass = pk.getClass();
        return (SingularAttribute<? super AbstractModel, ?>) em
                .getMetamodel()
                .entity(getClass())
                .getId(pkClass);
    }

}
