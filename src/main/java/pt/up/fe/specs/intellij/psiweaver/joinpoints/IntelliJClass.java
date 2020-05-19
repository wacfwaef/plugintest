package pt.up.fe.specs.intellij.psiweaver.joinpoints;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AClass;

public class IntelliJClass extends AClass {

    private final PsiClass aClass;

    public IntelliJClass(PsiClass aClass) {
        super(new IntelliJObjectType(aClass));
        this.aClass = aClass;
    }

    @Override
    public PsiElement getNode() {
        return aClass;
    }
}
