package com.github.zelmothedragon.whiteapp.view;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputFile;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.Part;

/**
 *
 * @author MOSELLE Maxime
 */
@FacesConverter(value = "imageConverter", managed = true)
public class ImageConverter implements Converter<String> {

    @Override
    public String getAsObject(
            final FacesContext context,
            final UIComponent component,
            final String value) {

        System.out.printf("COMPONENT: %s%n", component);

        var input = (HtmlInputFile) component;
        var file = (Part) input.getSubmittedValue();

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
