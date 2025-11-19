package com.example.webapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SeasonService 테스트")
class SeasonServiceTest {

    private SeasonService seasonService;

    @BeforeEach
    void setUp() {
        seasonService = new SeasonService();
    }

    @Test
    @DisplayName("현재 계절을 반환해야 한다")
    void shouldReturnCurrentSeason() {
        SeasonService.Season currentSeason = seasonService.getCurrentSeason();
        assertThat(currentSeason).isNotNull();
        assertThat(currentSeason).isIn(
                SeasonService.Season.SPRING,
                SeasonService.Season.SUMMER,
                SeasonService.Season.AUTUMN,
                SeasonService.Season.WINTER
        );
    }

    @Test
    @DisplayName("현재 계절의 한글 이름을 반환해야 한다")
    void shouldReturnCurrentSeasonKoreanName() {
        String koreanName = seasonService.getCurrentSeasonKoreanName();
        assertThat(koreanName).isNotNull();
        assertThat(koreanName).isIn("봄", "여름", "가을", "겨울");
    }

    @Test
    @DisplayName("현재 계절의 영문 이름을 반환해야 한다")
    void shouldReturnCurrentSeasonEnglishName() {
        String englishName = seasonService.getCurrentSeasonEnglishName();
        assertThat(englishName).isNotNull();
        assertThat(englishName).isIn("spring", "summer", "autumn", "winter");
    }

    @ParameterizedTest
    @CsvSource({
            "3, SPRING, 봄, spring",
            "4, SPRING, 봄, spring",
            "5, SPRING, 봄, spring",
    })
    @DisplayName("봄(3월, 4월, 5월)을 올바르게 감지해야 한다")
    void shouldDetectSpring(int month, String expectedSeasonName, String expectedKorean, String expectedEnglish) {
        LocalDate date = LocalDate.of(2024, month, 15);
        SeasonService.Season season = seasonService.getSeasonByDate(date);

        assertThat(season).isEqualTo(SeasonService.Season.valueOf(expectedSeasonName));
        assertThat(season.getKoreanName()).isEqualTo(expectedKorean);
        assertThat(season.getEnglishName()).isEqualTo(expectedEnglish);
    }

    @ParameterizedTest
    @CsvSource({
            "6, SUMMER, 여름, summer",
            "7, SUMMER, 여름, summer",
            "8, SUMMER, 여름, summer",
    })
    @DisplayName("여름(6월, 7월, 8월)을 올바르게 감지해야 한다")
    void shouldDetectSummer(int month, String expectedSeasonName, String expectedKorean, String expectedEnglish) {
        LocalDate date = LocalDate.of(2024, month, 15);
        SeasonService.Season season = seasonService.getSeasonByDate(date);

        assertThat(season).isEqualTo(SeasonService.Season.valueOf(expectedSeasonName));
        assertThat(season.getKoreanName()).isEqualTo(expectedKorean);
        assertThat(season.getEnglishName()).isEqualTo(expectedEnglish);
    }

    @ParameterizedTest
    @CsvSource({
            "9, AUTUMN, 가을, autumn",
            "10, AUTUMN, 가을, autumn",
            "11, AUTUMN, 가을, autumn",
    })
    @DisplayName("가을(9월, 10월, 11월)을 올바르게 감지해야 한다")
    void shouldDetectAutumn(int month, String expectedSeasonName, String expectedKorean, String expectedEnglish) {
        LocalDate date = LocalDate.of(2024, month, 15);
        SeasonService.Season season = seasonService.getSeasonByDate(date);

        assertThat(season).isEqualTo(SeasonService.Season.valueOf(expectedSeasonName));
        assertThat(season.getKoreanName()).isEqualTo(expectedKorean);
        assertThat(season.getEnglishName()).isEqualTo(expectedEnglish);
    }

    @ParameterizedTest
    @CsvSource({
            "12, WINTER, 겨울, winter",
            "1, WINTER, 겨울, winter",
            "2, WINTER, 겨울, winter",
    })
    @DisplayName("겨울(12월, 1월, 2월)을 올바르게 감지해야 한다")
    void shouldDetectWinter(int month, String expectedSeasonName, String expectedKorean, String expectedEnglish) {
        LocalDate date = LocalDate.of(2024, month, 15);
        SeasonService.Season season = seasonService.getSeasonByDate(date);

        assertThat(season).isEqualTo(SeasonService.Season.valueOf(expectedSeasonName));
        assertThat(season.getKoreanName()).isEqualTo(expectedKorean);
        assertThat(season.getEnglishName()).isEqualTo(expectedEnglish);
    }

    @Test
    @DisplayName("월의 첫날도 올바른 계절을 반환해야 한다")
    void shouldDetectSeasonOnFirstDayOfMonth() {
        LocalDate firstDayOfMarch = LocalDate.of(2024, Month.MARCH, 1);
        SeasonService.Season season = seasonService.getSeasonByDate(firstDayOfMarch);
        assertThat(season).isEqualTo(SeasonService.Season.SPRING);
    }

    @Test
    @DisplayName("월의 마지막 날도 올바른 계절을 반환해야 한다")
    void shouldDetectSeasonOnLastDayOfMonth() {
        LocalDate lastDayOfMay = LocalDate.of(2024, Month.MAY, 31);
        SeasonService.Season season = seasonService.getSeasonByDate(lastDayOfMay);
        assertThat(season).isEqualTo(SeasonService.Season.SPRING);
    }

    @Test
    @DisplayName("윤년의 2월 29일도 올바르게 처리해야 한다")
    void shouldHandleLeapYear() {
        LocalDate leapDay = LocalDate.of(2024, Month.FEBRUARY, 29);
        SeasonService.Season season = seasonService.getSeasonByDate(leapDay);
        assertThat(season).isEqualTo(SeasonService.Season.WINTER);
    }

    @Test
    @DisplayName("연도가 바뀌어도 계절 감지가 정확해야 한다")
    void shouldDetectSeasonAcrossYears() {
        // 2023년 겨울
        LocalDate december2023 = LocalDate.of(2023, Month.DECEMBER, 25);
        assertThat(seasonService.getSeasonByDate(december2023))
                .isEqualTo(SeasonService.Season.WINTER);

        // 2024년 겨울
        LocalDate january2024 = LocalDate.of(2024, Month.JANUARY, 15);
        assertThat(seasonService.getSeasonByDate(january2024))
                .isEqualTo(SeasonService.Season.WINTER);

        // 2024년 봄
        LocalDate march2024 = LocalDate.of(2024, Month.MARCH, 1);
        assertThat(seasonService.getSeasonByDate(march2024))
                .isEqualTo(SeasonService.Season.SPRING);
    }
}
