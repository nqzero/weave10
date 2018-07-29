// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Pausable;

public interface Win {
    void execute() throws Pausable, Exception;
    
    public static void main(String[] args) throws Exception {
        Win mytask = () -> {
            System.out.println(args);
        };
    }

    
}
