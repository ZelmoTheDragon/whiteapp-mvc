package com.github.zelmothedragon.whiteapp.model.entity;

import java.util.UUID;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Test Unitaire sur une entité persistante.
 *
 * @author MOSELLE Maxime
 */
@RunWith(Arquillian.class)
public class CustomerTest {

    @Inject
    private Customer instance;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de l'environnement
     * de test.
     */
    public CustomerTest() {
        // RAS
    }

    /**
     * Construire une archive à déployer sur le serveur d'application embarqué.
     * Cette archive comporte les classes et la configuration necessaire pour
     * l'éxecution des tests de cette classe.
     *
     * @return Archive web à déployer
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "customer-controller.war")
                .addAsWebInfResource("beans.xml")
                .addAsWebInfResource("faces-config.xml")
                .addAsWebInfResource("web.xml")
                .addAsWebInfResource("payara-web.xml")
                .addAsWebInfResource("payara-resources.xml")
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addPackages(true, "com.github.zelmothedragon.whiteapp");
    }

    /**
     * Tester l'injection CDI d'une entité.
     */
    @Test
    public void testInject() {
        Assert.assertNotNull(instance);
    }

    /**
     * Tester la recherche de toutes les entités.
     */
    @Test
    public void testFind() {
        var customers = Customer.find();
        Assert.assertTrue(customers.isEmpty());
    }

    /**
     * Tester qu'aucune entité n'existe en base de données.
     */
    @Test
    public void testIsEmpty() {
        var empty = Customer.isEmpty();
        Assert.assertTrue(empty);
    }

    /**
     * Tester qu'une adresse de courriel n'existe pas.
     */
    @Test
    public void testEmailExists() {
        var exists = Customer.emailExists("nobody@arquillian.org");
        Assert.assertFalse(exists);
    }

    /**
     * Tester qu'une entité n'existe pas.
     */
    @Test
    public void testContains() {
        var fake = new Customer();
        var exists = fake.contains();

        Assert.assertFalse(exists);
    }

    /**
     * Tester qu'une entité n'existe pas.
     */
    @Test
    public void testSelectWhere() {
        var fake = new Customer();
        var option = fake.selectWhere(UUID.randomUUID());

        Assert.assertTrue(option.isEmpty());
    }

}
