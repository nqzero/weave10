import java.io.File;
import java.io.FileOutputStream;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
public class WinDump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter classWriter = new ClassWriter(0);
FieldVisitor fieldVisitor;
MethodVisitor methodVisitor;
AnnotationVisitor annotationVisitor0;

classWriter.visit(V1_8, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, "Win", null, "java/lang/Object", null);

classWriter.visitSource("Win.java", null);

classWriter.visitInnerClass("java/lang/invoke/MethodHandles$Lookup", "java/lang/invoke/MethodHandles", "Lookup", ACC_PUBLIC | ACC_FINAL | ACC_STATIC);

{
fieldVisitor = classWriter.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, "$isWoven", "Z", "Z", new Integer(1));
fieldVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_ABSTRACT, "execute", "()V", null, new String[] { "java/lang/Exception" });
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "execute", "(Ljava/lang/Object;)V", null, new String[] { "java/lang/Exception" });
methodVisitor.visitCode();
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(0, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, new String[] { "java/lang/Exception" });
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(13, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitInvokeDynamicInsn("execute", "([Ljava/lang/String;)LWin;", new Handle(Opcodes.H_INVOKESTATIC, "java/lang/invoke/LambdaMetafactory", "metafactory", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", false), new Object[]{Type.getType("(Ljava/lang/Object;)V"), new Handle(Opcodes.H_INVOKESTATIC, "Win", "lambda$main$0", "([Ljava/lang/String;Ljava/lang/Object;)V", true), Type.getType("(Ljava/lang/Object;)V")});
methodVisitor.visitVarInsn(ASTORE, 1);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(16, label1);
methodVisitor.visitInsn(RETURN);
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, label0, label2, 0);
methodVisitor.visitLocalVariable("mytask", "LWin;", null, label1, label2, 1);
methodVisitor.visitMaxs(1, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_STATIC | ACC_SYNTHETIC, "lambda$main$0", "([Ljava/lang/String;Ljava/lang/Object;)V", null, new String[] { "java/lang/Exception" });
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(14, label0);
methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(15, label1);
methodVisitor.visitInsn(RETURN);
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, label0, label2, 0);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_STATIC | ACC_SYNTHETIC, "lambda$main$0", "([Ljava/lang/String;)V", null, new String[] { "java/lang/Exception" });
methodVisitor.visitCode();
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(0, 1);
methodVisitor.visitEnd();
}
classWriter.visitEnd();

return classWriter.toByteArray();
}
    public static void main(String[] args) throws Exception {
        byte [] bytes = WinDump.dump();
        new FileOutputStream(new File("Win.class")).write(bytes);
    }
}


/* 
    We will review your report and have assigned it an internal review ID : 9056496

    NoClassDefFoundError with abstract lambdas that work in java 8 and 9
    i use ASM to modify lambdas, targeting a default method instead of the SAM.
      these lambdas worked in java 8 and 9.
      running in java 10.0.2 they throw  java.lang.NoClassDefFoundError
    ubuntu 16.04 with oracle jdk 10.0.2 installed locally, ie downloaded manually from oracle.com

    # save WinDump.java and download asm
    $java8/bin/javac -cp asm-6.2.jar -d . WinDump.java 
    $java10/bin/java -cp .:asm-6.2.jar WinDump
    $java10/bin/java -cp . Win

    successful completion with exit status 0 (and running with java 8 or 9 produces this result)

    Exception in thread "main" java.lang.NoClassDefFoundError: Win$$Lambda$1
            at Win$$Lambda$1/1612799726.get$Lambda(Unknown Source)
            at Win.main(Win.java:13)
    Caused by: java.lang.ClassNotFoundException: Win$$Lambda$1
            at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:582)
            at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:190)
            at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:499)
            ... 2 more

    use ASM to modify the classes to target the abstract interface method directly

*/
