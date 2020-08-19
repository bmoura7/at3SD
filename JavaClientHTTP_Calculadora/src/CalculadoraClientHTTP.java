import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

	public static void main(String[] args) {
		
	String result="";
    try {

    	//endereço do servidor 
    	URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
    	//representa a conexão a url acima
    	HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    	//intervalo para que a leitura seja iniciada em ms 
        conn.setReadTimeout(10000);
        //intervalo para que a conexão seja feita em ms
        conn.setConnectTimeout(15000);
        //define o método de requisição como POST, ou seja, dados serão enviados ao servidor
        conn.setRequestMethod("POST");
        //a conexão será utilizada para entrada de dados
        conn.setDoInput(true);
        //e também para saída de dados
        conn.setDoOutput(true) ;

        //ENVIO DOS PARAMETROS
        //obtém uma instância de um OutputStream para escrita 
        OutputStream os = conn.getOutputStream();
        /*um OutputStreamWriter atua como uma ponte de streams de caracteres para streams de bytes.
         *Os caracteres gravados nele são codificados em bytes usando um charset específico;
         *o BufferedWriter encapsula qualquer Writer e utiliza um buffer de memória para 
         *otimizar as operações de I/O 
         * */
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        
        //1-somar 2-subtrair 3-multiplicar 4-dividir
        writer.write("oper1=20&oper2=20&operacao=3"); 
        writer.flush();
        writer.close();
        os.close();

        //código de status da resposta http
        //https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status
        int responseCode=conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            //RECBIMENTO DOS PARAMETROS
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            result = response.toString();
            System.out.println("Resposta do Servidor PHP: "+result);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}
