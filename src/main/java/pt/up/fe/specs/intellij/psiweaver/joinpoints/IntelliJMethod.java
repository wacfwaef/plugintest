package pt.up.fe.specs.intellij.psiweaver.joinpoints;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import pt.up.fe.specs.intellij.psiweaver.IntelliJJoinPoints;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AMethod;
import pt.up.fe.specs.intellij.util.IntelliJNodes;

public class IntelliJMethod extends AMethod {

    private final PsiMethod method;

    public IntelliJMethod(PsiMethod method) {
        this.method = method;
    }

    @Override
    public PsiElement getNode() {
        return method;
    }

    @Override
    public String getNameImpl() {
        return IntelliJNodes.read(() -> method.getName());
    }
}
