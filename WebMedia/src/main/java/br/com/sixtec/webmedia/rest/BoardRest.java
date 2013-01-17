/**
 * 
 */
package br.com.sixtec.webmedia.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import net.sf.json.JSONObject;


/**
 * @author maicon
 *
 */
@Path("board")
public class BoardRest {

	@GET
	@Path("retorna-midia/{mediaid}")
	//@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] retornaMidia(@PathParam("mediaid") String idMidia) {
		JSONObject o = new JSONObject();
		o.put("id", 1);
		o.put("nome", "Fiesta");
		System.out.println("id Midia " + idMidia);
		return o.toString().getBytes();
	}

	@POST
	@Path("postar")
	@Consumes()
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] postar(MultivaluedMap<String, String> params) {
		JSONObject o = new JSONObject();
		o.put("id", 1);
		o.put("nome", "Fiesta");
		System.out.println("id Midia " + params);
		return o.toString().getBytes();
	}

}
