package ru.blogspot.feomatr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PutSurveyLogTest {

    Supplier<List<String>> listSupplier = () -> Arrays.asList("<app-num>395900/ДУ-ФЛ-2021</app-num><date>",
            "Payload: <soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><SurveyResponse xmlns:ns3=\"http://alfacapital.ru/schemas/common\" xmlns:ns2=\"http://alfacapital.ru/schemas/types\" xmlns=\"http://alfacapital.ru/schemas/survey\"><survey-id>58017280</survey-id><app-num>360875/ДУ-ФЛ-ИИС-2021</app-num><date>2021-08-11+03:00</date></SurveyResponse></soap:Body></soap:Envelope>"
    );

    Supplier<List<String>> listSupplierFromFile = () -> {
        String filePath = "";
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
    };

    @Test
    void checkTestData() {
        List<String> strings = listSupplier.get();

        Set<String> contractNumbers = extractContractNumbers(strings);

        Assertions.assertTrue(contractNumbers.contains("395900"));
        Assertions.assertTrue(contractNumbers.contains("360875"));

    }

    @Test
    @Disabled
    void checkFileData() {
        List<String> strings = listSupplierFromFile.get();

        Set<String> contractNumbers = extractContractNumbers(strings);

        Assertions.assertEquals(22807, contractNumbers.size());
        Set<String> collect = contractNumbers.stream().filter(f -> f.endsWith("<")).peek(c -> System.out.println("c = " + c)).collect(Collectors.toSet());
        System.out.println("collect.size() = " + collect.size());

        saveContractNumbersSQL(contractNumbers);
    }

    private void saveContractNumbersSQL(Set<String> contractNumbers) {
        String filePath = "";
        if (true) {
            return;
        }
        Set<String> collect = contractNumbers.stream().collect(Collectors.toSet());
        try {
            FileWriter writer = new FileWriter(filePath);
            int i = 0;
            writer.write("INSERT ALL" + System.lineSeparator());
            for (String str : collect) {
                writer.write(str + System.lineSeparator());

                ++i;
                boolean chank = i % 500 == 0;
                if (chank) {
                    writer.write("SELECT 1 FROM DUAL;" + System.lineSeparator());
                    writer.write("commit;" + System.lineSeparator());
                    writer.write("INSERT ALL" + System.lineSeparator());
                }

            }
            writer.write("SELECT 1 FROM DUAL;" + System.lineSeparator());
            writer.write("commit;" + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
    }

    private Set<String> extractContractNumbers(List<String> strings) {
        String startTag = "<app-num>";
        int startTagLength = startTag.length();
        Set<String> contractNumbers = new HashSet<>();
        for (String string : strings) {
            int i = string.indexOf(startTag);
            String contractNumberWithTail = string.substring(i + startTagLength);
            int indexOfContractEnd = contractNumberWithTail.indexOf("/");
            String contractNumber = contractNumberWithTail.substring(0, indexOfContractEnd);
            if (contractNumber.endsWith("<")) {
                continue;
            }
            contractNumbers.add(contractNumber);
        }
        return contractNumbers;
    }

}
