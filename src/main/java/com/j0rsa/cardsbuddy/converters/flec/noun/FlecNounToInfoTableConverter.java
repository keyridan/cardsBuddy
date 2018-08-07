package com.j0rsa.cardsbuddy.converters.flec.noun;

import com.j0rsa.cardsbuddy.common.InfoRow;
import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.common.SmallTitleRow;
import com.j0rsa.cardsbuddy.common.TitleRow;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecTable;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecTables;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlec;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecRecord;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecTable;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecTables;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class FlecNounToInfoTableConverter implements Converter<NounFlec, InfoTable> {

    @Override
    public InfoTable convert(NounFlec flec) {
        InfoTable table = new InfoTable();
        addRowsToTable(flec, table);
        return table;
    }

    private void addRowsToTable(NounFlec flec, InfoTable table) {
        Consumer<NounFlecRecord> addRecordRow = row -> addInfoRow(table, row);

        Consumer<NounFlecTable> addTableRows = flecTable -> {
            addSmallTitleRow(table, flecTable);
            flecTable.getFlecRows().forEach(addRecordRow);
        };

        Consumer<NounFlecTables> addTablesRows = flecTables -> {
            addTitleRow(table, flecTables);
            flecTables.getTables().forEach(addTableRows);
        };

        flec.getFlecs().forEach(addTablesRows);
    }

    private void addTitleRow(InfoTable table, FlecTables flecTables) {
        TitleRow titleRow = TitleRow.builder()
                .value(flecTables.getTitle())
                .build();
        table.addRow(titleRow);
    }

    private void addSmallTitleRow(InfoTable table, FlecTable verbFlecTable) {
        SmallTitleRow smallTitleRow = SmallTitleRow.builder()
                .value(verbFlecTable.getTitle())
                .build();
        table.addRow(smallTitleRow);
    }

    private void addInfoRow(InfoTable table, NounFlecRecord flecRow) {
        InfoRow infoRow = InfoRow.builder()
                .highLights(flecRow.getHighlights())
                .value(flecRow.getValue())
                .title(flecRow.getCaseValue())
                .build();
        table.addRow(infoRow);
    }


}
