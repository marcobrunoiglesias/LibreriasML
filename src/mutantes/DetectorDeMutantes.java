/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mutantes;
import excepciones.ExcepcionDeChequeo;
/*
 *
 *
 * @author Marco Iglesias
 */
public class DetectorDeMutantes {
    private final SecuenciaDeADN secuenciaDeADN;
    private int cantidadMinimaDeRepeticionesDeBasesNitrogenadasParaSerMutante = 2;
    private int cantidadDeBasesNitrogenadasContiguas = 4;

    
    /**
     * Crea una instancia de un detector. No hace controles sobre la correctitud de
     * la secuencia de adn ingresada.
     * @param secuenciaDeADN 
     */
    public DetectorDeMutantes(SecuenciaDeADN secuenciaDeADN) {
        this.secuenciaDeADN = secuenciaDeADN;
    }
    
    /**
     * Esta funcion recorre las secuencias de ADN verificando si pertenece o no a un mutante.
     * @return
     * @throws ExcepcionDeChequeo 
     */
    public boolean esMutante () throws ExcepcionDeChequeo{
        boolean esMutante = false;
        int contadorDeOcurrencias = 0;

        try{
            //Recorre todas las secuencias
            for (int nroSecuencia = 0; nroSecuencia < secuenciaDeADN.getCantidadDeSecuenciasDeBasesNitrogenadas(); nroSecuencia++){
                if (!esMutante){
                    //Por cada secuencia chequea las distintas bases nitrogenadas
                    for (int nroDeBaseNitrogenada = 0; nroDeBaseNitrogenada < secuenciaDeADN.getCantidadDeBasesNitrogenadasPorSecuenciaDeADN(); nroDeBaseNitrogenada++){

                        //check hacia el este o derecha
                        if (checkearHaciaEste(nroSecuencia, nroDeBaseNitrogenada)){
                            contadorDeOcurrencias++;
                            if (contadorDeOcurrencias >= this.cantidadMinimaDeRepeticionesDeBasesNitrogenadasParaSerMutante){
                                esMutante = true;
                                break;
                            }
                        }

                        //check hacia el sureste o en diagonal hacia la derecha
                        if (checkearHaciaSudEste(nroSecuencia, nroDeBaseNitrogenada)){
                            contadorDeOcurrencias++;
                            if (contadorDeOcurrencias >= this.cantidadMinimaDeRepeticionesDeBasesNitrogenadasParaSerMutante){
                                esMutante = true;
                                break;
                            }
                        }
                        
                        //check hacia el sur o hacia abajo
                        if (checkearHaciaSur(nroSecuencia, nroDeBaseNitrogenada)){
                            contadorDeOcurrencias++;
                            if (contadorDeOcurrencias >= this.cantidadMinimaDeRepeticionesDeBasesNitrogenadasParaSerMutante){
                                esMutante = true;
                                break;
                            }
                        }
                        
                        //check hacia el sudoeste o en diagonal hacia la izquierda
                        if (checkearHaciaSudOeste(nroSecuencia, nroDeBaseNitrogenada)){
                            contadorDeOcurrencias++;
                            if (contadorDeOcurrencias >= this.cantidadMinimaDeRepeticionesDeBasesNitrogenadasParaSerMutante){
                                esMutante = true;
                                break;
                            }
                        }
                        
                        //El resto de los chequeos no hacen falta ya que estaria comparando las mismas celdas, pero en sentido inverso.
                    }
                }
                else{
                    //si ya se encontraron mas de 2 repeticiones, no hace falta seguir buscando
                    break;
                }
                
            }
            
        }
        catch(ExcepcionDeChequeo excepcionDeChequeo){
            throw excepcionDeChequeo;
        }
        
        return esMutante;
    }

    /**
     * La cantidad minima de repeticiones de bases nitrogenadas es la cantidad de secuencias de bases nitrogenadas contiguas minimas
     * que tiene que tener un conjunto de secuencias de adn para ser mutante
     * @return 
     */
    public int getCantidadMinimaDeRepeticionesDeBasesNitrogenadas() {
        return cantidadMinimaDeRepeticionesDeBasesNitrogenadasParaSerMutante;
    }

    /**
     * La cantidad minima de repeticiones de bases nitrogenadas es la cantidad de secuencias de bases nitrogenadas contiguas minimas
     * que tiene que tener un conjunto de secuencias de adn para ser mutante
     * @param cantidadMinimaDeRepeticionesDeBasesNitrogenadas 
     */
    public void setCantidadMinimaDeRepeticionesDeBasesNitrogenadas(int cantidadMinimaDeRepeticionesDeBasesNitrogenadas) {
        this.cantidadMinimaDeRepeticionesDeBasesNitrogenadasParaSerMutante = cantidadMinimaDeRepeticionesDeBasesNitrogenadas;
    }

