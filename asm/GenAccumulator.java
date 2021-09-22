// Program #9
// Get input (I or D), from the user, run a loop that adds that number to an accumulator and then prints the result.

package asm;

import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class GenAccumulator {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"Accumulator", null, "java/lang/Object", null);
		
        {
        	MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); // load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }//end constructor generation
        
        {
        	MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        	mv.visitCode();
        	//New scanner to get starting number
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("Enter your number: ");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        	mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
        	mv.visitInsn(Opcodes.DUP);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
        	mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
        	mv.visitVarInsn(Opcodes.ASTORE, 1);
        	mv.visitVarInsn(Opcodes.ALOAD, 1);
        	//Gets second input as first number to add
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
        	mv.visitVarInsn(Opcodes.ISTORE, 2);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("Enter another number or 0 to quit: ");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        	mv.visitVarInsn(Opcodes.ALOAD, 1);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
        	mv.visitVarInsn(Opcodes.ISTORE, 3);
        	Label l0 = new Label();
        	//start of loop
        	mv.visitLabel(l0);
        	mv.visitVarInsn(Opcodes.ILOAD, 3);
        	Label l1 = new Label();
        	//compares number to add to 0, if zero quit and print sum;
        	mv.visitJumpInsn(Opcodes.IFEQ, l1);
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	mv.visitVarInsn(Opcodes.ILOAD, 3);
        	mv.visitInsn(Opcodes.IADD);
        	mv.visitVarInsn(Opcodes.ISTORE, 2);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("Enter another number or 0 to quit: ");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        	mv.visitVarInsn(Opcodes.ALOAD, 1);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
        	mv.visitVarInsn(Opcodes.ISTORE, 3);
        	mv.visitJumpInsn(Opcodes.GOTO, l0);
        	mv.visitLabel(l1);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        	mv.visitVarInsn(Opcodes.ALOAD, 1);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "close", "()V", false);
        	mv.visitInsn(Opcodes.RETURN);
        	
        	mv.visitMaxs(3, 4);
        	mv.visitEnd();
        }//end main generation
        
        cw.visitEnd();
        
        byte[] b = cw.toByteArray();
        
        writeFile(b, "Accumulator.class");
        
        System.out.println("Done!");
        
	}//end main
	
}//end class
