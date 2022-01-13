package ru.blogspot.feomatr.service;

import com.github.moneytostr.MoneyToStr;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

public class MoneyToWordsConverter {

    public static final int LAST_WORDS_COUNT_FOR_UNKNOWN_CURRENCY = 1;
    public static final int LAST_WORDS_COUNT_FOR_KNOWN_CURRENCY = 2;

    @NotNull
    public String getPaymentSumInWords(BigDecimal sum, String currencyCode) {
        MoneyToStr.Currency currency = toMoneyCurrency(currencyCode);
        String paymentSumInWords;
        if (currency != null) {
            paymentSumInWords = getMoneyToStr(sum, currency);
        } else {
            RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);

            paymentSumInWords = nf.format(sum.intValue()) + " " + getCents(sum);
        }

        paymentSumInWords = paymentSumInWords.substring(0, LAST_WORDS_COUNT_FOR_UNKNOWN_CURRENCY).toUpperCase() + paymentSumInWords.substring(LAST_WORDS_COUNT_FOR_UNKNOWN_CURRENCY);

        return paymentSumInWords;
    }

    public MoneyToStr.Currency toMoneyCurrency(String code) {
        if ("RUB".equals(code) || "RUR".equals(code)) {
            return MoneyToStr.Currency.RUR;
        } else if ("USD".equals(code)) {
            return MoneyToStr.Currency.USD;
        } else if ("EUR".equals(code)) {
            return MoneyToStr.Currency.EUR;
        }
        return null;
    }

    private String getMoneyToStr(BigDecimal getPaymentSum, MoneyToStr.Currency currency) {
        MoneyToStr moneyToStr = new MoneyToStr(currency, MoneyToStr.Language.RUS, MoneyToStr.Pennies.NUMBER);
        return moneyToStr.convert(getPaymentSum.doubleValue());
    }

    private String getCents(BigDecimal sum) {
        int i = sum.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(100L)).intValue();
        return String.format("%02d", i);
    }

    @NotNull
    public String getPaymentIntegerPartOfSumInWords(String paymentSumInWords, String currencyCode) {
        int centWordCountByCurrency = getCentWordCountByCurrency(currencyCode);

        return deleteLastWords(paymentSumInWords, centWordCountByCurrency);
    }

    @NotNull
    public String getPaymentFractionPartOfSumInWords(String paymentSumInWords, String currencyCode) {
        int centWordCountByCurrency = getCentWordCountByCurrency(currencyCode);

        return getLastWords(paymentSumInWords, centWordCountByCurrency);

    }

    private int getCentWordCountByCurrency(String currencyCode) {
        MoneyToStr.Currency currency = toMoneyCurrency(currencyCode);
        return Objects.nonNull(currency) ? LAST_WORDS_COUNT_FOR_KNOWN_CURRENCY : LAST_WORDS_COUNT_FOR_UNKNOWN_CURRENCY;
    }

    private String deleteLastWords(String string, int count) {

        int indexOfLastCountWord = indexOfLastCountWord(string, count);

        return string.substring(0, indexOfLastCountWord);
    }

    private String getLastWords(String string, int count) {

        int indexOfLastCountWord = indexOfLastCountWord(string, count);

        return string.substring(indexOfLastCountWord + 1);
    }

    private int indexOfLastCountWord(String string, int count) {
        String head = string;
        int index = -1;
        for (int i = 0; i < count; i++) {
            index = head.lastIndexOf(' ');
            head = head.substring(0, index);
        }
        return index;
    }
}
