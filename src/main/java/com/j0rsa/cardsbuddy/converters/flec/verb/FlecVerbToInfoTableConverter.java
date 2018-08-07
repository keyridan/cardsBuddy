package com.j0rsa.cardsbuddy.converters.flec.verb;

import com.j0rsa.cardsbuddy.common.InfoRow;
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
        Consumer<VerbFlecRecord> addRecordRow = row -> addInfoRow(table, row);

        Consumer<VerbFlecTable> addTableRows = flecTable -> {
            addSmallTitleRow(table, flecTable);
            flecTable.getFlecRows().forEach(addRecordRow);
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

    private void addSmallTitleRow(InfoTable table, VerbFlecTable verbFlecTable) {
        SmallTitleRow smallTitleRow = SmallTitleRow.builder()
                .value(verbFlecTable.getTitle())
                .build();
        table.addRow(smallTitleRow);
    }

    private void addInfoRow(InfoTable table, VerbFlecRecord flecRow) {
        InfoRow infoRow = InfoRow.builder()
                .highLights(flecRow.getHighlights())
                .value(flecRow.getValue())
                .build();
        table.addRow(infoRow);
    }


}
