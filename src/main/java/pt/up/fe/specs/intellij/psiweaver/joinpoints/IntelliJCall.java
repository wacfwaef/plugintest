package pt.up.fe.specs.intellij.psiweaver.joinpoints;

import com.intellij.psi.PsiCall;
import com.intellij.psi.PsiElement;
import pt.up.fe.specs.intellij.psiweaver.IntelliJJoinPoints;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.ACall;
import pt.up.fe.specs.intellij.util.IntelliJNodes;

public class IntelliJCall extends ACall {

    private final PsiCall call;

    public IntelliJCall(PsiCall call) {
        this.call = call;
    }

    @Override
    public PsiElement getNode() {
        return call;
    }

    @Override
    public String getNameImpl() {
        IntelliJNodes.read(() -> System.out.println("Method class: " + call.resolveMethod().getClass()));

        var method = IntelliJNodes.read(() -> IntelliJJoinPoints.create(call.resolveMethod(), IntelliJMethod.class));

        return method.getNameImpl();
    }

}
