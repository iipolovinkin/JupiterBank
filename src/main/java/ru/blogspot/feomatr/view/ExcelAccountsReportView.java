package ru.blogspot.feomatr.view;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import ru.blogspot.feomatr.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author iipolovinkin
 * @since 04.09.2015
 */
public class ExcelAccountsReportView extends AbstractExcelView {
    private static final Logger log = LoggerFactory.getLogger(ExcelAccountsReportView.class);

    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Model data = (Model) model.get("data");
        //create a wordsheet
        HSSFSheet sheet = workbook.createSheet("Accounts Report");

        //create header (filling row)
        List<String> head = Lists.newArrayList("ID", "AccountNo", "Balance");
        HSSFRow header = sheet.createRow(0);
        for (int i = 0; i < head.size(); i++) {
            header.createCell(i).setCellValue(head.get(i));
        }

        List<Account> list = (List<Account>) data.asMap().get("list");
        int rowNum = 1;
        for (int i = 0; i < list.size(); i++) {
            //create row data
            HSSFRow row = sheet.createRow(rowNum++);
            Account a = list.get(i);
            row.createCell(0).setCellValue(a.getId().toString());
            row.createCell(1).setCellValue(a.getAccountNo());
            row.createCell(2).setCellValue(a.getBalance().toString());
        }
    }
}
