package com.github.zelmothedragon.whiteapp.view;

import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * Test d'intégration de la page web JSF. (Non supporté par Jacoco)
 *
 * @author MOSELLE Maxime
 */
@RunWith(Arquillian.class)
public class CustomerViewTest {

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de l'environnement
     * de test.
     */
    public CustomerViewTest() {
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

        var webapp = ShrinkWrap
                .create(GenericArchive.class)
                .as(ExplodedImporter.class)
                .importDirectory(new File("src/main/webapp"))
                .as(GenericArchive.class);
        
        var libs = Maven
                        .resolver()
                        .loadPomFromFile("pom.xml")
                        .importDependencies(ScopeType.COMPILE)
                        .resolve()
                        .withTransitivity()
                        .asFile();

        return ShrinkWrap
                .create(WebArchive.class, "customer-view.war")
                .addAsWebInfResource("beans.xml")
                .addAsWebInfResource("faces-config.xml")
                .addAsWebInfResource("web.xml")
                .addAsWebInfResource("payara-web.xml")
                .addAsWebInfResource("payara-resources.xml")
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addAsResource("Messages_en.properties")
                .addAsResource("Messages_fr.properties")
                .addAsResource("ValidationMessages_en.properties")
                .addAsResource("ValidationMessages_fr.properties")
                .addAsLibraries(libs)
                .addPackages(true, "com.github.zelmothedragon.whiteapp")
                .merge(
                        webapp,
                        "/",
                        Filters.exclude(".*\\.xml$")
                );
    }

    @Test
    @RunAsClient
    public void createCustomer() {
        browser.navigate().to(deploymentUrl);
        var customerPage = Graphene.goTo(CustomerPage.class);
        customerPage.save();
    }

}
