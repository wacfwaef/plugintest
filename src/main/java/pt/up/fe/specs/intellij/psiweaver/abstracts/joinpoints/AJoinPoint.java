package pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints;

import org.lara.interpreter.weaver.interf.JoinPoint;
import com.intellij.psi.PsiElement;
import java.util.List;
import org.lara.interpreter.weaver.interf.events.Stage;
import java.util.Optional;
import org.lara.interpreter.exception.ActionException;
import org.lara.interpreter.exception.AttributeException;
import pt.up.fe.specs.intellij.psiweaver.PsiWeaver;
import org.lara.interpreter.weaver.interf.SelectOp;

/**
 * Abstract class containing the global attributes and default action exception.
 * This class is overwritten when the weaver generator is executed.
 * @author Lara Weaver Generator
 */
public abstract class AJoinPoint extends JoinPoint {

    /**
     * 
     */
    @Override
    public boolean same(JoinPoint iJoinPoint) {
        if (this.get_class().equals(iJoinPoint.get_class())) {
        
                return this.compareNodes((AJoinPoint) iJoinPoint);
            }
            return false;
    }

    /**
     * Compares the two join points based on their node reference of the used compiler/parsing tool.<br>
     * This is the default implementation for comparing two join points. <br>
     * <b>Note for developers:</b> A weaver may override this implementation in the editable abstract join point, so
     * the changes are made for all join points, or override this method in specific join points.
     */
    public boolean compareNodes(AJoinPoint aJoinPoint) {
        return this.getNode().equals(aJoinPoint.getNode());
    }

    /**
     * Returns the tree node reference of this join point.<br><b>NOTE</b>This method is essentially used to compare two join points
     * @return Tree node reference
     */
    public abstract PsiElement getNode();

    /**
     * 
     */
    @Override
    public void defImpl(String attribute, Object value) {
        switch(attribute){
        default: throw new UnsupportedOperationException("Join point "+get_class()+": attribute '"+attribute+"' cannot be defined");
        }
    }

    /**
     * 
     */
    @Override
    protected void fillWithActions(List<String> actions) {
        actions.add("insertBefore(AJoinPoint node)");
        actions.add("insertBefore(String code)");
        actions.add("insertAfter(AJoinPoint node)");
        actions.add("insertAfter(String code)");
        actions.add("insertReplace(AJoinPoint jp)");
        actions.add("insertReplace(String code)");
        actions.add("copy()");
        actions.add("remove()");
    }

    /**
     * 
     * @param node 
     */
    public AJoinPoint insertBeforeImpl(AJoinPoint node) {
        throw new UnsupportedOperationException(get_class()+": Action insertBefore not implemented ");
    }

