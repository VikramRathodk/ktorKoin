package org.devvikram.ktorkoin.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import org.devvikram.ktorkoin.R
import org.devvikram.ktorkoin.models.ConversationItem

data class ConversationInfo(
    val conversationId: String,
    val conversationName: String,
    val conversationDescription: String,
    val conversationType: String,
    val lastMessage: String,
    val lastMessageTime: String,
    val unreadMessagesCount: Int,
    val isPinned: Boolean,
    val isMuted: Boolean,
    val isStarred: Boolean,
    val isArchived: Boolean
)

@Composable
fun ConversationScreen() {
    val conversationList = ArrayList<ConversationInfo>()
    val allConversations = mutableListOf<ConversationItem>()

    val isMenuOpen = remember {
        mutableStateOf(false)
    }

    for (i in 1..10) {
        val conversationType = if (i % 2 == 0) "Group" else "Personal"
        conversationList.add(
            ConversationInfo(
                conversationId = "$i",
                conversationName = if (conversationType == "Personal") "Vikram$i" else "Group $i",
                conversationDescription = if (conversationType == "Personal") "Vikram is a good boy" else "A discussion about various topics.",
                conversationType = conversationType,
                lastMessage = if (conversationType == "Personal") "How are you ?" else "Meeting postponed to next week.",
                lastMessageTime = if (conversationType == "Personal") "10:00 AM" else "11:30 AM",
                unreadMessagesCount = 0,
                isPinned = false,
                isMuted = false,
                isStarred = false,
                isArchived = false
            )
        )
    }

    conversationList.forEach { conversation ->
        when (conversation.conversationType) {
            "Personal" -> {
                allConversations.add(
                    ConversationItem.PersonalConversation(
                        conversationId = conversation.conversationId,
                        conversationName = conversation.conversationName,
                        conversationDescription = conversation.conversationDescription,
                        userProfile = "",
                        conversationType = conversation.conversationType,
                        lastMessage = conversation.lastMessage,
                        lastMessageTime = conversation.lastMessageTime,
                        unreadMessagesCount = conversation.unreadMessagesCount,
                        isPinned = conversation.isPinned,
                        isMuted = conversation.isMuted,
                        isStarred = conversation.isStarred,
                        isArchived = conversation.isArchived

                    )
                )

            }

            "Group" -> {
                val participants = listOf("Participant 1", "Participant 2", "Participant 3")
                allConversations.add(
                    ConversationItem.GroupConversation(
                        conversationId = conversation.conversationId,
                        conversationName = conversation.conversationName,
                        conversationDescription = conversation.conversationDescription,
                        conversationProfile = "",
                        conversationType = conversation.conversationType,
                        lastMessage = conversation.lastMessage,
                        lastMessageTime = conversation.lastMessageTime,
                        unreadMessagesCount = conversation.unreadMessagesCount,
                        isPinned = conversation.isPinned,
                        isMuted = conversation.isMuted,
                        isStarred = conversation.isStarred,
                        isArchived = conversation.isArchived,
                        participants = participants,
                        admins = listOf("Admin 1", "Admin 2")
                    )
                )
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Conversations") },
                actions = {
                    IconButton(onClick = {
                        isMenuOpen.value = true
                    }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                    if (isMenuOpen.value) {
                        DropdownMenu(
                            expanded = isMenuOpen.value,
                            onDismissRequest = { isMenuOpen.value = false }
                        ) {
                            DropdownMenuItem(onClick = { }) {
                                Text(text = "Settings")
                            }
                            DropdownMenuItem(onClick = { }) {
                                Text(text = "Search")
                            }
                        }
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(allConversations.size) {
                when (val item = allConversations[it]) {
                    is ConversationItem.GroupConversation -> {
                        GroupConversationCard(groupConversationItem = item)
                    }

                    is ConversationItem.PersonalConversation -> {
                        PersonalConversationCard(personalConversationItem = item)
                    }
                }
            }
        }

    }

}

@Composable
fun GroupConversationCard(groupConversationItem: ConversationItem.GroupConversation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    groupConversationItem.conversationProfile,
                    error = painterResource(id = R.drawable.baseline_person_24)
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.onSurface, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = groupConversationItem.conversationName,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = groupConversationItem.lastMessage,
                    style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Badge(
                modifier = Modifier
                    .padding(4.dp)
                    .background(MaterialTheme.colors.primary, shape = CircleShape)
            ) {
                Text(
                    text = groupConversationItem.unreadMessagesCount.toString(),
                    style = MaterialTheme.typography.body2.copy(color = Color.White),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun PersonalConversationCard(personalConversationItem: ConversationItem.PersonalConversation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    personalConversationItem.userProfile,
                    error = painterResource(id = R.drawable.baseline_person_24)
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.onSurface, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = personalConversationItem.conversationName,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = personalConversationItem.lastMessage,
                    style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Badge(
                modifier = Modifier
                    .padding(4.dp)
                    .background(MaterialTheme.colors.primary, shape = CircleShape)
            ) {
                Text(
                    text = personalConversationItem.unreadMessagesCount.toString(),
                    style = MaterialTheme.typography.body2.copy(color = Color.White),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

