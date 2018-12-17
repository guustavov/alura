import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class OrdenaStrings {
    public static void main(String[] args){

        List<String> palavras = new ArrayList();

        palavras.add("alura online");
        palavras.add("editora casa do codigo");
        palavras.add("caelum");

//        Sort 1
/*
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() < o2.length())
                    return -1;
                if(o1.length() > o2.length())
                    return 1;
                return 0
            }
        };
        Collections.sort(palavras, comparator);
        palavras.sort(comparator);
        */


//        Sort 2 (Lambda com método estático compare do Integer)
//        palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

//        Sort 3 (Lambda com método estático comparing do Comparator)
//        palavras.sort(Comparator.comparing(s -> s.length()));

//        Sort 4 (method reference)
        palavras.sort(Comparator.comparing(String::length));


//        Print 1
/*
        for (String p : palavras) {
            System.out.println(p);
        }
        */

//        Print 2
/*
        Consumer<String> consumidor = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        palavras.forEach(consumidor);
        */

//        Print 3
//        palavras.forEach(s -> System.out.println(s));

//        Print 4
        palavras.forEach(System.out::println);
    }
}