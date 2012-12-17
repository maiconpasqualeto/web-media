/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sixtec.webmedia.dao.base;


import java.io.Serializable;
import java.util.List;

/**
 *
 * @author maicon.pasqualeto
 */
public interface IBaseDAO {

	public <T> T adicionar(T o) throws DAOException;

	public <T> void alterar(T o) throws DAOException;

	public <T> void excluir(Serializable id, Class<T> classe)throws DAOException;
	
	public <T> void excluir(T o, Class<T> classe)throws DAOException;

    public <T> T buscar(Serializable id, Class<T> classe) throws DAOException;

    public <T> List<T> buscarTodos(Class<T> c) throws DAOException;
    
}
