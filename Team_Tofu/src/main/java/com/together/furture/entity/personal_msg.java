package com.together.furture.entity;

import java.sql.Timestamp;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class personal_msg {
    private int p_msg_idx;
    private String p_msg_content;
    private Timestamp sended_at;
    private String sender_id;
    private String receiver_id;
    private String is_read;
}