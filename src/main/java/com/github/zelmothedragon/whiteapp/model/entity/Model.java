package com.github.zelmothedragon.whiteapp.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Classe mère pour toutes les entités persistantes.
 *
 * @author MOSELLE Maxime
 */
public abstract class Model implements Serializable {

    /**
     * Numéro de série.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    protected Model() {
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
            // Lecture dans le cache de persistance
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
     * Obtenir le nombre d'occurence enregistré.
     *
     * @return Le nombre d'occurence
     */
    protected long count() {
        // Méthode de lecture
        // Rendre accèssible de manière statique
        var em = getEntityManager();
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Long.class);
        var root = query.from(getClass());
        query.select(cb.count(root));
        return em
                .createQuery(query)
                .getSingleResult();
    }

    /**
     * Obtenir le nombre d'occurence enregistré.
     *
     * @param column Colonne du méta modèle
     * @param value Valeur
     * @return Le nombre d'occurence
     */
    protected long countWhere(final SingularAttribute<?, ?> column, final Object value) {
        // Méthode de lecture
        // Rendre accèssible de manière statique
        var em = getEntityManager();
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Long.class);
        var root = query.from(getClass());
        query.select(cb.count(root));
        var predicate = cb.equal(root.get(column.getName()), value);
        query.where(predicate);
        return em
                .createQuery(query)
                .getSingleResult();
    }

    /**
     * Chercher toutes les entités enregistrés.
     *
     * @param <E> Type de l'entité
     * @return Une liste des entités persistées
     */
    protected <E extends Model> List<E> select() {
        // Méthode de lecture
        // Rendre accèssible de manière statique
        var em = getEntityManager();
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(getClass());
        query.from(getClass());
        // /!\ ATTENTION: 
        // Peut surcharger la mémoire en fonction du nombre de tuple remonté
        return (List<E>) em
                .createQuery(query)
                .getResultList();
    }

    /**
     * Rechercher une entité en fonction de la clef primaire.
     *
     * @param <E> Type de l'entité
     * @param id Clef primaire
     * @return Une option contenant ou non l'entité correspondante à la clef
     * primaire
     */
    protected <E extends Model> Optional<E> selectWhere(final Object id) {
        // Méthode de lecture
        // Rendre accèssible de manière statique
        // Lecture dans le cache de persistance
        var em = getEntityManager();
        var entity = (E) em.find(getClass(), id);
        return Optional.ofNullable(entity);
    }

    /**
     * Rechercher une entité en fonction d'un critère de recherche.
     *
     * @param <E> Type de l'entité
     * @param column Colonne du méta modèle
     * @param value Valeur
     * @return Une liste des entités persistées
     */
    protected <E extends Model> List<E> selectWhere(final SingularAttribute<?, ?> column, final Object value) {
        // Méthode de lecture
        // Rendre accèssible de manière statique
        var em = getEntityManager();
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(getClass());
        var root = query.from(getClass());
        var predicate = cb.equal(root.get(column.getName()), value);
        query.where(predicate);
        return (List<E>) em
                .createQuery(query)
                .getSingleResult();
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
    protected SingularAttribute<? super Model, ?> getIdentifierType() {
        var em = getEntityManager();
        var pk = getIdentifier();
        var pkClass = pk.getClass();
        return (SingularAttribute<? super Model, ?>) em
                .getMetamodel()
                .entity(getClass())
                .getId(pkClass);
    }

}
