package com.isdmoldova.shipmentcontrolbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cargo_chat_message_log")
public class CargoChatMessageLog extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "message_sender")
    private String messageSender;

}
