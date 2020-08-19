import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

	

	public static void main(String[] args) {
		
		ServerSocket welcomeSocket;
		DataOutputStream socketOutput;     	
	    DataInputStream socketInput;
	    BufferedReader socketEntrada;
	    Calculadora calc = new Calculadora();
		try {
			//cria o servidor socket  na porta especificada
			welcomeSocket = new ServerSocket(9090);
		  
			//número de clientes
			int i=0;
	  
			System.out.println ("Servidor no ar");
			while(true) { 
	  
				/*//aguarda por uma conexão a ser feita no socket e a aceita. 
				 * O método é bloqueado até que a conexão seja feita.
				 */
				Socket connectionSocket = welcomeSocket.accept(); 
				i++;
				System.out.println ("Nova conexão");
	           
				//Interpretando dados do servidor
				//buffer de leitura de dados
				socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				String operacao= socketEntrada.readLine();
				String oper1=socketEntrada.readLine();
				String oper2=socketEntrada.readLine();
               
				//Chamando a calculadora
				String result= ""+calc.soma(Double.parseDouble(oper1),Double.parseDouble(oper2));
               
				//Enviando dados para o servidor
				socketOutput= new DataOutputStream(connectionSocket.getOutputStream());     	
				socketOutput.writeBytes(result+ '\n');
				System.out.println (result);	           
				socketOutput.flush();
				socketOutput.close();

	                    
					}
				} catch (IOException e) {
					e.printStackTrace();
		} 
	    
	}

}
