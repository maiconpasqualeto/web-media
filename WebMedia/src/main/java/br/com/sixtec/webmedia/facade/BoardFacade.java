/**
 * 
 */
package br.com.sixtec.webmedia.facade;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sixtec.webmedia.dao.BoardDAO;
import br.com.sixtec.webmedia.dao.MidiaDAO;
import br.com.sixtec.webmedia.dao.base.DAOException;
import br.com.sixtec.webmedia.entidades.Board;
import br.com.sixtec.webmedia.entidades.Midia;
import br.com.sixtec.webmedia.entidades.Playlist;

/**
 * @author maicon
 *
 */
public class BoardFacade {
	
	private static final Logger log = Logger.getLogger(BoardFacade.class);
	
	private static BoardFacade facade;
	
	public static BoardFacade getInstance(){
		if (facade == null)
			facade = new BoardFacade();
		return facade;
	}

	/**
	 * Registra a board
	 * Se a board já existir e estiver ligada a um playlist devolve a lista do playlist
	 * @param boardSerial
	 * @return
	 */
	public Playlist registrarBoard(String boardSerial, String identificador){
		Playlist p = null;
		try {
			BoardDAO dao = BoardDAO.getInstance();
			Board b = dao.buscarPeloBoardSerial(boardSerial);
			if (b == null) {
				b = new Board();
				b.setBoardSerial(boardSerial);
				b.setIdentificador(identificador);
				b = dao.adicionar(b);
			}
			
			if (b.getPlaylist() != null){ 
				p = b.getPlaylist();
			}
			
		} catch (DAOException e) {
			log.error("Erro ao registrar board", e);
		}
		return p;
	}
	
	public List<Midia> buscaMidiasPlaylist(Playlist p){
		List<Midia> midias = new ArrayList<Midia>();
		if (p != null)
			midias = MidiaDAO.getInstance().buscarMidiasDoPlaylist(p);
		return midias;		
	}
	
	public File downloadDaMidia(Long idMidia){
		File f = null;
		try {
			
			MidiaDAO dao = MidiaDAO.getInstance();
			Midia m = dao.buscar(idMidia, Midia.class);
			String strArquivo = m.getPathArquivo() + "/" + m.getNomeArquivo();
			f = new File(strArquivo);
			
		} catch (DAOException e) {
			log.error("Erro no dowload da Midia", e);
		}
		return f;
	}

}
