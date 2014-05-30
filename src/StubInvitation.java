import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Hakim on 05/05/14.
 */
public class StubInvitation extends UnicastRemoteObject implements IStubInvitation{
    private Mur mur;

    protected StubInvitation(Mur mur) throws RemoteException {
        this.mur = mur;
    }

    /*
        Méthodes distantes
     */
    @Override
    public IMur accept(IMur ami)  throws RemoteException {
        //TODO: test si  mur.getInvitationsEnAttente().contains(ami)

            return mur;

    }

    @Override
    public void invite(IStubInvitation inviteur)  throws RemoteException {
        //test si ils les deux users sont pas deja amis.

        mur.getInvitationsEnAttente().add(inviteur);
    }

    @Override
    public String quiEsTu() throws RemoteException {
        return mur.getNom();
    }

    /*
        Méthodes locales
     */
    public void invite2(String nom) throws RemoteException, NotBoundException {
        //rechercher dans le RMI registry
        //récupère le stubInvitation  xxx qui corespond au nom
        //appeler xxx.invite(this);
        //rajouter xxx à la liste DemandeAmisEnAttente

        //TODO: il faut regarder si ils sont pas deja amis.

        StubInvitation stubInviteMe = (StubInvitation) Annuaire.getRegistryInvitation().lookup(nom);
        stubInviteMe.invite(this);
        mur.getDemandeAmiEnAttente().add(stubInviteMe);
    }

    public void accepte2(String nom) throws RemoteException, NotBoundException {
        StubInvitation stubAcceptMe = (StubInvitation) Annuaire.getRegistryInvitation().lookup(nom);
        Mur nouvelAmi =  (Mur) stubAcceptMe.accept(this.getMur());
        this.mur.getListeAmis().add(nouvelAmi);
    }

    /*Getter-Setter*/

    //utile pour tester.
    public Mur getMur(){return mur;}
    public void setMur(Mur m){this.mur = m;}
}
