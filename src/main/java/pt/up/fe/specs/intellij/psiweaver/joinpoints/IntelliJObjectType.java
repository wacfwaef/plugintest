package pt.up.fe.specs.intellij.psiweaver.joinpoints;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AObjectType;

public class IntelliJObjectType extends AObjectType {

    private final PsiClass aClass;

    public IntelliJObjectType(PsiClass aClass) {
        this.aClass = aClass;
    }

    @Override
    public PsiElement getNode() {
        return aClass;
    }
}
