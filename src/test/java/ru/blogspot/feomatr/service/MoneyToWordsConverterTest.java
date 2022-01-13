package ru.blogspot.feomatr.service;

import com.github.moneytostr.MoneyToStr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

class MoneyToWordsConverterTest {

    private MoneyToWordsConverter sut = new MoneyToWordsConverter();

    static Stream<Arguments> getSumAndSumInWords() {
        return Stream.of(
                Arguments.of("123", "Сто двадцать три рубля 00 копеек", "RUR"),
                Arguments.of("123.01", "Сто двадцать три рубля 01 копейка", "RUR"),
                Arguments.of("120.03", "Сто двадцать рублей 03 копейки", "RUR"),
                Arguments.of("123.13", "Сто двадцать три рубля 13 копеек", "RUR"),
                Arguments.of("123.3", "Сто двадцать три рубля 30 копеек", "RUR"),
                Arguments.of("125.33", "Сто двадцать пять рублей 33 копейки", "RUR"),
                Arguments.of("3426563.89", "Три миллиона четыреста двадцать шесть тысяч пятьсот шестьдесят три рубля 89 копеек", "RUR"),
                Arguments.of("123", "Сто двадцать три доллара 00 центов", "USD"),
                Arguments.of("123.01", "Сто двадцать три доллара 01 цент", "USD"),
                Arguments.of("120.03", "Сто двадцать долларов 03 цента", "USD"),
                Arguments.of("123.13", "Сто двадцать три доллара 13 центов", "USD"),
                Arguments.of("123.3", "Сто двадцать три доллара 30 центов", "USD"),
                Arguments.of("125.33", "Сто двадцать пять долларов 33 цента", "USD"),
                Arguments.of("3426563.89", "Три миллиона четыреста двадцать шесть тысяч пятьсот шестьдесят три доллара 89 центов", "USD"),
                Arguments.of("123", "Сто двадцать три евро 00 центов", "EUR"),
                Arguments.of("123.01", "Сто двадцать три евро 01 цент", "EUR"),
                Arguments.of("120.03", "Сто двадцать евро 03 цента", "EUR"),
                Arguments.of("123.13", "Сто двадцать три евро 13 центов", "EUR"),
                Arguments.of("123.3", "Сто двадцать три евро 30 центов", "EUR"),
                Arguments.of("125.33", "Сто двадцать пять евро 33 цента", "EUR"),
                Arguments.of("3426563.89", "Три миллиона четыреста двадцать шесть тысяч пятьсот шестьдесят три евро 89 центов", "EUR"),
                Arguments.of("123", "Сто двадцать три 00", "GBP"),
                Arguments.of("123.01", "Сто двадцать три 01", "GBP"),
                Arguments.of("120.03", "Сто двадцать 03", "GBP"),
                Arguments.of("123.13", "Сто двадцать три 13", "GBP"),
                Arguments.of("123.3", "Сто двадцать три 30", "GBP"),
                Arguments.of("125.33", "Сто двадцать пять 33", "GBP"),
                Arguments.of("3426563.89", "Три миллиона четыреста двадцать шесть тысяч пятьсот шестьдесят три 89", "GBP")
        );
    }

    @ParameterizedTest
    @DisplayName("Проверка суммы прописью")
    @MethodSource("getSumAndSumInWords")
    void testPaymentSumInWords(String sumAsBigDecimal, String sumInWords, String currencyCode) {
        BigDecimal sum = new BigDecimal(sumAsBigDecimal);

        String paymentSumInWords = sut.getPaymentSumInWords(sum, currencyCode);

        Assertions.assertEquals(sumInWords, paymentSumInWords);
    }

    static Stream<Arguments> getSumAndIntegerPartSumInWords() {
        return Stream.of(
                Arguments.of("123", "Сто двадцать три рубля", "RUR"),
                Arguments.of("123.01", "Сто двадцать три рубля", "RUR"),
                Arguments.of("120.03", "Сто двадцать рублей", "RUR"),
                Arguments.of("123.13", "Сто двадцать три рубля", "RUR"),
                Arguments.of("123.3", "Сто двадцать три рубля", "RUR"),
                Arguments.of("125.33", "Сто двадцать пять рублей", "RUR"),
                Arguments.of("3426563.89", "Три миллиона четыреста двадцать шесть тысяч пятьсот шестьдесят три рубля", "RUR"),
                Arguments.of("123", "Сто двадцать три доллара", "USD"),
                Arguments.of("123.01", "Сто двадцать три доллара", "USD"),
                Arguments.of("120.03", "Сто двадцать долларов", "USD"),
                Arguments.of("123.13", "Сто двадцать три доллара", "USD"),
                Arguments.of("123.3", "Сто двадцать три доллара", "USD"),
                Arguments.of("125.33", "Сто двадцать пять долларов", "USD"),
                Arguments.of("3426563.89", "Три миллиона четыреста двадцать шесть тысяч пятьсот шестьдесят три доллара", "USD"),
                Arguments.of("123", "Сто двадцать три евро", "EUR"),
                Arguments.of("123.01", "Сто двадцать три евро", "EUR"),
                Arguments.of("120.03", "Сто двадцать евро", "EUR"),
                Arguments.of("123.13", "Сто двадцать три евро", "EUR"),
                Arguments.of("123.3", "Сто двадцать три евро", "EUR"),
                Arguments.of("125.33", "Сто двадцать пять евро", "EUR"),
                Arguments.of("3426563.89", "Три миллиона четыреста двадцать шесть тысяч пятьсот шестьдесят три евро", "EUR"),
                Arguments.of("123", "Сто двадцать три", "GBP"),
                Arguments.of("123.01", "Сто двадцать три", "GBP"),
                Arguments.of("120.03", "Сто двадцать", "GBP"),
                Arguments.of("123.13", "Сто двадцать три", "GBP"),
                Arguments.of("123.3", "Сто двадцать три", "GBP"),
                Arguments.of("125.33", "Сто двадцать пять", "GBP"),
                Arguments.of("3426563.89", "Три миллиона четыреста двадцать шесть тысяч пятьсот шестьдесят три", "GBP")
        );
    }

