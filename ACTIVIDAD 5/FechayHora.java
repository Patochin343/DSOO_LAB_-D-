public class FechayHora {
    private int hora;
    private int minuto;
    private int año;
    private int mes;
    private int dia;

    public FechayHora(String fecha, String hora) {
        Object[] formato = validarFormatoFechaYHora(fecha, hora);
        if (!(boolean) (formato[0])) {
            this.hora = 0;
            this.minuto = 0;
            this.año = 2025;
            this.mes = 1;
            this.dia = 1;
        } else {
            this.dia = (int) formato[1];
            this.mes = (int) formato[2];
            this.año = (int) formato[3];
            this.hora = (int) formato[4];
            this.minuto = (int) formato[5];
        }
    }

    /* Campo 0 fecha valida campo 1 dia campo 2 mes campo 3 año campo 4 hora campo 5 minutos */
    private Object[] validarFormatoFechaYHora(String fecha, String horaStr) {
        String[] stringfecha = fecha.split("/");
        String[] stringHora = horaStr.split(":");
        int Dia, Mes, Año, Hora, Minutos;
        try {
            Dia = Integer.parseInt(stringfecha[0]);
            Mes = Integer.parseInt(stringfecha[1]);
            Año = Integer.parseInt(stringfecha[2]);
            Hora = Integer.parseInt(stringHora[0]);
            Minutos = Integer.parseInt(stringHora[1]);
            
            Object[] datos = new Object[] {true, Dia, Mes, Año, Hora, Minutos};
            boolean enRango = validarRangoFechaHora(datos); 
            if (enRango) {
                return datos;
            } else {
                return new Object[] {false};
            }
        } catch (Exception e) {
            return new Object[] {false};
        }
    }

    private boolean validarRangoFechaHora(Object[] datos) {
        int dia = (int) datos[1];
        int mes = (int) datos[2];
        int año = (int) datos[3];
        int hora = (int) datos[4];
        int minutos = (int) datos[5];

        if (mes < 1 || mes > 12) {
            System.out.println("Error: Mes debe estar entre 1 y 12");
            return false;
        }
            
        if (dia < 1 || dia > 31) {
            System.out.println("Error: Día debe estar entre 1 y 31");
            return false;
        }
            
        if (hora < 0 || hora > 23) {
            System.out.println("Error: Hora debe estar entre 0 y 23");
            return false;
        }
            
        if (minutos < 0 || minutos > 59) {
            System.out.println("Error: Minutos debe estar entre 0 y 59");
            return false;
        }
        
        if (!validarDia(dia, mes, año)) {
            System.out.println("Error: Fecha inválida para el mes/año especificado");
            return false;
        }
        return true;
    }

    private boolean validarDia(int dia, int mes, int año) {
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || 
            mes == 8 || mes == 10 || mes == 12) {
            return dia <= 31;
        }
        
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return dia <= 30;
        }
        
        if (mes == 2) {
            return dia <= (esBisiesto(año) ? 29 : 28);
        }
        
        return false;
    }

    private boolean esBisiesto(int año) {
        return (año % 4 == 0) && (año % 100 != 0 || año % 400 == 0);
    }

    public static boolean estaEntre(FechayHora fecha, FechayHora fechaInicio, FechayHora fechaFin) {
        return (esMayorOIgual(fecha, fechaInicio) && esMenorOIgual(fecha, fechaFin));
    }

    public static boolean sonIguales(FechayHora fecha1, FechayHora fecha2) {
        return fecha1.dia == fecha2.dia &&
               fecha1.mes == fecha2.mes &&
               fecha1.año == fecha2.año &&
               fecha1.hora == fecha2.hora &&
               fecha1.minuto == fecha2.minuto;
    }

    private static boolean esMayorOIgual(FechayHora fecha1, FechayHora fecha2) {
        if (fecha1.año != fecha2.año) {
            return fecha1.año > fecha2.año;
        }
        if (fecha1.mes != fecha2.mes) {
            return fecha1.mes > fecha2.mes;
        }
        if (fecha1.dia != fecha2.dia) {
            return fecha1.dia > fecha2.dia;
        }
        if (fecha1.hora != fecha2.hora) {
            return fecha1.hora > fecha2.hora;
        }
        if (fecha1.minuto != fecha2.minuto) {
            return fecha1.minuto > fecha2.minuto;
        }
        return true;
    }

    private static boolean esMenorOIgual(FechayHora fecha1, FechayHora fecha2) {
        if (fecha1.año != fecha2.año) {
            return fecha1.año < fecha2.año;
        }
        if (fecha1.mes != fecha2.mes) {
            return fecha1.mes < fecha2.mes;
        }
        if (fecha1.dia != fecha2.dia) {
            return fecha1.dia < fecha2.dia;
        }
        if (fecha1.hora != fecha2.hora) {
            return fecha1.hora < fecha2.hora;
        }
        if (fecha1.minuto != fecha2.minuto) {
            return fecha1.minuto < fecha2.minuto;
        }
        return true;
        
    }
    public static boolean horaEstaEntre(FechayHora horaAComparar, FechayHora horaInicio, FechayHora horaFin) {
        return (esHoraMayorOIgual(horaAComparar, horaInicio) && esHoraMenorOIgual(horaAComparar, horaFin));
    }
    private static boolean esHoraMayorOIgual(FechayHora hora1, FechayHora hora2) {
        if (hora1.hora != hora2.hora) {
            return hora1.hora > hora2.hora;
        }
        if (hora1.minuto != hora2.minuto) {
            return hora1.minuto > hora2.minuto;
        }
        return true;
    }

    private static boolean esHoraMenorOIgual(FechayHora hora1, FechayHora hora2) {
        if (hora1.hora != hora2.hora) {
            return hora1.hora < hora2.hora;
        }
        if (hora1.minuto != hora2.minuto) {
            return hora1.minuto < hora2.minuto;
        }
        return true;
    }
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d %02d:%02d", dia, mes, año, hora, minuto);
    }
}
