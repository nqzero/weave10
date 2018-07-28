// https://stackoverflow.com/questions/18971830/generating-working-invokedynamic-instruction-with-asm
// https://blog.gypsyengineer.com/en/tech/whats-new-in-java-10-episode-two.html
// https://docs.oracle.com/javase/10/tools/java.htm  (-XX options, -Xlog, ...)



import kilim.Mailbox;
import kilim.Task;

public class Win {

    
    public static void main(String[] args) throws Exception {
        Mailbox<Integer> mb = new Mailbox();
        kilim.Pausable.Fork mytask = () -> {
            Task.sleep(1000);
            mb.put(1);
        };

        Task.fork(mytask);
        int tmp = mb.getb();
        System.out.println("tmp: " + tmp);
        
        Task.idledown();
    }

    
}
