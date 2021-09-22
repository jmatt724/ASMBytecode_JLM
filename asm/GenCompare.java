// Program #4
// Compare two numbers (I, L, and D) to determine which is bigger/smaller

package asm;

import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class GenCompare {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"CompareNumbers", null, "java/lang/Object", null);
		
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
        	//declare doubles
        	mv.visitLdcInsn((Double)55.5);
        	//use value of and store
        	mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
        	mv.visitVarInsn(Opcodes.ASTORE, 1);
        	mv.visitLdcInsn((Double)55.6);
        	mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
        	mv.visitVarInsn(Opcodes.ASTORE, 2);
        	mv.visitVarInsn(Opcodes.ALOAD, 1);
        	//call doubleValue to get the actual value
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
        	mv.visitVarInsn(Opcodes.ALOAD, 2);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
        	//compares the two doubles
        	mv.visitInsn(Opcodes.DCMPL);
        	//labels to mark where to tell the program to jump to
        	Label l0 = new Label();
        	mv.visitJumpInsn(Opcodes.IFLE, l0);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("First is larger");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	Label l1 = new Label();
        	mv.visitJumpInsn(Opcodes.GOTO, l1);
        	mv.visitLabel(l0);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("Second is larger");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitLabel(l1);
        	//same but for integers
        	mv.visitLdcInsn((Integer)9);
        	mv.visitVarInsn(Opcodes.ISTORE, 1);
        	mv.visitLdcInsn((Integer) 10);
        	mv.visitVarInsn(Opcodes.ISTORE, 2);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	Label l2 = new Label();
        	mv.visitJumpInsn(Opcodes.IF_ICMPLE, l2);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("Second is larger");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	Label l3 = new Label();
        	mv.visitJumpInsn(Opcodes.GOTO, l3);
        	mv.visitLabel(l2);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("First is larger");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitLabel(l3);
        	//same but for longs
        	mv.visitLdcInsn((long) 56);
        	mv.visitVarInsn(Opcodes.LSTORE, 1);
        	mv.visitLdcInsn((long)55);
        	mv.visitVarInsn(Opcodes.LSTORE, 3);
        	mv.visitVarInsn(Opcodes.LLOAD, 1);
        	mv.visitVarInsn(Opcodes.LLOAD, 3);
        	mv.visitInsn(Opcodes.LCMP);
        	Label l4 = new Label();
        	mv.visitJumpInsn(Opcodes.IFLE, l4);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("Second is larger");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	Label l5 = new Label();
        	mv.visitJumpInsn(Opcodes.GOTO, l5);
        	mv.visitLabel(l4);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("First is larger");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitLabel(l5);


        	
        	mv.visitInsn(Opcodes.RETURN);
        	mv.visitMaxs(0, 0);
        	mv.visitEnd(); 
        }//end main generation
        
        cw.visitEnd();
        
        byte[] b = cw.toByteArray();
        
        writeFile(b, "CompareNumbers.class");
        
        System.out.println("Done!");
        
	}//end main
	
}//end class
