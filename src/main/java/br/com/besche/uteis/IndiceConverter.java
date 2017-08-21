package br.com.besche.uteis;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.besche.modelo.Indice;


@FacesConverter(value = "indiceConverter")    
//@FacesConverter(forClass = IndiceModel.class)
public class IndiceConverter implements Converter {
  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
      if (value != null && !value.isEmpty()) {
          return (Indice) uiComponent.getAttributes().get(value);
      }
      return null;
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
      if (value instanceof Indice) {
          Indice entity = (Indice) value;
          if (entity != null && entity instanceof Indice && entity.getId() != null) {
              uiComponent.getAttributes().put( entity.getId().toString(), entity);
              return entity.getId().toString();
          }
      }
      return "";
  }
}
