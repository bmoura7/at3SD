import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora  implements ICalculadora {

	private static final long serialVersionUID = 1L;
	
	private static int chamadas = 0;
	
	//implementa��o das opera��es b�sicas da calculadora
	public int soma(int a, int b) throws RemoteException {
		System.out.println("Metodo soma chamado " + chamadas++);
		return a + b;
	}
	
	public int subtracao(int a, int b) throws RemoteException {
		System.out.println("Metodo subtracao chamado " + chamadas++);
		return a - b;
	}
	
	public int multiplicacao(int a, int b) throws RemoteException {
		System.out.println("Metodo multiplicacao chamado " + chamadas++);
		return a * b;
	}
	
	public int divisao(int a, int b) throws RemoteException {
		System.out.println("Metodo divisao chamado " + chamadas++);
		return a / b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		//cria o objeto servidor
		Calculadora calculadora = new Calculadora();		
		Registry reg = null;
		//cria o stub do objeto que ser� registrado
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100);
		try {
			System.out.println("Creating registry...");
			//registra (binds) o stub no registry
			reg = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		reg.rebind("calculadora", stub);
	}
}
