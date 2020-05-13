package pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints;

import java.util.List;
import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point AConstructor
 * This class is overwritten by the Weaver Generator.
 * 
 * 
 * @author Lara Weaver Generator
 */
public abstract class AConstructor extends AMethod {

    protected AMethod aMethod;

    /**
     * 
     */
    public AConstructor(AMethod aMethod){
        this.aMethod = aMethod;
    }
    /**
     * Method used by the lara interpreter to select scopes
     * @return 
     */
    @Override
    public List<? extends AScope> selectScope() {
        return this.aMethod.selectScope();
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertBeforeImpl(AJoinPoint node) {
        return this.aMethod.insertBeforeImpl(node);
    }

    /**
     * 
     * @param code 
     */
    @Override
    public AJoinPoint insertBeforeImpl(String code) {
        return this.aMethod.insertBeforeImpl(code);
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertAfterImpl(AJoinPoint node) {
        return this.aMethod.insertAfterImpl(node);
    }

    /**
     * 
     * @param code 
     */
    @Override
    public AJoinPoint insertAfterImpl(String code) {
        return this.aMethod.insertAfterImpl(code);
    }

    /**
     * 
     * @param jp 
     */
    @Override
    public AJoinPoint insertReplaceImpl(AJoinPoint jp) {
        return this.aMethod.insertReplaceImpl(jp);
    }

    /**
     * 
     * @param code 
     */
    @Override
    public AJoinPoint insertReplaceImpl(String code) {
        return this.aMethod.insertReplaceImpl(code);
    }

    /**
     * 
     */
    @Override
    public AJoinPoint copyImpl() {
        return this.aMethod.copyImpl();
    }

    /**
     * 
     */
    @Override
    public void removeImpl() {
        this.aMethod.removeImpl();
    }

    /**
     * Sets the name of this method, returns the previous name
     * @param name 
     */
    @Override
    public String setNameImpl(String name) {
        return this.aMethod.setNameImpl(name);
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, String code) {
        return this.aMethod.insertImpl(position, code);
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, JoinPoint code) {
        return this.aMethod.insertImpl(position, code);
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return this.aMethod.toString();
    }

    /**
     * 
     */
    @Override
    public Optional<? extends AMethod> getSuper() {
        return Optional.of(this.aMethod);
    }

    /**
     * 
     */
    @Override
    public final List<? extends JoinPoint> select(String selectName) {
        List<? extends JoinPoint> joinPointList;
        switch(selectName) {
        	case "scope": 
        		joinPointList = selectScope();
        		break;
        	default:
        		joinPointList = this.aMethod.select(selectName);
        		break;
        }
        return joinPointList;
    }

    /**
     * 
     */
    @Override
    public final void defImpl(String attribute, Object value) {
        switch(attribute){
        case "line": {
        	if(value instanceof Integer){
        		this.defLineImpl((Integer)value);
        		return;
        	}
        	if(value instanceof String){
        		this.defLineImpl((String)value);
        		return;
        	}
        	this.unsupportedTypeForDef(attribute, value);
        }
        default: throw new UnsupportedOperationException("Join point "+get_class()+": attribute '"+attribute+"' cannot be defined");
        }
    }

    /**
     * 
     */
    @Override
    protected final void fillWithAttributes(List<String> attributes) {
        this.aMethod.fillWithAttributes(attributes);
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        this.aMethod.fillWithSelects(selects);
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        this.aMethod.fillWithActions(actions);
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "constructor";
    }

    /**
     * Defines if this joinpoint is an instanceof a given joinpoint class
     * @return True if this join point is an instanceof the given class
     */
    @Override
    public final boolean instanceOf(String joinpointClass) {
        boolean isInstance = get_class().equals(joinpointClass);
        if(isInstance) {
        	return true;
        }
        return this.aMethod.instanceOf(joinpointClass);
    }
    /**
     * 
     */
    protected enum ConstructorAttributes {
        PARENT("parent"),
        ISSTATEMENT("isStatement"),
        CODE("code"),
        AST("ast"),
        ISBLOCK("isBlock"),
        CHILDREN("children"),
        LINE("line"),
        ANCESTOR("ancestor"),
        NUMCHILDREN("numChildren"),
        MODIFIERS("modifiers"),
        DESCENDANTS("descendants"),
        CHILD("child");
        private String name;

        /**
         * 
         */
        private ConstructorAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<ConstructorAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(ConstructorAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}