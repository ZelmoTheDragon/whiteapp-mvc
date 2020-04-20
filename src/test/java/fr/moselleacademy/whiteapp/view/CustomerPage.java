package fr.moselleacademy.whiteapp.view;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("customer.xhtml")
public class CustomerPage {

    @FindBy(id = "customerFormSave:givenName")
    private WebElement givenName;

    @FindBy(id = "customerFormSave:familyName")
    private WebElement familyName;

    @FindBy(id = "customerFormSave:email")
    private WebElement email;

    @FindBy(id = "customerFormSave:birthDate")
    private WebElement birthDate;

    @FindBy(id = "customerFormSave:save")
    private WebElement save;

    public CustomerPage() {
    }

    public void save() {
        givenName.sendKeys("John");
        familyName.sendKeys("DOE");
        email.sendKeys("john.doe@arquillian.org");
        birthDate.sendKeys("1970-01-01");

        Graphene.guardHttp(save).click();
    }

}
