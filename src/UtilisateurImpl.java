import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Hakim on 01/06/14.
 */
public class UtilisateurImpl extends UnicastRemoteObject implements Utilisateur {
    private MurImpl mur;
    private Invitation invitation;

    public UtilisateurImpl(MurImpl mur, Invitation invitation) throws RemoteException{
        this.mur = mur;
        this.invitation = invitation;
    }


    @Override
    public ArrayList<Invitation> getInvitationsEnAttente() throws RemoteException {
        return mur.getInvitationsEnAttente();
    }

    @Override
    public ArrayList<Invitation> getDemandeAmiEnAttente() throws RemoteException {
        return mur.getDemandeAmiEnAttente();
    }

    @Override
    public ArrayList<String> getNotifications() throws RemoteException {
        return mur.getNotifications();
    }

    @Override
    public Mur getMur() throws RemoteException {
        return mur;
    }

    public String getPassword() {
        return mur.getPassword();
    }
}
