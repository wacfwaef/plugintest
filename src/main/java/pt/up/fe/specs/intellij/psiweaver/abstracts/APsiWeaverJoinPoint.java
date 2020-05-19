package pt.up.fe.specs.intellij.psiweaver.abstracts;

import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import org.lara.interpreter.weaver.interf.JoinPoint;
import pt.up.fe.specs.intellij.psiweaver.IntelliJJoinPoints;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AFile;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AJoinPoint;
import org.lara.interpreter.weaver.interf.SelectOp;
import pt.up.fe.specs.intellij.psiweaver.joinpoints.IntelliJFile;
import pt.up.fe.specs.intellij.util.IntelliJNodes;
import pt.up.fe.specs.util.exceptions.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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
    public <T extends AJoinPoint> List<T> select(Class<T> joinPointClass, SelectOp op) {
        List<AJoinPoint> selected = new ArrayList<>();

        Runnable action = () -> {
//            System.out.println("NODE CLASS: " + getNode().getClass());
            // HACK: This is temporary, while feature is not implemented
            var realOp = PsiJavaFileImpl.class.isInstance(getNode()) ? SelectOp.CHILDREN : op;
            if(AFile.class.isInstance(getNode())) {
                System.out.println("Changing op of " + getNode().getClass() + "->" + joinPointClass + " to 'children'");
            }
//        if (joinPointClass.equals(AFile.class) || joinPointClass.equals(IntelliJFile.class)) {
//            System.out.println("Changing op of " + joinPointClass + " to children");
//            op = SelectOp.CHILDREN;
//        }

            //        System.out.println("DEFAULT SELECT: " + op);

            Supplier<AJoinPoint[]> nodes = getNodesSupplier(realOp);
//            System.out.println("NODES: " + Arrays.toString(nodes.get()));
            for (var child : nodes.get()) {
//                System.out.println("JP TYPE: " + child.getJoinPointType());
                if (joinPointClass.isInstance(child)) {
                    selected.add(child);
                }
            }
        };

        IntelliJNodes.read(action);

        return (List<T>) selected;
    }


    private Supplier<AJoinPoint[]> getNodesSupplier(SelectOp op) {
        switch (op) {
            case CHILDREN:
                return () -> getChildrenArrayImpl();
            case DESCENDANTS:
                return () -> getDescendantsArrayImpl();
            default:
                throw new NotImplementedException(op);
        }
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
        return IntelliJNodes.toTree(getNode(), APsiWeaverJoinPoint::toString, "");
    }

    private static String toString(PsiElement node) {
//        return node.toString();
//        return node.toString() + " (line: " + IntelliJNodes.getLine(node) + ")";
        return node.toString() + " (code: " + IntelliJNodes.getCode(node) + ")";
    }

    @Override
    public Integer getLineImpl() {
        return IntelliJNodes.getLine(getNode());
    }

    @Override
    public String getCodeImpl() {
        return IntelliJNodes.getCode(getNode());
    }

    @Override
    public AJoinPoint[] getChildrenArrayImpl() {
        return Arrays.stream(getNode().getChildren())
                .map(IntelliJJoinPoints::create)
                .filter(jp -> jp != null)
                .toArray(size -> new AJoinPoint[size]);
    }

    @Override
    public AJoinPoint[] getDescendantsArrayImpl() {
        return IntelliJNodes.getDescendantsStream(getNode())
                .map(IntelliJJoinPoints::create)
                .filter(jp -> jp != null)
                .toArray(size -> new AJoinPoint[size]);
    }
}
