import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Hakim on 05/05/14.
 */
public interface Mur extends Remote{
    public void notifier(String notification) throws RemoteException ;

    public ArrayList<String> getContenu() throws RemoteException;

    public ArrayList<Mur> getListeAmis() throws RemoteException ;

    public String getNom() throws RemoteException;


    //TODO: vérifie que ArrayList est sérialisable.
}
