package com.j0rsa.cardsbuddy;

import io.vavr.*;
import org.apache.http.StatusLine;

import java.util.List;
import java.util.function.Function;

public class PatternMatchingUtils {
    private static Function1<Integer, Function<List, Tuple1<Object>>> listElement =
            i -> list -> listElement(list, i);

    private static Tuple2<Object, Object> listOfTuple2(List list) {
        Function<Integer, Object> getOrNull = i -> getOrNull(list, i);
        return Tuple.of(
                getOrNull.apply(0),
                getOrNull.apply(1)
        );
    }

    public static API.Match.Pattern2<List, Object, Object> $List(API.Match.Pattern<Object, ?> p1, API.Match.Pattern<Object, ?> p2) {
        return API.Match.Pattern2.of(List.class, p1, p2, PatternMatchingUtils::listOfTuple2);
    }

    private static Tuple1<Object> listElement(List list, int i) {
        return Tuple.of(list.size() <= i ? null : list.get(i));
    }

    public static API.Match.Pattern1<List, Object> $ListElementNo(int index, API.Match.Pattern<Object, ?> p1) {
        return API.Match.Pattern1.of(List.class, p1, listElement.apply(index));
    }

    private static Tuple5<Object, Object, Object, Object, Object> longList(List list) {
        Function<Integer, Object> getOrNull = i -> getOrNull(list, i);
        return Tuple.of(
                getOrNull.apply(0),
                getOrNull.apply(1),
                getOrNull.apply(2),
                getOrNull.apply(3),
                getOrNull.apply(4));
    }

    public static API.Match.Pattern5<List, Object, Object, Object, Object, Object> $List(
            API.Match.Pattern<Object, ?> p1,
            API.Match.Pattern<Object, ?> p2,
            API.Match.Pattern<Object, ?> p3,
            API.Match.Pattern<Object, ?> p4,
            API.Match.Pattern<Object, ?> p5
    ) {
        return API.Match.Pattern5.of(List.class, p1, p2, p3, p4, p5, PatternMatchingUtils::longList);
    }

    private static Object getOrNull(List list, int index) {
        return list.size() > index ? list.get(index) : null;
    }


    private static Tuple2<Integer, String> statusLine(StatusLine statusLine) {
        return Tuple.of(
                statusLine.getStatusCode(),
                statusLine.getReasonPhrase()
        );
    }

    public static <T extends Integer, T1 extends String> API.Match.Pattern2<StatusLine, T, T1> $StatusLine(API.Match.Pattern<T, ?> p1, API.Match.Pattern<T1, ?> p2) {
        return API.Match.Pattern2.of(StatusLine.class, p1, p2, PatternMatchingUtils::statusLine);
    }

}
