package fr.norsys.tpTdd.technique;

/**
 * Type d'exception lancee lorsque une erreur technique intervient.
 */
public class TechniqueException extends Exception {

    private static final long serialVersionUID = -4215296213711975660L;

    public TechniqueException() {
	super();
    }

    public TechniqueException(String arg0) {
	super(arg0);
    }

    public TechniqueException(Throwable arg0) {
	super(arg0);
    }

    public TechniqueException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }
}
