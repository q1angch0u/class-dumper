package com.dumpclass;

import java.lang.instrument.Instrumentation;

/**
 * @author q1angch0u
 */
public class MainAgent {
    public static void main(String[] args) {

    }

    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new DumpClassTransformer());
    }
}