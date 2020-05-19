package myplugin;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.NotNull;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.intellij.psiweaver.PsiWeaver;
import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.providers.ResourceProvider;
import runnable.MyRunnable;

import java.io.File;
import java.util.concurrent.Callable;

/*
import org.suikasoft.XStreamPlus.XStreamUtils;
import pt.up.fe.specs.jsengine.JsEngine;
import pt.up.fe.specs.jsengine.JsEngineType;
import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.providers.ResourceProvider;
import pt.up.fe.specs.util.xml.XmlDocument;
import pt.up.fe.specs.intellij.psiweaver.PsiWeaver;
*/

public class AnAction extends com.intellij.openapi.actionSystem.AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {


            PsiFile rootFile = e.getData(LangDataKeys.PSI_FILE);

            Project currentProject = e.getProject();
            if (rootFile == null) {
                Messages.showMessageDialog(currentProject, "No file selected!", "PsiWeaver Execution", Messages.getErrorIcon());
                return;
            }

            System.out.println("Before LARA");



//        ResourceProvider testLara = () -> "pt/up/fe/specs/intellij/weaverspecs/Test.lara";
        ResourceProvider testLara = () -> "pt/up/fe/specs/intellij/weaverspecs/hashmap.lara";

        Callable<DataStore> runnable = () ->  PsiWeaver.runAspect(rootFile, testLara.read());
        DataStore results = launch(runnable);

        Messages.showMessageDialog(currentProject, "LARA Finished, results:\n" + results, "PsiWeaver Execution Finished", Messages.getInformationIcon());

        //System.out.println("Field types object: " + results.get("Field types").getClass());

        System.out.println("After  LARA");
    }

    public DataStore launch(Callable<DataStore> runnable) {
        Thread t = Thread.currentThread();
        ClassLoader previousClassLoader = t.getContextClassLoader();
        var newClassloader = SpecsIo.class.getClassLoader();
        System.out.println("Unloading classloader " + previousClassLoader);
        System.out.println("Using classloader " + newClassloader);
        System.out.println("Parent classloader " + newClassloader.getParent());
        t.setContextClassLoader(newClassloader);

        try {
            return runnable.call();
        } catch (Exception e) {
            System.out.println("Exception while calling weaver: " + e);
            e.printStackTrace();
        } finally {
            t.setContextClassLoader(previousClassLoader);
        }

        return null;
    }

    /*
    public final class MyRunnable implements Runnable {
        public MyRunnable() {

        }

        public void run() {
            System.out.println("running...");
            Context.Builder contextBuilder = createBuilder();
            var engine = GraalJSScriptEngine.create(null, contextBuilder);
        }
    }
*/

    /*
    private void graalVmTesT() {

        try {
            var classLoader = GraalJSScriptEngine.class.getClassLoader();
//            final Class runnableClass = classLoader.loadClass("actiona.MyRunnable");
            final Class runnableClass = classLoader.loadClass(MyRunnable.class.getName());
            final Thread thread = new Thread((Runnable) runnableClass.newInstance());

            thread.setContextClassLoader(classLoader); // this is unnecessary unless you you are using libraries that themselves call .getContextClassLoader()

            thread.start();
        } catch(Exception e) {
            throw new RuntimeException("Problem", e);
        }

//        System.out.println("PLUGIN CLASSLOADER: " + PluginManager.getPlugin(PluginId.getId("plugintest")).getPluginClassLoader());
//        System.out.println("CLASSLOADER: " + GraalJSScriptEngine.class.getClassLoader());
//        Context.Builder contextBuilder = createBuilder();
//       var engine = GraalJSScriptEngine.create(null, contextBuilder);
//        var engine = GraalJSScriptEngine.create();
    }
*/

    /*
    private Context.Builder createBuilder() {
        Context.Builder contextBuilder = Context.newBuilder("js")
                .allowHostAccess(HostAccess.ALL);

            contextBuilder.allowExperimentalOptions(true).option("js.nashorn-compat", "true");

        return contextBuilder;
    }
*/

    // System.out.println("CLASS LOADER: " + GraalvmJsEngine.class.getClassLoader());
    // Thread.currentThread().setContextClassLoader(classLoader);

    //        System.out.println("Got root file, running PsiWeaver");
    //       System.out.println("Finished PsiWeaver");


    public static void toTree(PsiElement node, String prefix) {
        System.out.println(prefix + node);

        for (PsiElement child : node.getChildren()) {
            toTree(child, prefix + " ");
        }
    }
}
