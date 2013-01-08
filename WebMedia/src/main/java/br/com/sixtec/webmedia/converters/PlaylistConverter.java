/**
 * 
 */
package br.com.sixtec.webmedia.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import br.com.sixtec.webmedia.entidades.Midia;

/**
 * @author maicon
 *
 */
@FacesConverter(value="playlistConverter")
public class PlaylistConverter implements Converter {

	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String str) {
		
		DualListModel<Midia> dualList = (DualListModel<Midia>) ((PickList) comp).getValue();  
		
		Midia m = new Midia();
		m.setId(Long.valueOf(str));
		return m;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		Midia m = (Midia) obj;
		return m.getId().toString();
	}

}
