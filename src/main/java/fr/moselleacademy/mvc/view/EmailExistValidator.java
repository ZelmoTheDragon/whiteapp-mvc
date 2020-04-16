package fr.moselleacademy.mvc.view;

import fr.moselleacademy.mvc.model.entity.Customer;
import fr.moselleacademy.mvc.model.entity.Customer_;
import javax.enterprise.inject.spi.CDI;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;

/**
 * Validateur du champ <i>email</i>. Vérifie que l'adresse de courriel n'est pas
 * déjà utilisé.
 *
 * @author MOSELLE Maxime
 */
@FacesValidator(managed = true)
public class EmailExistValidator implements Validator<String> {

    /**
     * Constructeur d'injection.Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public EmailExistValidator() {
        // Ne pas appeler explicitement.
    }

    @Override
    public void validate(
            final FacesContext context,
            final UIComponent component,
            final String value) throws ValidatorException {

        if (exists(value)) {
            var lang = context.getViewRoot().getLocale();
            var message = new FacesMessage(
                    FacesMessage.SEVERITY_WARN,
                    Message.get("customer.emailExist", lang),
                    ""
            );

            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
    }

    private boolean exists(final String email) {
        var em = CDI.current().select(EntityManager.class).get();
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Long.class);
        var root = query.from(Customer.class);
        query.select(cb.count(root));
        query.where(cb.equal(root.get(Customer_.email), email));
        var result = em.createQuery(query).getSingleResult();
        return result == 1L;
    }

}
