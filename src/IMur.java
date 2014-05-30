import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Hakim on 05/05/14.
 */
public interface IMur extends Remote{
    public void notifier(String notification) throws RemoteException ;

    public ArrayList<String> getContenu() throws RemoteException;

    public ArrayList<IMur> getListeAmis() throws RemoteException ;


    //TODO: vérifie que ArrayList est sérialisable.
}
