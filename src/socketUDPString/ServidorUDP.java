package socketUDPString;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorUDP {

   private int porta = 0;

   public ServidorUDP(int porta) {
      this.porta = porta;
      iniciar();
   }

   //metodo que contém toda a lógica de execução
   private void iniciar() {
      //Criando o socket UDP
      DatagramSocket socket;

      //cria o datagrama UDP para envio e recebimento
      DatagramPacket dgEnvio, dgRec;

      //variaveis para troca de msg
      String requisicao, resposta;

      try {
         //Instancia o socket
         socket = new DatagramSocket(porta);
         System.out.println("Servidor UDP operando na porta " + porta);

         boolean exit = false;
         do {
            //Recebe o conteúdo do datagrama do cliente em bytes
            dgRec = new DatagramPacket(new byte[1024], 1024);

            //recebe o datagrama do cliente
            socket.receive(dgRec);

            //converte os bytes para string
            requisicao = new String(dgRec.getData()).trim();
            System.out.println("Recebeu " + requisicao + " de " + dgRec.getAddress() + " : " + dgRec.getPort());

            //logica do servidor
            // aqui manipula os dados
            resposta = "Resposta: " + requisicao.toUpperCase();
            
            if(requisicao.equals("encerrar")){
               exit=true;
               resposta="Servidor Encerrado";
            }
            
            //respondendo
            dgEnvio = new DatagramPacket(
                    resposta.getBytes(),
                    resposta.getBytes().length,
                    dgRec.getAddress(),
                    dgRec.getPort());
                    
            socket.send(dgRec);
            

         } while (!exit);
         
         //fecha spclert
            socket.close();
            System.out.println("Servidor Encerrado");

      } catch (SocketException ex) {
         Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
         Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   public static void main(String[] args) {
      ServidorUDP s = new ServidorUDP(12345);
   }

}
