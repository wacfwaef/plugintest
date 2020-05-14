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
        GraalJSScriptEngine.create();
    }

}
