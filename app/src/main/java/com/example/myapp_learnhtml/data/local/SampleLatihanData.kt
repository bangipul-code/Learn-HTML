package com.example.myapp_learnhtml.data.local

import com.example.myapp_learnhtml.data.model.LatihanQuestion

object SampleLatihanData {

    val questions = listOf(
        q(0, 0, "Apa kepanjangan dari HTML?", listOf("HyperText Markup Language", "HighText Machine Language", "Hyperlink Text Manager", "Home Tool Markup Logic"), 0),
        q(1, 0, "Fungsi utama HTML adalah...", listOf("Mengatur database", "Menyusun struktur halaman web", "Membuat animasi 3D", "Mengompilasi aplikasi Android"), 1),
        q(2, 0, "Istilah tag dalam HTML merujuk pada...", listOf("Folder proyek", "Penanda seperti <p>", "Nama browser", "File gambar"), 1),
        q(3, 0, "Ekstensi umum untuk file HTML adalah...", listOf(".css", ".html", ".js", ".png"), 1),
        q(4, 0, "Elemen HTML biasanya terdiri dari...", listOf("Tag pembuka, isi, dan tag penutup", "Database dan server", "Warna dan ikon", "Akun dan password"), 0),

        q(10, 1, "Deklarasi yang umum ditulis paling atas pada dokumen HTML5 adalah...", listOf("<html>", "<!DOCTYPE html>", "<body>", "<head>"), 1),
        q(11, 1, "Elemen <head> biasanya berisi...", listOf("Konten visual utama", "Metadata dokumen", "Tabel pengguna", "Gambar profil"), 1),
        q(12, 1, "Elemen yang menampung konten yang terlihat di halaman web adalah...", listOf("<title>", "<head>", "<body>", "<meta>"), 2),
        q(13, 1, "Tag <title> berfungsi untuk...", listOf("Judul tab browser", "Membuat paragraf", "Memasang gambar", "Membuat tautan"), 0),
        q(14, 1, "Urutan struktur dasar yang paling tepat adalah...", listOf("body, head, html", "html, head, body", "head, body, title", "title, html, body"), 1),

        q(20, 2, "Heading terbesar dan paling penting adalah...", listOf("<h1>", "<h3>", "<h6>", "<p>"), 0),
        q(21, 2, "Tag untuk paragraf adalah...", listOf("<br>", "<p>", "<hr>", "<a>"), 1),
        q(22, 2, "Tag <br> digunakan untuk...", listOf("Membuat tautan", "Pindah baris", "Membuat tabel", "Memasukkan video"), 1),
        q(23, 2, "Tag <hr> menghasilkan...", listOf("Garis horizontal", "Judul halaman", "Kotak input", "Daftar bernomor"), 0),
        q(24, 2, "Urutan heading dari terbesar ke terkecil dimulai dari...", listOf("<h6> ke <h1>", "<h1> ke <h6>", "<p> ke <h1>", "<title> ke <body>"), 1),

        q(30, 3, "Tag untuk membuat link adalah...", listOf("<a>", "<linktext>", "<navlink>", "<href>"), 0),
        q(31, 3, "Atribut tujuan pada link adalah...", listOf("src", "alt", "href", "width"), 2),
        q(32, 3, "Nilai target untuk membuka link di tab baru adalah...", listOf("_blank", "_self", "_newtab", "_window"), 0),
        q(33, 3, "Teks yang bisa diklik pada anchor berada...", listOf("Di luar tag <a>", "Di antara tag pembuka dan penutup <a>", "Di dalam atribut src", "Di file CSS"), 1),
        q(34, 3, "Contoh link yang benar adalah...", listOf("<a href=\"page.html\">Buka</a>", "<a src=\"page.html\">Buka</a>", "<href>page.html</href>", "<link>page.html</link>"), 0),

        q(40, 4, "Tag untuk menampilkan gambar adalah...", listOf("<picture-only>", "<img>", "<image>", "<src>"), 1),
        q(41, 4, "Atribut lokasi file gambar adalah...", listOf("href", "title", "src", "target"), 2),
        q(42, 4, "Atribut alt berguna untuk...", listOf("Mengatur warna", "Deskripsi alternatif gambar", "Membuka tab baru", "Membuat tabel"), 1),
        q(43, 4, "Tag <img> termasuk tag...", listOf("Yang wajib punya penutup", "Mandiri tanpa penutup", "Khusus form", "Khusus tabel"), 1),
        q(44, 4, "Atribut untuk mengatur lebar gambar adalah...", listOf("width", "href", "alt", "target"), 0),

        q(50, 5, "Tag untuk daftar tidak berurutan adalah...", listOf("<ol>", "<ul>", "<li>", "<table>"), 1),
        q(51, 5, "Tag item daftar adalah...", listOf("<item>", "<li>", "<tr>", "<td>"), 1),
        q(52, 5, "Tag untuk tabel adalah...", listOf("<table>", "<list>", "<grid>", "<data>"), 0),
        q(53, 5, "Baris tabel dibuat dengan tag...", listOf("<td>", "<tr>", "<th>", "<row>"), 1),
        q(54, 5, "Sel data pada tabel dibuat dengan tag...", listOf("<td>", "<tr>", "<ul>", "<ol>"), 0),

        q(60, 6, "Tag utama untuk membuat form adalah...", listOf("<input>", "<form>", "<label>", "<button>"), 1),
        q(61, 6, "Tag untuk kolom masukan pengguna adalah...", listOf("<input>", "<field>", "<type>", "<value>"), 0),
        q(62, 6, "Tag label berguna untuk...", listOf("Memberi keterangan input", "Membuat gambar", "Membuat heading", "Menutup form"), 0),
        q(63, 6, "Atribut type pada input menentukan...", listOf("Jenis input", "Warna halaman", "Alamat link", "Ukuran tabel"), 0),
        q(64, 6, "Input untuk kata sandi biasanya memakai type...", listOf("text", "password", "email", "button"), 1),

        q(70, 7, "Contoh tag semantik adalah...", listOf("<div>", "<span>", "<article>", "<b>"), 2),
        q(71, 7, "Tag <header> biasanya digunakan untuk...", listOf("Bagian pembuka halaman atau section", "Isi password", "Data tabel", "File video"), 0),
        q(72, 7, "Manfaat semantic HTML adalah...", listOf("Membantu struktur dan aksesibilitas", "Menghapus kebutuhan browser", "Membuat gambar otomatis", "Menyimpan database"), 0),
        q(73, 7, "Konten navigasi cocok diletakkan dalam tag...", listOf("<nav>", "<footer>", "<main>", "<aside>"), 0),
        q(74, 7, "Konten utama halaman cocok memakai tag...", listOf("<main>", "<meta>", "<title>", "<br>"), 0),

        q(80, 8, "Tag untuk audio adalah...", listOf("<sound>", "<audio>", "<music>", "<media-audio>"), 1),
        q(81, 8, "Tag untuk video adalah...", listOf("<movie>", "<video>", "<media>", "<clip>"), 1),
        q(82, 8, "Atribut controls pada media berfungsi untuk...", listOf("Menampilkan kontrol pemutar", "Mengubah warna", "Membuat tabel", "Menambah link"), 0),
        q(83, 8, "Sumber file media dapat ditulis pada atribut...", listOf("href", "src", "alt", "rowspan"), 1),
        q(84, 8, "Teks fallback di dalam tag media akan tampil jika...", listOf("Browser tidak mendukung media", "Internet cepat", "Form dikirim", "Heading dipilih"), 0),

        q(90, 9, "Mini project HTML sebaiknya menggabungkan...", listOf("Struktur, teks, gambar, link, dan form sederhana", "Hanya warna CSS", "Hanya database", "Hanya file audio"), 0),
        q(91, 9, "Halaman profil sederhana biasanya membutuhkan...", listOf("Judul, deskripsi, gambar, dan kontak", "Compiler Kotlin", "Server email wajib", "Database kompleks"), 0),
        q(92, 9, "Agar gambar profil tetap informatif, gunakan atribut...", listOf("alt", "target", "row", "col"), 0),
        q(93, 9, "Kontak email dapat dibuat sebagai link dengan skema...", listOf("mailto:", "email:", "contact:", "src:"), 0),
        q(94, 9, "Langkah akhir mini project yang penting adalah...", listOf("Mengecek struktur dan tampilan di browser", "Menghapus semua tag", "Mengganti HTML dengan PNG", "Menutup browser sebelum diuji"), 0)
    )

    private fun q(
        id: Int,
        topicId: Int,
        pertanyaan: String,
        opsi: List<String>,
        jawabanBenar: Int
    ) = LatihanQuestion(
        id = id,
        topicId = topicId,
        pertanyaan = pertanyaan,
        opsi = opsi,
        jawabanBenar = jawabanBenar
    )
}
