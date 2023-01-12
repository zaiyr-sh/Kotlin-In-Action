package ch_8;

import kotlin.jvm.functions.Function1;

public class Example {
    public static void main(String[] args) {
        Ex_8_1Kt.processTheAnswer(number -> number + 1);
        // For older Java versions
        Ex_8_1Kt.processTheAnswer(
                // Uses the Kotlin function type from Java code (prior to Java 8)
                new Function1<Integer, Integer>() {
                    @Override
                    public Integer invoke(Integer number) {
                        System.out.println(number);
                        return number + 1;
                    }
                }
        );
    }
}
