import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;
public class WinDump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter classWriter = new ClassWriter(0);
FieldVisitor fieldVisitor;
MethodVisitor methodVisitor;
AnnotationVisitor annotationVisitor0;

classWriter.visit(V9, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, "Win", null, "java/lang/Object", null);

classWriter.visitSource("Win.java", null);

classWriter.visitInnerClass("java/lang/invoke/MethodHandles$Lookup", "java/lang/invoke/MethodHandles", "Lookup", ACC_PUBLIC | ACC_FINAL | ACC_STATIC);

{
fieldVisitor = classWriter.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, "$isWoven", "Z", "Z", new Integer(1));
fieldVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_ABSTRACT, "execute", "()V", null, new String[] { "kilim/Pausable", "java/lang/Exception" });
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "execute", "(Lkilim/Fiber;)V", null, new String[] { "kilim/Pausable", "java/lang/Exception" });
methodVisitor.visitCode();
methodVisitor.visitMethodInsn(INVOKESTATIC, "kilim/Task", "errNotWoven", "()V", false);
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
methodVisitor.visitInvokeDynamicInsn("execute", "([Ljava/lang/String;)LWin;", new Handle(Opcodes.H_INVOKESTATIC, "java/lang/invoke/LambdaMetafactory", "metafactory", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", false), new Object[]{Type.getType("(Lkilim/Fiber;)V"), new Handle(Opcodes.H_INVOKESTATIC, "Win", "lambda$main$0", "([Ljava/lang/String;Lkilim/Fiber;)V", false), Type.getType("(Lkilim/Fiber;)V")});
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
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_STATIC | ACC_SYNTHETIC, "lambda$main$0", "([Ljava/lang/String;Lkilim/Fiber;)V", null, new String[] { "kilim/Pausable", "java/lang/Exception" });
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
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_STATIC | ACC_SYNTHETIC, "lambda$main$0", "([Ljava/lang/String;)V", null, new String[] { "kilim/Pausable", "java/lang/Exception" });
methodVisitor.visitCode();
methodVisitor.visitMethodInsn(INVOKESTATIC, "kilim/Task", "errNotWoven", "()V", true);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(0, 1);
methodVisitor.visitEnd();
}
classWriter.visitEnd();

return classWriter.toByteArray();
}
}
