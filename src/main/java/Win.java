// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Task;

public class Win {

    
    public static void main(String[] args) throws Exception {
        kilim.Pausable.Fork mytask = () -> {
            System.out.println(args);
        };
    }

    
}