    /**
     * Es la cantidad de bases nitrogenadas, o caracteres, que deben estar contiguos para sumar una base nitrogenada mutante.
     * Las repeticiones pueden ser horizontales, verticales u oblicuas
     * @return 
     */
    public int getCantidadDeBasesNitrogenadasContiguas() {
        return cantidadDeBasesNitrogenadasContiguas;
    }

    /**
     * Es la cantidad de bases nitrogenadas, o caracteres, que deben estar contiguos para sumar una base nitrogenada mutante.
     * Las repeticiones pueden ser horizontales, verticales u oblicuas
     * @param cantidadDeBasesNitrogenadasContiguas 
     */
    public void setCantidadDeBasesNitrogenadasContiguas(int cantidadDeBasesNitrogenadasContiguas) {
        this.cantidadDeBasesNitrogenadasContiguas = cantidadDeBasesNitrogenadasContiguas;
    }
    
    /**
     * Dada una base nitrogenada de las secuencias de ADN, chequea ocurrencias hacia la derecha.
     * Retorna true si hay repeticiones
     * @param nroSecuencia
     * @param nroDeBaseNitrogenada
     * @return
     * @throws ExcepcionDeChequeo 
     */
    private boolean checkearHaciaEste(int nroSecuencia, int nroDeBaseNitrogenada) throws ExcepcionDeChequeo{
        boolean respuesta = false;

        try{
            char baseNitrogenadaActual = this.secuenciaDeADN.getBaseNitrogenada(nroSecuencia, nroDeBaseNitrogenada);
        
            //Si el numero de la celda esta muy cerca del borde Este, no hace falta preguntar, porque nunca se va a dar que tenga una secuencia completa con la cantidad necesaria de bases nitrogenadas
            int cantidadDeCeldasRestantesHaciaElEste = this.secuenciaDeADN.getCantidadDeBasesNitrogenadasPorSecuenciaDeADN()-nroDeBaseNitrogenada;
            if (cantidadDeCeldasRestantesHaciaElEste>= this.cantidadDeBasesNitrogenadasContiguas){
                respuesta = true;

                for (int i= 1; i < this.cantidadDeBasesNitrogenadasContiguas; i++){
                    char baseNitrogenadaAComprar = this.secuenciaDeADN.getBaseNitrogenada(nroSecuencia, nroDeBaseNitrogenada+i);
                    respuesta = respuesta && baseNitrogenadaActual == baseNitrogenadaAComprar;
                }
            }
        }
        catch (Exception e){
            String mensajeParaUsuario = "Hubo un error al chequear las ocurrencias contiguas para la base nitrogenada nro "+nroDeBaseNitrogenada+" de la secuencia nro "+nroSecuencia+".";
            String mensajeDeError = "Hubo un error al chequear las ocurrencias contiguas hacia el ESTE para la base nitrogenada nro "+nroDeBaseNitrogenada+" de la secuencia nro "+nroSecuencia+". Excepcion: "+e.getMessage();
            throw new ExcepcionDeChequeo(mensajeParaUsuario,mensajeDeError);
        }
        
        return respuesta;
    }
    
    /**
     * Dada una base nitrogenada de las secuencias de ADN, chequea ocurrencias en diagonal hacia abajo y a la derecha.
     * Retorna true si hay repeticiones
     * @param nroSecuencia
     * @param nroDeBaseNitrogenada
     * @return
     * @throws ExcepcionDeChequeo 
     */
    private boolean checkearHaciaSudEste(int nroSecuencia, int nroDeBaseNitrogenada) throws ExcepcionDeChequeo{
         boolean respuesta = false;
        
         try{
            char baseNitrogenadaActual = this.secuenciaDeADN.getBaseNitrogenada(nroSecuencia, nroDeBaseNitrogenada);

            //Si el numero de la celda esta muy cerca del borde Este y del borde Sur, no hace falta preguntar, porque nunca se va a dar que tenga una secuencia completa con la cantidad necesaria de bases nitrogenadas
            int cantidadDeCeldasRestantesHaciaElEste = this.secuenciaDeADN.getCantidadDeBasesNitrogenadasPorSecuenciaDeADN()-nroDeBaseNitrogenada;
            int cantidadDeCeldasRestantesHaciaElSur = this.secuenciaDeADN.getCantidadDeSecuenciasDeBasesNitrogenadas()-nroSecuencia;
            if (cantidadDeCeldasRestantesHaciaElEste>= this.cantidadDeBasesNitrogenadasContiguas && cantidadDeCeldasRestantesHaciaElSur>= this.cantidadDeBasesNitrogenadasContiguas){
                respuesta = true;
                for (int i= 1; i < this.cantidadDeBasesNitrogenadasContiguas; i++){
                    respuesta = respuesta && baseNitrogenadaActual == this.secuenciaDeADN.getBaseNitrogenada(nroSecuencia+i, nroDeBaseNitrogenada+i);
                }
            }
        }
        catch (Exception e){
            String mensajeParaUsuario = "Hubo un error al chequear las ocurrencias contiguas para la base nitrogenada nro "+nroDeBaseNitrogenada+" de la secuencia nro "+nroSecuencia+".";
            String mensajeDeError = "Hubo un error al chequear las ocurrencias contiguas hacia el SUDESTE para la base nitrogenada nro "+nroDeBaseNitrogenada+" de la secuencia nro "+nroSecuencia+". Excepcion: "+e.getMessage();
            throw new ExcepcionDeChequeo(mensajeParaUsuario,mensajeDeError);
        }
        
        return respuesta;
    }
    
