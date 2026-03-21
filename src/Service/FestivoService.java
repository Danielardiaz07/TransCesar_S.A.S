package Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.Set;

public class FestivoService {

    private static final Set<MonthDay> fijos = Set.of(
        MonthDay.of(1, 1),  // Año Nuevo
        MonthDay.of(5, 1),  // Día del Trabajo
        MonthDay.of(7, 20),  // Independencia de Colombia
        MonthDay.of(8, 7),  // Batalla de Boyacá
        MonthDay.of(12, 8),  // Inmaculada Concepción
        MonthDay.of(12, 25)  // Navidad
    );

    private static final Set<MonthDay> emiliani = Set.of(
        MonthDay.of(1, 6),  // Reyes Magos
        MonthDay.of(3, 19),  // San José
        MonthDay.of(6, 29),  // San Pedro y San Pablo
        MonthDay.of(8, 15),  // Asunción de la Virgen
        MonthDay.of(10, 12),  // Día de la Raza
        MonthDay.of(11, 1),  // Todos los Santos
        MonthDay.of(11, 11)  // Independencia de Cartagena
    );

    public static final double recargoFestivo = 1.20;

    public boolean esFestivo(LocalDate fecha) {
        Set<LocalDate> festivos = generarFestivosDelAnio(fecha.getYear());
        return festivos.contains(fecha);
    }

    public double getFactor(LocalDate fecha) {
        return esFestivo(fecha) ? recargoFestivo : 1.0;
    }

    private Set<LocalDate> generarFestivosDelAnio(int anio) {
        Set<LocalDate> festivos = new HashSet<>();

        for (MonthDay md : fijos) {
            LocalDate fecha = md.atYear(anio);
            festivos.add(ajustarFijo(fecha));
        }

        for (MonthDay md : emiliani) {
            LocalDate fecha = md.atYear(anio);
            festivos.add(siguienteLunes(fecha));
        }
        return festivos;
    }

    private LocalDate ajustarFijo(LocalDate fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();
        if (dia == DayOfWeek.SATURDAY) 
            return fecha.plusDays(2);
        if (dia == DayOfWeek.SUNDAY)   
            return fecha.plusDays(1);
        return fecha;
    }

    private LocalDate siguienteLunes(LocalDate fecha) {
        if (fecha.getDayOfWeek() == DayOfWeek.MONDAY) 
            return fecha;
        int diasHastaLunes = DayOfWeek.MONDAY.getValue() - fecha.getDayOfWeek().getValue();
        if (diasHastaLunes <= 0) diasHastaLunes += 7;
        return fecha.plusDays(diasHastaLunes);
    }
}