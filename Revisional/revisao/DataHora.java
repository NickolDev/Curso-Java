// Importa a classe Duration.
// Ela serve para calcular duração entre dois instantes de tempo.
// Exemplo: quantidade de dias, horas, minutos entre dois momentos.
import java.time.Duration;

// Importa Instant.
// Instant representa um momento exato no tempo, em UTC.
// Normalmente usado quando queremos um horário "universal".
import java.time.Instant;

// Importa LocalDate.
// LocalDate representa somente data: dia, mês e ano.
// Não possui hora.
import java.time.LocalDate;

// Importa LocalDateTime.
// LocalDateTime representa data + hora.
// Não possui fuso horário.
import java.time.LocalDateTime;

// Importa ZoneId.
// ZoneId representa um fuso horário/região, como "Portugal"
// ou o fuso padrão do sistema.
import java.time.ZoneId;

// Importa DateTimeFormatter.
// Ele permite formatar datas em textos personalizados
// e também converter textos para datas.
import java.time.format.DateTimeFormatter;

// Importa ChronoUnit.
// Ele fornece unidades de tempo, como DAYS, HOURS, MINUTES,
// úteis para somar ou subtrair tempo.
import java.time.temporal.ChronoUnit;

/*
 * Classe principal de estudo sobre datas e horas em Java.
 *
 * Aqui estão reunidos os principais exemplos:
 * - Data atual
 * - Hora atual
 * - Conversão de texto para data
 * - Formatação de data para texto
 * - Criação manual de datas
 * - Conversão de Instant para data local
 * - Acesso a partes da data/hora
 * - Cálculos com datas
 * - Duração entre datas
 */
public class DataHora {

