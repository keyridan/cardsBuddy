package com.j0rsa.cardsbuddy.converters.flec.noun;

import com.j0rsa.cardsbuddy.common.InfoData;
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
        Consumer<NounFlecTable> addTableRows = flecTable -> {
            SmallTitleRow smallTitleRow = createSmallTitleRow(flecTable);
            flecTable.getFlecRows().forEach(row -> addInfoRow(smallTitleRow, row));
            addSmallTitleRow(table, smallTitleRow);
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

    private void addSmallTitleRow(InfoTable table, SmallTitleRow smallTitleRow) {
        table.addRow(smallTitleRow);
    }

    private SmallTitleRow createSmallTitleRow(FlecTable verbFlecTable) {
        return SmallTitleRow.builder()
                .value(verbFlecTable.getTitle())
                .build();
    }

    private void addInfoRow(SmallTitleRow row, NounFlecRecord flecRow) {
        InfoData infoData = InfoData.builder()
                .highLights(flecRow.getHighlights())
                .value(flecRow.getValue())
                .title(flecRow.getCaseValue())
                .build();
        row.addData(infoData);
    }


}
