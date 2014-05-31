import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Hakim on 05/05/14.
 */
public class InvitationImpl extends UnicastRemoteObject implements Invitation {
    private MurImpl mur;

    protected InvitationImpl(MurImpl mur) throws RemoteException {
        this.mur = mur;
    }

    /*
        Méthodes distantes
     */
    @Override
    public Mur accept(Mur ami)  throws RemoteException {
        //TODO: test si  mur.getInvitationsEnAttente().contains(ami)
        Registry registryInvitation = LocateRegistry.getRegistry(8080);

        return mur;

    }

    @Override
    public void invite(Invitation inviteur)  throws RemoteException {
        if(!mur.getInvitationsEnAttente().contains(inviteur)){
            mur.getInvitationsEnAttente().add(inviteur);
        }
    }

    @Override
    public String quiEsTu() throws RemoteException {
        return mur.getNom();
    }
}
