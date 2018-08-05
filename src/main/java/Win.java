// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Fiber;
import kilim.Pausable;
import kilim.Task;
import kilim.Mailbox;

public interface Win {
    interface Pow extends Wow {
        void execute(Fiber fiber) throws Pausable, Exception;
        default void execute() throws Pausable, Exception {}
    }

    interface Lambda {
        void execute(Fiber fiber) throws Pausable, Exception;
        default void execute() throws Pausable, Exception {}
    }
    static void dummy() throws Pausable, Exception {
        Lambda lambda = dummy -> {
            Task.sleep(1000);
            System.out.println("hello world");
        };
        lambda.execute();
    }
    
    interface Wow {
        void execute() throws Pausable, Exception;
    }
    
    public static class Fork extends Task {
        Pow body;
        Wow wow;
        public Fork(Pow body) { this.body = body; }
        public Fork(Wow body) { this.wow = body; }
        public void execute() throws Pausable, Exception {
            if (body != null)
                body.execute();
            else
                wow.execute();
        }
    }
    
    public static void main(String[] args) throws Exception {
//        if (kilim.tools.Kilim.trampoline(true,args)) return;
        Mailbox<String> mb = new Mailbox<String>();
        Mailbox<String> mb2 = new Mailbox<String>();
        Pow mytask = fiber -> {
            String s = mb.get();
            System.out.println("win: " + s);
        };
        new Fork(mytask).start();
        mb.putb("Hello from " + 1);
        if (args.length > 0) {
            Wow t2 = () -> {
                String s = mb2.get();
                System.out.println("wow: " + s);
            };
            new Fork(t2).start();
            mb2.putb("Hello from " + 2);
        }
        Task.idledown();
    }

    
}
