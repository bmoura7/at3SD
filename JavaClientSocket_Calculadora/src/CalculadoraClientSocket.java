import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		double oper1=10,oper2=20;
		int operacao=1; //1-somar 2-subtrair 3-dividir 4-multiplicar
		String result="";
        try {

        	//Conexão com o Servidor
        	//cria um socket no endereço e porta especificados
            Socket clientSocket = new Socket("192.168.0.11", 9090);
            //stream de saída para o socket criado
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //Enviando os dados
            socketSaidaServer.writeBytes(operacao+"\n");
            socketSaidaServer.writeBytes(oper1+ "\n");
            socketSaidaServer.writeBytes( oper2+ "\n");
            socketSaidaServer.flush();

            //Recebendo a resposta
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            result=messageFromServer.readLine();
            
            System.out.println("resultado="+result);
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


	}

}
