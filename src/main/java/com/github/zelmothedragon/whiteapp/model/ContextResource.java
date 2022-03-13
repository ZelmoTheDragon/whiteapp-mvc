package com.github.zelmothedragon.whiteapp.model;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Gestionnaire de ressource injectable.
 *
 * @author MOSELLE Maxime
 */
@Singleton
public class ContextResource {

    /**
     * Gestionnaire d'entité (injecté par <i>JPA</i>).
     */
    @Produces
    @PersistenceContext
    private EntityManager em;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public ContextResource() {
        // Ne pas appeler explicitement
    }

}
