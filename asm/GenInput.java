// Program #6
// Get input from user using the Scanner class (I, L, and D)

package asm;

import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class GenInput {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"ScannerInput", null, "java/lang/Object", null);
		
        {
        	MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); // load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }//end constructor generation
        
        {
        	MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        	mv.visitCode();
        	//generating the scanner
        	mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
        	mv.visitInsn(Opcodes.DUP);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in",  "Ljava/io/InputStream;");
        	mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner" , "<init>", "(Ljava/io/InputStream;)V", false);
        	mv.visitVarInsn(Opcodes.ASTORE, 1);
        	//stores the input to 1
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("Input: ");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
        	mv.visitVarInsn(Opcodes.ASTORE, 2);
        	//prints the output back out for checking
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("Did you say?: ");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.ALOAD, 2);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitInsn(Opcodes.RETURN);
        	
        	mv.visitMaxs(0, 0);
        	mv.visitEnd();
        }//end main generation
        
        cw.visitEnd();
        
        byte[] b = cw.toByteArray();
        
        writeFile(b, "ScannerInput.class");
        
        System.out.println("Done!");
        
	}//end main
	
}//end class
