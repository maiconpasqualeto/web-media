/**
 * 
 */
package br.com.sixtec.webmedia.rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import br.com.sixtec.webmedia.entidades.Midia;
import br.com.sixtec.webmedia.entidades.Playlist;
import br.com.sixtec.webmedia.facade.BoardFacade;


/**
 * @author maicon
 *
 */
@Path("board")
public class BoardRest {
	
	public static final Logger log = Logger.getLogger(BoardRest.class);

	@GET
	@Path("retorna-midia/{mediaid}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] retornaMidia(@PathParam("mediaid") String idMidia) {
		JSONObject o = new JSONObject();
		o.put("id", 1);
		o.put("nome", "Fiesta");
		System.out.println("id Midia " + idMidia);
		return o.toString().getBytes();
	}

	/**
	 * post com Form
	 * @param p1
	 * @return
	 */
	@POST
	@Path("postar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] postar(@FormParam("p1") String p1) {
		JSONObject o = new JSONObject();
		o.put("id", 1);
		o.put("nome", "Fiesta");
		System.out.println("P1 " + p1);		
		return o.toString().getBytes();
	}
	
	@POST
	@Path("postarmultipart")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] postar(MultivaluedMap<String, String> p1) {
		JSONObject o = new JSONObject();
		o.put("id", 1);
		o.put("nome", "Fiesta");
		System.out.println("P1 " + p1.getFirst("p1"));		
		return o.toString().getBytes();
	}
	
	@POST
	@Path("registraboard")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registraboard(
			@FormParam("boardSerial") String boardSerial,
			@FormParam("identificador") String identificador) {
		BoardFacade facade = BoardFacade.getInstance();
		Playlist p = facade.registrarBoard(boardSerial, identificador);
		if (p == null)
			p = new Playlist();
		List<Midia> midias = facade.buscaMidiasPlaylist(p);
		JSONArray arr = new JSONArray();
		for (Midia m  : midias){
			arr.add(m.toJSONObject());
		}
		JSONObject o = new JSONObject();
		
		o.put("playlist", p.toJSONObject());
		o.put("midias", arr);
		return Response.status(Response.Status.ACCEPTED).entity(o.toString()).build();
	}
	
	@GET
	@Path("downloadmidia/{idmidia}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] downloadmidia(@PathParam("idmidia") String idMidia) {
		byte[] bytes = null;
		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {			
			File f = BoardFacade.getInstance().downloadDaMidia(Long.valueOf(idMidia));
			fis = new FileInputStream(f);
			byte[] buf = new byte[1024];
			int len = fis.read(buf);
			while (len > -1){
				baos.write(buf);
				len = fis.read(buf);
			}
			baos.flush();
			bytes = baos.toByteArray();
		} catch (IOException e) {
			log.error("Erro de IO no dowload midia", e);
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (baos != null)
					baos.close();
			} catch (IOException e) {
				log.error("Erro ao fechar arquivo", e);
			}
			
		}
		return bytes;
	}

}
