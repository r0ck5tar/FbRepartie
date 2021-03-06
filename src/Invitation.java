import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Hakim on 05/05/14.
 */
public interface Invitation extends Remote{

    public void accept(Mur ami) throws RemoteException;

    public void invite(Invitation inviteur) throws RemoteException;

    public String quiEsTu() throws RemoteException;

    public void retourInvitation(Invitation invitation)throws RemoteException;

    public void retourAccept(Mur murAmi) throws  RemoteException;
}
