// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Fiber;
import kilim.Pausable;

public interface Loss {
    void execute(Fiber fiber) throws Pausable, Exception;
    
    public static void main(String[] args) throws Exception {
        Loss mytask = fiber -> {
            System.out.println(args);
        };
    }

    
    
}
