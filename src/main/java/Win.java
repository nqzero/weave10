// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Fiber;
import kilim.Pausable;
import kilim.Task;
import kilim.Mailbox;

public interface Win {
    void execute(Fiber fiber) throws Pausable, Exception;
    default void execute() throws Pausable, Exception {}
    
    public static class Fork extends Task {
        Win body;
        public Fork(Win body) { this.body = body; }
        public void execute() throws Pausable, Exception {
            body.execute();
        }
    }
    
    public static void main(String[] args) throws Exception {
        if (kilim.tools.Kilim.trampoline(true,args)) return;
        Mailbox<String> mb = new Mailbox<String>();
        Win mytask = fiber -> {
            String s = mb.get();  // mb captured from environment.
            System.out.println(s);
        };
        new Fork(mytask).start();
        mb.putb("Hello from " + 0);  // mb and fi captured from environment
        Task.idledown();
    }

    
}
