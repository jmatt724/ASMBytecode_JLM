// Program #8
// Implement a While Loop

package asm;

import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class GenIfThenElse {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"IfThenElse", null, "java/lang/Object", null);
		
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
        	
        	mv.visitLdcInsn((Integer)5);
        	mv.visitVarInsn(Opcodes.ISTORE, 1);
        	mv.visitLdcInsn((Integer)0);
        	mv.visitVarInsn(Opcodes.ISTORE, 2);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	mv.visitLdcInsn("Equal");
        	mv.visitVarInsn(Opcodes.ASTORE, 3);
        	mv.visitLdcInsn("Not Equal");
        	mv.visitVarInsn(Opcodes.ASTORE, 4);
        	
        	Label iftrue = new Label();
        	Label end = new Label();
        	
        	mv.visitJumpInsn(Opcodes.IF_ICMPNE, iftrue);
        	
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.ALOAD, 3); // equal
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitJumpInsn(Opcodes.GOTO,end);
            
        	
        	mv.visitLabel(iftrue); // not equal
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.ALOAD, 4);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            mv.visitLabel(end);
        	mv.visitInsn(Opcodes.RETURN);
        	mv.visitMaxs(10,10);
        	mv.visitEnd();
        }//end main generation
        
        cw.visitEnd();
        
        byte[] b = cw.toByteArray();
        
        writeFile(b, "IfThenElse.class");
        
        System.out.println("Done!");
        
	}//end main
	
}//end class
