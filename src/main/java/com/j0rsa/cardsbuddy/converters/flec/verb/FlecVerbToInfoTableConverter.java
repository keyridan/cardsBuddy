package com.j0rsa.cardsbuddy.converters.flec.verb;

import com.j0rsa.cardsbuddy.common.InfoData;
import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.common.SmallTitledRows;
import com.j0rsa.cardsbuddy.common.Title;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlec;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecRecord;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecTable;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecTables;
import org.assertj.core.util.Lists;
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
        Consumer<VerbFlecTables> addTablesRows = flecTables -> {
            Title title = addTitle(table, flecTables);

            flecTables.getTables().forEach(flecTable -> {
                SmallTitledRows smallTitledRows = addSmallTitledRow(title, flecTable);

                flecTable.getFlecRows().forEach(row -> addInfoRow(smallTitledRows, row));
            });
        };

        flec.getFlecs().forEach(addTablesRows);
    }

    private Title addTitle(InfoTable table, VerbFlecTables flecTables) {
        Title title = Title.builder()
                .value(flecTables.getTitle())
                .rows(Lists.newArrayList())
                .build();
        table.addRow(title);
        return title;
    }

    private SmallTitledRows addSmallTitledRow(Title title, VerbFlecTable flecTable) {
        SmallTitledRows smallTitledRows = SmallTitledRows.builder()
                .title(flecTable.getTitle())
                .infoData(Lists.newArrayList())
                .build();
        title.addRows(smallTitledRows);
        return smallTitledRows;
    }

    private void addInfoRow(SmallTitledRows row, VerbFlecRecord flecRow) {
        InfoData infoData = InfoData.builder()
                .highlights(flecRow.getHighlights())
                .value(flecRow.getValue())
                .build();
        row.addData(infoData);
    }


}
