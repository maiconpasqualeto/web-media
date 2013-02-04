/**
 * 
 */
package br.com.sixtec.webmedia.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sixtec.webmedia.utils.WebMediaHelper;

import net.sf.json.JSONObject;


/**
 * @author maicon
 *
 */
@Entity
@Table(name="midia")
public class Midia implements Serializable, StringJASON {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seqMidia", sequenceName="midia_id_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqMidia")
	@Column(name="id")
	private Long id;
	
	@Column(name="nome_arquivo")
	private String nomeArquivo;
	
	@Column(name="path_arquivo")
	private String pathArquivo;
	
	@Column(name="tempo_reproducao")
	private Integer tempoReproducao;
	
	@Column(name="data_upload")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUpload;
	
	@ManyToMany(mappedBy="midias")
	private List<Playlist> playlists;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getPathArquivo() {
		return pathArquivo;
	}

	public void setPathArquivo(String pathArquivo) {
		this.pathArquivo = pathArquivo;
	}

	public Integer getTempoReproducao() {
		return tempoReproducao;
	}

	public void setTempoReproducao(Integer tempoReproducao) {
		this.tempoReproducao = tempoReproducao;
	}

	public Date getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject o = new JSONObject();
		o.put("id", id);
		o.put("nomeArquivo", nomeArquivo != null ? nomeArquivo : "");
		o.put("pathArquivo", pathArquivo != null ? pathArquivo : "");
		o.put("tempoReproducao", tempoReproducao != null ? tempoReproducao : "");
		o.put("dataUpload", dataUpload != null ?
				WebMediaHelper.FORMATADOR_DATA_HORA.format(dataUpload) : "");
		return o;
	}

}
