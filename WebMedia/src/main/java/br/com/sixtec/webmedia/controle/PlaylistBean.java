/**
 * 
 */
package br.com.sixtec.webmedia.controle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import br.com.sixtec.webmedia.dao.PlaylistDAO;
import br.com.sixtec.webmedia.dao.base.DAOException;
import br.com.sixtec.webmedia.entidades.Midia;
import br.com.sixtec.webmedia.entidades.Playlist;

/**
 * @author maicon
 *
 */
@ManagedBean(name="playlistBean")
@ViewScoped
public class PlaylistBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PlaylistBean.class);
	
	private List<Playlist> playlists;
	
	private Playlist playlistApagar;
	
	private Playlist playlist;
	
	private Playlist selectedPlaylist;
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	public Playlist getPlaylistApagar() {
		return playlistApagar;
	}

	public void setPlaylistApagar(Playlist playlistApagar) {
		this.playlistApagar = playlistApagar;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Playlist getSelectedPlaylist() {
		return selectedPlaylist;
	}

	public void setSelectedPlaylist(Playlist selectedPlaylist) {
		this.selectedPlaylist = selectedPlaylist;
	}

	@PostConstruct
	public void listaPlaylist(){
		playlists = PlaylistDAO.getInstance().buscarPlaylists();
		for (Playlist p : playlists) {
			Playlist pl = PlaylistDAO.getInstance().buscarPlaylist(p.getId());
			for (Midia m : pl.getMidias()) {
				log.debug("Midia : " + m.getId());
			}
		}
	}
	
	public void salvar() {
		
	}
	
	public void deletarPlaylist() throws DAOException {  
		
		PlaylistDAO.getInstance().excluir(playlistApagar.getId(), Playlist.class);
		
		listaPlaylist();
		
		FacesMessage msg = new FacesMessage(
				"O Registro foi excluído.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public void onSelect(SelectEvent event) {  
		Playlist p = (Playlist) event.getObject();
		playlist = p;
        FacesMessage msg = new FacesMessage("Selecionado", p.getDescricao()); 
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  

}
