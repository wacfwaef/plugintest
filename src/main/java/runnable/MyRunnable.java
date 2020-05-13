package runnable;

import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;

public class MyRunnable implements Runnable {

    public void run() {
        System.out.println("running on "+Thread.currentThread().getContextClassLoader()+"...");
        Context.Builder contextBuilder = createBuilder();
        var engine = GraalJSScriptEngine.create(null, contextBuilder);
    }

    private Context.Builder createBuilder() {
        Context.Builder contextBuilder = Context.newBuilder("js")
                .allowHostAccess(HostAccess.ALL);

        contextBuilder.allowExperimentalOptions(true).option("js.nashorn-compat", "true");

        return contextBuilder;
    }
}
