import com.sun.org.apache.xerces.internal.xs.StringList;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Curso{
    private String nome;
    private int alunos;

    public Curso(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }
}

public class ExemploCursos {
    public static void main(String[] args){
        List<Curso> cursos = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            cursos.add(new Curso("Curso " + i, Integer.parseInt(String.valueOf(System.nanoTime()).substring(13))));
        }

        long startTime = System.currentTimeMillis();
        cursos.stream().sorted(Comparator.comparing(Curso::getAlunos)).collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - startTime);

/*        startTime = System.currentTimeMillis();
        cursos.parallelStream().sorted(Comparator.comparing(Curso::getAlunos)).collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        cursos.sort(Comparator.comparing(Curso::getAlunos));
        System.out.println(System.currentTimeMillis() - startTime);*/

        cursos.stream()
                .filter(curso -> curso.getAlunos() > 50)
                .forEach(curso -> System.out.println(curso.getNome() + " - " + curso.getAlunos() + " alunos"));

        Stream<String> nomes = cursos.stream()
                .filter(curso -> curso.getAlunos() > 50)
                .map(Curso::getNome);

        Optional<Curso> cursoOptional =cursos.stream()
                .filter(curso -> curso.getAlunos() > 50)
                .findFirst();

        OptionalDouble averageOptional = cursos.stream()
                .mapToInt(curso -> curso.getAlunos())
                .average();

        averageOptional.ifPresent(average -> System.out.println("Media de alunos: " + average));

        List<Curso> cursosPopulares = cursos.stream()
                                            .filter(curso -> curso.getAlunos() > 50)
                                            .collect(Collectors.toList());

        /*int totalAlunos = cursos.stream()
                .filter(curso -> curso.getAlunos() >= 50)
                .mapToInt(Curso::getAlunos)
                .sum();

        System.out.println(totalAlunos);*/

    }
}
