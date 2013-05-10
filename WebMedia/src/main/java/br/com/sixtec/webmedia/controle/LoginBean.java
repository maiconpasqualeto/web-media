/**
 * 
 */
package br.com.sixtec.webmedia.controle;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * @author maicon
 *
 */
@ManagedBean(name="loginBean")
public class LoginBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String logar(){
		if ("maicon".equals(usuario) &&
				"maicon".equals(senha))
			return "/layout/home.xhtml";
		else {
			FacesMessage m = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Usuário ou senha inválidos.", 
					"Usuário ou senha inválidos.");
			FacesContext.getCurrentInstance().addMessage(null, m);
		}
		return "";		
	}
	
	public String sair(){
		return "/pages/login.xhtml";
	}

}
