import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by user on 30/05/14.
 */
public class Annuaire {
    private static Registry registryInvitation;

    public Annuaire() throws RemoteException, AlreadyBoundException {
        registryInvitation = LocateRegistry.createRegistry(8080);
        Mur mur1 = new Mur(8080,"mur1");
        StubInvitation invite1 = new StubInvitation(mur1);
        registryInvitation.bind("invitation1", invite1);

        Mur mur2 = new Mur(8080,"mur2");
        StubInvitation invite2 = new StubInvitation(mur1);
        registryInvitation.bind("invitation2", invite2);

        Mur mur3 = new Mur(8080,"mur3");
        StubInvitation invite3 = new StubInvitation(mur3);
        registryInvitation.bind("invitation3", invite3);
    }

    public static Registry getRegistryInvitation(){return registryInvitation;}
    public void setRegistryInvitation(Registry r){this.registryInvitation = r;}
}
