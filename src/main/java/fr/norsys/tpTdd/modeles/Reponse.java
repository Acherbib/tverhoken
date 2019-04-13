package fr.norsys.tpTdd.modeles;

public class Reponse {

    public static final String MESSAGE_OK = "OK";
    public static final String MESSAGE_KO_RETARD_RENDU_VIDEO = "Vidéo rendu en retard !!!\n Nombre de jours de retard : ";

    private String message;

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
	return message;
    }
}
