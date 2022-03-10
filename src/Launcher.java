import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Launcher{
    public static void main(String[] args)
    {
        System.out.println("Bonjour");
        Scanner scanner = new Scanner( System.in );
        List<Command> Listcommands;
        Listcommands = new ArrayList<>();

        Fibo fibo = new Fibo();
        Freq freq = new Freq();
        Quit quit = new Quit();
        Predict predict = new Predict();

        Listcommands.add(fibo);
        Listcommands.add(freq);
        Listcommands.add(predict);
        Listcommands.add(quit);

        boolean end = false;
        String msg;
        int checker = 0;

        do
        {
            checker = 0;
            msg = scanner.nextLine();
            for(Command command : Listcommands)
            {
                if (msg.equals(command.name())){
                    end = command.run(scanner);
                    checker = 1;
                    break;
                }
            }
            if(checker == 0){
                System.out.println("Unknown command");
            }
        } while(!end);
        scanner.close();
    }
}