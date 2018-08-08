package com.j0rsa.cardsbuddy.common;

import com.j0rsa.cardsbuddy.info.leo.model.flec.ObjectFactory;
import com.j0rsa.cardsbuddy.info.leo.model.flec.verb.VerbType;
import io.vavr.Tuple2;
import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.xml.bind.JAXBElement;
import java.util.List;

import static com.j0rsa.cardsbuddy.common.JaxBUtils.getSplitedCollection;
import static org.assertj.core.api.Assertions.assertThat;

public class JaxBUtilsTest {

    @Test
    public void whenEmptyElement() {
        ObjectFactory factory = factory();
        VerbType.Ending verbTypeEnding = factory.createVerbTypeEnding();
        JAXBElement element1 = factory.createVerbTypeEnding(verbTypeEnding);
        List<JAXBElement> elements = Lists.newArrayList(element1);

        List<JAXBElement> firstElements = Lists.newArrayList(element1);
        Tuple2<String, List<JAXBElement>> firstExpected = tuple("ending", firstElements);

        List<Tuple2<String, List<JAXBElement>>> splitedCollection = getSplitedCollection(elements);

        assertThat(splitedCollection).containsExactly(firstExpected);
    }

    @Test
    public void when2SameElementsThen1TupleOfElements() {
        ObjectFactory factory = factory();
        JAXBElement element1 = factory.createVerbTypeAux("aux1");
        JAXBElement element2 = factory.createVerbTypeAux("aux2");
        List<JAXBElement> elements = Lists.newArrayList(element1, element2);

        List<JAXBElement> firstElements = Lists.newArrayList(element1, element2);
        Tuple2<String, List<JAXBElement>> firstExpected = tuple("aux", firstElements);

        List<Tuple2<String, List<JAXBElement>>> splitedCollection = getSplitedCollection(elements);

        assertThat(splitedCollection).containsExactly(firstExpected);
    }

    @Test
    public void when2DifferentElementsThen2TupleOfElements() {
        ObjectFactory factory = factory();
        JAXBElement element1 = factory.createVerbTypeAux("aux1");
        JAXBElement elementWIthAnotherName = factory.createVerbTypeRaux("raux");
        List<JAXBElement> elements = Lists.newArrayList(element1, elementWIthAnotherName);

        List<JAXBElement> firstElements = Lists.newArrayList(element1);
        Tuple2<String, List<JAXBElement>> firstExpected = tuple("aux", firstElements);

        List<JAXBElement> secondElement = Lists.newArrayList(elementWIthAnotherName);
        Tuple2<String, List<JAXBElement>> secondExpected = tuple("raux", secondElement);

        List<Tuple2<String, List<JAXBElement>>> splitedCollection = getSplitedCollection(elements);

        assertThat(splitedCollection).containsExactly(firstExpected, secondExpected);
    }

    @Test
    public void when2SameElementsThan1AnotherAnd1SameElementThenListOf3Tuples() {
        ObjectFactory factory = factory();
        JAXBElement element1 = factory.createVerbTypeAux("aux1");
        JAXBElement element2 = factory.createVerbTypeAux("aux2");
        JAXBElement elementWIthAnotherName = factory.createVerbTypeRaux("another");
        JAXBElement element3 = factory.createVerbTypeAux("aux3");
        List<JAXBElement> elements = Lists.newArrayList(element1, element2, elementWIthAnotherName, element3);

        List<JAXBElement> firstElements = Lists.newArrayList(element1, element2);
        Tuple2<String, List<JAXBElement>> firstExpected = tuple("aux", firstElements);

        List<JAXBElement> secondElements = Lists.newArrayList(elementWIthAnotherName);
        Tuple2<String, List<JAXBElement>> secondExpected = tuple("raux", secondElements);

        List<JAXBElement> thirdElements = Lists.newArrayList(element3);
        Tuple2<String, List<JAXBElement>> thirdExpected = tuple("aux", thirdElements);

        List<Tuple2<String, List<JAXBElement>>> splitedCollection = getSplitedCollection(elements);

        assertThat(splitedCollection).containsExactly(firstExpected, secondExpected, thirdExpected);
    }

    private ObjectFactory factory() {
        return new ObjectFactory();
    }

    private Tuple2<String, List<JAXBElement>> tuple(String name, List<JAXBElement> firstElements) {
        return new Tuple2<>(name, firstElements);
    }
}