package pt.up.fe.specs.intellij.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class IntelliJNodes {

    public static String toTree(PsiElement node) {
        return toTree(node, element -> element.toString(), "");
    }

    /**
     * @param node
     * @return a string with a tree representation of the given node
     */
    public static String toTree(PsiElement node, Function<PsiElement, String> toString, String prefix) {
        StringBuilder builder = new StringBuilder();

        toTree(node, toString, prefix, builder);

        return builder.toString();
    }

    private static void toTree(PsiElement node, Function<PsiElement, String> toString, String prefix, StringBuilder builder) {
        builder.append(prefix);
        builder.append(toString.apply(node));
        builder.append("\n");

        for (var child : node.getChildren()) {
            toTree(child, toString, prefix + "  ", builder);
        }
    }

    public static int getLine(PsiElement node) {
        PsiFile containingFile = node.getContainingFile();
        Project project = containingFile.getProject();
        PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(project);
        Document document = psiDocumentManager.getDocument(containingFile);
        int textOffset = node.getTextOffset();
        int lineNumber = document.getLineNumber(textOffset);

        return lineNumber + 1;
    }

    public static String getCode(PsiElement node) {
        return node.getText();
//        return "TEXT: " + node.getText() + "\nTEXT RANGE: " + node.getTextRange();
    }

    public static Stream<PsiElement> getChildrenStream(PsiElement node) {
        // As indicated here: https://www.jetbrains.org/intellij/sdk/docs/basics/architectural_overview/general_threading_rules.html
        Computable<Stream<PsiElement>> computable = () -> Arrays.stream(node.getChildren());
        return ApplicationManager.getApplication().runReadAction(computable);
    }

    public static Stream<PsiElement> getDescendantsStream(PsiElement node) {
        Computable<Stream<PsiElement>> computable = () -> getChildrenStream(node).flatMap(c -> getDescendantsAndSelfStream(c));


        return ApplicationManager.getApplication().runReadAction(computable);
        //return getChildrenStream(node).flatMap(c -> getDescendantsAndSelfStream(c));
    }

    public static Stream<PsiElement> getDescendantsAndSelfStream(PsiElement node) {
        Computable<Stream<PsiElement>> computable = () -> Stream.concat(Stream.of(node), getDescendantsStream(node));

//        var desc = ApplicationManager.getApplication().runReadAction(computable).count();
//        ApplicationManager.getApplication().runReadAction(() -> System.out.println("SELF AND DESCENDANTS OF " + node + ": " + desc));

        //ApplicationManager.getApplication().runReadAction(() -> System.out.println("SELF AND DESCENDANTS OF " + node));
        //System.out.println("NUMBER: " + ApplicationManager.getApplication().runReadAction(computable).count());

        return ApplicationManager.getApplication().runReadAction(computable);
    }

    /**
     * Any read action over PsiElements must run on the correct thread.
     *
     * @param readAction
     * @param <T>
     * @return
     */
    public static <T> T read(Supplier<T> readAction) {
        Computable<T> computable = () -> readAction.get();
        return ApplicationManager.getApplication().runReadAction(computable);
    }

    /**
     * Any read action over PsiElements must run on the correct thread.
     *
     * @param readAction
     */
    public static void read(Runnable readAction) {
        ApplicationManager.getApplication().runReadAction(readAction);
    }
}