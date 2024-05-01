package bai1;


public class PhanTuDaThuc {
    private float heSo;
    private int soMu;

    public PhanTuDaThuc() {
    }

    public PhanTuDaThuc(float heSo, int soMu) {
        this.heSo = heSo;
        this.soMu = soMu;
    }

    public float getHeSo() {
        return heSo;
    }

    public void setHeSo(float heSo) {
        this.heSo = heSo;
    }

    public int getSoMu() {
        return soMu;
    }

    public void setSoMu(int soMu) {
        this.soMu = soMu;
    }

    @Override
    public String toString() {
        return heSo + "x^" + soMu;
    }
}
