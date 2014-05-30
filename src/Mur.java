import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Hakim on 05/05/14.
 */
public class Mur extends UnicastRemoteObject implements IMur {
    private String nom;
    private ArrayList<String> messagesDeMur;
    private ArrayList<IMur> listeAmis;
    private ArrayList<IStubInvitation> invitationsEnAttente; //c'est lui qui invite
    private ArrayList<IStubInvitation> demandeAmiEnAttente; //c'est moi qui invite
    private ArrayList<String> notifications;

    protected Mur(int port, String nom) throws RemoteException {
        super(port);
        this.nom = nom;
        this.invitationsEnAttente = new ArrayList<IStubInvitation>();
        this.demandeAmiEnAttente = new ArrayList<IStubInvitation>();
    }

    /*
      Méthodes distantes
     */
    @Override
    public void notifier(String notification)  throws RemoteException {
        notifications.add(notification);
    }

    @Override
    public ArrayList<String> getContenu()  throws RemoteException {
        return messagesDeMur;
    }

    @Override
    public ArrayList<IStubInvitation> getListeAmis()   throws RemoteException{
        return null;
    }

    /*
        Méthodes locales
     */

    public void publishMessage(String message)  {
        messagesDeMur.add(message);
        try {
            for(IMur ami : listeAmis) {
                ami.notifier(nom + "a publié : " + message );
            }
        }
        catch (RemoteException e) { }
    }


    public void lireNotification() {
        //enlever la dernière notification de la liste de notifications

        notifications.remove(0);
    }


    /*
    Getters
     */

    public String getNom() {return nom;}

    public ArrayList<IStubInvitation> getInvitationsEnAttente() {
        return invitationsEnAttente;
    }

    public ArrayList<String> getMessagesDeMur() {
        return messagesDeMur;
    }

    public ArrayList<IStubInvitation> getDemandeAmiEnAttente() {
        return demandeAmiEnAttente;
    }
}
