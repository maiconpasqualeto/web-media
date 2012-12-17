/**
 * 
 */
package br.com.sixtec.webmedia.dao;

import br.com.sixtec.webmedia.dao.base.BridgeBaseDAO;
import br.com.sixtec.webmedia.dao.base.HibernateBaseDAOImp;

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
	
	

}
