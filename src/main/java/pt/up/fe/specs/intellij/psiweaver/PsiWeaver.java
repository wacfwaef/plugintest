package pt.up.fe.specs.intellij.psiweaver;

import com.intellij.psi.PsiFile;
import org.lara.interpreter.weaver.options.WeaverOption;
import org.lara.language.specification.LanguageSpecification;
import org.lara.language.specification.dsl.LanguageSpecificationV2;
import pt.up.fe.specs.intellij.psiweaver.abstracts.weaver.APsiWeaver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.util.UUID;

import org.suikasoft.jOptions.Interfaces.DataStore;
import org.lara.interpreter.weaver.interf.JoinPoint;
import org.lara.interpreter.weaver.interf.AGear;
import pt.up.fe.specs.intellij.psiweaver.joinpoints.IntelliJFile;
import pt.up.fe.specs.lara.WeaverLauncher;
import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.SpecsLogs;
import pt.up.fe.specs.util.SpecsSystem;

import pt.up.fe.specs.lara.langspec.LangSpecsXmlParser;


/**
 * Weaver Implementation for PsiWeaver<br>
 * Since the generated abstract classes are always overwritten, their implementation should be done by extending those abstract classes with user-defined classes.<br>
 * The abstract class {@link pt.up.fe.specs.intellij.psiweaver.abstracts.APsiWeaverJoinPoint} can be used to add user-defined methods and fields which the user intends to add for all join points and are not intended to be used in LARA aspects.
 * @author Lara Weaver Generator
 */
public class PsiWeaver extends APsiWeaver {

    private final PsiFile rootFile;
    private final DataStore userData;

    public PsiWeaver(PsiFile rootFile) {
        this.rootFile = rootFile;
        this.userData = DataStore.newInstance("PsiWeaver User Data");
    }


    public static DataStore runAspect(PsiFile rootFile, String laraAspect) {
        File tempFolder = SpecsIo.getTempFolder("psiweaver");
        File aspectFile = new File(tempFolder, UUID.randomUUID().toString() + ".lara");
        SpecsIo.write(aspectFile, laraAspect);
        SpecsLogs.debug(() -> "Wrote temporary lara file '"+aspectFile.getAbsolutePath()+"'");

        return runAspect(rootFile, aspectFile);
    }

    public static DataStore runAspect(PsiFile rootFile, File laraAspect) {
        // This is mostly because of formatting of prints
        SpecsSystem.programStandardInit();
        //SpecsLogs.setupConsoleOnly();

        //CLIConfigOption.ALLOW_GUI = false;
        List<String> arguments = new ArrayList<>();


        arguments.add(laraAspect.getAbsolutePath());
        arguments.add("-b");
        arguments.add("2");

        arguments.add("-js");
        arguments.add("NASHORN");

        PsiWeaver weaver = new PsiWeaver(rootFile);
//        System.out.println("Current classloader: " +  Thread.currentThread().getContextClassLoader());
//        System.out.println("Truffle classloader: " + ArrayUtils.class.getClassLoader());
//
//        var otherThreadClassloader = SpecsSystem.executeOnThreadAndWait(() -> ArrayUtils.class.getClassLoader());
//        System.out.println("Other thread classloader: " + otherThreadClassloader);

//        Thread t = Thread.currentThread();
//        ClassLoader previousClassLoader = t.getContextClassLoader();
//        System.out.println("Previous classloader: " + previousClassLoader);
//        t.setContextClassLoader(ArrayUtils.class.getClassLoader());
//       boolean success = false;
//        try {
//            new WeaverLauncher(weaver).launch(arguments.toArray(new String[0]));
//        } finally {
//            t.setContextClassLoader(previousClassLoader);
//        }

//        var result = new WeaverLauncher(weaver).launchExternal(arguments.toArray(new String[0]));
        var result = new WeaverLauncher(weaver).launch(arguments.toArray(new String[0]));

        System.out.println("RESULT: " + result);
        return weaver.getUserData();
    }



    public static void main(String[] args) {
        DataStore userData = PsiWeaver.runAspect(null, "aspectdef println('Hello'); end");

/*
        SpecsSystem.programStandardInit();
        //CLIConfigOption.ALLOW_GUI = false;
        List<String> arguments = new ArrayList<>();
        File tempFolder = SpecsIo.getTempFolder("psiweaver");
        File aspectFile = new File(tempFolder, "test_aspect.lara");
        SpecsIo.write(aspectFile, "aspectdef Test println('Hello'); end");
        arguments.add(aspectFile.getAbsolutePath());
        arguments.add("-b");
        arguments.add("2");

         new WeaverLauncher(new PsiWeaver(null)).launch(arguments.toArray(new String[0]));

 */
    }

    public DataStore getUserData() {
        return userData;
    }

    /**
     * Warns the lara interpreter if the weaver accepts a folder as the application or only one file at a time.
     * 
     * @return true if the weaver is able to work with several files, false if only works with one file
     */
    public boolean handlesApplicationFolder() {
        //Can the weaver handle an application folder?
        return false;
    }

    /**
     * Set a file/folder in the weaver if it is valid file/folder type for the weaver.
     * 
     * @param source the file with the source code
     * @param outputDir output directory for the generated file(s)
     * @param args arguments to start the weaver
     * @return true if the file type is valid
     */
    public boolean begin(List<File> sources, File outputDir, DataStore args) {
        System.out.println("Root psi file: " + rootFile);
        return true;
    }

    /**
     *  Return a JoinPoint instance of the language root, i.e., an instance of AApp
     * @return an instance of the join point root/program
     */
    public JoinPoint select() {
        return new IntelliJFile(rootFile);
        //return new <AApp implementation>;
//        throw new UnsupportedOperationException("Method select for PsiWeaver is not yet implemented");
    }

    /**
     *  Closes the weaver to the specified output directory location, if the weaver generates new file(s)
     * 
     * @return if close was successful
     */
    public boolean close() {
        //Terminate weaver execution with final steps required and writing output files
        //throw new UnsupportedOperationException("Method close for PsiWeaver is not yet implemented");
        return true;
    }

    /**
     *  Returns a list of Gears associated to this weaver engine
     * 
     * @return a list of implementations of {@link AGear} or null if no gears are available
     */
    public List<AGear> getGears() {
        return null; //i.e., no gears currently being used
    }

    /**
     * Returns Weaving Engine as a PsiWeaver
     */
    public static PsiWeaver getPsiWeaver() {
        return (PsiWeaver) getThreadLocalWeaver();
    }

    @Override
    public LanguageSpecification getLanguageSpecification() {
        return LanguageSpecification.newInstance(() -> "pt/up/fe/specs/intellij/weaverspecs/joinPointModel.xml", () -> "pt/up/fe/specs/intellij/weaverspecs/artifacts.xml",
                () -> "pt/up/fe/specs/intellij/weaverspecs/actionModel.xml", true);
    }


    @Override
    public List<WeaverOption> getOptions() {
        return Collections.emptyList();
    }

    @Override
    protected LanguageSpecificationV2 buildLangSpecsV2() {
        return LangSpecsXmlParser.parse(() -> "pt/up/fe/specs/intellij/weaverspecs/joinPointModel.xml", () -> "pt/up/fe/specs/intellij/weaverspecs/artifacts.xml",
                () -> "pt/up/fe/specs/intellij/weaverspecs/actionModel.xml", true);
    }
}
