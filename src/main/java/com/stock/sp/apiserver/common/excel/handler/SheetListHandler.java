package com.stock.sp.apiserver.common.excel.handler;

import com.stock.sp.apiserver.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SheetListHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
    private List<String> header = new ArrayList<>();
    private List<List<String>> rows = new ArrayList<>();
    private List<String> row = new ArrayList<>();
    private int currentCol = -1;
    private int currRowNum = 0;

    public List<String> getHeader() {
        return header;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public void startRow(int rowNum) {
        this.currentCol = -1;
        this.currRowNum = rowNum;
    }

    public void endRow(int rowNum) {
        if (rowNum == 0) {
            header = new ArrayList<>(row);
        } else {
            if (row.size() < header.size()) {
                for (int i = row.size(); i < header.size(); i++) {
                    row.add("");
                }
            }
            if (!this.checkEmptyList(row)) {
                rows.add(new ArrayList<>(row));
            }
        }
        row.clear();
    }

    public void cell(String columnName, String value, XSSFComment var3) {
        int iCol = (new CellReference(columnName)).getCol();
        int emptyCol = iCol - currentCol - 1;
        for (int i = 0; i < emptyCol; i++) {
            row.add("");
        }
        currentCol = iCol;
        row.add(value);
    }

    private boolean checkEmptyList(List<String> list) {
        boolean empty = false;
        for (String item : list) {
            if (StringUtils.isNotEmpty(item)) {
                empty = false;
                break;
            } else {
                empty = true;
            }
        }
        return empty;
    }

}
