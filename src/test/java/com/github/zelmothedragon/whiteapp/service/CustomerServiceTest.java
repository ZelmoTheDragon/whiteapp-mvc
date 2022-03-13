package com.github.zelmothedragon.whiteapp.service;

import com.github.zelmothedragon.whiteapp.model.entity.Customer;
import java.time.LocalDate;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test Unitaire sur un service.
 *
 * @author MOSELLE Maxime
 */
@RunWith(Arquillian.class)
public class CustomerServiceTest {

    /**
     * Service métier.
     */
    @Inject
    private CustomerService service;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de l'environnement
     * de test.
     */
    public CustomerServiceTest() {
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
                .create(WebArchive.class, "customer-service.war")
                .addAsWebInfResource("beans.xml")
                .addAsWebInfResource("faces-config.xml")
                .addAsWebInfResource("web.xml")
                .addAsWebInfResource("payara-web.xml")
                .addAsWebInfResource("payara-resources.xml")
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addPackages(true, "com.github.zelmothedragon.whiteapp");
    }

    /**
     * Tester l'injection CDI d'un service.
     */
    @Test
    public void testInject() {
        Assert.assertNotNull(service);
    }

    /**
     * Tester la persistance d'une entité.
     */
    @Test
    public void testSave() {
        var entity = Customer.of();
        entity.setGivenName("John");
        entity.setFamilyName("DOE");
        entity.setEmail("john.doe@arquillian.org");
        entity.setBirthDate(LocalDate.EPOCH);
        
        service.save(entity);
        
        var exists = entity.contains();
        Assert.assertTrue(exists);
    }

    /**
     * Tester la suppression des entités.
     */
    @Test
    public void testRemove() {
        var entities = Customer.find();
        Assert.assertFalse(entities.isEmpty());
        
        entities.forEach(service::remove);
        Assert.assertTrue(Customer.isEmpty());
    }
}
