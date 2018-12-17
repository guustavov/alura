import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Datas {
    public static void main(String[] args){
        LocalDate hoje = LocalDate.now();
        System.out.println(hoje);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataNoFuturo = LocalDate.of(2099, Month.JANUARY, 25);
        LocalDate dataNoPassado = LocalDate.parse("15/02/1903", formatter);

        System.out.println(dataNoFuturo.format(formatter));
        System.out.println(dataNoPassado.format(formatter));


        Period intervalo = Period.between(dataNoPassado, dataNoFuturo);

        System.out.println(intervalo.getYears() + " anos, " +
                            intervalo.getMonths() + " meses e " +
                            intervalo.getDays() + " dias");

        //Duration para intervalo de horas

//        int anos = olimpiadasRio.getYear() - hoje.getYear();

//        System.out.println(anos);
    }
}
