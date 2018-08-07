package com.j0rsa.cardsbuddy.common;

import io.vavr.Tuple2;
import org.assertj.core.util.Lists;

import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

public class JaxBUtils {
    public static String getStringValue(Object element) {
        return ((String) element).trim();
    }

    public static boolean isStringOrJaxBElement(Serializable contentElement) {
        return isString(contentElement) || isJaxBElement(contentElement);
    }

    public static boolean isJaxbElementAndClass(Class clazz, Serializable element) {
        return isJaxBElement(element) && isClassElement(element, clazz);
    }

    public static boolean isJaxBElement(Serializable contentElement) {
        return contentElement.getClass().equals(JAXBElement.class);
    }

    public static boolean isClassElement(Serializable element, Class clazz) {
        return clazz.equals(getJaxBDeclaredType(element));
    }

    public static boolean hasName(Serializable element, String name) {
        return getJaxBElementName(element).equals(name);
    }

    public static String getJaxBElementName(Serializable element) {
        return ((JAXBElement) element).getName().toString();
    }

    public static Class getJaxBDeclaredType(Serializable element) {
        return ((JAXBElement) element).getDeclaredType();
    }

    public static boolean isString(Serializable contentElement) {
        return contentElement.getClass().equals(String.class);
    }

    public static boolean isStringOrJaxBElementInClasses(List<Class> classes, Serializable element) {
        return isString(element) || (isJaxBElement(element) && !classes.contains(getJaxBDeclaredType(element)));
    }

    public static List<Tuple2<String, List<JAXBElement>>> getSplitedCollection(List<JAXBElement> contentJaxBElements) {
        List<Tuple2<String, List<JAXBElement>>> contents = Lists.newArrayList();
        List<JAXBElement> contentPart = Lists.newArrayList();
        ListIterator<JAXBElement> iterator = contentJaxBElements.listIterator();

        while (iterator.hasNext()) {
            JAXBElement element = iterator.next();
            if (iterator.hasNext()) {
                JAXBElement nextElement = contentJaxBElements.get(iterator.nextIndex());
                if (nextElementNameIsEqualsElementName(element, nextElement)) {
                    contentPart.add(element);
                } else {
                    addItemWithItsName(contents, contentPart, element);
                    contentPart = Lists.newArrayList();
                }
            } else {
                addItemWithItsName(contents, contentPart, element);
            }
        }

        return contents;
    }

    private static void addItemWithItsName(List<Tuple2<String, List<JAXBElement>>> contents, List<JAXBElement> contentPart, JAXBElement element) {
        contentPart.add(element);
        Tuple2<String, List<JAXBElement>> namedItems = namedItem(contentPart, element);
        contents.add(namedItems);
    }

    private static Tuple2<String, List<JAXBElement>> namedItem(List<JAXBElement> contentPart, JAXBElement element) {
        return new Tuple2<>(getJaxBElementName(element), contentPart);
    }

    private static boolean nextElementNameIsEqualsElementName(JAXBElement element1, JAXBElement element2) {
        return getJaxBElementName(element2).equals(getJaxBElementName(element1));
    }
}
