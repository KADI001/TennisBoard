package org.kadirov.refactor.score;

public enum Point {
    ZERO,
    FIFTEEN,
    THIRTY,
    FORTY,
    ADVANTAGE;

    public Point next(){
        if(this == ADVANTAGE){
            return ADVANTAGE;
        }else {
            return Point.values()[this.ordinal() + 1];
        }
    }
}
