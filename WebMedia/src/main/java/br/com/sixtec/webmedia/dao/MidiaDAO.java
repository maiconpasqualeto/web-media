/**
 * 
 */
package br.com.sixtec.webmedia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sixtec.webmedia.dao.base.BridgeBaseDAO;
import br.com.sixtec.webmedia.dao.base.HibernateBaseDAOImp;
import br.com.sixtec.webmedia.entidades.Midia;
import br.com.sixtec.webmedia.entidades.Playlist;
import br.com.sixtec.webmedia.persistencia.base.AdministradorPersistencia;

/**
 * @author maicon
 *
 */
public class MidiaDAO extends BridgeBaseDAO {
	
	public static MidiaDAO dao;
	
	public static MidiaDAO getInstance(){
		if (dao == null)
			dao = new MidiaDAO();
		return dao;
	}
	
	/**
	 * 
	 */
	public MidiaDAO() {
		super(new HibernateBaseDAOImp());
	}
	
	public List<Midia> buscarMidiasQueNaoEstaoNoPlaylist(Playlist p){
		EntityManager em = AdministradorPersistencia.getEntityManager();
		List<Midia> retorno = null;
        try{
        	StringBuilder hql = new StringBuilder();
        	hql.append("select distinct m from Midia m ");
        	hql.append("left outer join m.playlists p ");
        	hql.append("where m not in ( ");
			hql.append("select m from Midia m join m.playlists p ");
			hql.append("where p.id = :idPlaylist) ");
			hql.append("or p = null");
        	TypedQuery<Midia> q = em.createQuery(hql.toString(), Midia.class);
        	q.setParameter("idPlaylist", p.getId());
            retorno = q.getResultList();
        } finally {
            em.close();
        }
        return retorno;		
	}

}
