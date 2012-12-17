/**
 * 
 */
package br.com.sixtec.webmedia.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import br.com.sixtec.webmedia.persistencia.base.AdministradorPersistencia;
import br.com.sixtec.webmedia.persistencia.base.PersistenciaException;

/**
 * @author maicon
 *
 */
public class WebMediaListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		AdministradorPersistencia.close();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			AdministradorPersistencia.createEntityManagerFactory("webmedia");
		} catch (PersistenciaException e) {
			Logger.getLogger(WebMediaListener.class).error(e);
		}
	}

}
