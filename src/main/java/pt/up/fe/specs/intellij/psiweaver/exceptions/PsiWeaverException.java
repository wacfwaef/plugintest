package pt.up.fe.specs.intellij.psiweaver.exceptions;

import pt.up.fe.specs.tools.lara.exception.BaseException;

/**
 * This class can be used as the exception of this weaver in order to follow the message pretty print used by the interpreter
 */
public class PsiWeaverException extends BaseException {

    private static final long serialVersionUID = 1L;
    private final String event;

    /**
     * Create a new exception with a message
     * @param event the exception message
     */
    public PsiWeaverException(String event){
        this(event,null);
    }
    /**
     * Create a new exception with the cause and the triggering event
     * @param event the event that caused the exception
     * @param cause the cause of this exception
     */
    public PsiWeaverException(String event, Throwable cause){
        super(cause);
        this.event = event;
    }
    /**
     * 
     * @see pt.up.fe.specs.tools.lara.exception.BaseException#generateSimpleMessage()
     */
    @Override
    protected String generateSimpleMessage() {
        return " [PsiWeaver] " +this.event;
    }

    /**
     * 
     * @see pt.up.fe.specs.tools.lara.exception.BaseException#generateMessage()
     */
    @Override
    protected String generateMessage() {
        return "Exception in "+this.generateSimpleMessage();
    }
}
