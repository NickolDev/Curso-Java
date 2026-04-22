package DataHora;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DataHora {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final ZoneId SYSTEM_ZONE = ZoneId.systemDefault();
    private static final ZoneId LISBON_ZONE = ZoneId.of("Europe/Lisbon");
    private static final DateTimeFormatter INSTANT_LOCAL_FORMATTER =
            DATE_TIME_FORMATTER.withZone(SYSTEM_ZONE);

    public static void main(String[] args) {
        StudyData studyData = createStudyData();
        CalculationData calculationData = createCalculationData(studyData);
        DurationData durationData = createDurationData(studyData, calculationData);

        printIntroduction();
        printOriginalValues(studyData);
        printFormattingExamples(studyData);
        printTimeZoneConversions(studyData);
        printDateTimeComponents(studyData);
        printCalculations(calculationData);
        printDurations(durationData);
        printSummary();
    }

    private static void printIntroduction() {
        System.out.println("=== ESTUDO DE DATA E HORA EM JAVA ===");
        System.out.println("LocalDate -> somente data");
        System.out.println("LocalDateTime -> data e hora, sem fuso");
        System.out.println("Instant -> momento exato global em UTC");
        System.out.println("ZoneId -> representa um fuso horario");
        System.out.println("DateTimeFormatter -> formata e le datas em texto");
        System.out.println();
        System.out.println("As classes de java.time sao imutaveis.");
        System.out.println("Metodos como plusDays() e minusDays() retornam novos objetos.");
    }

    private static StudyData createStudyData() {
        LocalDate hoje = LocalDate.now();
        LocalDateTime agora = LocalDateTime.now();
        Instant agoraUtc = Instant.now();

        LocalDate dataIso = LocalDate.parse("2022-07-20");
        LocalDateTime dataHoraIso = LocalDateTime.parse("2022-07-20T01:30:26");
        Instant instanteIso = Instant.parse("2022-07-20T01:30:26Z");
        Instant instanteComOffset = Instant.parse("2022-07-20T01:30:26-03:00");

        LocalDate dataCustom = LocalDate.parse("20/07/2022", DATE_FORMATTER);
        LocalDateTime dataHoraCustom = LocalDateTime.parse("20/07/2022 01:30", DATE_TIME_FORMATTER);

        LocalDate dataManual = LocalDate.of(2022, 7, 20);
        LocalDateTime dataHoraManual = LocalDateTime.of(2022, 7, 20, 1, 30);

        return new StudyData(
                hoje,
                agora,
                agoraUtc,
                dataIso,
                dataHoraIso,
                instanteIso,
                instanteComOffset,
                dataCustom,
                dataHoraCustom,
                dataManual,
                dataHoraManual
        );
    }

    private static CalculationData createCalculationData(StudyData studyData) {
        LocalDate semanaPassada = studyData.dataIso.minusDays(7);
        LocalDate proximaSemana = studyData.dataIso.plusDays(7);

        LocalDateTime semanaPassadaComHora = studyData.dataHoraIso.minusDays(7);
        LocalDateTime proximaSemanaComHora = studyData.dataHoraIso.plusDays(7);

        Instant semanaPassadaUtc = studyData.instanteIso.minus(7, ChronoUnit.DAYS);
        Instant proximaSemanaUtc = studyData.instanteIso.plus(7, ChronoUnit.DAYS);

        return new CalculationData(
                semanaPassada,
                proximaSemana,
                semanaPassadaComHora,
                proximaSemanaComHora,
                semanaPassadaUtc,
                proximaSemanaUtc
        );
    }

    private static DurationData createDurationData(StudyData studyData, CalculationData calculationData) {
        Duration diasEntreDatas = Duration.between(
                calculationData.semanaPassada.atStartOfDay(),
                studyData.dataIso.atStartOfDay()
        );

        Duration diasEntreDataHora = Duration.between(
                calculationData.semanaPassadaComHora,
                studyData.dataHoraIso
        );

        Duration diasEntreInstantes = Duration.between(
                calculationData.semanaPassadaUtc,
                studyData.instanteIso
        );

        Duration duracaoNegativa = Duration.between(
                studyData.instanteIso,
                calculationData.semanaPassadaUtc
        );

        return new DurationData(
                diasEntreDatas,
                diasEntreDataHora,
                diasEntreInstantes,
                duracaoNegativa
        );
    }

    private static void printOriginalValues(StudyData studyData) {
        printSection("VALORES ORIGINAIS");
        System.out.println("hoje = " + studyData.hoje);
        System.out.println("agora = " + studyData.agora);
        System.out.println("agoraUtc = " + studyData.agoraUtc);
        System.out.println("dataIso = " + studyData.dataIso);
        System.out.println("dataHoraIso = " + studyData.dataHoraIso);
        System.out.println("instanteIso = " + studyData.instanteIso);
        System.out.println("instanteComOffset = " + studyData.instanteComOffset);
        System.out.println("dataCustom = " + studyData.dataCustom);
        System.out.println("dataHoraCustom = " + studyData.dataHoraCustom);
        System.out.println("dataManual = " + studyData.dataManual);
        System.out.println("dataHoraManual = " + studyData.dataHoraManual);
        System.out.println();
        System.out.println("LocalDateTime tem data e hora, mas nao conhece fuso.");
        System.out.println("Instant representa um instante absoluto em UTC.");
    }

    private static void printFormattingExamples(StudyData studyData) {
        printSection("FORMATACAO");
        System.out.println("dataIso com DATE_FORMATTER = " + studyData.dataIso.format(DATE_FORMATTER));
        System.out.println("dataHoraIso com DATE_FORMATTER = " + studyData.dataHoraIso.format(DATE_FORMATTER));
        System.out.println("dataHoraIso com DATE_TIME_FORMATTER = " + studyData.dataHoraIso.format(DATE_TIME_FORMATTER));
        System.out.println("instanteIso no fuso local = " + INSTANT_LOCAL_FORMATTER.format(studyData.instanteIso));
        System.out.println("instanteIso em ISO_INSTANT = " + DateTimeFormatter.ISO_INSTANT.format(studyData.instanteIso));
    }

    private static void printTimeZoneConversions(StudyData studyData) {
        LocalDate dataSistema = LocalDate.ofInstant(studyData.instanteIso, SYSTEM_ZONE);
        LocalDate dataPortugal = LocalDate.ofInstant(studyData.instanteIso, LISBON_ZONE);

        LocalDateTime dataHoraSistema = LocalDateTime.ofInstant(studyData.instanteIso, SYSTEM_ZONE);
        LocalDateTime dataHoraPortugal = LocalDateTime.ofInstant(studyData.instanteIso, LISBON_ZONE);

        printSection("CONVERSAO DE FUSO");
        System.out.println("dataSistema = " + dataSistema);
        System.out.println("dataPortugal = " + dataPortugal);
        System.out.println("dataHoraSistema = " + dataHoraSistema);
        System.out.println("dataHoraPortugal = " + dataHoraPortugal);
        System.out.println();
        System.out.println("O mesmo Instant muda de exibicao conforme o fuso escolhido.");
    }

    private static void printDateTimeComponents(StudyData studyData) {
        printSection("COMPONENTES");
        System.out.println("Dia da dataIso = " + studyData.dataIso.getDayOfMonth());
        System.out.println("Mes da dataIso = " + studyData.dataIso.getMonthValue());
        System.out.println("Ano da dataIso = " + studyData.dataIso.getYear());
        System.out.println("Hora da dataHoraIso = " + studyData.dataHoraIso.getHour());
        System.out.println("Minuto da dataHoraIso = " + studyData.dataHoraIso.getMinute());
    }

    private static void printCalculations(CalculationData calculationData) {
        printSection("CALCULOS");
        System.out.println("semanaPassada = " + calculationData.semanaPassada);
        System.out.println("proximaSemana = " + calculationData.proximaSemana);
        System.out.println("semanaPassadaComHora = " + calculationData.semanaPassadaComHora);
        System.out.println("proximaSemanaComHora = " + calculationData.proximaSemanaComHora);
        System.out.println("semanaPassadaUtc = " + calculationData.semanaPassadaUtc);
        System.out.println("proximaSemanaUtc = " + calculationData.proximaSemanaUtc);
    }

    private static void printDurations(DurationData durationData) {
        printSection("DURACAO");
        System.out.println("diasEntreDatas = " + durationData.diasEntreDatas.toDays());
        System.out.println("diasEntreDataHora = " + durationData.diasEntreDataHora.toDays());
        System.out.println("diasEntreInstantes = " + durationData.diasEntreInstantes.toDays());
        System.out.println("duracaoNegativa = " + durationData.duracaoNegativa.toDays());
        System.out.println();
        System.out.println("Para LocalDate, atStartOfDay() ajuda a converter para LocalDateTime.");
        System.out.println("Se a ordem dos parametros em Duration.between() for invertida, o resultado pode ser negativo.");
    }

    private static void printSummary() {
        printSection("RESUMO FINAL");
        System.out.println("LocalDate -> somente data");
        System.out.println("LocalDateTime -> data e hora sem fuso");
        System.out.println("Instant -> momento exato em UTC");
        System.out.println("ZoneId -> fuso para conversao de Instant");
        System.out.println("DateTimeFormatter -> leitura e formatacao de datas");
        System.out.println("plusDays / minusDays -> calculos de tempo");
        System.out.println("Duration -> duracao entre dois momentos");
        System.out.println("Todos os objetos de java.time usados aqui sao imutaveis.");
    }

    private static void printSection(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    private static final class StudyData {
        private final LocalDate hoje;
        private final LocalDateTime agora;
        private final Instant agoraUtc;
        private final LocalDate dataIso;
        private final LocalDateTime dataHoraIso;
        private final Instant instanteIso;
        private final Instant instanteComOffset;
        private final LocalDate dataCustom;
        private final LocalDateTime dataHoraCustom;
        private final LocalDate dataManual;
        private final LocalDateTime dataHoraManual;

        private StudyData(
                LocalDate hoje,
                LocalDateTime agora,
                Instant agoraUtc,
                LocalDate dataIso,
                LocalDateTime dataHoraIso,
                Instant instanteIso,
                Instant instanteComOffset,
                LocalDate dataCustom,
                LocalDateTime dataHoraCustom,
                LocalDate dataManual,
                LocalDateTime dataHoraManual
        ) {
            this.hoje = hoje;
            this.agora = agora;
            this.agoraUtc = agoraUtc;
            this.dataIso = dataIso;
            this.dataHoraIso = dataHoraIso;
            this.instanteIso = instanteIso;
            this.instanteComOffset = instanteComOffset;
            this.dataCustom = dataCustom;
            this.dataHoraCustom = dataHoraCustom;
            this.dataManual = dataManual;
            this.dataHoraManual = dataHoraManual;
        }
    }

    private static final class CalculationData {
        private final LocalDate semanaPassada;
        private final LocalDate proximaSemana;
        private final LocalDateTime semanaPassadaComHora;
        private final LocalDateTime proximaSemanaComHora;
        private final Instant semanaPassadaUtc;
        private final Instant proximaSemanaUtc;

        private CalculationData(
                LocalDate semanaPassada,
                LocalDate proximaSemana,
                LocalDateTime semanaPassadaComHora,
                LocalDateTime proximaSemanaComHora,
                Instant semanaPassadaUtc,
                Instant proximaSemanaUtc
        ) {
            this.semanaPassada = semanaPassada;
            this.proximaSemana = proximaSemana;
            this.semanaPassadaComHora = semanaPassadaComHora;
            this.proximaSemanaComHora = proximaSemanaComHora;
            this.semanaPassadaUtc = semanaPassadaUtc;
            this.proximaSemanaUtc = proximaSemanaUtc;
        }
    }

    private static final class DurationData {
        private final Duration diasEntreDatas;
        private final Duration diasEntreDataHora;
        private final Duration diasEntreInstantes;
        private final Duration duracaoNegativa;

        private DurationData(
                Duration diasEntreDatas,
                Duration diasEntreDataHora,
                Duration diasEntreInstantes,
                Duration duracaoNegativa
        ) {
            this.diasEntreDatas = diasEntreDatas;
            this.diasEntreDataHora = diasEntreDataHora;
            this.diasEntreInstantes = diasEntreInstantes;
            this.duracaoNegativa = duracaoNegativa;
        }
    }
}
