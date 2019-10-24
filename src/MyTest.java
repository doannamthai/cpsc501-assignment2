import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MyTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testClassName() {
        ClassA classA = new ClassA();
        new Inspector().findClassName(classA.getClass(), classA, false, 0);
        assertEquals("Class name: ClassA", outContent.toString().trim());
        outContent.reset();
        ClassD classD = new ClassD();
        new Inspector().findClassName(classD.getClass(), classD, false, 0);
        assertEquals("Class name: ClassD", outContent.toString().trim());

    }

    @Test
    public void testInterfaceInfo() throws Exception {
        ClassA classA = new ClassA();
        new Inspector().findInterfaceInfo("ClassA", classA.getClass(), classA, false, 0);
        String expected = "[ClassA] Interface(s) information" +
                "Class name: Serializable" +
                "[Serializable] No super-class" +
                "[Serializable] No interface(s)" +
                "[Serializable] Constructor(s) information" +
                "[Serializable] Method(s) information" +
                "[Serializable] Field(s) information" +
                "Class name: Runnable" +
                "[Runnable] No super-class" +
                "[Runnable] No interface(s)" +
                "[Runnable] Constructor(s) information" +
                "[Runnable] Method(s) information" +
                "Method name: run" +
                "Exceptions thrown (0): Empty" +
                "Parameter types (0): Empty" +
                "Return type: void" +
                "Modifiers: public abstract" +
                "[Runnable] Field(s) information";
        assertEquals(expected.replaceAll("\\s",""),
                outContent.toString().replaceAll("\\s",""));
    }

    @Test
    public void testConstructorInfo(){
        ClassD classD = new ClassD();
        new Inspector().findConstructorInfo("ClassD", classD.getClass(), classD, false, 0);
        String expected  = "[ClassD] Constructor(s) information" +
                "Constructor name: ClassD" +
                "Parameter types (0): Empty" +
                "Modifiers: public" +
                "Constructor name: ClassD" +
                "Parameter types (1): int" +
                "Modifiers: public";
        assertEquals(expected.replaceAll("\\s",""),
                outContent.toString().replaceAll("\\s",""));
    }

    @Test
    public void testMethodInfo(){
        ClassD classD = new ClassD();
        new Inspector().findMethodInfo("ClassD", classD.getClass(), classD, false, 0);
        String expected = "[ClassD] Method(s) information" +
                "Method name: toString" +
                "Exceptions thrown (0): Empty" +
                "Parameter types (0): Empty" +
                "Return type: java.lang.String" +
                "Modifiers: public" +
                "Method name: getVal3" +
                "Exceptions thrown (0): Empty" +
                "Parameter types (0): Empty" +
                "Return type: int" +
                "Modifiers: public";
        assertEquals(expected.replaceAll("\\s",""),
                outContent.toString().replaceAll("\\s",""));
    }

    @Test
    public void testFieldInfo() throws Exception{
        ClassA classA = new ClassA();
        new Inspector().findFieldInfo("ClassA", classA.getClass(), classA, false, 0);
        String expected = "[ClassA] Field(s) information" +
                "Field name:val" +
                "Type:int" +
                "Modifiers:private" +
                "Current value:3" +
                "Field name:val2" +
                "Type:double" +
                "Modifiers:private" +
                "Current value:0.2" +
                "Field name:val3" +
                "Type:boolean" +
                "Modifiers:private" +
                "Current value:true";
        assertEquals(expected.replaceAll("\\s",""),
                outContent.toString().replaceAll("\\s",""));
    }

}