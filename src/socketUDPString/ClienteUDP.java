package socketUDPString;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteUDP {

   private int porta = 0;
   private String ip = "";

   public ClienteUDP(String ip, int porta) {
      this.ip = ip;
      this.porta = porta;
      executar();
   }

   private void executar() {
      try {
         //cria-se o socket
         DatagramSocket socket = new DatagramSocket();

         String mensagem = "encerrar";
         //converte a msg para bytes
         byte[] msg = mensagem.getBytes();
         //envia o datagrama para o servidor
         DatagramPacket dgEnvio = new DatagramPacket(msg,
                 msg.length,
                 InetAddress.getByName(ip),
                 porta
         );
         socket.send(dgEnvio);

         DatagramPacket dgRec = new DatagramPacket(new byte[1024], 1024);

         socket.receive(dgRec);

         String resposta = new String(dgRec.getData()).trim();

         System.out.println("Resposta:" + resposta);
         socket.close();

      } catch (SocketException ex) {
         Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
      } catch (UnknownHostException ex) {
         Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
         Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   public static void main(String[] args) {
      ClienteUDP c = new ClienteUDP("127.0.0.1", 12345);
   }
}
