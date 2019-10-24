package ClienteServidorUDP;

import socketUDPString.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorUDP {

   private int porta = 0;
   InterfaceString objstr;

   public ServidorUDP(int porta) {
      this.porta = porta;
      objstr = new ImplementaExercicioString();
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

      String[] msgSplit;

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
            msgSplit = requisicao.split(" ");
            resposta = "";

            String argumento = "";
            
            for (int i = 1; i < msgSplit.length; i++) {
               argumento += msgSplit[i] + " ";
            }
            argumento.trim();
            
            System.out.println(msgSplit[0]);
            switch (msgSplit[0]) {
               case "ecoar":
                  resposta = objstr.ecoar(argumento);
                  break;
               case "inverter":
                  resposta = objstr.inverter(argumento);
                  break;
               case "inverterPalavra":
                  resposta = objstr.inverterPalavra(argumento);
                  break;
               case "ehPalidroma":
                  resposta = objstr.ehPalidroma(argumento);
                  break;
               case "ehpagram":
                  if (objstr.ehPagran(argumento)) {
                     resposta = "É Pagran";
                  } else {
                     resposta = "nao é Pagran";
                  }
                  break;
            }

            if (requisicao.equals("encerrar")) {
               exit = true;
               resposta = "Servidor Encerrado";
            }
            System.out.println(resposta);
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
