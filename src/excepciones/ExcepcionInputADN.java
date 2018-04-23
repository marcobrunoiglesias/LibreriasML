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
public class ExcepcionInputADN extends ExcepcionConMensajeParaUsuario{

    /**
     * Excepcion generada si los datos de entrada de la matriz del DNA está mal formada
     * @param mensajeParaUsuario
     * @param message 
     */
    public ExcepcionInputADN(String mensajeParaUsuario, String message) {
        super(mensajeParaUsuario, message);
    }
    
}
