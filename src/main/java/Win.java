// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Pausable;
import kilim.Task;
import kilim.Mailbox;

public interface Win {
    
    public static void main(String[] args) throws Exception {
        Mailbox<String> mb = new Mailbox<String>();
        Pausable.Fork mytask = fiber -> {
                String s = mb.get();  // mb captured from environment.
                System.out.println(s);
        };
        Task.fork(mytask);
                mb.putb("Hello from " + 0);  // mb and fi captured from environment
        Task.idledown();
    }

    
}
