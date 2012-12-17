/**
 * 
 */
package br.com.sixtec.webmedia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author maicon
 *
 */
@Entity
@Table(name="midia")
public class Midia implements Serializable{
	
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

	
	

}
