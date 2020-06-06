package com.github.zelmothedragon.whiteapp.view;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page web pour les actions liées à l'entité client.
 *
 * @author MOSELLE Maxime
 */
@Location("customer.xhtml")
public class CustomerPage {

    /**
     * Champ du formulaire pour le prénom.
     */
    @FindBy(id = "givenName")
    private WebElement givenName;

    /**
     * Champ du formulaire pour le nom.
     */
    @FindBy(id = "familyName")
    private WebElement familyName;

    /**
     * Champ du formulaire pour l'adresse de courriel.
     */
    @FindBy(id = "email")
    private WebElement email;

    /**
     * Champ du formulaire pour la date de naissance.
     */
    @FindBy(id = "birthDate")
    private WebElement birthDate;

    /**
     * Bouton de validation du formulaire.
     */
    @FindBy(id = "save")
    private WebElement save;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de l'environnement
     * de test.
     */
    public CustomerPage() {
        // RAS
    }

    /**
     * Action du formulaire avec des données de tests pré-renseignées.
     */
    public void save() {
        givenName.sendKeys("John");
        familyName.sendKeys("DOE");
        email.sendKeys("john.doe@arquillian.org");
        birthDate.sendKeys("1970-01-01");
        Graphene.guardHttp(save).click();
    }

}
