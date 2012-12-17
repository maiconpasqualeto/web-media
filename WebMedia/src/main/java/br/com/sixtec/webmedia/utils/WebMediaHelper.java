/**
 * 
 */
package br.com.sixtec.webmedia.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

/**
 * @author maicon
 *
 */
public class WebMediaHelper {
	
	private static final Logger log = Logger.getLogger(WebMediaHelper.class);
	
	public static void copyFile(File destino, UploadedFile fileUploaded){
		FileOutputStream fos = null;
		InputStream is = null;
		
        try {
			fos = new  FileOutputStream(destino);
			is = fileUploaded.getInputstream();
			
			byte[] buff = new byte[2048];
			while ( is.read(buff)  > -1){
				fos.write(buff);
			}
			
			fos.flush();
			
		} catch (FileNotFoundException e) {
			log.error("Arquivo não encontrado.", e);
		} catch (IOException e) {
			log.error("Erro de IO.", e);
		} finally {
			try {
				if (fos != null){
					fos.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				log.error("Erro ao fechar os arquivos.", e);
			}
		}
	}

}
