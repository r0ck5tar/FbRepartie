import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Hakim on 31/05/14.
 */
public interface Annuaire extends Remote {

    public void createUser (String nom, String password) throws RemoteException;

    public Mur login(String nom, String password) throws RemoteException;

    public Invitation findUser(String nom) throws RemoteException;
}
