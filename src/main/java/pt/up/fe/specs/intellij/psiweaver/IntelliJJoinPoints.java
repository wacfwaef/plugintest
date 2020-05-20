package pt.up.fe.specs.intellij.psiweaver;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.compiled.ClsMethodImpl;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.intellij.psi.impl.source.PsiFieldImpl;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.psi.impl.source.PsiMethodImpl;
import com.intellij.psi.impl.source.tree.java.PsiMethodCallExpressionImpl;
import pt.up.fe.specs.intellij.psiweaver.abstracts.APsiWeaverJoinPoint;
import pt.up.fe.specs.intellij.psiweaver.joinpoints.*;
import pt.up.fe.specs.intellij.util.IntelliJNodes;
import pt.up.fe.specs.util.classmap.FunctionClassMap;

public class IntelliJJoinPoints {

    private static final FunctionClassMap<PsiElement, APsiWeaverJoinPoint> JOINPOINT_FACTORY;
    static {
        JOINPOINT_FACTORY = new FunctionClassMap<>();

        JOINPOINT_FACTORY.put(PsiJavaFileImpl.class, IntelliJFile::new);
        JOINPOINT_FACTORY.put(PsiClassImpl.class, IntelliJJoinPoints::classFactory);
        JOINPOINT_FACTORY.put(PsiFieldImpl.class, IntelliJField::new);
        JOINPOINT_FACTORY.put(PsiMethodImpl.class, IntelliJMethod::new);
        JOINPOINT_FACTORY.put(ClsMethodImpl.class, IntelliJMethod::new);
        JOINPOINT_FACTORY.put(PsiMethodCallExpressionImpl.class, IntelliJCall::new);
    }

    public static <T extends APsiWeaverJoinPoint> T create(PsiElement node, Class<T> expectedClass) {
        var newJp = create(node);

        if(newJp == null) {
            return null;
        }

        if(!expectedClass.isInstance(newJp)) {
            throw new RuntimeException("Expected class '"+expectedClass+"', got " + newJp.getClass());
        }

        return expectedClass.cast(newJp);
    }

    public static APsiWeaverJoinPoint create(PsiElement node) {
        return IntelliJNodes.read(() -> JOINPOINT_FACTORY.applyTry(node).orElse(null));
//        var jp =  JOINPOINT_FACTORY.applyTry(node).orElse(null);

//        System.out.println("NODE: " + node);
//        System.out.println("JP: " + jp);

//        return jp;
    }

    public static APsiWeaverJoinPoint classFactory(PsiClass node) {
        if(!node.isEnum() && !node.isInterface()) {
            return new IntelliJClass(node);
        }

        return null;
        //throw new RuntimeException("Not implemented for enum or interface");
    }

}
