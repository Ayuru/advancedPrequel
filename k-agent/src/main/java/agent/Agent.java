package agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("UserAgent agent is running.");

        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("com.extra.chapter2.User"))
                .transform(((builder, typeDescription, classLoader, module, protectionDomain) -> {
                    System.out.println("Class " + typeDescription);
                    return builder.visit(Advice.to(Monitor.class).on(ElementMatchers.any()));
                }));

        agentBuilder.installOn(instrumentation);
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        premain(args, instrumentation);
    }
}
