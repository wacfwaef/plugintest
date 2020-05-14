package pt.up.fe.specs.intellij.psiweaver.joinpoints;

import com.intellij.psi.PsiElement;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AFile;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AJoinPoint;

public class IntelliJFile extends AFile {

    private final com.intellij.psi.PsiFile file;

    public IntelliJFile(com.intellij.psi.PsiFile file) {
        this.file = file;
    }


    @Override
    public PsiElement getNode() {
        return file;
    }

    @Override
    public String getNameImpl() {
        return file.getName();
    }

    @Override
    public String getPackageImpl() {
        return null;
    }

    @Override
    public String getPathImpl() {
        return null;
    }
}
