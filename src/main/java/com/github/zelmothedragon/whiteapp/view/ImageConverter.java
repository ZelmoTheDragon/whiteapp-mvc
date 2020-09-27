package com.github.zelmothedragon.whiteapp.view;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Convertisseur pour champ de formulaire de type image.
 *
 * @author MOSELLE Maxime
 */
@FacesConverter(value = "imageConverter", managed = true)
public class ImageConverter implements Converter<String> {

    /**
     * Constructeur d'injection. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public ImageConverter() {
        // Ne pas appeler explicitement.
    }

    @Override
    public String getAsObject(
            final FacesContext context,
            final UIComponent component,
            final String value) {

        return null;
    }

    @Override
    public String getAsString(
            final FacesContext context,
            final UIComponent component,
            final String value) {

        return null;
    }

}
