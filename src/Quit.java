import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Quit implements Command
{
    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "quit";
    }

    @Override
    public boolean run(Scanner scanner) {
        // TODO Auto-generated method stub
        return true;
    }
}