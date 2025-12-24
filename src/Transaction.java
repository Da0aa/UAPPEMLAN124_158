public class Transaction {
    String user, id, game, paket, harga;

    public Transaction(String user, String id, String game, String paket, String harga) {
        this.user = user;
        this.id = id;
        this.game = game;
        this.paket = paket;
        this.harga = harga;
    }

    // Format CSV untuk disimpan ke file teks
    public String toFileString() {
        return user + "," + id + "," + game + "," + paket + "," + harga;
    }
}