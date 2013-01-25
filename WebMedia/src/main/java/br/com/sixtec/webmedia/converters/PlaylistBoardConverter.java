/**
 * 
 */
package br.com.sixtec.webmedia.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sixtec.webmedia.entidades.Playlist;

/**
 * @author maicon
 *
 */
@FacesConverter(value="pbConverter")
public class PlaylistBoardConverter implements Converter {

	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
		if (value != null)
			return comp.getAttributes().get(value);
			
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
		if (value != null && !"".equals(value)) {
			Playlist p = (Playlist) value;
			String strId = p.getId().toString();
			comp.getAttributes().put(strId, p);
			return strId;
		}
		return (String) value;
	}

}
