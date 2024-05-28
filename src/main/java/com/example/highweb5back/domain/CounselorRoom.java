package com.example.highweb5back.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@Getter
public class CounselorRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="client_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member client;

    @OneToOne
    @JoinColumn(name="counselor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member counselor;

    public CounselorRoom(Member counselor, Member client){
        this.client = client;
        this.counselor = counselor;
    }
}
