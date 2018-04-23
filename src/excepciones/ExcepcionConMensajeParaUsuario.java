/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excepciones;

/**
 *
 * @author Marco Iglesias
 */
public class ExcepcionConMensajeParaUsuario extends Exception{
    String mensajeParaUsuario;

    public String getMensajeParaUsuario() {
        return mensajeParaUsuario;
    }

    public void setMensajeParaUsuario(String mensajeParaUsuario) {
        this.mensajeParaUsuario = mensajeParaUsuario;
    }

    /**
     * Esta excepcion hereda de Exception. Adjunta el mensaje amigable para el usuario final.
     * @param mensajeParaUsuario
     * @param message 
     */
    public ExcepcionConMensajeParaUsuario(String mensajeParaUsuario, String message) {
        super(message);
        this.mensajeParaUsuario = mensajeParaUsuario;
    }
    
    
    
}
