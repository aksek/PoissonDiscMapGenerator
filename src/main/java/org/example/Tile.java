package org.example;

public class Tile {
    int X_a, Y_a;
    int X_b, Y_b;
    int X_c, Y_c;

    public Tile(int Xa, int Ya, int Xb, int Yb, int Xc, int Yc) {
        X_a = Xa;
        Y_a = Ya;
        X_b = Xb;
        Y_b = Yb;
        X_c = Xc;
        Y_c = Yc;
    }
    public int Xa() {
        return X_a;
    }
    public int Ya() {
        return Y_a;
    }
    public int Xb() {
        return X_b;
    }
    public int Yb() {
        return Y_b;
    }
    public int Xc() {
        return X_c;
    }
    public int Yc() {
        return Y_c;
    }
}
