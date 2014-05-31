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
        System.out.println("accept called");
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
                System.out.println(mur.getNom() + " est maintenant ami avec " + stubAmi.quiEsTu());
                stubAmi.retourAccept(mur);
                mur.getInvitationsEnAttente().remove(stubAmi);
                return mur;
            }
            else{
                System.out.println(mur.getNom() + " n'a pas pu devenir ami avec " + stubAmi.quiEsTu());
            }
        }

        return null;
    }

    @Override
    public void invite(Invitation inviteur)  throws RemoteException {
        if(!mur.getInvitationsEnAttente().contains(inviteur)){
            mur.getInvitationsEnAttente().add(inviteur);
            System.out.println(mur.getNom() + " a reçu une invitation de " + inviteur.quiEsTu());
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
            System.out.println(mur.getNom()
                    + " a rajouté l'invitation de " + invitation.quiEsTu() +
                    " à sa liste de Demande d'amis en attente");
        }
    }

    @Override
    public void retourAccept(Mur murAmi) throws RemoteException {
        try {
            Invitation stubAmi = (Invitation) LocateRegistry.getRegistry(1096).lookup(murAmi.getNom());
            if(mur.getDemandeAmiEnAttente().contains(stubAmi)) {
                mur.getDemandeAmiEnAttente().remove(stubAmi);
                mur.getListeAmis().add(murAmi);
                System.out.println(mur.getNom() + " est maintenant ami avec " + stubAmi.quiEsTu());
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
