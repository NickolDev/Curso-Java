package DataHora;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DataHora {

    public static void main(String[] args) {

        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html

        /*
         * =========================================================
         * ESTUDO DE DATA E HORA EM JAVA
         * =========================================================
         *
         * IDEIA PRINCIPAL:
         *
         * LocalDate
         * -> guarda apenas a data (dia, mês, ano)
         *
         * LocalDateTime
         * -> guarda data e hora, mas NÃO guarda fuso horário
         *
         * Instant
         * -> representa um momento exato global em UTC
         *
         * ZoneId
         * -> representa um fuso horário/região
         *
         * DateTimeFormatter
         * -> formata datas para texto e lê texto para data
         *
         * IMPORTANTE:
         * As classes de data/hora do java.time são imutáveis.
         * Isso significa que métodos como plusDays() e minusDays()
         * NÃO alteram o objeto original.
         * Eles retornam um novo objeto.
         */

        // =========================================================
        // 1) FORMATADORES
        // =========================================================

        // Formato de data no padrão dia/mês/ano
        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Formato de data e hora no padrão
        DateTimeFormatter fmtDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        /*
         * Este formatter será usado para exibir Instant em horário local.
         *
         * Por que usar withZone(...)?
         * Porque Instant representa um momento em UTC.
         * Para mostrar esse momento como data/hora local, o Java precisa saber
         * em qual fuso horário deve fazer a conversão.
         *
         * Sem withZone(...), o formatter não teria a referência de fuso
         * necessária para exibir o Instant como horário local.
         */
        DateTimeFormatter fmtInstantLocal = DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm")
                .withZone(ZoneId.systemDefault());


        // =========================================================
        // 2) OBTENDO DATA E HORA ATUAIS
        // =========================================================

        // Data atual do sistema
        LocalDate hoje = LocalDate.now();

        // Data e hora atuais do sistema
        LocalDateTime agora = LocalDateTime.now();

        // Momento atual em UTC
        Instant agoraUtc = Instant.now();


        // =========================================================
        // 3) CONVERTENDO TEXTOS NO FORMATO ISO
        // =========================================================

        /*
         * ISO 8601 é o padrão internacional usado com frequência pelo Java.
         *
         * Exemplos:
         * 2022-07-20
         * 2022-07-20T01:30:26
         * 2022-07-20T01:30:26Z
         */

        // Texto ISO convertido para LocalDate
        LocalDate dataIso = LocalDate.parse("2022-07-20");

        // Texto ISO convertido para LocalDateTime
        LocalDateTime dataHoraIso = LocalDateTime.parse("2022-07-20T01:30:26");

        /*
         * "Z" significa UTC (tempo universal coordenado).
         * Portanto, este valor já está explicitamente em UTC.
         */
        Instant instanteIso = Instant.parse("2022-07-20T01:30:26Z");

        /*
         * Aqui temos um offset de fuso: -03:00
         *
         * Isso quer dizer que o horário informado está 3 horas atrás do UTC.
         * O Java converte esse valor para um Instant equivalente em UTC.
         *
         * Em outras palavras:
         * mesmo que o texto tenha -03:00, o Instant sempre representa
         * um momento universal exato.
         */
        Instant instanteComOffset = Instant.parse("2022-07-20T01:30:26-03:00");


        // =========================================================
        // 4) CONVERTENDO TEXTOS COM FORMATO PERSONALIZADO
        // =========================================================

        /*
         * Como os textos abaixo NÃO estão no formato ISO,
         * precisamos informar explicitamente o formatter.
         */

        LocalDate dataCustom = LocalDate.parse("20/07/2022", fmtData);
        LocalDateTime dataHoraCustom = LocalDateTime.parse("20/07/2022 01:30", fmtDataHora);


        // =========================================================
        // 5) CRIANDO DATAS MANUALMENTE
        // =========================================================

        // Cria uma data informando ano, mês e dia
        LocalDate dataManual = LocalDate.of(2022, 7, 20);

        // Cria uma data e hora informando ano, mês, dia, hora e minuto
        LocalDateTime dataHoraManual = LocalDateTime.of(2022, 7, 20, 1, 30);


        // =========================================================
        // 6) EXIBINDO OS VALORES ORIGINAIS
        // =========================================================

        System.out.println("=== VALORES ORIGINAIS ===");
        System.out.println("hoje = " + hoje);
        System.out.println("agora = " + agora);
        System.out.println("agoraUtc = " + agoraUtc);
        System.out.println("dataIso = " + dataIso);
        System.out.println("dataHoraIso = " + dataHoraIso);
        System.out.println("instanteIso = " + instanteIso);
        System.out.println("instanteComOffset = " + instanteComOffset);
        System.out.println("dataCustom = " + dataCustom);
        System.out.println("dataHoraCustom = " + dataHoraCustom);
        System.out.println("dataManual = " + dataManual);
        System.out.println("dataHoraManual = " + dataHoraManual);

        /*
         * OBSERVAÇÃO IMPORTANTE:
         *
         * LocalDateTime e Instant NÃO são a mesma coisa.
         *
         * LocalDateTime:
         * - tem data e hora
         * - não sabe nada sobre fuso horário
         *
         * Instant:
         * - representa um instante real, absoluto, em UTC
         * - é ideal quando queremos um momento exato global
         */


        // =========================================================
        // 7) FORMATANDO PARA EXIBIÇÃO
        // =========================================================

        System.out.println("\n=== FORMATACAO ===");
        System.out.println("dataIso com fmtData = " + dataIso.format(fmtData));
        System.out.println("dataHoraIso com fmtData = " + dataHoraIso.format(fmtData));
        System.out.println("dataHoraIso com fmtDataHora = " + dataHoraIso.format(fmtDataHora));

        /*
         * Aqui o Instant é mostrado no fuso local do sistema.
         * O mesmo Instant pode ser exibido com horários diferentes
         * dependendo do fuso utilizado.
         */
        System.out.println("instanteIso no fuso local = " + fmtInstantLocal.format(instanteIso));

        // Exibição padrão ISO do Instant (sempre em UTC)
        System.out.println("instanteIso em ISO_INSTANT = " + DateTimeFormatter.ISO_INSTANT.format(instanteIso));


        // =========================================================
        // 8) CONVERTENDO INSTANT PARA DATA/HORA LOCAL
        // =========================================================

        /*
         * Instant representa um momento global.
         * Para transformá-lo em data ou horário local,
         * precisamos informar um ZoneId.
         */

        LocalDate dataSistema = LocalDate.ofInstant(instanteIso, ZoneId.systemDefault());
        LocalDate dataPortugal = LocalDate.ofInstant(instanteIso, ZoneId.of("Europe/Lisbon"));

        LocalDateTime dataHoraSistema = LocalDateTime.ofInstant(instanteIso, ZoneId.systemDefault());
        LocalDateTime dataHoraPortugal = LocalDateTime.ofInstant(instanteIso, ZoneId.of("Europe/Lisbon"));

        System.out.println("\n=== CONVERSAO DE FUSO ===");
        System.out.println("dataSistema = " + dataSistema);
        System.out.println("dataPortugal = " + dataPortugal);
        System.out.println("dataHoraSistema = " + dataHoraSistema);
        System.out.println("dataHoraPortugal = " + dataHoraPortugal);

        /*
         * CONCEITO-CHAVE:
         * O mesmo Instant pode gerar horários locais diferentes
         * dependendo do fuso horário escolhido.
         *
         * Isso acontece porque o momento é o mesmo,
         * mas a "forma de enxergar no relógio local" muda.
         */


        // =========================================================
        // 9) ACESSANDO PARTES DA DATA E DA HORA
        // =========================================================

        System.out.println("\n=== COMPONENTES ===");
        System.out.println("Dia da dataIso = " + dataIso.getDayOfMonth());
        System.out.println("Mes da dataIso = " + dataIso.getMonthValue());
        System.out.println("Ano da dataIso = " + dataIso.getYear());
        System.out.println("Hora da dataHoraIso = " + dataHoraIso.getHour());
        System.out.println("Minuto da dataHoraIso = " + dataHoraIso.getMinute());


        // =========================================================
        // 10) FAZENDO CALCULOS COM DATAS E HORAS
        // =========================================================

        /*
         * Repare que nada abaixo altera dataIso, dataHoraIso ou instanteIso.
         * Novos objetos são criados e guardados em novas variáveis.
         * Isso acontece porque as classes são imutáveis.
         */

        LocalDate semanaPassada = dataIso.minusDays(7);
        LocalDate proximaSemana = dataIso.plusDays(7);

        LocalDateTime semanaPassadaComHora = dataHoraIso.minusDays(7);
        LocalDateTime proximaSemanaComHora = dataHoraIso.plusDays(7);

        Instant semanaPassadaUtc = instanteIso.minus(7, ChronoUnit.DAYS);
        Instant proximaSemanaUtc = instanteIso.plus(7, ChronoUnit.DAYS);

        System.out.println("\n=== CALCULOS ===");
        System.out.println("semanaPassada = " + semanaPassada);
        System.out.println("proximaSemana = " + proximaSemana);
        System.out.println("semanaPassadaComHora = " + semanaPassadaComHora);
        System.out.println("proximaSemanaComHora = " + proximaSemanaComHora);
        System.out.println("semanaPassadaUtc = " + semanaPassadaUtc);
        System.out.println("proximaSemanaUtc = " + proximaSemanaUtc);


        // =========================================================
        // 11) CALCULANDO DURACAO ENTRE DATAS
        // =========================================================

        /*
         * Duration mede uma quantidade de tempo entre dois momentos.
         *
         * Para LocalDate, existe um detalhe:
         * LocalDate não possui hora.
         * Então, para calcular Duration, precisamos transformar a data
         * em um LocalDateTime no início do dia, usando atStartOfDay().
         *
         * Exemplo:
         * 2022-07-20 vira 2022-07-20T00:00:00
         *
         * Observação:
         * Para diferença entre datas "puras", também existe Period.
         * Mas aqui estamos usando Duration para manter o estudo focado
         * no cálculo de tempo entre dois momentos.
         */

        Duration diasEntreDatas = Duration.between(
                semanaPassada.atStartOfDay(),
                dataIso.atStartOfDay()
        );

        Duration diasEntreDataHora = Duration.between(
                semanaPassadaComHora,
                dataHoraIso
        );

        Duration diasEntreInstantes = Duration.between(
                semanaPassadaUtc,
                instanteIso
        );

        /*
         * Quando a ordem é invertida, o resultado fica negativo.
         * Aqui estamos calculando do instante atual para o instante anterior.
         */
        Duration duracaoNegativa = Duration.between(
                instanteIso,
                semanaPassadaUtc
        );

        System.out.println("\n=== DURACAO ===");
        System.out.println("diasEntreDatas = " + diasEntreDatas.toDays());
        System.out.println("diasEntreDataHora = " + diasEntreDataHora.toDays());
        System.out.println("diasEntreInstantes = " + diasEntreInstantes.toDays());
        System.out.println("duracaoNegativa = " + duracaoNegativa.toDays());


        // =========================================================
        // 12) RESUMO FINAL
        // =========================================================

        /*
         * RESUMO MENTAL:
         *
         * LocalDate
         * -> somente data
         *
         * LocalDateTime
         * -> data e hora, sem fuso
         *
         * Instant
         * -> momento exato em UTC
         *
         * ZoneId
         * -> fuso usado para converter Instant em horário local
         *
         * DateTimeFormatter
         * -> formata e lê datas/horas em texto
         *
         * plusDays / minusDays
         * -> somam ou subtraem tempo
         *
         * Duration
         * -> mede duração entre dois momentos
         *
         * IMPORTANTE:
         * Os objetos de data/hora em Java são imutáveis.
         */
    }
}