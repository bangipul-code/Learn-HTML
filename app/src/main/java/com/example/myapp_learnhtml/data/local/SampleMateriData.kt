package com.example.myapp_learnhtml.data.local

import com.example.myapp_learnhtml.data.model.MateriPage
import com.example.myapp_learnhtml.data.model.MateriTopic

/*
    DAFTAR HALAMAN
    1. Pengenalan HTML
    2. Struktur Dokumen
    3. Heading dan Paragraf
    4. Link dan Navigasi
    5. Gambar dan Atribut
    6. List dan Tabel
    7. Form Dasar
    8. Semantic HTML
    9. Media HTML
    10. Mini Project
*/

object SampleMateriData {

    val topics = listOf(
        MateriTopic(id = 0, title = "Pengenalan HTML"),
        MateriTopic(id = 1, title = "Struktur Dokumen"),
        MateriTopic(id = 2, title = "Heading dan Paragraf"),
        MateriTopic(id = 3, title = "Link dan Navigasi"),
        MateriTopic(id = 4, title = "Gambar dan Atribut")
    )

    val pages = listOf(

        // 1. Pengenalan HTML
        MateriPage(
            id = 0, topicId = 0, pageOrder = 0,
            judul = "Apa itu HTML?",
            deskripsi = "HTML (HyperText Markup Language) adalah bahasa standar untuk membuat halaman web. HTML menyusun struktur halaman web, bertindak sebagai 'kerangka dasar' sebelum dipercantik dengan gaya (styling).",
            analogi = "Analogi: Bayangkan HTML seperti struktur tulang manusia atau kerangka beton pada gedung sebelum dipasang dinding dan cat.",
            contohKode = """
                <h1>Hello World!</h1>
            """.trimIndent(),
            hasilDiWeb = """
                <h1>Hello World!</h1>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"HyperText","definisi":"Teks yang berisi tautan ke teks lain."},{"istilah":"Markup Language","definisi":"Bahasa penanda menggunakan tag (seperti <h1>)."}]"""
        ),

        MateriPage(
            id = 1, topicId = 0, pageOrder = 1,
            judul = "Tag, Elemen, dan Atribut",
            deskripsi = "HTML menggunakan tanda khusus bernama 'tag' yang diapit tanda kurung siku. Kebanyakan tag berpasangan: tag pembuka dan tag penutup (diawali garis miring /). Elemen adalah gabungan tag pembuka, isi, dan tag penutup.",
            analogi = "Analogi: Tag pembuka dan penutup seperti sepasang sepatu kiri dan kanan, harus lengkap agar dapat digunakan dengan benar.",
            contohKode = """
                <p>Ini adalah elemen paragraf.</p>
            """.trimIndent(),
            hasilDiWeb = """
                <p>Ini adalah elemen paragraf.</p>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Tag","definisi":"Kode penanda seperti <p>."},{"istilah":"Elemen","definisi":"Keseluruhan dari tag pembuka, isi teks, hingga tag penutup."}]"""
        ),

        MateriPage(
            id = 2, topicId = 0, pageOrder = 2,
            judul = "Dokumen HTML Pertama",
            deskripsi = "Dokumen HTML disimpan dengan ekstensi file '.html'. Halaman web paling sederhana dimulai dengan tag pembuka html dan diakhiri dengan penutup html.",
            analogi = "Analogi: File HTML seperti sebuah buku, semua halaman dan isinya harus berada di dalam sampul depan dan belakang.",
            contohKode = """
                <html>
                    Halaman Web.
                </html>
            """.trimIndent(),
            hasilDiWeb = """
                <html>
                    Halaman Web.
                </html>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Browser","definisi":"Aplikasi pembaca halaman web (seperti Chrome, Firefox, Safari)."}]"""
        ),


        // 2. Struktur Dokumen
        MateriPage(
            id = 10, topicId = 1, pageOrder = 0,
            judul = "Deklarasi DOCTYPE",
            deskripsi = "Deklarasi <!DOCTYPE html> ditulis di baris paling atas dokumen HTML untuk memberi tahu browser bahwa kita menggunakan HTML versi terbaru (HTML5).",
            analogi = "Analogi: Seperti mencantumkan label 'Surat Resmi' di baris paling atas surat agar penerima langsung tahu formatnya.",
            contohKode = """
                <!DOCTYPE html>
                <html></html>
            """.trimIndent(),
            hasilDiWeb = """
                (Halaman kosong)
                - `!DOCTYPE` tidak memicu tampilan visual apa pun.
            """.trimIndent(),
            glosariumJson = """[{"istilah":"HTML5","definisi":"Standar versi HTML modern yang paling banyak digunakan saat ini."}]"""
        ),

        MateriPage(
            id = 11, topicId = 1, pageOrder = 1,
            judul = "Elemen Head dan Title",
            deskripsi = "Tag <head> berisi informasi latar belakang (metadata) tentang dokumen yang tidak terlihat secara langsung di halaman web utama. Di dalamnya terdapat tag <title> untuk judul tab browser.",
            analogi = "Analogi: Seperti kepala manusia, menyimpan pikiran (metadata) yang tidak terlihat langsung oleh orang lain, namun menentukan identitas (nama).",
            contohKode = """
                <head>
                    <title>Belajar HTML</title>
                </head>
                """.trimIndent(),
            hasilDiWeb = """
                <head>
                    <title>Belajar HTML</title>
                </head>
                <body>
                    (Halaman kosong)
                    - head dan title juga tidak memicu tampilan visual apa pun.
                </body>
                """.trimIndent(),
            glosariumJson = """[{"istilah":"Metadata","definisi":"Informasi terstruktur yang mendeskripsikan informasi lainnya."}]"""
        ),

        MateriPage(
            id = 12, topicId = 1, pageOrder = 2,
            judul = "Elemen Body",
            deskripsi = "Tag <body> menampung seluruh konten visual yang ingin ditampilkan kepada pengunjung situs, seperti teks, gambar, tombol, dan tabel.",
            analogi = "Analogi: Seperti tubuh manusia yang terlihat secara fisik oleh orang lain.",
            contohKode = """
                <body>
                    <p>Ini adalah paragraf!</p>
                </body>
            """.trimIndent(),
            hasilDiWeb = """
                <body>
                    <p>Ini adalah paragraf!</p>
                </body>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Konten Visual","definisi":"Semua bagian halaman web yang dapat dilihat langsung oleh pengguna."}]"""
        ),


        // 3. Heading dan Paragraf
        MateriPage(
            id = 20, topicId = 2, pageOrder = 0,
            judul = "Heading (h1 - h6)",
            deskripsi = "Heading digunakan untuk membuat judul utama dan subjudul. Ada enam tingkatan, dari <h1> (terbesar & terpenting) hingga <h6> (terkecil).",
            analogi = "Analogi: Seperti tajuk berita utama surat kabar (H1) diikuti oleh sub-subjudul kecil di bawahnya (H2, H3, dst.).",
            contohKode = """
                <h1>Judul Bab</h1>
                <h2>Sub-Bab Pertama</h2>
            """.trimIndent(),
            hasilDiWeb = """
                <h1>Judul Bab</h1>
                <h2>Sub-Bab Pertama</h2>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Heading","definisi":"Judul penegas bagian teks."}]"""
        ),

        MateriPage(
            id = 21, topicId = 2, pageOrder = 1,
            judul = "Paragraf (<p>)",
            deskripsi = "Tag <p> digunakan untuk menulis blok teks/paragraf. Browser akan otomatis menambahkan sedikit ruang kosong (margin) di atas dan di bawah paragraf.",
            analogi = "Analogi: Seperti paragraf dalam buku cetak, memisahkan satu kelompok ide dengan kelompok ide lainnya.",
            contohKode = """
                <p>Paragraf satu.</p>
                <p>Paragraf dua.</p>
            """.trimIndent(),
            hasilDiWeb = """
                <p>Paragraf satu.</p>
                <p>Paragraf dua.</p>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Margin","definisi":"Jarak kosong di luar batas/border elemen."}]"""
        ),

        MateriPage(
            id = 22, topicId = 2, pageOrder = 2,
            judul = "Break Line (<br>) & Horizontal Rule (<hr>)",
            deskripsi = "Tag <br> digunakan untuk pindah baris tanpa membuat paragraf baru. Tag <hr> digunakan untuk membuat garis horizontal pembatas. Kedua tag ini tidak memiliki tag penutup (tag mandiri).",
            analogi = "Analogi: <br> seperti menekan tombol Enter pada keyboard ketik, sedangkan <hr> seperti menggarisbawahi kertas pembatas bab.",
            contohKode = """
                Baris Satu <br> 
                Baris Dua
                <hr>
            """.trimIndent(),
            hasilDiWeb = """
                Baris Satu <br> 
                Baris Dua
                <hr>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Void Elements","definisi":"Tag yang tidak memiliki konten penutup, hanya berisi deklarasi tunggal."}]"""
        ),


        // 4. Link dan Navigasi
        MateriPage(
            id = 30, topicId = 3, pageOrder = 0,
            judul = "Tag Anchor (<a>)",
            deskripsi = "Navigasi antarhalaman web menggunakan tag anchor <a> (anchor). Teks di antara tag pembuka dan penutup akan menjadi tautan yang dapat diklik.",
            analogi = "Analogi: Seperti pintu portal ajaib yang menghubungkan satu ruangan ke ruangan lain di tempat yang berbeda.",
            contohKode = "<a>Anchor a</a>",
            hasilDiWeb = "<a>Anchor a</a>",
            glosariumJson = """[{"istilah":"Anchor","definisi":"Penanda jangkar untuk tautan halaman."}]"""
        ),

        MateriPage(
            id = 31, topicId = 3, pageOrder = 1,
            judul = "Atribut href",
            deskripsi = "Atribut href (hypertext reference) diletakkan di dalam tag pembuka <a> untuk menentukan alamat tujuan dari link tersebut.",
            analogi = "Analogi: Seperti alamat rumah pada amplop surat, memberi tahu tujuan ke mana link harus diarahkan.",
            contohKode = """
                <a href="https://google.com">Cari di Google</a>
            """.trimIndent(),
            hasilDiWeb = """
                <a href=https://google.com>Cari di Google</a>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Atribut","definisi":"Informasi tambahan yang disisipkan ke dalam tag pembuka HTML."}]"""
        ),

        MateriPage(
            id = 32, topicId = 3, pageOrder = 2,
            judul = "Membuka Link di Tab Baru",
            deskripsi = "Secara default, link akan terbuka di tab yang sama. Kita bisa memaksa link terbuka di tab browser baru dengan menambahkan atribut target=\"_blank\".",
            analogi = "Analogi: Seperti menyuruh seseorang membuka buku baru di atas meja, tanpa menutup buku lama yang sedang ia baca.",
            contohKode = """
                <a href="halaman.html" target="_blank">Buka Tab Baru</a>
            """.trimIndent(),
            hasilDiWeb = """
                <a href="halaman.html" target="_blank">Buka Tab Baru</a>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"_blank","definisi":"Nilai khusus (keyword) yang memerintahkan browser untuk membuat konteks penjelajahan baru."}]"""
        ),


        // 5. Gambar dan Atribut
        MateriPage(
            id = 40, topicId = 4, pageOrder = 0,
            judul = "Tag Gambar (<img>)",
            deskripsi = "Tag <img> digunakan untuk menampilkan gambar. Tag ini merupakan tag mandiri (tidak memiliki pasangan tag penutup).",
            analogi = "Analogi: Seperti memasang bingkai foto di dinding kamar. Bingkai itu berdiri sendiri tanpa penutup.",
            contohKode = """
                <img src='logo.png'>
            """.trimIndent(),
            hasilDiWeb = """
                <img src='https://www.w3.org/html/logo/img/mark-word-icon.png>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Self-closing/Mandiri","definisi":"Tag yang tidak mengapit konten teks sehingga tidak memerlukan tag penutup."}]"""
        ),

        MateriPage(
            id = 41, topicId = 4, pageOrder = 1,
            judul = "Atribut src dan alt",
            deskripsi = "Atribut src (source) berisi lokasi gambar. Atribut alt (alternate text) berisi deskripsi gambar yang muncul jika gambar gagal dimuat atau dibaca oleh pembaca layar (screen reader) bagi penyandang disabilitas.",
            analogi = "Analogi: src adalah peta arah jalan ke toko foto, sedangkan alt adalah deskripsi tertulis tentang foto tersebut ketika mati lampu.",
            contohKode = """
                <img src="kucing.jpg" alt="Foto Kucing Hitam">
            """.trimIndent(),
            hasilDiWeb = """
                <img src='https://images.unsplash.com/photo-1603108025081-4146ecdec5b5?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'>
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Screen Reader","definisi":"Perangkat lunak pembantu tunanetra yang membacakan teks di layar."}]"""
        ),

        MateriPage(
            id = 42, topicId = 4, pageOrder = 2,
            judul = "Mengatur Ukuran Gambar",
            deskripsi = "Kita dapat mengatur lebar (width) dan tinggi (height) gambar menggunakan atribut dalam satuan piksel secara langsung pada tag <img>.",
            analogi = "Analogi: Menentukan ukuran cetak foto (misal: lebar 20cm dan tinggi 15cm) agar pas di dalam bingkai.",
            contohKode = """
                <img src="kucing.jpg" width="200" height="150" alt="Foto Kucing">
            """.trimIndent(),
            hasilDiWeb = """
                <img src width="200" height="150"="https://plus.unsplash.com/premium_photo-1667030474693-6d0632f97029?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D">
            """.trimIndent(),
            glosariumJson = """[{"istilah":"Piksel","definisi":"Unsur gambar atau representasi titik terkecil dalam gambar grafis."}]"""
        )
    )
}
