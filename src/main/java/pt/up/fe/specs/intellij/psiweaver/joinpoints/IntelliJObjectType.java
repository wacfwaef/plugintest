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

    @Override
    public String getNameImpl() {
        return aClass.getName();
    }

    @Override
    public String getQualifiedNameImpl() {
        return aClass.getQualifiedName();
    }

    @Override
    public String getPackageImpl() {
        var qualifiedName = getQualifiedNameImpl();
        var simpleName = getNameImpl();

        if(qualifiedName.equals(simpleName)) {
            return "";
        }

        int endIndex = qualifiedName.length() - simpleName.length() - 1;
        return qualifiedName.substring(0, endIndex);
    }
}
