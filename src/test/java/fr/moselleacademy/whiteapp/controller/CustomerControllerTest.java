package fr.moselleacademy.whiteapp.controller;

import fr.moselleacademy.whiteapp.model.entity.Customer;
import java.time.LocalDate;
import java.time.Month;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;

/**
 * Test unitaire sur un contrôleur.
 *
 * @author MOSELLE Maxime
 */
//@RunWith(Arquillian.class)
public class CustomerControllerTest {

    //@Inject
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
    //@Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "customer-controller.war")
                .addAsWebInfResource("beans.xml")
                .addAsWebInfResource("faces-config.xml")
                .addAsWebInfResource("web.xml")
                .addAsWebInfResource("payara-web.xml")
                .addAsWebInfResource("payara-resources.xml")
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addPackages(true, "fr.moselleacademy.whiteapp");
    }

    /**
     * Tester l'injection CDI d'une entité.
     */
    //@Test
    public void injectController() {
        Assert.assertNotNull(controller);
    }

    /**
     * Tester la création d'une entité avec JPA.
     */
    //@Test
    public void createEntity() {
        var entity = Customer.of();
        entity.setGivenName("John");
        entity.setFamilyName("DOE");
        entity.setEmail("john.doe@arquillian.org");
        entity.setBirthDate(LocalDate.of(1970, Month.JANUARY, 1));

        controller.setEntity(entity);
        controller.save();

        Assert.assertEquals(1L, entity.getVersion().longValue());
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
