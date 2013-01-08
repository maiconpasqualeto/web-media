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

/**
 * @author maicon
 *
 */
@Entity
@Table(name="playlist")
public class Playlist implements Serializable {
	
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
	
	
}
