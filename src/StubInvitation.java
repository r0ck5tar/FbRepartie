import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Hakim on 05/05/14.
 */
public class StubInvitation extends UnicastRemoteObject implements IStubInvitation{
    private Mur mur;

    protected StubInvitation() throws RemoteException {
    }

    /*
        Méthodes distantes
     */
    @Override
    public IMur accept(IMur ami)  throws RemoteException {
        if(mur.getInvitationsEnAttente().contains(ami)) {
            return mur;
        }

        return null;
    }

    @Override
    public void invite(IStubInvitation inviteur)  throws RemoteException {
        mur.getInvitationsEnAttente().add(inviteur);
    }

    @Override
    public String quiEsTu() throws RemoteException {
        return mur.getNom();
    }

    /*
        Méthodes locales
     */
    public void invite2(String nom) {
        //rechercher dans le RMI registry
        //récupère le stubInvitation  xxx qui corespond au nom
        //appeler xxx.invite(this);
        //rajouter xxx à la liste DemandeAmisEnAttente
    }
}
