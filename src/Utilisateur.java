import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Hakim on 01/06/14.
 */
public interface Utilisateur extends Remote {

    public ArrayList<Invitation> getInvitationsEnAttente() throws RemoteException;

    public ArrayList<Invitation> getDemandeAmiEnAttente() throws RemoteException;

    public ArrayList<String> getNotifications() throws  RemoteException;

    public Mur getMur() throws RemoteException;
}
