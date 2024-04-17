package ru.khomyakov;

class WrongExpressionException extends Exception{
    public WrongExpressionException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
