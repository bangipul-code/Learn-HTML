package com.example.myapp_learnhtml.data.local

data class PraktikExercise(
    val topicIndex: Int,
    val langkah: List<String>,
    val kodeAwal: String,
    val kriteriaSelesai: List<String>
)

object SamplePraktikData {

    val exercises = listOf(
        PraktikExercise(
            topicIndex = 0,
            langkah = listOf(
                "Buat tag heading <h1> dengan teks apa saja.",
                "Tambahkan satu paragraf <p> di bawahnya.",
                "Jalankan kode untuk melihat hasilnya."
            ),
            kodeAwal = """
                <h1>Hello World!</h1>
                <p>Ini adalah halaman HTML pertamaku.</p>
            """.trimIndent(),
            kriteriaSelesai = listOf("h1", "p")
        ),

        PraktikExercise(
            topicIndex = 1,
            langkah = listOf(
                "Tambahkan deklarasi <!DOCTYPE html> di baris pertama.",
                "Bungkus konten dengan tag <html>.",
                "Buat <head> berisi <title> dan <body> berisi paragraf."
            ),
            kodeAwal = """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>Halaman Saya</title>
                </head>
                <body>
                    <p>Selamat datang!</p>
                </body>
            </html>
            """.trimIndent(),
            kriteriaSelesai = listOf("DOCTYPE", "html", "head", "title", "body")
        ),

        PraktikExercise(
            topicIndex = 2,
            langkah = listOf(
                "Buat minimal 3 heading berbeda (<h1>, <h2>, <h3>).",
                "Tambahkan minimal 2 paragraf <p>.",
                "Gunakan <br> untuk pindah baris di dalam paragraf."
            ),
            kodeAwal = """
                <h1>Judul Utama</h1>
                <h2>Sub Judul</h2>
                <p>Paragraf satu.</p>
                <p>Paragraf dua.</p>
            """.trimIndent(),
            kriteriaSelesai = listOf("h1", "h2", "p")
        ),

        PraktikExercise(
            topicIndex = 3,
            langkah = listOf(
                "Buat tag anchor <a> dengan href ke URL bebas.",
                "Tambahkan teks tautan yang jelas.",
                "Coba buka link di tab baru dengan target=\"_blank\"."
            ),
            kodeAwal = """
                <p>Klik tautan di bawah:</p>
                <a href="https://google.com">Cari di Google</a>
            """.trimIndent(),
            kriteriaSelesai = listOf("a", "href")
        ),

        PraktikExercise(
            topicIndex = 4,
            langkah = listOf(
                "Sisipkan tag <img> dengan src gambar dari internet.",
                "Tambahkan atribut alt untuk deskripsi gambar.",
                "Atur ukuran gambar dengan width dan height."
            ),
            kodeAwal = """
                <h1>Gambar Logo</h1>
                <img src="https://www.w3.org/html/logo/img/mark-word-icon.png" alt="Logo HTML5">
            """.trimIndent(),
            kriteriaSelesai = listOf("img", "src", "alt")
        ),

        PraktikExercise(
            topicIndex = 5,
            langkah = listOf(
                "Buat list tak berurut (<ul>) dengan minimal 3 item.",
                "Buat list berurut (<ol>) dengan minimal 2 item.",
                "Buat tabel (<table>) dengan minimal 2 baris data."
            ),
            kodeAwal = """
                <h1>Data Terstruktur</h1>
                <ul>
                    <li>Item Satu</li>
                    <li>Item Dua</li>
                </ul>
                
                <table>
                    <tr>
                        <th>Nama</th>
                        <th>Umur</th>
                    </tr>
                    <tr>
                        <td>Budi</td>
                        <td>20</td>
                    </tr>
                </table>
            """.trimIndent(),
            kriteriaSelesai = listOf("ul", "ol", "table")
        ),

        PraktikExercise(
            topicIndex = 6,
            langkah = listOf(
                "Buat form dengan tag <form>.",
                "Tambahkan minimal 2 input berbeda (text dan email).",
                "Sertakan label untuk setiap input menggunakan <label>."
            ),
            kodeAwal = """
                <h1>Form Pendaftaran</h1>
                <form>
                    <label for="nama">Nama:</label><br>
                    <input type="text" id="nama" name="nama"><br><br>
                    <label for="email">Email:</label><br>
                    <input type="email" id="email" name="email"><br><br>
                    <button type="submit">Kirim</button>
                </form>
            """.trimIndent(),
            kriteriaSelesai = listOf("form", "input", "label")
        ),

        PraktikExercise(
            topicIndex = 7,
            langkah = listOf(
                "Gunakan tag <header> untuk bagian atas halaman.",
                "Gunakan tag <nav> untuk navigasi.",
                "Gunakan tag <main>, <section>, dan <footer> untuk konten."
            ),
            kodeAwal = """
                <header>
                    <h1>Website Saya</h1>
                    <nav>
                        <a href="#">Beranda</a> |
                        <a href="#">Tentang</a>
                    </nav>
                </header>

                <main>
                    <section>
                        <h2>Selamat Datang</h2>
                        <p>Ini adalah website sederhana.</p>
                    </section>
                </main>

                <footer>
                    <p>&copy; 2024 Website Saya</p>
                </footer>
            """.trimIndent(),
            kriteriaSelesai = listOf("header", "nav", "main", "footer")
        ),

        PraktikExercise(
            topicIndex = 8,
            langkah = listOf(
                "Sisipkan video dengan tag <video> dan atribut controls.",
                "Tambahkan tag <audio> dengan controls juga.",
                "Pastikan kedua media memiliki tag sumber <source>."
            ),
            kodeAwal = """
                <h1>Media HTML</h1>

                <h2>Video</h2>
                <video width="320" height="240" controls>
                    <source src="video.mp4" type="video/mp4">
                    Browser kamu tidak mendukung video.
                </video>
                
                <h2>Audio</h2>
                <audio controls>
                    <source src="audio.mp3" type="audio/mpeg">
                    Browser kamu tidak mendukung audio.
                </audio>
                """.trimIndent(),
            kriteriaSelesai = listOf("video", "audio", "controls", "source")
        ),

        PraktikExercise(
            topicIndex = 9,
            langkah = listOf(
                "Buat halaman profil lengkap dengan struktur dokumen yang benar.",
                "Gunakan tag semantik: <header>, <main>, <section>, <footer>.",
                "Tambahkan gambar profil dengan <img>.",
                "Buat daftar skill dengan <ul>.",
                "Tambahkan link ke media sosial dengan <a>.",
                "Jalankan kode dan pastikan semua elemen tampil dengan benar."
            ),
            kodeAwal = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Profil Saya</title>
                </head>

                <body>
                    <header>
                        <h1>Nama Saya</h1>
                        <p>Web Developer Pemula</p>
                    </header>

                    <main>
                        <section>
                            <h2>Tentang Saya</h2>
                            <p>Deskripsi singkat tentang diri saya.</p>
                        </section>

                        <section>
                            <h2>Skill</h2>
                            <ul>
                                <li>HTML</li>
                                <li>CSS</li>
                            </ul>
                        </section>
                    </main>
                    <footer>
                        <p>Hubungi saya di email@example.com</p>
                    </footer>
                </body>
                </html>
            """.trimIndent(),
            kriteriaSelesai = listOf("html", "head", "body", "header", "main", "footer", "img", "ul", "a")
        )
    )
}
