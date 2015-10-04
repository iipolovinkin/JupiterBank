package ru.blogspot.feomatr.entity;

import com.google.common.base.Preconditions;

/**
 * AccountNo is a 20 digit number consists of 6 groups:
 * <br>
 * AAA BB CCC D EEEE FFFFFFF
 * <br>
 * 1-3 4-5 6-8 9 10-13 14-20
 * <br>
 * Пример: 40817810099910004312, в котором 408 - означает что это счёт физического лица, <br>
 * 17 - бессрочный, 810 (840, 978..) - валюта счета - рубли (доллары, евро... ) , 0 - контрольное число.
 * <br>
 * 9991 -подразделение банка, 0004312 - порядковый номер клиента в данном банке
 * <p/>
 * https://ru.wikipedia.org/wiki/%D0%A0%D0%B0%D1%81%D1%87%D1%91%D1%82%D0%BD%D1%8B%D0%B9_%D1%81%D1%87%D1%91%D1%82
 *
 * @author iipolovinkin
 * @since 03.10.2015
 */
public class AccountNo {
    public static String generatePrivateBankAccountNo(int clientNum) {
        Preconditions.checkArgument(clientNum > 0, "ClientNum should be Ge 1.");
        String accountType = "40817";
        String currency = "810";
        String someDigit = "0";
        String bankDepartment = "6101";
        String clientNo = String.format("%07d", clientNum);
        String accountNo = accountType + currency + someDigit + bankDepartment + clientNo;

        return accountNo;
    }

    public static String generatePrivateBankAccountNo() {
        int clientNum = getCurrentNo();
        return generatePrivateBankAccountNo(clientNum);
    }

    private static int getCurrentNo() {
        return 1;
    }

}
