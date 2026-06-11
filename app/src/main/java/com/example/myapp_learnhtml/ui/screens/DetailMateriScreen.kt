package com.example.myapp_learnhtml.ui.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

private data class MateriPage(
    val judul: String,
    val deskripsi: String,
    val analogi: String,
    val contohKode: String,
    val hasilDiWeb: String,
    val pertanyaan: String,
    val pilihanJawaban: List<String>,
    val correctAnswerIndex: Int,
    val jawabSalah: String,
    val jawabBenar: String,
    val glosarium: String
)

private data class MateriDetail(
    val title: String,
    val pages: List<MateriPage>
)

// Data statis untuk 5 materi pertama
private val materiDetails = listOf(
    // 0: Pengenalan HTML
    MateriDetail(
        title = "Pengenalan HTML",
        pages = listOf(
            MateriPage(
                judul = "Apa itu HTML?",
                deskripsi = "HTML (HyperText Markup Language) adalah bahasa standar untuk membuat halaman web. HTML menyusun struktur halaman web, bertindak sebagai 'kerangka dasar' sebelum dipercantik dengan gaya (styling).",
                analogi = "Analogi: Bayangkan HTML seperti struktur tulang manusia atau kerangka beton pada gedung sebelum dipasang dinding dan cat.",
                contohKode = "<!-- Ini adalah contoh komentar dalam HTML -->\n<h1>Halo Dunia</h1>",
                hasilDiWeb = "<h1>Hello World</h1>",
                pertanyaan = "Apakah kegunaan utama dari HTML?",
                pilihanJawaban = listOf(
                    "Mempercantik warna dan memberikan animasi pada halaman web",
                    "Menyusun kerangka struktur dasar halaman web",
                    "Membuat sistem database penyimpanan di server web"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: HTML berfokus pada struktur/kerangka, sedangkan warna/animasi adalah tugas CSS.",
                jawabBenar = "Benar! HTML adalah kerangka dasar halaman web.",
                glosarium = "HyperText: Teks yang berisi tautan ke teks lain.\nMarkup Language: Bahasa penanda menggunakan tag (seperti <h1>)."
            ),

            MateriPage(
                judul = "Tag, Elemen, dan Atribut",
                deskripsi = "HTML menggunakan tanda khusus bernama 'tag' yang diapit tanda kurung siku. Kebanyakan tag berpasangan: tag pembuka dan tag penutup (diawali garis miring /). Elemen adalah gabungan tag pembuka, isi, dan tag penutup.",
                analogi = "Analogi: Tag pembuka dan penutup seperti sepasang sepatu kiri dan kanan, harus lengkap agar dapat digunakan dengan benar.",
                contohKode = "<p>Ini adalah elemen paragraf.</p>",
                hasilDiWeb = "<p>Ini adalah elemen paragraf.</p>",
                pertanyaan = "Manakah penulisan tag penutup paragraf yang benar?",
                pilihanJawaban = listOf(
                    "<p>",
                    "</p>",
                    "<p/>"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: Tag penutup selalu diawali dengan tanda garis miring (/) setelah kurung siku buka.",
                jawabBenar = "Benar! </p> adalah tag penutup paragraf.",
                glosarium = "Tag: Kode penanda seperti <p>.\nElemen: Keseluruhan dari tag pembuka, isi teks, hingga tag penutup."
            ),

            MateriPage(
                judul = "Dokumen HTML Pertama",
                deskripsi = "Dokumen HTML disimpan dengan ekstensi file '.html'. Halaman web paling sederhana dimulai dengan tag pembuka html dan diakhiri dengan penutup html.",
                analogi = "Analogi: File HTML seperti sebuah buku, semua halaman dan isinya harus berada di dalam sampul depan dan belakang.",
                contohKode = "<html>\n  Halaman web pertama saya.\n</html>",
                hasilDiWeb = "<html>Halaman web pertama saya.</html>",
                pertanyaan = "Apa ekstensi file yang digunakan untuk menyimpan dokumen HTML?",
                pilihanJawaban = listOf(
                    ".html",
                    ".txt",
                    ".doc"
                ),
                correctAnswerIndex = 0,
                jawabSalah = "Salah! Petunjuk: Ekstensi harus mewakili jenis dokumen HTML (.html).",
                jawabBenar = "Benar! File harus disimpan dengan ekstensi .html agar dapat dibaca oleh browser web.",
                glosarium = "Browser: Aplikasi pembaca halaman web (seperti Chrome, Firefox, Safari)."
            )
        )
    ),

    // 1: Struktur Dokumen
    MateriDetail(
        title = "Struktur Dokumen",
        pages = listOf(
            MateriPage(
                judul = "Deklarasi DOCTYPE",
                deskripsi = "Deklarasi <!DOCTYPE html> ditulis di baris paling atas dokumen HTML untuk memberi tahu browser bahwa kita menggunakan HTML versi terbaru (HTML5).",
                analogi = "Analogi: Seperti mencantumkan label 'Surat Resmi' di baris paling atas surat agar penerima langsung tahu formatnya.",
                contohKode = "<!DOCTYPE html>\n<html>\n</html>",
                hasilDiWeb = "(Halaman kosong) - DOCTYPE tidak memicu tampilan visual apa pun.",
                pertanyaan = "Di manakah posisi penulisan deklarasi <!DOCTYPE html> yang benar?",
                pilihanJawaban = listOf(
                    "Di dalam tag <body>",
                    "Di baris paling atas, sebelum tag <html>",
                    "Di baris paling bawah dokumen"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: Browser harus mengetahui versi dokumen sebelum membaca elemen lainnya.",
                jawabBenar = "Benar! Deklarasi ini ditulis di baris pertama sebelum tag lainnya.",
                glosarium = "HTML5: Standar versi HTML modern yang paling banyak digunakan saat ini."
            ),

            MateriPage(
                judul = "Elemen Head dan Title",
                deskripsi = "Tag <head> berisi informasi latar belakang (metadata) tentang dokumen yang tidak terlihat secara langsung di halaman web utama. Di dalamnya terdapat tag <title> untuk judul tab browser.",
                analogi = "Analogi: Seperti kepala manusia, menyimpan pikiran (metadata) yang tidak terlihat langsung oleh orang lain, namun menentukan identitas (nama).",
                contohKode = "<head>\n  <title>Belajar HTML</title>\n</head>",
                hasilDiWeb = "<head> <title>Belajar HTML</title> </head>",
                pertanyaan = "Di manakah teks di dalam tag <title> akan muncul?",
                pilihanJawaban = listOf(
                    "Di tengah halaman utama sebagai konten web",
                    "Di tab browser atau bilah judul jendela browser",
                    "Di dalam tombol navigasi halaman"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: Tag <title> mengatur judul tab pada browser, bukan konten halaman utama.",
                jawabBenar = "Benar! Judul tab browser diatur melalui tag <title>.",
                glosarium = "Metadata: Informasi terstruktur yang mendeskripsikan informasi lainnya."
            ),

            MateriPage(
                judul = "Elemen Body",
                deskripsi = "Tag <body> menampung seluruh konten visual yang ingin ditampilkan kepada pengunjung situs, seperti teks, gambar, tombol, dan tabel.",
                analogi = "Analogi: Seperti tubuh manusia yang terlihat secara fisik oleh orang lain.",
                contohKode = "<body>\n  <p>Halo, selamat datang di web saya!</p>\n</body>",
                hasilDiWeb = "<body> <p>HHalo, selamat datang di web saya!</p> </body>",
                pertanyaan = "Konten apa saja yang diletakkan di dalam tag <body>?",
                pilihanJawaban = listOf(
                    "Hanya judul tab browser saja",
                    "Semua konten visual halaman yang ingin dilihat pengunjung",
                    "Hanya data pengaturan server web"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: Elemen body menampung semua elemen visual halaman web.",
                jawabBenar = "Benar! Semua elemen yang ingin ditampilkan diletakkan di dalam body.",
                glosarium = "Konten Visual: Semua bagian halaman web yang dapat dilihat langsung oleh pengguna."
            )
        )
    ),

    // 2: Heading dan Paragraf
    MateriDetail(
        title = "Heading dan Paragraf",
        pages = listOf(
            MateriPage(
                judul = "Heading (h1 - h6)",
                deskripsi = "Heading digunakan untuk membuat judul utama dan subjudul. Ada enam tingkatan, dari <h1> (terbesar & terpenting) hingga <h6> (terkecil).",
                analogi = "Analogi: Seperti tajuk berita utama surat kabar (H1) diikuti oleh sub-subjudul kecil di bawahnya (H2, H3, dst.).",
                contohKode = "<h1>Judul Bab</h1>\n<h2>Sub-Bab Pertama</h2>",
                hasilDiWeb = "<h1>Judul Bab</h1> <h2>Sub-Bab Pertama</h2>",
                pertanyaan = "Manakah tag heading yang menghasilkan ukuran teks paling besar?",
                pilihanJawaban = listOf(
                    "<h6>",
                    "<h1>",
                    "<h3>"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: Semakin kecil angka indeks h, semakin besar ukuran teksnya (1 adalah yang terbesar).",
                jawabBenar = "Benar! <h1> menghasilkan teks paling besar dan memiliki tingkat kepentingan tertinggi.",
                glosarium = "Heading: Judul penegas bagian teks."
            ),

            MateriPage(
                judul = "Paragraf (<p>)",
                deskripsi = "Tag <p> digunakan untuk menulis blok teks/paragraf. Browser akan otomatis menambahkan sedikit ruang kosong (margin) di atas dan di bawah paragraf.",
                analogi = "Analogi: Seperti paragraf dalam buku cetak, memisahkan satu kelompok ide dengan kelompok ide lainnya.",
                contohKode = "<p>Paragraf satu.</p>\n<p>Paragraf dua.</p>",
                hasilDiWeb = "<p>Paragraf satu.</p> <p>Paragraf dua.</p>",
                pertanyaan = "Apakah yang otomatis dilakukan browser saat kita menggunakan tag <p>?",
                pilihanJawaban = listOf(
                    "Membuat teks menjadi tebal dan besar",
                    "Menyisipkan garis horizontal pembatas",
                    "Memberikan spasi kosong di atas dan di bawah paragraf"
                ),
                correctAnswerIndex = 2,
                jawabSalah = "Salah! Petunjuk: Browser membantu memisahkan teks paragraf dengan spasi kosong secara otomatis.",
                jawabBenar = "Benar! Spasi vertikal otomatis ditambahkan oleh browser di antara paragraf.",
                glosarium = "Margin: Jarak kosong di luar batas/border elemen."
            ),

            MateriPage(
                judul = "Break Line (<br>) & Horizontal Rule (<hr>)",
                deskripsi = "Tag <br> digunakan untuk pindah baris tanpa membuat paragraf baru. Tag <hr> digunakan untuk membuat garis horizontal pembatas. Kedua tag ini tidak memiliki tag penutup (tag mandiri).",
                analogi = "Analogi: <br> seperti menekan tombol Enter pada keyboard ketik, sedangkan <hr> seperti menggarisbawahi kertas pembatas bab.",
                contohKode = "Baris Satu<br>Baris Dua\n<hr>",
                hasilDiWeb = "Baris Satu <br> Baris Dua <hr> ",
                pertanyaan = "Manakah pernyataan yang benar mengenai tag <br> dan <hr>?",
                pilihanJawaban = listOf(
                    "Mereka memerlukan tag penutup seperti </br>",
                    "Mereka adalah tag mandiri tanpa tag penutup",
                    "Mereka hanya dapat diletakkan di dalam tag <head>"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: <br> dan <hr> adalah contoh tag kosong (void elements) yang tidak berpasangan.",
                jawabBenar = "Benar! Keduanya adalah tag mandiri yang tidak memerlukan penutup.",
                glosarium = "Void Elements: Tag yang tidak memiliki konten penutup, hanya berisi deklarasi tunggal."
            )
        )
    ),

    // 3: Link dan Navigasi
    MateriDetail(
        title = "Link dan Navigasi",
        pages = listOf(
            MateriPage(
                judul = "Tag Anchor (<a>)",
                deskripsi = "Navigasi antarhalaman web menggunakan tag anchor <a> (anchor). Teks di antara tag pembuka dan penutup akan menjadi tautan yang dapat diklik.",
                analogi = "Analogi: Seperti pintu portal ajaib yang menghubungkan satu ruangan ke ruangan lain di tempat yang berbeda.",
                contohKode = "<a>Klik saya</a>",
                hasilDiWeb = "Klik saya",
                pertanyaan = "Apa fungsi utama dari tag <a> di HTML?",
                pilihanJawaban = listOf(
                    "Membuat teks tebal penjelas",
                    "Menyisipkan gambar animasi",
                    "Membuat tautan/link navigasi antarhalaman"
                ),

                correctAnswerIndex = 2,
                jawabSalah = "Salah! Petunjuk: Anchor (<a>) digunakan untuk menautkan/menghubungkan halaman.",
                jawabBenar = "Benar! Tag <a> berfungsi untuk membuat tautan navigasi.",
                glosarium = "Anchor: Penanda jangkar untuk tautan halaman."
            ),

            MateriPage(
                judul = "Atribut href",
                deskripsi = "Atribut href (hypertext reference) diletakkan di dalam tag pembuka <a> untuk menentukan alamat tujuan dari link tersebut.",
                analogi = "Analogi: Seperti alamat rumah pada amplop surat, memberi tahu tujuan ke mana link harus diarahkan.",
                contohKode = "<a href=\"https://google.com\">Cari di Google</a>",
                hasilDiWeb = "Cari di Google (Teks biru bergaris bawah)",
                pertanyaan = "Di manakah letak penulisan atribut href yang tepat?",
                pilihanJawaban = listOf(
                    "Di dalam tag pembuka <a>",
                    "Di dalam tag penutup </a>",
                    "Di luar teks konten tautan"
                ),
                correctAnswerIndex = 0,
                jawabSalah = "Salah! Petunjuk: Semua atribut HTML selalu ditulis di dalam tag pembuka sebelum tanda kurung siku tutup.",
                jawabBenar = "Benar! Atribut ditulis di dalam tag pembuka.",
                glosarium = "Atribut: Informasi tambahan yang disisipkan ke dalam tag pembuka HTML."
            ),

            MateriPage(
                judul = "Membuka Link di Tab Baru",
                deskripsi = "Secara default, link akan terbuka di tab yang sama. Kita bisa memaksa link terbuka di tab browser baru dengan menambahkan atribut target=\"_blank\".",
                analogi = "Analogi: Seperti menyuruh seseorang membuka buku baru di atas meja, tanpa menutup buku lama yang sedang ia baca.",
                contohKode = "<a href=\"halaman.html\" target=\"_blank\">Buka Tab Baru</a>",
                hasilDiWeb = "Buka Tab Baru (Mengklik akan membuka halaman di tab baru)",
                pertanyaan = "Atribut apa yang digunakan untuk membuka link di tab baru?",
                pilihanJawaban = listOf(
                    "target=\"_new\"",
                    "target=\"_blank\"",
                    "open=\"new_tab\""
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: Atribut target dengan nilai khusus '_blank' adalah standar HTML untuk tab baru.",
                jawabBenar = "Benar! target=\"_blank\" adalah cara standar membuka tab baru.",
                glosarium = "_blank: Nilai khusus (keyword) yang memerintahkan browser untuk membuat konteks penjelajahan baru."
            )
        )
    ),

    // 4: Gambar dan Atribut
    MateriDetail(
        title = "Gambar dan Atribut",
        pages = listOf(
            MateriPage(
                judul = "Tag Gambar (<img>)",
                deskripsi = "Tag <img> digunakan untuk menampilkan gambar. Tag ini merupakan tag mandiri (tidak memiliki pasangan tag penutup).",
                analogi = "Analogi: Seperti memasang bingkai foto di dinding kamar. Bingkai itu berdiri sendiri tanpa penutup.",
                contohKode = "<img src=\"logo.png\">",
                hasilDiWeb = "[Tampilan Gambar Logo]",
                pertanyaan = "Apakah tag <img> membutuhkan tag penutup seperti </img>?",
                pilihanJawaban = listOf(
                    "Ya, harus memiliki tag penutup",
                    "Tidak, tag img adalah tag mandiri",
                    "Tergantung pada jenis gambar yang dimasukkan"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: Tag gambar adalah void element sehingga tidak membutuhkan tag penutup terpisah.",
                jawabBenar = "Benar! Tag <img> adalah tag mandiri.",
                glosarium = "Self-closing/Mandiri: Tag yang tidak mengapit konten teks sehingga tidak memerlukan tag penutup."
            ),

            MateriPage(
                judul = "Atribut src dan alt",
                deskripsi = "Atribut src (source) berisi lokasi gambar. Atribut alt (alternate text) berisi deskripsi gambar yang muncul jika gambar gagal dimuat atau dibaca oleh pembaca layar (screen reader) bagi penyandang disabilitas.",
                analogi = "Analogi: src adalah peta arah jalan ke toko foto, sedangkan alt adalah deskripsi tertulis tentang foto tersebut ketika mati lampu.",
                contohKode = "<img src=\"kucing.jpg\" alt=\"Foto Kucing Hitam\">",
                hasilDiWeb = "[Tampilan Foto Kucing Hitam / Teks alternatif jika gambar gagal]",
                pertanyaan = "Apa kegunaan dari atribut alt pada tag <img>?",
                pilihanJawaban = listOf(
                    "Mengubah format gambar dari JPG ke PNG",
                    "Menampilkan teks deskripsi jika gambar gagal dimuat",
                    "Mengatur transparansi gambar"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: alt singkatan dari alternate text, digunakan sebagai teks cadangan penjelas gambar.",
                jawabBenar = "Benar! alt berfungsi menampilkan deskripsi gambar sebagai alternatif.",
                glosarium = "Screen Reader: Perangkat lunak pembantu tunanetra yang membacakan teks di layar."
            ),

            MateriPage(
                judul = "Mengatur Ukuran Gambar",
                deskripsi = "Kita dapat mengatur lebar (width) dan tinggi (height) gambar menggunakan atribut dalam satuan piksel secara langsung pada tag <img>.",
                analogi = "Analogi: Menentukan ukuran cetak foto (misal: lebar 20cm dan tinggi 15cm) agar pas di dalam bingkai.",
                contohKode = "<img src=\"kucing.jpg\" width=\"200\" height=\"150\" alt=\"Kucing\">",
                hasilDiWeb = "[Gambar Kucing dengan lebar 200 piksel dan tinggi 150 piksel]",
                pertanyaan = "Satuan default apakah yang digunakan ketika menentukan atribut width=\"200\"?",
                pilihanJawaban = listOf(
                    "Persentase (%)",
                    "Piksel (pixels)",
                    "Sentimeter (cm)"
                ),
                correctAnswerIndex = 1,
                jawabSalah = "Salah! Petunjuk: HTML menggunakan satuan titik layar (piksel) secara default untuk width dan height.",
                jawabBenar = "Benar! Satuan default yang digunakan adalah piksel.",
                glosarium = "Piksel: Unsur gambar atau representasi titik terkecil dalam gambar grafis."
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMateriScreen(
    materiIndex: Int,
    navController: NavController
) {
    // Validasi index agar tidak keluar batas
    val safeIndex = if (materiIndex in materiDetails.indices) materiIndex else 0
    val materi = materiDetails[safeIndex]
    val pages = materi.pages

    var currentPageIndex by rememberSaveable { mutableIntStateOf(0) }
    val page = pages[currentPageIndex]

    var selectedOptionIndex by rememberSaveable(currentPageIndex) { mutableStateOf<Int?>(null) }
    var glossaryExpanded by rememberSaveable(currentPageIndex) { mutableStateOf(false) }
    var menuExpanded by remember { mutableStateOf(false) }

    val progress = (currentPageIndex + 1).toFloat() / pages.size

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = materi.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.border(width = 1.dp, color = Color(0xFFE0E0E0))
            )
        },
        bottomBar = {
            // Navigation Bottom Bar (Sebelumnya & Berikutnya)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color(0xFFE0E0E0)),
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Tombol Sebelumnya
                    OutlinedButton(
                        onClick = {
                            if (currentPageIndex > 0) {
                                currentPageIndex--
                            }
                        },
                        enabled = currentPageIndex > 0,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Black,
                            disabledContentColor = Color.Gray
                        ),
                        modifier = Modifier.width(130.dp)
                    ) {
                        Text(text = "Sebelumnya", fontWeight = FontWeight.SemiBold)
                    }

                    // Tombol Berikutnya / Selesai
                    Button(
                        onClick = {
                            if (currentPageIndex < pages.lastIndex) {
                                currentPageIndex++
                            } else {
                                navController.popBackStack()
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.width(130.dp)
                    ) {
                        Text(
                            text = if (currentPageIndex == pages.lastIndex) "Selesai" else "Berikutnya",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Heuristic 1: Visibility of System Status (Progress Indicator)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Heuristic 7: Flexibility and Efficiency of Use (Dropdown to jump pages)
                        Box {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable { menuExpanded = true }
                                    .padding(vertical = 4.dp)
                            ) {
                                Text(
                                    text = "Langkah ${currentPageIndex + 1} dari ${pages.size}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "Pilih Halaman",
                                    tint = Color.Black,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            DropdownMenu(
                                expanded = menuExpanded,
                                onDismissRequest = { menuExpanded = false },
                                modifier = Modifier.background(Color.White)
                            ) {
                                pages.forEachIndexed { index, p ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "${index + 1}. ${p.judul}",
                                                color = if (index == currentPageIndex) Color.Black else Color.Gray,
                                                fontWeight = if (index == currentPageIndex) FontWeight.Bold else FontWeight.Normal
                                            )
                                        },
                                        onClick = {
                                            currentPageIndex = index
                                            menuExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // Persentase Progress
                        Text(
                            text = "${(progress * 100).toInt()}% Selesai",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                        color = Color.Black,
                        trackColor = Color(0xFFE0E0E0)
                    )
                }
            }

            // Halaman Judul
            Text(
                text = page.judul,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            // Card Deskripsi / Materi
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = page.deskripsi,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = Color.Black
                    )
                }
            }

            // Heuristic 2: Match between system and real world (Analogi)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Analogi",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = page.analogi,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        color = Color(0xFF4A4A4A)
                    )
                }
            }

            // Heuristic 6: Recognition rather than recall (Contoh Kode & Visualisasi Hasil)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Contoh Kode HTML:",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                // Kode editor monospaced
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color(0xFFCCCCCC), shape = RoundedCornerShape(8.dp))
                        .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = page.contohKode,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                }

                Text(
                    text = "Hasil Render di Browser:",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                // Browser output simulation
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    AndroidWebView(htmlContent = page.hasilDiWeb)

                    // Text(
                    //     text = page.simulatedOutput,
                    //     fontSize = 14.sp,
                    //     fontWeight = FontWeight.Medium,
                    //     color = Color.Black
                    // )
                }
            }

            // Heuristic 5: Error Prevention & Interactive Simulation
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Simulasi Interaktif",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = page.pertanyaan,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )

                    // Pilihan Jawaban
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        page.pilihanJawaban.forEachIndexed { index, option ->
                            val isSelected = selectedOptionIndex == index
                            val cardBg = if (isSelected) Color(0xFFF0F0F0) else Color.White
                            val cardBorderWidth = if (isSelected) 2.dp else 1.dp
                            val cardBorderColor = if (isSelected) Color.Black else Color(0xFFE0E0E0)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = cardBorderWidth,
                                        color = cardBorderColor,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(color = cardBg, shape = RoundedCornerShape(8.dp))
                                    .clickable { selectedOptionIndex = index }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(18.dp)
                                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(9.dp))
                                        .background(
                                            color = if (isSelected) Color.Black else Color.Transparent,
                                            shape = RoundedCornerShape(9.dp)
                                        )
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                                )
                            }
                        }
                    }

                    // Heuristic 9: Help users recognize, diagnose, and recover from errors (Feedback)
                    selectedOptionIndex?.let { index ->
                        val isCorrect = index == page.correctAnswerIndex
                        val feedbackBg = Color(0xFFFAFAFA)
                        val feedbackBorderColor = if (isCorrect) Color(0xFF888888) else Color(0xFFCCCCCC)

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(width = 1.dp, color = feedbackBorderColor, shape = RoundedCornerShape(8.dp))
                                .background(color = feedbackBg, shape = RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = if (isCorrect) Icons.Filled.Check else Icons.Filled.Close,
                                    contentDescription = if (isCorrect) "Benar" else "Salah",
                                    tint = Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                                Column {
                                    Text(
                                        text = if (isCorrect) "BENAR" else "SALAH",
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 12.sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = if (isCorrect) page.jawabBenar else page.jawabSalah,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Black,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Heuristic 10: Help and documentation (Glossary expandable)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val glossaryRotation = if (glossaryExpanded) 180f else 0f
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { glossaryExpanded = !glossaryExpanded }
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Glosarium",
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Glosarium & Bantuan Cepat",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = if (glossaryExpanded) "Tutup" else "Buka",
                            tint = Color.Black,
                            modifier = Modifier.rotate(glossaryRotation)
                        )
                    }

                    AnimatedVisibility(visible = glossaryExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                                .padding(bottom = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            page.glosarium.split("\n").forEach { termLine ->
                                val parts = termLine.split(":", limit = 2)
                                if (parts.size == 2) {
                                    Column(modifier = Modifier.padding(bottom = 4.dp)) {
                                        Text(
                                            text = parts[0].trim(),
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = parts[1].trim(),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.DarkGray,
                                            lineHeight = 16.sp
                                        )
                                    }
                                } else {
                                    Text(
                                        text = termLine,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AndroidWebView(htmlContent: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = false
            }
        },
        update = { webView ->
            webView.loadDataWithBaseURL(
                null,
                htmlContent,
                "text/html",
                "utf-8",
                null
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailMateriScreenPreview() {
    val navController = rememberNavController()
    DetailMateriScreen(materiIndex = 0, navController = navController)
}
