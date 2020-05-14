package runnable;

import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;

import java.util.concurrent.Callable;

import org.suikasoft.jOptions.Interfaces.DataStore;


public class MyCallable implements Callable<DataStore> {
    @Override
    public DataStore call() throws Exception {
        GraalJSScriptEngine.create();
        return DataStore.newInstance("Graal");
    }
}
