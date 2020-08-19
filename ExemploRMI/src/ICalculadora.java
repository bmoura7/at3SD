import java.rmi.Remote;
import java.rmi.RemoteException;

//interface da calculadora com as 4 opera��es b�sicas
public interface ICalculadora extends Remote{

	public int soma(int a, int b) throws RemoteException;
	
	public int subtracao(int a, int b) throws RemoteException;
	
	public int multiplicacao(int a, int b) throws RemoteException;
	
	public int divisao(int a, int b) throws RemoteException;
}
