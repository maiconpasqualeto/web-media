/**
 * 
 */
package br.com.sixtec.webmedia.controle;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import br.com.sixtec.webmedia.dao.BoardDAO;
import br.com.sixtec.webmedia.dao.MidiaDAO;
import br.com.sixtec.webmedia.dao.PlaylistDAO;
import br.com.sixtec.webmedia.dao.base.DAOException;
import br.com.sixtec.webmedia.entidades.Board;
import br.com.sixtec.webmedia.entidades.Midia;
import br.com.sixtec.webmedia.entidades.Playlist;

/**
 * @author maicon
 *
 */
@ManagedBean(name="boardBean")
@ViewScoped
public class BoardBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(BoardBean.class);
	
	private Board board = new Board();
	
	private Board boardSelecionada;
	
	private Board boardApagar;
	
	private Playlist playlist;
	
	private List<Midia> midiasBoard;
	
	private List<Board> boards;
	
	private List<Playlist> playlists;

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public Board getBoardSelecionada() {
		return boardSelecionada;
	}

	public void setBoardSelecionada(Board boardSelecionada) {
		this.boardSelecionada = boardSelecionada;
	}

	public Board getBoardApagar() {
		return boardApagar;
	}

	public void setBoardApagar(Board boardApagar) {
		this.boardApagar = boardApagar;
	}

	public List<Midia> getMidiasBoard() {
		return midiasBoard;
	}

	public void setMidiasBoard(List<Midia> midiasBoard) {
		this.midiasBoard = midiasBoard;
	}

	@PostConstruct
	public void listaInformacoesInciciais(){
		boardApagar = null;
		boardSelecionada = null;
		playlist = null;
		board = new Board();
		playlists = PlaylistDAO.getInstance().buscarPlaylists();
		boards = BoardDAO.getInstance().buscarBoards();
	}
	
	public void deletarBoard(){
		if (boardApagar != null || boardApagar.getId() != null) {
			try {
				BoardDAO.getInstance().excluir(boardApagar.getId(), Board.class);
			} catch (DAOException e) {
				log.error("Erro ao excluir a board", e);
			}
		}
		FacesMessage m = new FacesMessage("A Board foi deletada!");
		FacesContext.getCurrentInstance().addMessage(null, m);
		
		listaInformacoesInciciais();
	}
	
	public void onSelect(SelectEvent event) {  
		board = (Board) event.getObject();
		playlist = PlaylistDAO.getInstance().buscarPlaylistDaBoard(board.getId());
    }
	
	public void salvar(){
		if (board == null || board.getId() == null) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Erro ao salvar Board", "Selecione uma Board da lista para salvar.");
			FacesContext.getCurrentInstance().addMessage(null, m);
			return;
		}
		board.setPlaylist(playlist);
		board.setDataHoraPlaylist(Calendar.getInstance().getTime());
		
		try {
			BoardDAO.getInstance().alterar(board);
			FacesMessage m = new FacesMessage("Board salva com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, m);
			
		} catch (DAOException e) {
			log.error("Erro ao alterar a board", e);
		}
		listaInformacoesInciciais();
	}
	
	public void mostraMidias(Board b){
		midiasBoard = MidiaDAO.getInstance().buscarMidiasDoPlaylist(b.getPlaylist());
	}
}
