// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Fiber;
import kilim.Pausable;
import kilim.Task;
import kilim.Mailbox;

public interface Wip {
    void execute() throws Pausable, Exception;
    default void executp() throws Pausable, Exception {}

    public static class Fork extends Task {
        Wip body;
        public Fork(Wip body) { this.body = body; }
        public void execute() throws Pausable, Exception {
            body.execute();
        }
    }
    
    public static void main(String[] args) throws Exception {
        if (kilim.tools.Kilim.trampoline(true,args)) return;
        Mailbox<String> mb = new Mailbox<String>();
        Wip mytask = () -> {
            String s = mb.get();  // mb captured from environment.
            System.out.println(s);
        };
        new Fork(mytask).start();
        mb.putb("Hello from " + 0);  // mb and fi captured from environment
        Task.idledown();
    }

    
}