    public static void main(String[] args) {

        // =========================================================
        // 1) FORMATADORES
        // =========================================================

        // Cria um formatador no padrão dia/mês/ano.
        // Exemplo de saída: 20/07/2022
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Cria um formatador com data + hora.
        // Exemplo de saída: 20/07/2022 01:30
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Cria um formatador com data + hora, mas aplicando o fuso do sistema.
        // Isso é útil principalmente quando trabalhamos com Instant,
        // porque Instant está em UTC e precisa de um fuso para ser exibido
        // como data e hora local.
        DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                .withZone(ZoneId.systemDefault());

        // Formatador padrão ISO para data e hora.
        // Exemplo: 2022-07-20T01:30:26
        DateTimeFormatter fmt4 = DateTimeFormatter.ISO_DATE_TIME;

        // Formatador padrão ISO para Instant.
        // Exemplo: 2022-07-20T01:30:26Z
        DateTimeFormatter fmt5 = DateTimeFormatter.ISO_INSTANT;


        // =========================================================
        // 2) DATA E HORA ATUAL
        // =========================================================

        // Pega a data atual do sistema.
        // Exemplo: 2026-04-16
        LocalDate d01 = LocalDate.now();

        // Pega data e hora atual do sistema.
        // Exemplo: 2026-04-16T14:35:20.123
        LocalDateTime d02 = LocalDateTime.now();

        // Pega o instante atual em UTC.
        // Exemplo: 2026-04-16T17:35:20.123Z
        Instant d03 = Instant.now();


        // =========================================================
        // 3) CONVERSÃO DE TEXTO ISO 8601
        // =========================================================

        // Converte uma String no padrão ISO para LocalDate.
        // ISO para data: yyyy-MM-dd
        LocalDate d04 = LocalDate.parse("2022-07-20");

        // Converte uma String no padrão ISO para LocalDateTime.
        // ISO para data e hora: yyyy-MM-ddTHH:mm:ss
        LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:26");

        // Converte uma String ISO com "Z" para Instant.
        // O "Z" significa UTC.
        Instant d06 = Instant.parse("2022-07-20T01:30:26Z");

        // Também converte uma String com deslocamento de fuso (-03:00) para Instant.
        // Mesmo que esteja com -03:00, o Instant guarda o momento universal equivalente.
        Instant d07 = Instant.parse("2022-07-20T01:30:26-03:00");


        // =========================================================
        // 4) CONVERSÃO DE TEXTO COM FORMATO PERSONALIZADO
        // =========================================================

        // Aqui a String NÃO está no padrão ISO.
        // Então precisamos informar o formatador fmt1.
        LocalDate d08 = LocalDate.parse("20/07/2022", fmt1);

        // Aqui também usamos o formatador fmt2 porque o texto tem data + hora.
        LocalDateTime d09 = LocalDateTime.parse("20/07/2022 01:30", fmt2);


        // =========================================================
        // 5) CRIAÇÃO MANUAL DE DATA E HORA
        // =========================================================

        // Cria uma data manualmente passando ano, mês e dia.
        LocalDate d10 = LocalDate.of(2022, 7, 20);

        // Cria data e hora manualmente passando:
        // ano, mês, dia, hora e minuto.
        LocalDateTime d11 = LocalDateTime.of(2022, 7, 20, 1, 30);


        // =========================================================
        // 6) EXIBIÇÃO DOS VALORES ORIGINAIS
        // =========================================================

        System.out.println("===== VALORES ORIGINAIS =====");

        // O toString() é chamado automaticamente na concatenação com String,
        // então escrever só d01 já funciona.
        System.out.println("d01 = " + d01); // data atual
        System.out.println("d02 = " + d02); // data e hora atual
        System.out.println("d03 = " + d03); // instante atual UTC

        System.out.println("d04 = " + d04); // LocalDate a partir de texto ISO
        System.out.println("d05 = " + d05); // LocalDateTime a partir de texto ISO
        System.out.println("d06 = " + d06); // Instant com Z
        System.out.println("d07 = " + d07); // Instant com -03:00 convertido para UTC

        System.out.println("d08 = " + d08); // LocalDate a partir de texto customizado
        System.out.println("d09 = " + d09); // LocalDateTime a partir de texto customizado
        System.out.println("d10 = " + d10); // LocalDate criado manualmente
        System.out.println("d11 = " + d11); // LocalDateTime criado manualmente


        // =========================================================
        // 7) FORMATAÇÃO DE DATA/HORA PARA TEXTO
        // =========================================================

        System.out.println("\n===== FORMATAÇÃO =====");

        // Formata d04 usando fmt1.
        // d04 é um LocalDate.
        System.out.println("d04 formatado com fmt1 = " + d04.format(fmt1));

        // Outra forma de fazer a mesma coisa:
        // o formatador formatando a data.
        System.out.println("d04 formatado direto = " + fmt1.format(d04));

        // Também é possível criar o formatador direto no comando.
        System.out.println("d04 formatado inline = " + d04.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // d05 é LocalDateTime.
        // Pode ser formatado só com data...
        System.out.println("d05 com fmt1 = " + d05.format(fmt1));

        // ...ou com data e hora.
        System.out.println("d05 com fmt2 = " + d05.format(fmt2));

        // Formatação padrão ISO.
        System.out.println("d05 com ISO_DATE_TIME = " + d05.format(fmt4));

        // d06 é Instant.
        // Para mostrar num padrão local, usamos fmt3 com fuso.
        System.out.println("d06 com fmt3 = " + fmt3.format(d06));

        // Formatação padrão própria de Instant.
        System.out.println("d06 com ISO_INSTANT = " + fmt5.format(d06));

        // toString() de Instant também imprime em formato ISO.
        System.out.println("d06 toString = " + d06);


        // =========================================================
        // 8) CONVERSÃO DE INSTANT PARA DATA/HORA LOCAL
        // =========================================================

        /*
         * Instant representa um momento global.
         * Para transformá-lo em data ou data/hora local,
         * precisamos informar um fuso horário.
         */

        // Converte o Instant d06 para LocalDate no fuso do sistema.
        LocalDate r1 = LocalDate.ofInstant(d06, ZoneId.systemDefault());

        // Converte o mesmo Instant para LocalDate no fuso de Portugal.
        LocalDate r2 = LocalDate.ofInstant(d06, ZoneId.of("Portugal"));

        // Converte o Instant para LocalDateTime no fuso do sistema.
        LocalDateTime r3 = LocalDateTime.ofInstant(d06, ZoneId.systemDefault());

        // Converte o Instant para LocalDateTime no fuso de Portugal.
        LocalDateTime r4 = LocalDateTime.ofInstant(d06, ZoneId.of("Portugal"));

        System.out.println("\n===== CONVERSÃO DE INSTANT PARA DATA/HORA LOCAL =====");
        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);
        System.out.println("r3 = " + r3);
        System.out.println("r4 = " + r4);

        /*
         * Observação importante:
         * O mesmo Instant pode gerar horários locais diferentes
         * dependendo do fuso horário usado.
         */


        // =========================================================
        // 9) ACESSANDO PARTES DA DATA E HORA
        // =========================================================

        System.out.println("\n===== COMPONENTES DA DATA/HORA =====");

        // Obtém o dia do mês de d04.
        System.out.println("d04 dia = " + d04.getDayOfMonth());

        // Obtém o número do mês.
        System.out.println("d04 mês = " + d04.getMonthValue());

        // Obtém o ano.
        System.out.println("d04 ano = " + d04.getYear());

        // Obtém a hora de d05.
        System.out.println("d05 hora = " + d05.getHour());

        // Obtém o minuto de d05.
        System.out.println("d05 minuto = " + d05.getMinute());


        // =========================================================
        // 10) CÁLCULOS COM DATA E HORA
        // =========================================================

        /*
         * Podemos somar ou subtrair dias, meses, anos, etc.
         * Os objetos são imutáveis.
         * Isso significa que o metodo NÃO altera o original;
         * ele retorna um novo objeto.
         */

        // Subtrai 7 dias de d04.
        LocalDate pastWeekDate = d04.minusDays(7);

        // Soma 7 dias em d04.
        LocalDate nextWeekDate = d04.plusDays(7);

        // Subtrai 7 dias de d05.
        LocalDateTime pastWeekLocalDate = d05.minusDays(7);

        // Soma 7 dias em d05.
        LocalDateTime nextWeekLocalDate = d05.plusDays(7);

        // Para Instant, podemos usar ChronoUnit.DAYS.
        Instant pastWeekInstant = d06.minus(7, ChronoUnit.DAYS);
        Instant nextWeekInstant = d06.plus(7, ChronoUnit.DAYS);

        System.out.println("\n===== CÁLCULOS COM DATA/HORA =====");
        System.out.println("pastWeekDate = " + pastWeekDate);
        System.out.println("nextWeekDate = " + nextWeekDate);

        System.out.println("pastWeekLocalDate = " + pastWeekLocalDate);
        System.out.println("nextWeekLocalDate = " + nextWeekLocalDate);

        System.out.println("pastWeekInstant = " + pastWeekInstant);
        System.out.println("nextWeekInstant = " + nextWeekInstant);


        // =========================================================
        // 11) DURAÇÃO ENTRE DATAS
        // =========================================================

        /*
         * Duration calcula intervalo entre dois momentos.
         *
         * Atenção:
         * LocalDate não possui hora.
         * Então, para calcular Duration entre LocalDate,
         * transformamos a data em início do dia com atStartOfDay().
         */

        // Calcula a duração entre pastWeekDate às 00:00 e d04 às 00:00.
        Duration t1 = Duration.between(pastWeekDate.atStartOfDay(), d04.atStartOfDay());

        // Calcula a duração entre duas datas com hora.
        Duration t2 = Duration.between(pastWeekLocalDate, d05);

        // Calcula a duração entre dois Instants.
        Duration t3 = Duration.between(pastWeekInstant, d06);

        // Aqui a ordem foi invertida.
        // Como d06 está depois de pastWeekInstant,
        // o resultado será negativo.
        Duration t4 = Duration.between(d06, pastWeekInstant);

        System.out.println("\n===== DURAÇÃO =====");
        System.out.println("t1 dias = " + t1.toDays());
        System.out.println("t2 dias = " + t2.toDays());
        System.out.println("t3 dias = " + t3.toDays());
        System.out.println("t4 dias = " + t4.toDays());


        // =========================================================
        // 12) RESUMO RÁPIDO
        // =========================================================

        /*
         * LocalDate
         * -> guarda apenas data
         *
         * LocalDateTime
         * -> guarda data e hora
         *
         * Instant
         * -> guarda um momento exato em UTC
         *
         * DateTimeFormatter
         * -> formata e interpreta datas
         *
         * ZoneId
         * -> representa fuso horário
         *
         * Duration
         * -> calcula intervalo entre momentos
         *
         * plusDays / minusDays
         * -> soma ou subtrai dias
         *
         * parse
         * -> converte texto para data/hora
         *
         * format
         * -> converte data/hora para texto
         */
    }
}