package fr.norsys.tpTdd.technique;

/**
 * Type d'exception lancee lorsque l'on detecte une erreur metier. Typiquement
 * lorsque l'on detecte des contraintes de validite ou un comportement anormale
 * et que l'on sait identifier pourquoi.
 */
public class MetierException extends Exception {

    private static final long serialVersionUID = -1727859912618307232L;

    public MetierException() {
	super();
    }

    public MetierException(String arg0) {
	super(arg0);
    }

    public MetierException(Throwable arg0) {
	super(arg0);
    }

    public MetierException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }
}
