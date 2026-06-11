package com.example.myapp_learnhtml.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MateriScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color = Color(0xFFFF6B35),
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    ),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Materials",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color.White
                    )
                }
            }
        }

        // Search & Filter Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar()
            }
        }

        // Course Info
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "HTML Complete Tutorial",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Learn HTML from W3Schools",
                    fontSize = 12.sp,
                    color = Color(0xFF6C757D)
                )
            }
        }

        // Chapter 1: HTML Introduction
        item {
            ChapterSection(
                chapterNumber = 1,
                chapterTitle = "HTML Introduction",
                lessons = listOf(
                    Lesson("What is HTML?", "Basics", 10, true),
                    Lesson("HTML Documents", "Basics", 12, true),
                    Lesson("HTML History", "Basics", 8, false)
                )
            )
        }

        // Chapter 2: HTML Basics
        item {
            ChapterSection(
                chapterNumber = 2,
                chapterTitle = "HTML Basics",
                lessons = listOf(
                    Lesson("DOCTYPE", "Foundation", 10, true),
                    Lesson("Elements", "Foundation", 15, true),
                    Lesson("Attributes", "Foundation", 12, false),
                    Lesson("Headings", "Foundation", 11, false)
                )
            )
        }

        // Chapter 3: HTML Text Formatting
        item {
            ChapterSection(
                chapterNumber = 3,
                chapterTitle = "HTML Text Formatting",
                lessons = listOf(
                    Lesson("Paragraphs", "Text", 10, false),
                    Lesson("Text Formatting", "Text", 14, false),
                    Lesson("Quotations", "Text", 9, false),
                    Lesson("Comments", "Text", 8, false)
                )
            )
        }

        // Chapter 4: HTML Links & Media
        item {
            ChapterSection(
                chapterNumber = 4,
                chapterTitle = "HTML Links & Media",
                lessons = listOf(
                    Lesson("Links", "Media", 13, false),
                    Lesson("Images", "Media", 15, false),
                    Lesson("Image Maps", "Media", 11, false),
                    Lesson("Favicon", "Media", 7, false)
                )
            )
        }

        // Chapter 5: HTML Tables
        item {
            ChapterSection(
                chapterNumber = 5,
                chapterTitle = "HTML Tables",
                lessons = listOf(
                    Lesson("Table Basics", "Structure", 12, false),
                    Lesson("Table Headers", "Structure", 10, false),
                    Lesson("Table Borders", "Structure", 9, false),
                    Lesson("Table Padding & Spacing", "Structure", 11, false),
                    Lesson("Colspan & Rowspan", "Structure", 13, false)
                )
            )
        }

        // Chapter 6: HTML Lists
        item {
            ChapterSection(
                chapterNumber = 6,
                chapterTitle = "HTML Lists",
                lessons = listOf(
                    Lesson("Unordered Lists", "Lists", 10, false),
                    Lesson("Ordered Lists", "Lists", 10, false),
                    Lesson("Description Lists", "Lists", 8, false),
                    Lesson("Nested Lists", "Lists", 11, false)
                )
            )
        }

        // Chapter 7: HTML Forms
        item {
            ChapterSection(
                chapterNumber = 7,
                chapterTitle = "HTML Forms",
                lessons = listOf(
                    Lesson("Form Basics", "Interaction", 12, false),
                    Lesson("Input Types", "Interaction", 18, false),
                    Lesson("Attributes", "Interaction", 14, false),
                    Lesson("Form Validation", "Interaction", 13, false)
                )
            )
        }

        // Chapter 8: Semantic & Layout
        item {
            ChapterSection(
                chapterNumber = 8,
                chapterTitle = "Semantic & Layout",
                lessons = listOf(
                    Lesson("Semantic Elements", "Advanced", 15, false),
                    Lesson("Layout", "Advanced", 14, false),
                    Lesson("Responsive", "Advanced", 12, false),
                    Lesson("Meta Tags", "Advanced", 9, false)
                )
            )
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                "Search lessons...",
                fontSize = 13.sp,
                color = Color(0xFF999999)
            )
        },
        modifier = Modifier
            .height(45.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF5F5F5),
            focusedContainerColor = Color(0xFFF5F5F5),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFFFF6B35)
        ),
        textStyle = androidx.compose.material3.LocalTextStyle.current.copy(fontSize = 13.sp)
    )
}

@Composable
fun ChapterSection(
    chapterNumber: Int,
    chapterTitle: String,
    lessons: List<Lesson>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Chapter Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFFF6B35))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Chapter $chapterNumber",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = chapterTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Text(
                text = "${lessons.count { it.completed }}/${lessons.size}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Lessons List
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            lessons.forEach { lesson ->
                LessonCard(lesson = lesson)
            }
        }
    }
}

@Composable
fun LessonCard(lesson: Lesson) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {}
            .background(Color.White),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left content
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Status indicator
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (lesson.completed) Color(0xFFD1FAE5) else Color(0xFFFEF3C7)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (lesson.completed) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Completed",
                            tint = Color(0xFF10B981),
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Start",
                            tint = Color(0xFFF59E0B),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Lesson info
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = lesson.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        CategoryBadge(category = lesson.category)
                        Text(
                            text = "${lesson.duration} min",
                            fontSize = 11.sp,
                            color = Color(0xFF6C757D)
                        )
                    }
                }
            }

            // Right action
            IconButton(
                onClick = {},
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = Color(0xFF6C757D),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun CategoryBadge(category: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFEEF2FF),
        modifier = Modifier.clip(RoundedCornerShape(12.dp))
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF4F46E5)
        )
    }
}

data class Lesson(
    val title: String,
    val category: String,
    val duration: Int,
    val completed: Boolean
)
