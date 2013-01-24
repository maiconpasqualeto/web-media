/**
 * 
 */
package br.com.sixtec.webmedia.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import br.com.sixtec.webmedia.dao.base.BridgeBaseDAO;
import br.com.sixtec.webmedia.dao.base.HibernateBaseDAOImp;
import br.com.sixtec.webmedia.entidades.Board;
import br.com.sixtec.webmedia.persistencia.base.AdministradorPersistencia;

/**
 * @author maicon
 *
 */
public class BoardDAO extends BridgeBaseDAO {
	
	private static final Logger log = Logger.getLogger(BoardDAO.class);
	
	private static BoardDAO dao;
	
	public static BoardDAO getInstance(){
		if (dao == null)
			dao = new BoardDAO();
		return dao;
	}

	/**
	 * @param baseDAO
	 */
	public BoardDAO() {
		super(new HibernateBaseDAOImp());
	}
	
	public Board buscarPeloBoardSerial(String boardSerial) {
		EntityManager em = AdministradorPersistencia.getEntityManager();
		Board b = null;
		
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select b from Board b ");
			hql.append("where b.boardSerial = :boardSerial ");
			TypedQuery<Board> q = em.createQuery(hql.toString(), Board.class);
			q.setParameter("boardSerial", boardSerial);
			
			b = q.getSingleResult();
			
			Hibernate.initialize(b.getPlaylist());
			
		} catch (NoResultException e) {
			// não retornou nenhum objeto não faz nada
		} catch (Exception e) {
			log.error("Erro ao buscar Playlist", e);
		} finally {
            em.close();
        }
		return b;
	}

}
