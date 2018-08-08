package com.j0rsa.cardsbuddy.info.leo.model.info;

import com.j0rsa.cardsbuddy.info.leo.model.flec.ObjectFactory;
import com.j0rsa.cardsbuddy.info.leo.model.flec.verb.VerbType;
import io.vavr.Tuple2;
import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentContainerTest {

    @Test
    public void parseValuesByNames() {
        Tuple2<String, List<String>> expectedValue = tuple("ending", Lists.newArrayList("test"));
        ObjectFactory factory = factory();
        VerbType verbType = factory.createVerbTypeWithValue();

        JAXBElement<VerbType.Ending> element = createTestEndingElementWithValue(factory, "test");
        addElements(verbType, element);

        List<Tuple2<String, List<String>>> endings = verbType.parseValuesByNames(Lists.newArrayList("ending"));

        assertThat(endings).containsExactly(expectedValue);
    }

    @Test
    public void parseValuesByNamesWhenValueIsEmpty() {
        Tuple2<String, List<String>> expectedValue = tuple("ending", Lists.newArrayList());
        ObjectFactory factory = factory();
        VerbType verbType = factory.createVerbTypeWithValue();

        JAXBElement<VerbType.Ending> element = createTestEndingElementWithValue(factory, "");
        addElements(verbType, element);

        List<Tuple2<String, List<String>>> endings = verbType.parseValuesByNames(Lists.newArrayList("ending"));

        assertThat(endings).containsExactly(expectedValue);
    }

    private void addElements(VerbType verbType, JAXBElement... elements) {
        for (JAXBElement element : elements) {
            verbType.getContent().add(element);
        }
    }

    private JAXBElement<VerbType.Ending> createTestEndingElementWithValue(ObjectFactory factory, String value) {
        VerbType.Ending ending = factory.createVerbTypeEnding();
        ending.setValue(value);
        return factory.createVerbTypeEnding(ending);
    }

    private com.j0rsa.cardsbuddy.info.leo.model.flec.ObjectFactory factory() {
        return new ObjectFactory();
    }

    private Tuple2<String, List<String>> tuple(String name, List<String> firstElements) {
        return new Tuple2<>(name, firstElements);
    }
}