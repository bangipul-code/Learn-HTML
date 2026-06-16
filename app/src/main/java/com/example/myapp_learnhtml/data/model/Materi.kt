package com.example.myapp_learnhtml.data.model

data class MateriItem(
    val title: String,
    val description: String,
    val duration: String,
    val isUnlocked: Boolean = false,
    val isCompleted: Boolean = false
)

val materiItemsStatic = listOf(
    MateriItem(
        title = "Pengenalan HTML",
        description = "Mengenal Fungsi HTML dan Struktur Dasar Halaman Web",
        duration = "8 menit",
        isUnlocked = true
    ),
    MateriItem(
        title = "Struktur Dokumen",
        description = "Mengenal Struktur Utama: doctype, html, head, dan body",
        duration = "10 menit"
    ),
    MateriItem(
        title = "Heading dan Paragraf",
        description = "Susun konten teks yang mudah dibaca.",
        duration = "7 menit"
    ),
    MateriItem(
        title = "Link dan Navigasi",
        description = "Buat hubungan antarhalaman dengan anchor.",
        duration = "9 menit"
    ),
    MateriItem(
        title = "Gambar dan Atribut",
        description = "Gunakan gambar dengan atribut yang jelas.",
        duration = "11 menit"
    ),
    MateriItem(
        title = "List dan Tabel",
        description = "Tampilkan data terstruktur secara rapi.",
        duration = "12 menit"
    ),
    MateriItem(
        title = "Form Dasar",
        description = "Kenali input, label, dan validasi sederhana.",
        duration = "15 menit"
    ),
    MateriItem(
        title = "Semantic HTML",
        description = "Gunakan tag semantik untuk aksesibilitas.",
        duration = "13 menit"
    ),
    MateriItem(
        title = "Media HTML",
        description = "Tambahkan audio dan video dengan kontrol yang tepat.",
        duration = "10 menit"
    ),
    MateriItem(
        title = "Mini Project",
        description = "Gabungkan konsep utama menjadi halaman profil.",
        duration = "20 menit"
    )
)
