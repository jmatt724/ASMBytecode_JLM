// Program #7
// Implement a While Loop

package asm;

import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class GenWhile {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"WhileLoop", null, "java/lang/Object", null);
		
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
        	
        	mv.visitLdcInsn((Integer)1);
        	mv.visitVarInsn(Opcodes.ISTORE, 1);
        	mv.visitLdcInsn((Integer)10);
        	mv.visitVarInsn(Opcodes.ISTORE, 2);
        	
        	Label l1 = new Label();
        	
        	mv.visitLabel(l1);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	
        	Label l2 = new Label();
        	
        	mv.visitJumpInsn(Opcodes.IF_ICMPEQ, l2);
        	mv.visitIincInsn(1,1);
        	mv.visitJumpInsn(Opcodes.GOTO, l1);
        	
        	mv.visitLabel(l2);
        	
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        	
        	mv.visitInsn(Opcodes.RETURN);
        	mv.visitMaxs(10, 10);
        	mv.visitEnd();
        }//end main generation
        
        cw.visitEnd();
        
        byte[] b = cw.toByteArray();
        
        writeFile(b, "WhileLoop.class");
        
        System.out.println("Done!");
        
	}//end main
	
}//end class
