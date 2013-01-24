/**
 * 
 */
package br.com.sixtec.webmedia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="board")
public class Board implements Serializable, StringJASON {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seqBoard", sequenceName="board_id_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seqBoard")
	@Column(name="id")
	private Long id;
	
	/**
	 * Número serial da board, enviada pelo android
	 */
	@Column(name="board_serial")
	private String boardSerial;
	
	/**
	 * Identificação da board definida pelo usuário: placa do carro (se for móvel)
	 * número da televisão, prédio, elevador, etc.
	 */
	@Column(name="identificador")
	private String identificador;
		
	@Column(name="datahora_playlist")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraPlaylist;
	
	@ManyToOne(targetEntity=Playlist.class)
	@JoinColumn(name="id_playlist")
	private Playlist playlist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBoardSerial() {
		return boardSerial;
	}

	public void setBoardSerial(String boardSerial) {
		this.boardSerial = boardSerial;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Date getDataHoraPlaylist() {
		return dataHoraPlaylist;
	}

	public void setDataHoraPlaylist(Date dataHoraPlaylist) {
		this.dataHoraPlaylist = dataHoraPlaylist;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject o = new JSONObject();
		o.put("id", id);
		o.put("identificador", identificador);
		o.put("boardSerial", boardSerial);
		o.put("dataHoraPlaylist", WebMediaHelper.FORMATADOR_DATA_HORA.format(dataHoraPlaylist));
		return o;
	}

}
