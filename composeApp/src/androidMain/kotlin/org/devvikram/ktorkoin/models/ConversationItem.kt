package org.devvikram.ktorkoin.models

import kotlinx.serialization.Serializable

@Serializable
sealed class ConversationItem{
    @Serializable
    data class PersonalConversation(
        val conversationId: String = "",
        val conversationName: String = "",
        val conversationDescription: String = "",
        val userProfile : String = "",
        val conversationType : String = "",
        val lastMessage : String = "",
        val lastMessageTime : String = "",
        val unreadMessagesCount : Int = 0,
        val isPinned : Boolean = false,
        val isMuted : Boolean = false,
        val isStarred : Boolean = false,
        val isArchived : Boolean = false,
        val isMedia : Boolean = false,
    ) : ConversationItem()

    @Serializable
    data class GroupConversation(
        val conversationId: String = "",
        val conversationName: String = "",
        val conversationDescription: String = "",
        val conversationProfile : String = "",
        val conversationType : String = "",
        val lastMessage : String = "",
        val lastMessageTime : String = "",
        val unreadMessagesCount : Int = 0,
        val isPinned : Boolean = false,
        val isMuted : Boolean = false,
        val isStarred : Boolean = false,
        val isArchived : Boolean = false,
        val participants: List<String> = emptyList(),
        val admins: List<String> = emptyList(),
        val isMedia : Boolean = false,
    ) : ConversationItem()

}