package com.company.task2.parser;

public class DepositBuilderFactory {
    private DepositBuilderFactory() {
    }

    public static AbstractDepositsBuilder createDepositsBuilder(String typeParser) {

        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM -> {
                return new DepositsDomBuilder();
            }

            case STAX -> {
                return new DepositsStaxBuilder();
            }

            case SAX -> {
                return new DepositsSaxBuilder();
            }

            default -> throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }

    private enum TypeParser {
        SAX, STAX, DOM
    }
}
