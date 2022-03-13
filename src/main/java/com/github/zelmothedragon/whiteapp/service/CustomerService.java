package com.github.zelmothedragon.whiteapp.service;

import com.github.zelmothedragon.whiteapp.model.entity.Customer;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Service métier de démonstration.
 *
 * @author MOSELLE Maxime
 */
@ApplicationScoped
@Transactional
public class CustomerService {

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public CustomerService() {
        // RAS
    }

    /**
     * Sauvegarder une entité.
     *
     * @param entity Entité à sauvegarder
     */
    public void save(Customer entity) {
        entity.save();
    }

    /**
     * Supprimer une entité.
     *
     * @param entity Entité à supprimer
     */
    public void remove(Customer entity) {
        entity.remove();
    }
}
