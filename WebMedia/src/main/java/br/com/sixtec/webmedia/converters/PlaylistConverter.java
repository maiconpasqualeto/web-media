/**
 * 
 */
package br.com.sixtec.webmedia.converters;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext ctx, UIComponent comp, String str) {
		Midia m = new Midia();
		m.setId(Long.valueOf(str));
		
		DualListModel<Midia> dualList = (DualListModel<Midia>) ((PickList) comp).getValue();  
		List<Midia> midiasSource = dualList.getSource();
		MidiaComparator mc = new MidiaComparator();
		Collections.sort(midiasSource, mc);		
		int idx = Collections.binarySearch(midiasSource, m, mc);
		if (idx < 0) {
			List<Midia> midiasTarget = dualList.getTarget();
			Collections.sort(midiasTarget, mc);
			idx = Collections.binarySearch(midiasTarget, m, mc);
			m = midiasTarget.get(idx);
		} else {
			m = midiasSource.get(idx);
		}
		
		return m;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		return ((Midia) obj).getId().toString();
	}
	
	private class MidiaComparator implements Comparator<Midia> {
		
		@Override
		public int compare(Midia m1, Midia m2) {
			return m1.getId().compareTo(m2.getId());
		}
	}

}
