import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by user on 30/05/14.
 */
public class AnnuaireImpl extends UnicastRemoteObject implements Annuaire{
    private Registry registryInvitation;
    private Registry registryMur;

    public AnnuaireImpl() throws RemoteException {

        try {
            registryInvitation = LocateRegistry.createRegistry(1096);
        } catch (RemoteException e) {
            registryInvitation = LocateRegistry.getRegistry(1096);
        }

        try {
            registryMur = LocateRegistry.createRegistry(1097);
        } catch (RemoteException e) {
            registryMur = LocateRegistry.getRegistry(1097);
        }
    }

    public void createUser (String nom, String password) throws RemoteException {
        MurImpl mur = new MurImpl(1097, nom, password);
        InvitationImpl invitation = new InvitationImpl(mur);
        registryInvitation.rebind(nom, invitation);
        registryMur.rebind(nom, mur);

        System.out.println("l'utilisateur " + nom + " a été créé");
    }

    @Override
    public Mur login(String nom, String password) throws RemoteException {

        try {
            MurImpl user = (MurImpl) registryMur.lookup(nom);
            if(password.equals(user.getPassword())){
                return user;
            }
            else return null;
        } catch (NotBoundException e) {
            return null;
        }
    }

    public Invitation findUser(String nom) throws RemoteException {
        try {
            Invitation user = (Invitation) registryInvitation.lookup(nom);
            return user;
        } catch (NotBoundException e) {
            return null;
        }
    }

    public static void main(String[] args) throws RemoteException {
        AnnuaireImpl annuaire = new AnnuaireImpl();
        Annuaire stubAnnuaire;
        Registry registry = LocateRegistry.createRegistry(1099);
        try{
         stubAnnuaire = (Annuaire) UnicastRemoteObject.exportObject(annuaire, 1099);
        }
        catch (Exception e) {
            stubAnnuaire = (Annuaire) UnicastRemoteObject.toStub(annuaire);
            //System.err.print(e);
        }

        try {
            registry.bind("Annuaire", stubAnnuaire);
            System.out.println("Annuaire started");
        } catch (AlreadyBoundException e) {
            System.err.println("Annuaire already started");
        }
    }
}
