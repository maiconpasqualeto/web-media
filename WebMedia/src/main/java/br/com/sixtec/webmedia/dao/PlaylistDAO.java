/**
 * 
 */
package br.com.sixtec.webmedia.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import br.com.sixtec.webmedia.dao.base.BridgeBaseDAO;
import br.com.sixtec.webmedia.dao.base.HibernateBaseDAOImp;
import br.com.sixtec.webmedia.entidades.Midia;
import br.com.sixtec.webmedia.entidades.Playlist;
import br.com.sixtec.webmedia.persistencia.base.AdministradorPersistencia;

/**
 * @author maicon
 *
 */
public class PlaylistDAO extends BridgeBaseDAO {
	
	private static final Logger log = Logger.getLogger(PlaylistDAO.class);
	
	public static PlaylistDAO dao;
	
	public static PlaylistDAO getInstance(){
		if (dao == null)
			dao = new PlaylistDAO();
		return dao;
	}

	/**
	 * @param baseDAO
	 */
	public PlaylistDAO() {
		super(new HibernateBaseDAOImp());
	}
	
	
	
	public List<Playlist> buscarPlaylists() {
		EntityManager em = AdministradorPersistencia.getEntityManager();
		List<Playlist> retorno = null;
        try{
        	StringBuilder hql = new StringBuilder();
        	hql.append("select p from Playlist p");
        	TypedQuery<Playlist> q = em.createQuery(hql.toString(), Playlist.class);        	
            retorno = q.getResultList();
        } finally {
            em.close();
        }
        return retorno;
	}
	
	public Playlist buscarPlaylist(Long id) {
		EntityManager em = AdministradorPersistencia.getEntityManager();
		Playlist p = null;
		try {
			p = em.find(Playlist.class, id);
			Hibernate.initialize(p.getMidias());
		} catch (Exception e) {
			log.error("Erro ao buscar Playlist", e);
		} finally {
            em.close();
        }
		return p;
	}
	
	public void adicionarPlaylist(Playlist p, List<Midia> midias) {
		EntityManager em = AdministradorPersistencia.getEntityManager();
		EntityTransaction t = em.getTransaction();
        try{
        	t.begin();
        	List<Midia> midiasSessao = new ArrayList<Midia>();
        	for (Midia m : midias) {
        		m = em.merge(m);
        		midiasSessao.add(m);
        	}
        	p.setMidias(midiasSessao);
        	em.persist(p);
        	
        	t.commit();
        	
        } catch (Exception e) {
        	t.rollback();
        	log.error("Erro ao inserir Playlist", e);
        } finally {
            em.close();
        }
	}

}
