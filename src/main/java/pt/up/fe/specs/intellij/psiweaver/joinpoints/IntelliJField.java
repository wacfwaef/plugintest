package pt.up.fe.specs.intellij.psiweaver.joinpoints;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AField;
import pt.up.fe.specs.intellij.util.IntelliJNodes;

import java.util.function.Supplier;

public class IntelliJField extends AField {

    private final PsiField field;

    public IntelliJField(PsiField field) {
        this.field = field;
    }

    @Override
    public PsiElement getNode() {
        return field;
    }

    @Override
    public String getTypeImpl() {
        Supplier<String> action = () -> {
//            System.out.println("PSY TYPE: " + field.getType());
//            System.out.println("PSY TYPE ELEMENT: " + field.getTypeElement());
            var typeName = field.getTypeElement().getText();

            // Remove template information
            int angleBracketIndex = typeName.indexOf('<');
            if(angleBracketIndex != -1) {
                typeName = typeName.substring(0, angleBracketIndex);
            }

            return typeName;
        };

        return IntelliJNodes.read(action);
    }
}