    @ParameterizedTest
    @MethodSource("getSumAndIntegerPartSumInWords")
    @DisplayName("Целая часть суммы прописью")
    void testIntegerPartOfSumInWords(String sumAsBigDecimal, String sumInWords, String currencyCode) {
        BigDecimal sum = new BigDecimal(sumAsBigDecimal);

        String sumPaymentInWords = sut.getPaymentSumInWords(sum, currencyCode);
        String paymentSumInWords = sut.getPaymentIntegerPartOfSumInWords(sumPaymentInWords, currencyCode);

        Assertions.assertEquals(sumInWords, paymentSumInWords);
    }

    static Stream<Arguments> getSumAndFractionPartOfSumInWords() {
        return Stream.of(
                Arguments.of("123", "00 копеек", "RUR"),
                Arguments.of("123.01", "01 копейка", "RUR"),
                Arguments.of("120.03", "03 копейки", "RUR"),
                Arguments.of("123.13", "13 копеек", "RUR"),
                Arguments.of("123.3", "30 копеек", "RUR"),
                Arguments.of("125.33", "33 копейки", "RUR"),
                Arguments.of("3426563.89", "89 копеек", "RUR"),
                Arguments.of("123", "00 центов", "USD"),
                Arguments.of("123.01", "01 цент", "USD"),
                Arguments.of("120.03", "03 цента", "USD"),
                Arguments.of("123.13", "13 центов", "USD"),
                Arguments.of("123.3", "30 центов", "USD"),
                Arguments.of("125.33", "33 цента", "USD"),
                Arguments.of("3426563.89", "89 центов", "USD"),
                Arguments.of("123", "00 центов", "EUR"),
                Arguments.of("123.01", "01 цент", "EUR"),
                Arguments.of("120.03", "03 цента", "EUR"),
                Arguments.of("123.13", "13 центов", "EUR"),
                Arguments.of("123.3", "30 центов", "EUR"),
                Arguments.of("125.33", "33 цента", "EUR"),
                Arguments.of("3426563.89", "89 центов", "EUR"),
                Arguments.of("123", "00", "GBP"),
                Arguments.of("123.01", "01", "GBP"),
                Arguments.of("120.03", "03", "GBP"),
                Arguments.of("123.13", "13", "GBP"),
                Arguments.of("123.3", "30", "GBP"),
                Arguments.of("125.33", "33", "GBP"),
                Arguments.of("3426563.89", "89", "GBP")
        );
    }

    @ParameterizedTest
    @MethodSource("getSumAndFractionPartOfSumInWords")
    @DisplayName("Дробная часть суммы прописью")
    void testFractionPartOfSumInWords(String sumAsBigDecimal, String sumInWords, String currencyCode) {
        BigDecimal sum = new BigDecimal(sumAsBigDecimal);

        String sumPaymentInWords = sut.getPaymentSumInWords(sum, currencyCode);
        String paymentSumInWords = sut.getPaymentFractionPartOfSumInWords(sumPaymentInWords, currencyCode);

        Assertions.assertEquals(sumInWords, paymentSumInWords);
    }

    @Test
    @DisplayName("Дробная часть суммы прописью")
    void testToMoneyCurrency() {
        Assertions.assertEquals(MoneyToStr.Currency.RUR, sut.toMoneyCurrency("RUR"));
        Assertions.assertEquals(MoneyToStr.Currency.USD, sut.toMoneyCurrency("USD"));
        Assertions.assertEquals(MoneyToStr.Currency.EUR, sut.toMoneyCurrency("EUR"));
        Assertions.assertEquals(MoneyToStr.Currency.RUR, sut.toMoneyCurrency("RUB"));
        Assertions.assertNull(sut.toMoneyCurrency("GBP"));
    }

}