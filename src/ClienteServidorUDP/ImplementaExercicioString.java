/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteServidorUDP;

import ClienteServidorUDP.InterfaceString;

/**
 *
 * @author Maicon
 */
public class ImplementaExercicioString implements InterfaceString {

    @Override
    public String ecoar(String x) {
        return x;
    }

    @Override
    public String inverter(String x) {
        String caracter[] = x.split("");
        String retorno = "";
        for (int i = caracter.length - 1; i >= 0; i--) {
            retorno += caracter[i];
        }
        return retorno;
    }

    @Override
    public String inverterPalavra(String x) {
        String palavras[] = x.split(" ");
        String retorno = "";
        for (int i = 0 ; i < palavras.length; i++) {
            retorno += inverter(palavras[i])+" ";
        }
        return retorno.trim();
    }

    @Override
    public String ehPalidroma(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ehPagran(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
