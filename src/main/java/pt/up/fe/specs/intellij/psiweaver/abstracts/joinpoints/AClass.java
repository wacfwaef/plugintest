package pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints;

import java.util.List;
import org.lara.interpreter.weaver.interf.SelectOp;
import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point AClass
 * This class is overwritten by the Weaver Generator.
 * 
 * join point representation of a class
 * @author Lara Weaver Generator
 */
public abstract class AClass extends AObjectType {

    protected AObjectType aObjectType;

    /**
     * 
     */
    public AClass(AObjectType aObjectType){
        this.aObjectType = aObjectType;
    }
    /**
     * the class constructors
     * @return 
     */
    public List<? extends AConstructor> selectConstructor() {
        return select(pt.up.fe.specs.intellij.psiweaver.abstracts.joinpoints.AConstructor.class, SelectOp.DESCENDANTS);
    }

    /**
     * Get value on attribute name
     * @return the attribute's value
     */
    @Override
    public String getNameImpl() {
        return this.aObjectType.getNameImpl();
    }

    /**
     * Get value on attribute qualifiedName
     * @return the attribute's value
     */
    @Override
    public String getQualifiedNameImpl() {
        return this.aObjectType.getQualifiedNameImpl();
    }

    /**
     * Get value on attribute _package
     * @return the attribute's value
     */
    @Override
    public String getPackageImpl() {
        return this.aObjectType.getPackageImpl();
    }

    /**
     * methods inside a class
     * @return 
     */
    @Override
    public List<? extends AMethod> selectMethod() {
        return this.aObjectType.selectMethod();
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertBeforeImpl(AJoinPoint node) {
        return this.aObjectType.insertBeforeImpl(node);
    }

    /**
     * 
     * @param code 
     */
    @Override
    public AJoinPoint insertBeforeImpl(String code) {
        return this.aObjectType.insertBeforeImpl(code);
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertAfterImpl(AJoinPoint node) {
        return this.aObjectType.insertAfterImpl(node);
    }

    /**
     * 
     * @param code 
     */
    @Override
    public AJoinPoint insertAfterImpl(String code) {
        return this.aObjectType.insertAfterImpl(code);
    }

    /**
     * 
     * @param jp 
     */
    @Override
    public AJoinPoint insertReplaceImpl(AJoinPoint jp) {
        return this.aObjectType.insertReplaceImpl(jp);
    }

    /**
     * 
     * @param code 
     */
    @Override
    public AJoinPoint insertReplaceImpl(String code) {
        return this.aObjectType.insertReplaceImpl(code);
    }

    /**
     * 
     */
    @Override
    public AJoinPoint copyImpl() {
        return this.aObjectType.copyImpl();
    }

    /**
     * 
     */
    @Override
    public void removeImpl() {
        this.aObjectType.removeImpl();
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, String code) {
        return this.aObjectType.insertImpl(position, code);
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public AJoinPoint[] insertImpl(String position, JoinPoint code) {
        return this.aObjectType.insertImpl(position, code);
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return this.aObjectType.toString();
    }

    /**
     * 
     */
    @Override
    public Optional<? extends AObjectType> getSuper() {
        return Optional.of(this.aObjectType);
    }

    /**
     * 
     */
    @Override
    public final List<? extends JoinPoint> select(String selectName) {
        List<? extends JoinPoint> joinPointList;
        switch(selectName) {
        	case "constructor": 
        		joinPointList = selectConstructor();
        		break;
        	case "method": 
        		joinPointList = selectMethod();
        		break;
        	default:
        		joinPointList = this.aObjectType.select(selectName);
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
        this.aObjectType.fillWithAttributes(attributes);
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        this.aObjectType.fillWithSelects(selects);
        selects.add("constructor");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        this.aObjectType.fillWithActions(actions);
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "class";
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
        return this.aObjectType.instanceOf(joinpointClass);
    }
    /**
     * 
     */
    protected enum ClassAttributes {
        NAME("name"),
        QUALIFIEDNAME("qualifiedName"),
        PACKAGE("package"),
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
        private ClassAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<ClassAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(ClassAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}
