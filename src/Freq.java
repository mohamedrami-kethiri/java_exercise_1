import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Freq implements Command{

    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("La Question");
        String b = scanner.nextLine();
        Path path = Paths.get(b);
        try {
            String content = java.nio.file.Files.readString(path);
            content = content.toLowerCase(Locale.ROOT);
            String[] words = content.split(" ");
            List<String> list = Arrays.asList(words);
            Map<Object, Integer> frequencyMap = list.stream()
                    .collect(toMap(
                            s -> s, // key is the word
                            s -> 1, // value is 1
                            Integer::sum));
            List<Object> res = list.stream()
                    .sorted(comparing(frequencyMap::get).reversed()) // sort by descending frequency
                    .distinct() // take only unique values
                    .limit(3)   // take only the first 10
                    .collect(toList()); // put it in a returned list

            for(int i=0;i<3;i++){
                if (i != 2){
                    System.out.print(res.get(i)+" ");
                }

                else{
                    System.out.print(res.get(i));
                }
            }
            System.out.println();


        } catch (Exception e) {
            System.out.println("Unreadable file: " + e.toString());
        }
        return false;
    }

}