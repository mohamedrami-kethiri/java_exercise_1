import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Fibo implements Command{

    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("La Question");
        String strNb = scanner.nextLine();
        int nb = Integer.parseInt(strNb);

        if (nb == 0)
        {
            System.out.println("1");
        }

        else if (nb == 1 || nb == 2)
        {
            System.out.println("1");
        }

        else if (nb > 1)
        {
            int f1 = 1;
            int f2 = 1;
            int nb2 = 0;
            for (int j = 2; j < nb ;j++)
            {
                nb2 = f1 + f2;
                f1 = f2;
                f2 = nb2;
            }
            System.out.println(nb2);
        }
        return false;
    }
}