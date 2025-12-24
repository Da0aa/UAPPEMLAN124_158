# ⚡ PakDe STORE Topup Application

Aplikasi desktop berbasis Java Swing untuk layanan top-up diamond game (Mobile Legends, Free Fire, HOK, AOV). Proyek ini dibuat untuk memenuhi kriteria tugas mata kuliah Pemrograman Lanjut (Modul 1-6).

## 🚀 Fitur Utama
Aplikasi ini mendukung operasi CRUD (Create, Read, Update, Delete) yang disimpan secara permanen:
- **Halaman Home:** Antarmuka awal yang interaktif.
- **Pilih Game:** Pemilihan game menggunakan layout grid (kotak-kotak).
- **Form Input:** Validasi input ketat (Username tidak boleh kosong & ID wajib angka).
- **Daftar Paket:** Pilihan harga variatif dari Rp 10.000 hingga Rp 5.000.000.
- **Riwayat Transaksi:** Menampilkan data dalam `JTable` dengan fitur **Hapus Data**.
- **Sorting:** Pengurutan data berdasarkan Harga atau Nama Game.

## 🛠️ Teknologi & Konsep (Implementasi Modul)
Aplikasi ini mengimplementasikan materi berikut:
1. **Modul 1 (Program Correctness):** Defensive programming dengan validasi input ID menggunakan Regex.
2. **Modul 2 (Refactoring):** Pemisahan logika kode ke dalam 4 kelas terpisah agar lebih bersih.
3. **Modul 3 (OOP):** Penggunaan class `Transaction` sebagai objek data.
4. **Modul 4 (Java API):** Penggunaan `ArrayList`, `Collections`, dan `Comparator` untuk pengolahan data.
5. **Modul 5 (File Handling):** Penyimpanan data permanen ke dalam file `.txt` (Database lokal).
6. **Modul 6 (GUI & Exception):** Antarmuka menggunakan Java Swing dan penanganan error menggunakan `try-catch`.



## 📁 Struktur Kelas
- `Main.java`: Titik masuk utama (Entry Point) aplikasi.
- `TopUpApp.java`: Mengatur seluruh tampilan antarmuka (UI) dan logika navigasi.
- `Transaction.java`: Model data untuk menyimpan informasi setiap transaksi.
- `FileManager.java`: Mengatur operasi baca, tulis, dan hapus pada file teks.

## 💻 Cara Menjalankan
1. Pastikan Anda telah menginstal **JDK 8** atau versi yang lebih baru.
2. Clone repository ini atau unduh semua file `.java`.
3. Kompilasi program:
   ```bash
   javac Main.java