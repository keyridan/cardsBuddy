package com.j0rsa.cardsbuddy.converters.flec.verb;

import com.j0rsa.cardsbuddy.common.InfoData;
import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.common.SmallTitleRow;
import com.j0rsa.cardsbuddy.common.TitleRow;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlec;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecRecord;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecTable;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecTables;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class FlecVerbToInfoTableConverter implements Converter<VerbFlec, InfoTable> {

    @Override
    public InfoTable convert(VerbFlec flec) {
        InfoTable table = new InfoTable();
        addRowsToTable(flec, table);
        return table;
    }

    private void addRowsToTable(VerbFlec flec, InfoTable table) {
        Consumer<VerbFlecTable> addTableRows = flecTable -> {
            SmallTitleRow smallTitleRow = createSmallTitleRow(flecTable);
            flecTable.getFlecRows().forEach(row -> addInfoRow(smallTitleRow, row));
            addSmallTitleRow(table, smallTitleRow);
        };

        Consumer<VerbFlecTables> addTablesRows = flecTables -> {
            addTitleRow(table, flecTables);
            flecTables.getTables().forEach(addTableRows);
        };

        flec.getFlecs().forEach(addTablesRows);
    }

    private void addTitleRow(InfoTable table, VerbFlecTables flecTables) {
        TitleRow titleRow = TitleRow.builder()
                .value(flecTables.getTitle())
                .build();
        table.addRow(titleRow);
    }

    private void addSmallTitleRow(InfoTable table, SmallTitleRow smallTitleRow) {
        table.addRow(smallTitleRow);
    }

    private SmallTitleRow createSmallTitleRow(VerbFlecTable verbFlecTable) {
        return SmallTitleRow.builder()
                .value(verbFlecTable.getTitle())
                .build();
    }

    private void addInfoRow(SmallTitleRow row, VerbFlecRecord flecRow) {
        InfoData infoData = InfoData.builder()
                .highLights(flecRow.getHighlights())
                .value(flecRow.getValue())
                .build();
        row.addData(infoData);
    }


}
