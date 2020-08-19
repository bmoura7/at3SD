import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CalculadoraCliente {
	
	public static void main(String[] args) {
		Registry reg = null;
		ICalculadora calc;		
		try {
			//obtém referência para o registro local operando na porta especificada
			reg = LocateRegistry.getRegistry(1099);
			//consulta(lookup) o rmi registry para obter uma referência ao objeto no registro
			calc = (ICalculadora) reg.lookup("calculadora");
			//realiza a chamada remota de cada uma das operações da calculadora e mostra o resultado
			System.out.println(calc.soma(3,2));
			System.out.println(calc.subtracao(10,3));
			System.out.println(calc.multiplicacao(7,8));
			System.out.println(calc.divisao(6,2));
		} catch (RemoteException | NotBoundException e) {
				System.out.println(e);
				System.exit(0);
		}
	}		

}
