package string;

public class EncodeAndDecodeStrings {

    public String encode(String s[]) {
        // write your logic to encode the strings

        if(s.length == 0) {
            return Character.toString((char)258);
        }

        String sep = Character.toString((char)257);
        StringBuilder sb = new StringBuilder();

        for(String str: s) {

            sb.append(str);
            sb.append(sep);

        }

        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    public String[] decode(String s) {
        // write your logic to decode the string
        if(s.equals(Character.toString((char)258))) {
            return new String[]{};
        }

        String sep = Character.toString((char)257);


        return s.split(sep,-1);
    }
}
