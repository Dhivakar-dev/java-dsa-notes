import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> lines = new ArrayList<>();

        int index = 0;
        while(index<words.length) {
            int next = index+1;
            int count = words[index].length();

            while(next <words.length) {
                if(count + 1 +words[next].length() > maxWidth) {
                    break;
                }
                count += 1 + words[next].length();
                next++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(words[index]);

            int diff = next - index -1;

            if(next == words.length || diff == 0) {
                for(int i=index+1; i<next; i++) {
                    sb.append(" ");
                    sb.append(words[i]);
                }

                for(int i=sb.length(); i<maxWidth; i++) {
                    sb.append(" ");
                }

            }

            else {
                int spaces = (maxWidth-count)/diff;
                int extraSpaces = (maxWidth-count)%diff;

                for(int i=index+1; i<next; i++) {

                    for(int s = spaces; s>0; s--) {
                        sb.append(" ");
                    }

                    if(extraSpaces>0) {
                        sb.append(" ");
                        extraSpaces--;
                    }

                    sb.append(" ");
                    sb.append(words[i]);
                }

            }

            lines.add(sb.toString());
            index = next;
        }
        return lines;

    }
}
