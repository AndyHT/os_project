package com.company;

/**
 * Created by huoteng on 4/27/15.
 */
public abstract class Entry {
    public abstract String getName();
    public abstract int getSize();
    public Entry add (Entry entry) throws FileTreatmentException{
        throw new FileTreatmentException();
    }

    public void printList() {
        printList("");
    }

    public abstract void printList(String prefix);

    @Override
    public String toString() {
        return getName() + "(" + getSize() + ")";
    }
}
