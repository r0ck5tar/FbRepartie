import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Hakim on 05/05/14.
 */
public class MurImpl extends UnicastRemoteObject implements Mur {
    private String nom;
    private String password;
    private ArrayList<String> messagesDeMur;
    private ArrayList<Mur> listeAmis;
    private ArrayList<Invitation> invitationsEnAttente; //c'est lui qui invite
    private ArrayList<Invitation> demandeAmiEnAttente; //c'est moi qui invite
    private ArrayList<String> notifications;

    protected MurImpl(int port, String nom, String password) throws RemoteException {
        super(port);
        this.nom = nom;
        this.password = password;
        this.invitationsEnAttente = new ArrayList<Invitation>();
        this.demandeAmiEnAttente = new ArrayList<Invitation>();
        this.listeAmis = new ArrayList<Mur>();
        this.notifications = new ArrayList<String>();
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
    public ArrayList<Mur> getListeAmis()   throws RemoteException{
        return this.listeAmis;
    }

    /*
        Méthodes locales
     */

    public void publishMessage(String message)  {
        messagesDeMur.add(message);
        try {
            for(Mur ami : listeAmis) {
                ami.notifier(nom + "a publié : " + message );
            }
        }
        catch (RemoteException e) { }
    }


    public void lireNotification() {
        //enlever la dernière notification de la liste de notifications

        if(notifications.size()>0){
            notifications.remove(0);
        }
    }


    /*
    Getters
     */

    public String getNom() {return nom;}

    public ArrayList<Invitation> getInvitationsEnAttente() {
        return invitationsEnAttente;
    }

    public ArrayList<String> getMessagesDeMur() {
        return messagesDeMur;
    }

    public ArrayList<Invitation> getDemandeAmiEnAttente() {
        return demandeAmiEnAttente;
    }

    public ArrayList<String> getNotifications(){return notifications;}

    public String getPassword() { return password; }
}
