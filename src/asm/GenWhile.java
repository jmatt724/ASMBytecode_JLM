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
        	MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()v", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); // load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()v",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitEnd();
        }//end constructor generation
        
        {
        	MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        	mv.visitCode();
        	
        	// TODO: main body
        	
        	mv.visitEnd();
        }//end main generation
        
        cw.visitEnd();
        
        byte[] b = cw.toByteArray();
        
        writeFile(b, "WhileLoop.class");
        
        System.out.println("Done!");
        
	}//end main
	
}//end class
