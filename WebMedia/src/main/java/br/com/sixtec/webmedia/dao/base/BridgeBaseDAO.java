/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sixtec.webmedia.dao.base;


import java.io.Serializable;
import java.util.List;


/**
 * Pattern Bridge
 * @author maicon.pasqualeto
 */
public abstract class BridgeBaseDAO implements IBaseDAO {

    private IBaseDAO baseDAO;

    public BridgeBaseDAO(IBaseDAO baseDAO){
        this.baseDAO = baseDAO;
    }

    /**
     * @return the hibernateBaseDAO
     */
    public IBaseDAO getBaseDAO() {
        return baseDAO;
    }

    public <T> T adicionar(T o) throws DAOException {
        return getBaseDAO().adicionar(o);
    }

    public <T> void alterar(T o) throws DAOException {
        getBaseDAO().alterar(o);
    }

    public <T> void excluir(Serializable id, Class<T> classe) throws DAOException {
        getBaseDAO().excluir(id, classe);
    }
    
    public <T> void excluir(T objeto, Class<T> classe) throws DAOException {
        getBaseDAO().excluir(objeto, classe);
    }

    public <T> T buscar(Serializable id, Class<T> classe) throws DAOException {
        return getBaseDAO().buscar(id, classe);
    }

    public <T> List<T> buscarTodos(Class<T> c) throws DAOException {
        return getBaseDAO().buscarTodos(c);
    }

}
