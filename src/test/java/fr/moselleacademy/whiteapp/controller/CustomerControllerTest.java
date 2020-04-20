package fr.moselleacademy.whiteapp.controller;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test unitaire sur un contrôleur.
 *
 * @author MOSELLE Maxime
 */
@RunWith(Arquillian.class)
public class CustomerControllerTest {

    @Inject
    private CustomerController controller;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de l'environnement
     * de test.
     */
    public CustomerControllerTest() {
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
                .create(WebArchive.class, "whiteapp.war")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("faces-config.xml")
                .addAsWebInfResource("web.xml")
                .addAsWebInfResource("payara-web.xml")
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addPackages(true, "fr.moselleacademy.whiteapp");
    }

    /**
     * Tester l'injection CDI d'une entité.
     */
    @Test
    public void injectController() {
        Assert.assertNotNull(controller);
    }

    /**
     * Tester la création d'une entité avec JPA.
     */
    //@Test
    public void createEntity() {
        Assert.fail("Not implemented yet!");
    }

    /**
     * Tester la lecture d'une entité avec JPA.
     */
    //@Test
    public void readEntity() {
        Assert.fail("Not implemented yet!");

    }

    /**
     * Tester la mise à jour d'une entité avec JPA.
     */
    //@Test
    public void updateEntity() {
        Assert.fail("Not implemented yet!");

    }

    /**
     * Tester la suppression d'une entité avec JPA.
     */
    //@Test
    public void deleteEntity() {
        Assert.fail("Not implemented yet!");

    }
}
