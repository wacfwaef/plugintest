import com.google.gson.Gson;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.NotNull;
/*
import org.suikasoft.XStreamPlus.XStreamUtils;
import pt.up.fe.specs.jsengine.JsEngine;
import pt.up.fe.specs.jsengine.JsEngineType;
import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.providers.ResourceProvider;
import pt.up.fe.specs.util.xml.XmlDocument;
import pt.up.fe.specs.intellij.psiweaver.PsiWeaver;
*/
import runnable.MyRunnable;


import java.io.File;

public class actiona extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        //try {
            // Using the event, create and show a dialog
            //Project currentProject = e.getProject();
        /*
        StringBuffer dlgMsg = new StringBuffer(e.getPresentation().getText() + " Selected!");
        String dlgTitle = e.getPresentation().getDescription();
        // If an element is selected in the editor, add info about it.
        Navigatable nav = e.getData(CommonDataKeys.NAVIGATABLE);
        if (nav != null) {
            dlgMsg.append(String.format("\nSelected Element: %s", nav.toString()));
        }
        Messages.showMessageDialog(currentProject, dlgMsg.toString(), dlgTitle, Messages.getInformationIcon());
*/
//        PsiFile psiFile = PsiManager.getInstance(currentProject).findFile(currentProject.getWorkspaceFile());
//        System.out.println("CURRENT PROJECT: " +  PsiManager.getInstance(currentProject).getProject());
//        System.out.println("PSI FILE: " + psiFile);
//        System.out.println("Children: " + psiFile.getChildren());
//        System.out.println("Tree:");
//        toTree(psiFile, "");
//        System.out.println("EVENT: " + e.get);

//        System.out.println("Action: " + e.getData(LangDataKeys.PSI_FILE));
            //System.out.println("Document: " + PsiDocumentManager.getInstance(currentProject).getPsiFile());

            PsiFile rootFile = e.getData(LangDataKeys.PSI_FILE);
            System.out.println("HEEEA");

            Project currentProject = e.getProject();
            if (rootFile == null) {
                Messages.showMessageDialog(currentProject, "No file selected!", "PsiWeaver Execution", Messages.getErrorIcon());
                return;
            }

 //           var xmlFile = new File("psifile.xml");

//        XStreamUtils.write(xmlFile, rootFile);
//        SpecsIo.write(xmlFile, new Gson().toJson(rootFile));

   //     System.out.println("Wrote " + xmlFile.getAbsolutePath());

            /*
            ResourceProvider xmlResource = () ->  "pt/up/fe/specs/intellij/weaverspecs/joinPointModel.xml";
            ResourceProvider schemaResource = () ->  "schemas/joinPointModel.xsd";
            var xmlDoc = XmlDocument.newInstance(SpecsIo.resourceToStream(xmlResource), SpecsIo.resourceToStream(schemaResource));
            System.out.println("XML Doc: " + xmlDoc);
             */
//        ResourceProvider xmlResource = () ->  "pt/up/fe/specs/intellij/weaverspecs/joinPointModel.xml";
//        ResourceProvider xmlResource = () ->  "pt/up/fe/specs/intellij/weaverspecs/joinPointModel.xml";
//        System.out.println("CONTENTS:\n"+xmlResource.read());
//        var root = SpecsXml.getXmlRoot(xmlResource.read());
 //       System.out.println("JPMODEL: " + root);
        System.out.println("BEFORE");

//        System.out.println("CLASS LOADER: " + Thread.currentThread().getContextClassLoader());
        //System.getenv().put("lara.jarpath", "C:\\Users\\jbispo\\Work\\workspaces\\specs-java-eclipse-2019\\temp");

//        var weaver = new PsiWeaver(rootFile);
           //System.out.println(weaver.getLanguageSpecificationV2());
//        System.out.println("NASHORN: ");
//        var engine = JsEngineType.NASHORN.newEngine();
//        var engine = JsEngineType.GRAALVM_COMPAT.newEngine();
//        System.out.println("EVAL: " + engine.eval("a = 3; b= 2; a + b;"));




            //PsiWeaver.runAspect(rootFile, "import lara.Io; aspectdef println('Hello'); Io.writeFile('test.txt', 'testeee'); end");
            System.out.println("AFTER");




//            System.out.println("AFTER WEAVER");
/*
            DataStore userData = PsiWeaver.runAspect(rootFile, "aspectdef println('Hello'); end");

            System.out.println("User data: " + userData);
 */
//        } catch (Exception ex) {
//           System.out.println("Exception");
//            ex.printStackTrace();
//        }

        graalVmTesT();
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

    private Context.Builder createBuilder() {
        Context.Builder contextBuilder = Context.newBuilder("js")
                .allowHostAccess(HostAccess.ALL);

            contextBuilder.allowExperimentalOptions(true).option("js.nashorn-compat", "true");

        return contextBuilder;
    }


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
