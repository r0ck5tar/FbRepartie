import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by user on 30/05/14.
 */
public class Main {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        Annuaire annuaire = new Annuaire();
        try {
            StubInvitation stub1 = (StubInvitation) annuaire.getRegistryInvitation().lookup("invitation1");
            StubInvitation stub2 = (StubInvitation) annuaire.getRegistryInvitation().lookup("invitation2");




            //le stub2 invite le stub1
            stub2.invite2("invitation1"); // ici on appelle la methode invite2 locale de stub2
            //stub1.invite(stub2); ici on appelle la methode invite de stub1

            System.out.println("taille de demande en attente pour stub1: " + stub1.getMur().getInvitationsEnAttente().size());
            System.out.println("taille de demande d'ami pour stub2: " + stub2.getMur().getDemandeAmiEnAttente().size());
            //le stub1 demande qui est stub2
            stub2.quiEsTu();

        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
}
