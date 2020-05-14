package pt.up.fe.specs.intellij.psiweaver.abstracts;

import org.lara.interpreter.weaver.interf.JoinPoint;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AJoinPoint;
import org.lara.interpreter.weaver.interf.SelectOp;

import java.util.List;

/**
 * Abstract class which can be edited by the developer. This class will not be overwritten.
 * 
 * @author Lara Weaver Generator
 */
public abstract class APsiWeaverJoinPoint extends AJoinPoint {

    /**
     * Compares the two join points based on their node reference of the used compiler/parsing tool.<br>
     * This is the default implementation for comparing two join points. <br>
     * <b>Note for developers:</b> A weaver may override this implementation in the editable abstract join point, so
     * the changes are made for all join points, or override this method in specific join points.
     */
    @Override
    public boolean compareNodes(AJoinPoint aJoinPoint) {
        return this.getNode().equals(aJoinPoint.getNode());
    }

    /**
     * Generic select function, used by the default select implementations.
     */
    @Override
    public <T extends AJoinPoint> List<? extends T> select(Class<T> joinPointClass, SelectOp op) {
        throw new RuntimeException("Generic select function not implemented yet. Implement it in order to use the default implementations of select");
    }

    @Override
    public AJoinPoint[] insertImpl(String position, String code) {
            throw new UnsupportedOperationException(
                    "Join point " + get_class() + ": Action insert(String,String) not implemented ");
    }

    @Override
    public AJoinPoint[] insertImpl(String position, JoinPoint JoinPoint) {
        throw new UnsupportedOperationException(
                "Join point " + get_class() + ": Action insert(String,joinpoint) not implemented ");
    }

    @Override
    public String getAstImpl() {
        throw new RuntimeException("");
    }
}