    /**
     * 
     * @param node 
     */
    public final AJoinPoint insertBefore(AJoinPoint node) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "insertBefore", this, Optional.empty(), node);
        	}
        	AJoinPoint result = this.insertBeforeImpl(node);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "insertBefore", this, Optional.ofNullable(result), node);
        	}
        	return result;
        } catch(Exception e) {
        	throw new ActionException(get_class(), "insertBefore", e);
        }
    }

    /**
     * 
     * @param code 
     */
    public AJoinPoint insertBeforeImpl(String code) {
        throw new UnsupportedOperationException(get_class()+": Action insertBefore not implemented ");
    }

    /**
     * 
     * @param code 
     */
    public final AJoinPoint insertBefore(String code) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "insertBefore", this, Optional.empty(), code);
        	}
        	AJoinPoint result = this.insertBeforeImpl(code);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "insertBefore", this, Optional.ofNullable(result), code);
        	}
        	return result;
        } catch(Exception e) {
        	throw new ActionException(get_class(), "insertBefore", e);
        }
    }

    /**
     * 
     * @param node 
     */
    public AJoinPoint insertAfterImpl(AJoinPoint node) {
        throw new UnsupportedOperationException(get_class()+": Action insertAfter not implemented ");
    }

    /**
     * 
     * @param node 
     */
    public final AJoinPoint insertAfter(AJoinPoint node) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "insertAfter", this, Optional.empty(), node);
        	}
        	AJoinPoint result = this.insertAfterImpl(node);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "insertAfter", this, Optional.ofNullable(result), node);
        	}
        	return result;
        } catch(Exception e) {
        	throw new ActionException(get_class(), "insertAfter", e);
        }
    }

    /**
     * 
     * @param code 
     */
    public AJoinPoint insertAfterImpl(String code) {
        throw new UnsupportedOperationException(get_class()+": Action insertAfter not implemented ");
    }

    /**
     * 
     * @param code 
     */
    public final AJoinPoint insertAfter(String code) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "insertAfter", this, Optional.empty(), code);
        	}
        	AJoinPoint result = this.insertAfterImpl(code);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "insertAfter", this, Optional.ofNullable(result), code);
        	}
        	return result;
        } catch(Exception e) {
        	throw new ActionException(get_class(), "insertAfter", e);
        }
    }

    /**
     * 
     * @param jp 
     */
    public AJoinPoint insertReplaceImpl(AJoinPoint jp) {
        throw new UnsupportedOperationException(get_class()+": Action insertReplace not implemented ");
    }

    /**
     * 
     * @param jp 
     */
    public final AJoinPoint insertReplace(AJoinPoint jp) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "insertReplace", this, Optional.empty(), jp);
        	}
        	AJoinPoint result = this.insertReplaceImpl(jp);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "insertReplace", this, Optional.ofNullable(result), jp);
        	}
        	return result;
        } catch(Exception e) {
        	throw new ActionException(get_class(), "insertReplace", e);
        }
    }

    /**
     * 
     * @param code 
     */
    public AJoinPoint insertReplaceImpl(String code) {
        throw new UnsupportedOperationException(get_class()+": Action insertReplace not implemented ");
    }

    /**
     * 
     * @param code 
     */
    public final AJoinPoint insertReplace(String code) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "insertReplace", this, Optional.empty(), code);
        	}
        	AJoinPoint result = this.insertReplaceImpl(code);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "insertReplace", this, Optional.ofNullable(result), code);
        	}
        	return result;
        } catch(Exception e) {
        	throw new ActionException(get_class(), "insertReplace", e);
        }
    }

    /**
     * 
     */
    public AJoinPoint copyImpl() {
        throw new UnsupportedOperationException(get_class()+": Action copy not implemented ");
    }

    /**
     * 
     */
    public final AJoinPoint copy() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "copy", this, Optional.empty());
        	}
        	AJoinPoint result = this.copyImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "copy", this, Optional.ofNullable(result));
        	}
        	return result;
        } catch(Exception e) {
        	throw new ActionException(get_class(), "copy", e);
        }
    }

    /**
     * 
     */
    public void removeImpl() {
        throw new UnsupportedOperationException(get_class()+": Action remove not implemented ");
    }

    /**
     * 
     */
    public final void remove() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "remove", this, Optional.empty());
        	}
        	this.removeImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "remove", this, Optional.empty());
        	}
        } catch(Exception e) {
        	throw new ActionException(get_class(), "remove", e);
        }
    }

    /**
     * 
     */
    @Override
    protected void fillWithAttributes(List<String> attributes) {
        //Attributes available for all join points
        attributes.add("ast");
        attributes.add("line");
        attributes.add("code");
        attributes.add("children");
        attributes.add("descendants");
    }

    /**
     * A string representation of the AST corresponding to this node
     */
    public abstract String getAstImpl();

    /**
     * A string representation of the AST corresponding to this node
     */
    public final Object getAst() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "ast", Optional.empty());
        	}
        	String result = this.getAstImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "ast", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "ast", e);
        }
    }

    /**
     * Get value on attribute line
     * @return the attribute's value
     */
    public abstract Integer getLineImpl();

    /**
     * Get value on attribute line
     * @return the attribute's value
     */
    public final Object getLine() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "line", Optional.empty());
        	}
        	Integer result = this.getLineImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "line", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "line", e);
        }
    }

    /**
     * The source code corresponding to this join point
     */
    public abstract String getCodeImpl();

    /**
     * The source code corresponding to this join point
     */
    public final Object getCode() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "code", Optional.empty());
        	}
        	String result = this.getCodeImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "code", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "code", e);
        }
    }

    /**
     * Get value on attribute children
     * @return the attribute's value
     */
    public abstract AJoinPoint[] getChildrenArrayImpl();

    /**
     * Returns an array with the children of the node
     */
    public Object getChildrenImpl() {
        AJoinPoint[] aJoinPointArrayImpl0 = getChildrenArrayImpl();
        Object nativeArray0 = getWeaverEngine().getScriptEngine().toNativeArray(aJoinPointArrayImpl0);
        return nativeArray0;
    }

    /**
     * Returns an array with the children of the node
     */
    public final Object getChildren() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "children", Optional.empty());
        	}
        	Object result = this.getChildrenImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "children", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "children", e);
        }
    }

    /**
     * Get value on attribute descendants
     * @return the attribute's value
     */
    public abstract AJoinPoint[] getDescendantsArrayImpl();

    /**
     * Returns an array with the descendants of the node
     */
    public Object getDescendantsImpl() {
        AJoinPoint[] aJoinPointArrayImpl0 = getDescendantsArrayImpl();
        Object nativeArray0 = getWeaverEngine().getScriptEngine().toNativeArray(aJoinPointArrayImpl0);
        return nativeArray0;
    }

    /**
     * Returns an array with the descendants of the node
     */
    public final Object getDescendants() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "descendants", Optional.empty());
        	}
        	Object result = this.getDescendantsImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "descendants", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "descendants", e);
        }
    }

    /**
     * Defines if this joinpoint is an instanceof a given joinpoint class
     * @return True if this join point is an instanceof the given class
     */
    @Override
    public boolean instanceOf(String joinpointClass) {
        boolean isInstance = get_class().equals(joinpointClass);
        if(isInstance) {
        	return true;
        }
        return super.instanceOf(joinpointClass);
    }

    /**
     * Returns the Weaving Engine this join point pertains to.
     */
    @Override
    public PsiWeaver getWeaverEngine() {
        return PsiWeaver.getPsiWeaver();
    }

    /**
     * Generic select function, used by the default select implementations.
     */
    public abstract <T extends AJoinPoint> List<? extends T> select(Class<T> joinPointClass, SelectOp op);
}
