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
        Registry registryInvitation = LocateRegistry.getRegistry(1096);
        Invitation stubAmi = null;
        try {
            stubAmi = (Invitation) registryInvitation.lookup(ami.getNom());
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        if(stubAmi != null) {
            if(mur.getInvitationsEnAttente().contains(stubAmi)) {
                mur.getListeAmis().add(ami);
                return mur;
            }
        }


        return null;
    }

    @Override
    public void invite(Invitation inviteur)  throws RemoteException {
        if(!mur.getInvitationsEnAttente().contains(inviteur)){
            mur.getInvitationsEnAttente().add(inviteur);
            inviteur.retourInvitation((Invitation) this);
        }
    }

    @Override
    public String quiEsTu() throws RemoteException {
        return mur.getNom();
    }

    @Override
    public void retourInvitation(Invitation invitation) throws RemoteException {
        if(!mur.getDemandeAmiEnAttente().contains(invitation)) {
            mur.getDemandeAmiEnAttente().add(invitation);
        }
    }
}
