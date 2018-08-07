package com.j0rsa.cardsbuddy.converters.flec.noun;

import com.j0rsa.cardsbuddy.common.InfoData;
import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.common.SmallTitledRows;
import com.j0rsa.cardsbuddy.common.Title;
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
        Consumer<NounFlecTables> addTablesRows = flecTables -> {
            Title title = addTitle(table, flecTables);

            flecTables.getTables().forEach(flecTable -> {
                SmallTitledRows smallTitledRows = addSmallTitledRow(title, flecTable);

                flecTable.getFlecRows().forEach(row -> addInfoRow(smallTitledRows, row));
            });
        };

        flec.getFlecs().forEach(addTablesRows);
    }

    private Title addTitle(InfoTable table, NounFlecTables flecTables) {
        Title title = Title.builder()
                .value(flecTables.getTitle())
                .build();
        table.addRow(title);
        return title;
    }

    private SmallTitledRows addSmallTitledRow(Title title, NounFlecTable flecTable) {
        SmallTitledRows smallTitleRows = SmallTitledRows.builder()
                .value(flecTable.getTitle())
                .build();
        title.addRows(smallTitleRows);
        return smallTitleRows;
    }

    private void addInfoRow(SmallTitledRows row, NounFlecRecord flecRow) {
        InfoData infoData = InfoData.builder()
                .highLights(flecRow.getHighlights())
                .value(flecRow.getValue())
                .title(flecRow.getCaseValue())
                .build();
        row.addData(infoData);
    }


}