    /**
     * Dada una base nitrogenada de las secuencias de ADN, chequea ocurrencias hacia abajo
     * Retorna true si hay repeticiones
     * @param nroSecuencia
     * @param nroDeBaseNitrogenada
     * @return
     * @throws ExcepcionDeChequeo 
     */
    private boolean checkearHaciaSur(int nroSecuencia, int nroDeBaseNitrogenada) throws ExcepcionDeChequeo{
        boolean respuesta = false;
        
        try{
            char baseNitrogenadaActual = this.secuenciaDeADN.getBaseNitrogenada(nroSecuencia, nroDeBaseNitrogenada);

            //Si el numero de la celda esta muy cerca del borde Este, no hace falta preguntar, porque nunca se va a dar que tenga una secuencia completa con la cantidad necesaria de bases nitrogenadas
            int cantidadDeCeldasRestantesHaciaElSur = this.secuenciaDeADN.getCantidadDeSecuenciasDeBasesNitrogenadas()-nroSecuencia;
            if (cantidadDeCeldasRestantesHaciaElSur>= this.cantidadDeBasesNitrogenadasContiguas){
                respuesta = true;
                for (int i= 1; i < this.cantidadDeBasesNitrogenadasContiguas; i++){
                    char baseNitrogenadaAComprar = this.secuenciaDeADN.getBaseNitrogenada(nroSecuencia+i, nroDeBaseNitrogenada);
                    respuesta = respuesta && baseNitrogenadaActual == baseNitrogenadaAComprar;
                }
            }
        }
        catch (Exception e){
            String mensajeParaUsuario = "Hubo un error al chequear las ocurrencias contiguas para la base nitrogenada nro "+nroDeBaseNitrogenada+" de la secuencia nro "+nroSecuencia+".";
            String mensajeDeError = "Hubo un error al chequear las ocurrencias contiguas hacia el SUR para la base nitrogenada nro "+nroDeBaseNitrogenada+" de la secuencia nro "+nroSecuencia+". Excepcion: "+e.getMessage();
            throw new ExcepcionDeChequeo(mensajeParaUsuario,mensajeDeError);
        }

        return respuesta;
    }
    
    /**
     * Dada una base nitrogenada de las secuencias de ADN, chequea ocurrencias en diagonal hacia abajo y a la izquierda.
     * Retorna true si hay repeticiones
     * @param nroSecuencia
     * @param nroDeBaseNitrogenada
     * @return
     * @throws ExcepcionDeChequeo 
     */
    private boolean checkearHaciaSudOeste(int nroSecuencia, int nroDeBaseNitrogenada)throws ExcepcionDeChequeo{
        boolean respuesta = false;
        
        try{
            char baseNitrogenadaActual = this.secuenciaDeADN.getBaseNitrogenada(nroSecuencia, nroDeBaseNitrogenada);

            //Si el numero de la celda esta muy cerca del borde Este, no hace falta preguntar, porque nunca se va a dar que tenga una secuencia completa con la cantidad necesaria de bases nitrogenadas
            int cantidadDeCeldasRestantesHaciaElSur = this.secuenciaDeADN.getCantidadDeSecuenciasDeBasesNitrogenadas()-nroSecuencia;
            int cantidadDeCeldasRestantesHaciaElOeste = nroDeBaseNitrogenada;

            if (cantidadDeCeldasRestantesHaciaElSur>= this.cantidadDeBasesNitrogenadasContiguas && cantidadDeCeldasRestantesHaciaElOeste>= this.cantidadDeBasesNitrogenadasContiguas){
                respuesta = true;
                for (int i= 1; i < this.cantidadDeBasesNitrogenadasContiguas; i++){
                    char baseNitrogenadaAComprar = this.secuenciaDeADN.getBaseNitrogenada(nroSecuencia+i, nroDeBaseNitrogenada-i);
                    respuesta = respuesta && baseNitrogenadaActual == baseNitrogenadaAComprar;
                }
            }
        }
        catch (Exception e){
            String mensajeParaUsuario = "Hubo un error al chequear las ocurrencias contiguas para la base nitrogenada nro "+nroDeBaseNitrogenada+" de la secuencia nro "+nroSecuencia+".";
            String mensajeDeError = "Hubo un error al chequear las ocurrencias contiguas hacia el SUDOESTE para la base nitrogenada nro "+nroDeBaseNitrogenada+" de la secuencia nro "+nroSecuencia+". Excepcion: "+e.getMessage();
            throw new ExcepcionDeChequeo(mensajeParaUsuario,mensajeDeError);
        }
        
        return respuesta;
    }
    
}
