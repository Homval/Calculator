package ru.khomyakov;

class WrongOperandsException extends Exception{
    public WrongOperandsException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
