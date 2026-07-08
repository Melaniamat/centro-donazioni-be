package it.corsojava.progettodonazioni.enumerator;

public enum RH {
    POSITIVE,
    NEGATIVE;

        public boolean canReceiveFrom(RH donorRh) {
            if (this == POSITIVE) return true; // Rh+ riceve da + e -
            return donorRh == NEGATIVE; // Rh- riceve solo da -
        }


}
