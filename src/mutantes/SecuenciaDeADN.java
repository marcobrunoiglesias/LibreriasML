/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mutantes;
import excepciones.ExcepcionInputADN;

/*
 *
 * @author Marco Iglesias
 */
public class SecuenciaDeADN {
    private char[][] matriz;
    private int cantidadDeSecuenciasNitrogenadas;
    private String[] arregloDeStrings;

    /**
     * A partir de un conjunto de arreglos de Strings, crea una secuencia completa de ADN
     * Verifica que las secuencias ingresadas correspondan a un humano o a un mutante, esto es, que
     * los caracteres correspondan a bases nitrogenadas validad, y que la matriz de datos tenga dimensiones
     * N x N. En caso de que no se cumplan dichas condiciones, lanza una excepcion.
     * @param arregloDeStrings
     * @throws ExcepcionInputADN 
     */
    public SecuenciaDeADN(String[] arregloDeStrings) throws ExcepcionInputADN {
        
        try{
            this.arregloDeStrings = arregloDeStrings;
            levantarSecuenciaADN(arregloDeStrings);
        }
        catch(ExcepcionInputADN excepcionInputADN){
            throw excepcionInputADN;
        }
        catch(Exception e){
            throw e;
        }
        
    }
    
    /**
     * A partir de los datos del arreglo de Strings, completa la matriz con las bases nitrogenadas
     * @param dna
     * @throws ExcepcionInputADN 
     */
    private void levantarSecuenciaADN(String[] dna) throws ExcepcionInputADN{
        
        try{
            cantidadDeSecuenciasNitrogenadas = dna.length;
            matriz = new char[cantidadDeSecuenciasNitrogenadas][cantidadDeSecuenciasNitrogenadas];
            
            for (int i=0; i < cantidadDeSecuenciasNitrogenadas; i++){
                String stringActual = dna[i];
                
                stringActual = stringActual.toUpperCase();
                
                if (stringActual.length() == cantidadDeSecuenciasNitrogenadas){
                    for (int j=0; j < stringActual.length(); j++){
                        char caracter = stringActual.charAt(j);
                        if (esCaracterCorrectoDeADN(caracter)){
                            matriz[i][j] = caracter;
                        }
                        else{
                            String mensajeParaUsuario = "La secuencia de ADN nro "+i+" contiene una base nitrogenada incorrecta ("+caracter+")";
                            throw new ExcepcionInputADN(mensajeParaUsuario, mensajeParaUsuario);
                        }
                    }
                }
                else{
                    String mensajeParaUsuario = "La secuencia de ADN nro "+i+" no tiene la cantidad de caracteres esperados ("+cantidadDeSecuenciasNitrogenadas+" caracteres)";
                    throw new ExcepcionInputADN(mensajeParaUsuario, mensajeParaUsuario);
                }
                
            }
        }
        catch(ExcepcionInputADN excepcionInputADN){
            throw excepcionInputADN;
        }
        catch(Exception e){
            throw e;
        }
    }
    
    private boolean esCaracterCorrectoDeADN(char caracter){
        boolean respuesta = false;
        
        if (caracter == 'A' || caracter == 'T' || caracter == 'C' || caracter == 'G'){
            respuesta = true;
        }
        
        return respuesta;
    }
    
    /**
     * Retorna el valor del caracter correspondiente a la base nitrogenada requerida
     * @param secuencia
     * @param nroDeBaseNitrogenada
     * @return 
     */
    public char getBaseNitrogenada(int secuencia, int nroDeBaseNitrogenada){
        return this.matriz[secuencia][nroDeBaseNitrogenada];
    }
    
    /**
     * Retorna la cantidad de strings o secuencias nitrogenadas que tiene el adn completo
     * @return 
     */
    public int getCantidadDeSecuenciasDeBasesNitrogenadas(){
        return cantidadDeSecuenciasNitrogenadas;
    }
    
    /**
     * Retorna la cantidad de caracteres que tiene cada string o secuencia nitrogenada.
     * Este numero deberia coincidir con la cantidad de secuencias nitrogenadas
     * @return 
     */
    public int getCantidadDeBasesNitrogenadasPorSecuenciaDeADN(){
        return cantidadDeSecuenciasNitrogenadas;
    }
    
    /**
     * Devuelve un string con todas las secuencias de adn concatenadas
     * @return 
     */
    public String getSecuenciasConcatenadas(){
        String respuesta = "";
        
        for (int i=0; i < this.arregloDeStrings.length; i++){
            respuesta = respuesta + this.arregloDeStrings[i];
        }
        
        return respuesta;
    }
    
}
