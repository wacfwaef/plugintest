package pt.up.fe.specs.intellij.psiweaver;

import org.lara.interpreter.weaver.generator.commandline.WeaverGenerator;

import java.io.File;

public class PsiWeaverGenerator {

    public static void main(String[] args) {
        System.out.println("new file" +(new File(".")).getAbsolutePath());
        String[] weaverArgs = {"-w", "PsiWeaver","-p","pt.up.fe.specs.intellij.psiweaver","-o","src/main/java","-x","src/main/resources/pt/up/fe/specs/intellij/weaverspecs","-n","com.intellij.psi.PsiElement","-e","-d","-j"};
        WeaverGenerator.main(weaverArgs);
    }
}
