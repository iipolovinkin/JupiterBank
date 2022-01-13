package ru.blogspot.feomatr.payments;

import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class LoadFailedPaymentsTest {


    @Test
    public void testClientIsNotFound() throws Exception {
        String[] split = getFailedPayments().split("\n");
        if (split[0].isEmpty()) return;
        int i = 0;
        for (String line : split) {
            ++i;
            System.out.println(i + ". " + process(line));
            System.out.println("------- \n");
        }


    }

    private String getFailedPayments() {
        return "";
    }

    private String process(String line) {
        String payment = line.substring(line.indexOf("{"), line.indexOf("}") + 1);
        System.out.println("payment = " + payment);
//        String paymentAppointment = payment.substring(payment.indexOf("paymentAppointment="));
        String paymentAppointment = getStringByManualBracesWithoutBraces("paymentAppointment=", ", problemDescription=",
                payment);
        System.out.println("paymentAppointment = " + paymentAppointment);

//        String number = getStringByManualBracesWithoutBraces("paymentAppointment='", paymentAppointment);
//        getStringByManualBraces(leftBrace, rightBrace, word);
//        System.out.println("paymentAppointment = " + paymentAppointment);
//        new Gson().fromJson(payment, Payment.class);
        return payment;
    }

//
//    private String getStringByManualBracesWithoutBraces(String leftBrace, String word) {
//        return getStringByManualBraces(leftBrace, rightBrace, word, true);
//    }

    private String getStringByManualBracesWithoutBraces(String leftBrace, String rightBrace, String word) {
        return getWordByAfterLeftBrace(leftBrace, rightBrace, word, true);
    }

    private String getWordByAfterLeftBrace(String leftBrace, String rightBrace, String word, boolean withoutBraces) {
        int leftBraceIndex = word.indexOf(leftBrace);
        if (withoutBraces) {
            leftBraceIndex = leftBraceIndex + leftBrace.length() + 1;
        }
        String substring = word.substring(leftBraceIndex);
        return substring.split(" ")[0];
    }

    private String getStringByManualBraces(String leftBrace, String rightBrace, String word, boolean withoutBraces) {
        int leftBraceIndex = word.indexOf(leftBrace);
        int rightBraceIndex = word.indexOf(rightBrace);
        if (withoutBraces) {
            leftBraceIndex = leftBraceIndex + leftBrace.length() + 1;
        }
        return word.substring(leftBraceIndex, rightBraceIndex + 1);
    }


    @Data
    private class Payment {
        private int paymentDocNum;
        private BigDecimal paymentSum;
        private Date paymentDate;
        private String paymentAppointment;
    }
}