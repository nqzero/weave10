
import java.io.File;
import java.io.FileOutputStream;


/*
cp=$(mvn -q dependency:build-classpath -Dmdep.outputFile=/dev/fd/1)
cpasmu=$(mvnrun org.ow2.asm:asm-util)

source readme.sh
$java10/bin/java -cp target/classes:$cp DumpRunner
$java10/bin/javap -v -p -c -s -l Win.class > y9.dump
$java10/bin/java -cp .:w9:t9:$cp Win
$java10/bin/java -cp $cpasmu org.objectweb.asm.util.ASMifier w9/Win.class > src/main/java/WinDump.java

to call execute() which causes AbstractMethodError, in windump:
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "Win", "execute", "()V", true);



*/

public class DumpRunner {
    public static void main(String[] args) throws Exception {
        byte [] bytes = WinDump.dump();
        new FileOutputStream(new File("Win.class")).write(bytes);
    }
    
}
