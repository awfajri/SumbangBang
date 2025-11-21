# SumbangBang

Deskripsi singkat
-----------------
SumbangBang adalah aplikasi manajemen donasi (khususnya donasi makanan) berbasis Java yang menyediakan interface untuk 3 peran utama: **Admin**, **Donatur**, dan **Penerima**. Aplikasi ini dibuat menggunakan NetBeans GUI Forms (Java Swing) dan menggunakan MySQL sebagai penyimpanan data.

Fitur utama
-----------
- Autentikasi: registrasi dan login untuk pengguna
- Peran pengguna: Donatur, Penerima, dan Admin
- Donasi makanan: Donatur dapat membuat entri donasi makanan (`FoodDonation`)
- Reservasi: Penerima dapat mereservasi donasi
- Komentar: pengguna dapat memberi komentar pada donasi
- Dashboard Admin: pengelolaan user, donasi, reservasi, dan komentar

Struktur proyek (ringkasan)
---------------------------
- `src/config` : konfigurasi koneksi database (`DatabaseConfig.java`)
- `src/dao` : Data Access Objects untuk operasi DB (mis. `AdminDAO`, `DonationDAO`, `UserDAO`)
- `src/model` : model entitas (mis. `User`, `Donatur`, `Penerima`, `FoodDonation`, `Reservation`, `Comment`, `Admin`)
- `src/service` : layanan aplikasi (mis. `AuthService`)
- `src/view` : tampilan GUI (NetBeans `.form` + `.java` files)
- `src/Main.java` : entry-point aplikasi

Persyaratan
-----------
- JDK 8 atau lebih baru
- MySQL (server) atau RDBMS lain yang kompatibel dengan Connector/J
- MySQL Connector/J pada classpath (versi modern: `com.mysql.cj.jdbc.Driver`)
- NetBeans (direkomendasikan) atau Apache Ant (`build.xml` sudah tersedia)

Konfigurasi database
---------------------
File koneksi database: `src/config/DatabaseConfig.java`.
Default di repositori:

```
URL: jdbc:mysql://localhost:3306/sumbangbang_db
USER: root
PASSWORD: (kosong)
```

Ubah nilai tersebut sesuai environment Anda.

Contoh pembuatan database (MySQL):

```sql
CREATE DATABASE sumbangbang_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- Buat user jika perlu
-- CREATE USER 'sumbang'@'localhost' IDENTIFIED BY 'password';
-- GRANT ALL PRIVILEGES ON sumbangbang_db.* TO 'sumbang'@'localhost';
```

Catatan: Skema tabel (CREATE TABLE) tidak disertakan di repo; silakan lihat nama model dan DAO di `src/model` dan `src/dao` sebagai acuan untuk membuat tabel yang sesuai. Jika Anda mau, saya bisa membantu membuat skrip SQL skema awal berdasarkan model di repo.

Build & Run
-----------
1. Pastikan MySQL berjalan dan database `sumbangbang_db` sudah dibuat.
2. Update kredensial di `src/config/DatabaseConfig.java` jika perlu.
3. Tambahkan MySQL Connector/J ke dependencies proyek (di NetBeans: Properties → Libraries → Add JAR/Folder).
4. Open project di NetBeans dan klik Run, atau gunakan Ant:

```bash
# dari root repo
ant -f build.xml

# menjalankan dari NetBeans biasanya lebih mudah; jika ingin menjalankan manual,
# jalankan class Main melalui IDE atau buat JAR lalu jalankan dengan:
# java -cp "path/to/mysql-connector-java.jar:build/classes" Main
```

Catatan: Penamaan class `Main` dan package default ada di `src/Main.java`.

Kontribusi
----------
- Untuk perubahan kecil: buka issue atau kirimkan PR.
- Jika Anda ingin bantuan menulis skrip SQL untuk skema atau mengemas aplikasi menjadi JAR, beri tahu saya dan saya akan bantu.

Masalah umum & debugging
------------------------
- Jika muncul error driver JDBC, pastikan MySQL Connector/J ada di classpath.
- Jika koneksi gagal, cek `URL`, `USER`, dan `PASSWORD` di `src/config/DatabaseConfig.java` serta akses dari host tempat aplikasi berjalan.

Lisensi
-------
SumbangBang Team (Auf Fajri, Imas Anisa, Maulana Gading, Muhamad hafiz)
