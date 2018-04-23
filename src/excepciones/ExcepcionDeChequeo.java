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
public class ExcepcionDeChequeo extends ExcepcionConMensajeParaUsuario{

    /**
     * Excepcion para un procedimiento de datos
     * @param mensajeParaUsuario
     * @param message 
     */
    public ExcepcionDeChequeo(String mensajeParaUsuario, String message) {
        super(mensajeParaUsuario, message);
    }
    
    
    
}
