package agent;


import net.bytebuddy.asm.Advice;

public class Monitor {
    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Class clazz, @Advice.Origin("#m") String methodName) {
        System.out.println("Entering method: " + methodName);
    }

    @Advice.OnMethodExit
    public static void exit() {
        System.out.println("Exiting method.");
    }
}