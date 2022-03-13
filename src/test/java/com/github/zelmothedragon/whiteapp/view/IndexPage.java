package com.github.zelmothedragon.whiteapp.view;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author MOSELLE Maxime
 */
@Location("index.xhtml")
public class IndexPage {

    @FindBy(id = "go")
    private WebElement buttonGo;

    public IndexPage() {
    }

    public void clickButtonGo() {
        Graphene
                .guardHttp(buttonGo)
                .click();
    }
}
