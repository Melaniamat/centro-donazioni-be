package it.corsojava.progettodonazioni.enumerator;

public enum BloodType {

    A,
    B,
    AB,
    O;

    public boolean canReceiveFrom(BloodType donorType) {
        if (this == AB) return true; // AB riceve da tutti
        if (donorType == O) return true; // O dona a tutti
        return this == donorType; // A riceve da A, B riceve da B
    }
}
