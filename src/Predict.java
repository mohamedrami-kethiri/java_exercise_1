import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Predict implements Command {
    private static class Word {
        private final String word;
        private final Map<String, Integer> occurences = new HashMap<>();

        public void putFollower(String w) {
            this.occurences.put(w, this.occurences.getOrDefault(w, 0) + 1);
        }

        public Word(String word) {
            this.word = word;
        }

        public String predict() {
            if (occurences.isEmpty())
                return null;

            int i = Collections.max(occurences.values());

            List<String> list = this.occurences.keySet().stream().filter(k -> occurences.get(k).equals(i)).toList();

            return list.get(0);
        }
    }

    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner sc) {
        System.out.println("Le fichier:");
        String content;
        try {
            content = Files.readString(Path.of(sc.nextLine()));
        } catch (Exception e) {
            System.err.println("Unreadable file: " + e.getMessage());
            return false;
        }


        if (content.isBlank())
            return false;

        content = content.toLowerCase();
        content = content.replaceAll("[,.!?\\-'\"\t\n]", " ");
        content = content.replaceAll(" {2}", " ");

        Map<String, Word> words = new HashMap<>();

        String lastWord = Arrays.stream(content.split(" "))
                .filter(s -> !s.isBlank())
                .reduce("", (prev, next) -> {
                    if (!prev.isBlank()) {
                        words.putIfAbsent(prev, new Word(prev));
                        words.get(prev).putFollower(next);
                    }
                    return next;
                });

        words.putIfAbsent(lastWord, new Word(lastWord));

        System.out.println("Veuillez maintenant entrer un mot :");
        String startWord = sc.nextLine();
        startWord = startWord.toLowerCase();

        if (!words.containsKey(startWord))
            System.err.println("Le mot n'est pas présent dans le texte !");

        else {
            List<String> sentence = new ArrayList<>(List.of(startWord));
            while (sentence.size() < 20) {
                String nextWord = words.get(sentence.get(sentence.size() - 1)).predict();
                if (nextWord == null)
                    break;
                sentence.add(nextWord);
            }

            System.out.println(String.join(" ", sentence));
        }


        return false;
    }
}