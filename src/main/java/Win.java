// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Pausable;
import kilim.Task;

public interface Win {
    void execute() throws Pausable, Exception;

    public static class Fork extends Task {
        Win body;
        public Fork(Win body) { this.body = body; }
        public void execute() throws Pausable, Exception {
            body.execute();
        }
    }
    
    public static void main(String[] args) throws Exception {
        Win mytask = () -> {
            System.out.println(args);
        };
        new Fork(mytask).start().joinb();
        Task.idledown();
    }

    
}
