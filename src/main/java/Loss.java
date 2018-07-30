// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)
import kilim.Fiber;
import kilim.Pausable;
import kilim.Task;
public interface Loss {
    static public final boolean $isWoven = true;    
    void execute(Fiber fiber) throws Pausable, Exception;
    default void execute() throws Pausable, Exception {
        Task.errNotWoven();
    }
    public static class Fork extends Task {
        Loss body;
        public Fork(Loss body) { this.body = body; }
        public void execute() throws Pausable, Exception {
            body.execute();
        }
    }
    
    public static void main(String[] args) throws Exception {
        Loss mytask = fiber -> {
            System.out.println(args);
        };
        new Fork(mytask).start().joinb();
        Task.idledown();
    }

    
    
}
