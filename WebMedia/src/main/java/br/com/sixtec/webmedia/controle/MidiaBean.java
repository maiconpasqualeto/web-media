/**
 * 
 */
package br.com.sixtec.webmedia.controle;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import br.com.sixtec.webmedia.dao.MidiaDAO;
import br.com.sixtec.webmedia.dao.base.DAOException;
import br.com.sixtec.webmedia.entidades.Midia;
import br.com.sixtec.webmedia.utils.WebMediaHelper;

/**
 * @author maicon
 *
 */
@ManagedBean(name="midiaBean")
@ViewScoped
public class MidiaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(MidiaBean.class);
	
	private static final String BASE_PATH = "/home/maicon/arquivos/";
	
	private List<Midia> midias;
	
	private Midia midiaSelecionada;
	
	private Midia midia = new Midia();
	
	private Midia midiaApagar;
	
	private UploadedFile fileUploaded;  
	
	/**
	 * 
	 */
	public MidiaBean() {
		
	}

	public List<Midia> getMidias() {
		return midias;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}
			
	public Midia getMidiaSelecionada() {
		return midiaSelecionada;
	}

	public void setMidiaSelecionada(Midia midiaSelecionada) {
		this.midiaSelecionada = midiaSelecionada;
	}

	public UploadedFile getFileUploaded() {
		return fileUploaded;
	}

	public void setFileUploaded(UploadedFile fileUploaded) {
		this.fileUploaded = fileUploaded;
	}
	
	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Midia getMidiaApagar() {
		return midiaApagar;
	}

	public void setMidiaApagar(Midia midiaApagar) {
		this.midiaApagar = midiaApagar;
	}

	@PostConstruct
	public void listaMidias(){
		try {		
			midias = MidiaDAO.getInstance().buscarTodos(Midia.class);
		} catch (DAOException e) {
			log.error("Erro ao buscar Midias", e);
		}
	}
	
	public void upload() throws DAOException {  
        if(fileUploaded != null) {
        	this.midia.setNomeArquivo(fileUploaded.getFileName());
            this.midia.setPathArquivo(BASE_PATH);
            
            File arquivo = new File(BASE_PATH + fileUploaded.getFileName());
            if (arquivo.exists()) {
            	log.warn("Arquivo " + fileUploaded.getFileName() + " já existe.");
            	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, fileUploaded.getFileName(), "Arquivo já existe.");  
            	//msg.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, msg);  
            	return;
            }
            WebMediaHelper.copyFile(arquivo, fileUploaded);
            
            Midia m = new Midia();
            m.setNomeArquivo(getMidia().getNomeArquivo());
            m.setPathArquivo(getMidia().getPathArquivo());
            m.setTempoReproducao(getMidia().getTempoReproducao());
            m.setDataUpload(new Date());
            
            MidiaDAO.getInstance().adicionar(m);
            
            listaMidias();
            
            setMidia(new Midia());
            
            FacesMessage msg = new FacesMessage(
            		fileUploaded.getFileName(), 
            		"O arquivo foi enviado para o servidor.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        } 
    }
	
	public void deletarMidia() throws DAOException {  
		/*Midia m = (Midia) actionEvent.getComponent()
				.getAttributes().get("midia");*/
				
		File arquivo = new File(midiaApagar.getPathArquivo() + midiaApagar.getNomeArquivo());
		if (arquivo.exists())
			arquivo.delete();		
		
		MidiaDAO.getInstance().excluir(midiaApagar.getId(), Midia.class);
		
		listaMidias();
		
		FacesMessage msg = new FacesMessage(
				midiaApagar.getNomeArquivo(), 
				"O arquivo foi excluído do servidor.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	
}
