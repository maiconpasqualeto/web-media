/**
 * 
 */
package br.com.sixtec.webmedia.controle;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.sixtec.webmedia.dao.MidiaDAO;
import br.com.sixtec.webmedia.dao.base.DAOException;
import br.com.sixtec.webmedia.entidades.Midia;
import br.com.sixtec.webmedia.utils.WebMediaHelper;

/**
 * @author maicon
 *
 */
@ManagedBean
@RequestScoped
public class MidiaBean {
	
	private static final Logger log = Logger.getLogger(MidiaBean.class);
	
	private static final String BASE_PATH = "/home/maicon/";
	
	private List<Midia> midias;
	
	private Midia midiaSelecionada = new Midia();
	
	private Midia midia = new Midia();
	
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

	@PostConstruct
	public void listaMidias(){
		try {		
			midias = MidiaDAO.getInstance().buscarTodos(Midia.class);
		} catch (DAOException e) {
			log.error("Erro ao buscar Midias", e);
		}
	}
	
	public void upload() {  
        if(fileUploaded != null) {             
        	this.midia.setNomeArquivo(fileUploaded.getFileName());
            this.midia.setPathArquivo(BASE_PATH);
            
            File arquivo = new File(BASE_PATH + fileUploaded.getFileName());
            if (arquivo.exists()) {
            	log.warn("Arquivo " + fileUploaded.getFileName() + " já existe.");
            	FacesMessage msg = new FacesMessage("Warning", "Arquivo " + fileUploaded.getFileName() + " já existe.");  
            	msg.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, msg);  
            	return;
            }
            WebMediaHelper.copyFile(arquivo, fileUploaded);
            
            FacesMessage msg = new FacesMessage("Arquivo ", fileUploaded.getFileName() + " foi enviado para o servidor.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        } 
    }
	
	public void salvar() throws DAOException{
        Midia m = new Midia();
        m.setNomeArquivo(getMidia().getNomeArquivo());
        m.setPathArquivo(getMidia().getPathArquivo());
        m.setTempoReproducao(getMidia().getTempoReproducao());
        
        MidiaDAO.getInstance().adicionar(m);
        
        listaMidias();
        
        FacesMessage msg = new FacesMessage("Registro salvo!"); 
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
}
