import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Hakim on 05/05/14.
 */
public interface IStubInvitation extends Remote{
    public IMur accept(IMur ami) throws RemoteException;

    public void invite(IStubInvitation inviteur) throws RemoteException;

    public String quiEsTu() throws RemoteException ;
}
