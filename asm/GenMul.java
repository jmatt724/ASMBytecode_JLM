// Program #1
// Multiply two numbers (I, L and D) and store them

package asm;

import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class GenMul {

	public static void main(String[] args) {
		//boilerplate code
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"MultiplyNumbers", null, "java/lang/Object", null);
		
        {
        	MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); // load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();
        }//end constructor generation
        
        {
        	MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        	mv.visitCode();
        	//declare and store doubles
            mv.visitLdcInsn((Double)173.43);
            mv.visitVarInsn(Opcodes.DSTORE,1);
            mv.visitLdcInsn((Double)45.56);
            mv.visitVarInsn(Opcodes.DSTORE,3);
            mv.visitVarInsn(Opcodes.DLOAD,1);
            mv.visitVarInsn(Opcodes.DLOAD,3);
            //pop 2 from stack and multiply then store
            mv.visitInsn(Opcodes.DMUL);
            mv.visitVarInsn(Opcodes.DSTORE,5);
            //print to terminal
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            //declare and store integers
            mv.visitLdcInsn((Integer)10);
            mv.visitVarInsn(Opcodes.ISTORE,1);
            mv.visitLdcInsn((Integer)2);
            mv.visitVarInsn(Opcodes.ISTORE,2);
            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitVarInsn(Opcodes.ILOAD,2);
            //pops integers and multiplies them
            mv.visitInsn(Opcodes.IMUL);
            mv.visitVarInsn(Opcodes.ISTORE,3);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            //declares and stores longs
            mv.visitLdcInsn((long)173.43);
            mv.visitVarInsn(Opcodes.LSTORE,1);
            mv.visitLdcInsn((long)45.56);
            mv.visitVarInsn(Opcodes.LSTORE,3);
            mv.visitVarInsn(Opcodes.LLOAD,1);
            mv.visitVarInsn(Opcodes.LLOAD,3);
            //pops longs and multiplies them
            mv.visitInsn(Opcodes.LMUL);
            mv.visitVarInsn(Opcodes.LSTORE,5);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
        	mv.visitEnd();
        }//end main generation
        
        cw.visitEnd();
        
        byte[] b = cw.toByteArray();
        
        writeFile(b, "MultiplyNumbers.class");
        
        System.out.println("Done!");
        
	}//end main
	
}//end class
