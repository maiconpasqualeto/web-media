/**
 * 
 */
package br.com.sixtec.webmedia.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name="playlist")
public class Playlist implements Serializable, StringJASON {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="seqPlaylist", sequenceName="playlist_id_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqPlaylist")
	@Column(name="id")
	private Long id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="data_hora_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraCriacao;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Playlist_Midia",
				joinColumns={@JoinColumn(name="id_playlist")},
				inverseJoinColumns={@JoinColumn(name="id_midia")})
	private List<Midia> midias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(Date dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public List<Midia> getMidias() {
		return midias;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject o = new JSONObject();
		o.put("id", id != null ? id : "");
		o.put("descricao", descricao != null ? descricao : "");
		o.put("dataHoraCriacao", 
				dataHoraCriacao != null ? 
						WebMediaHelper.FORMATADOR_DATA_HORA.format(dataHoraCriacao) : dataHoraCriacao);
		return o;
	}
	
	
}
