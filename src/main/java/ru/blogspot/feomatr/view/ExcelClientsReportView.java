package ru.blogspot.feomatr.view;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.blogspot.feomatr.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author iipolovinkin
 * @since 06.09.2015
 */
@Component
public class ExcelClientsReportView {
    private static final Logger log = LoggerFactory.getLogger(ExcelClientsReportView.class);

    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Model data = (Model) model.get("data");
        //create a wordsheet
        HSSFSheet sheet = workbook.createSheet("Clients Report");

        //create header (filling row)
        List<String> head = Lists.newArrayList("ID", "Firstname", "Address", "Age");
        HSSFRow header = sheet.createRow(0);
        for (int i = 0; i < head.size(); i++) {
            header.createCell(i).setCellValue(head.get(i));
        }

        List<Client> list = (List<Client>) data.asMap().get("list");
        int rowNum = 1;
        for (int i = 0; i < list.size(); i++) {
            //create row data
            HSSFRow row = sheet.createRow(rowNum++);
            Client client = list.get(i);
            row.createCell(0).setCellValue(client.getId());
            row.createCell(1).setCellValue(client.getFirstname());
            row.createCell(2).setCellValue(client.getAddress());
            row.createCell(3).setCellValue(client.getAge());
        }
    }
}
