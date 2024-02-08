package ru.fortech.ahub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;
@Data
@Entity
@Table(name = "refresh_token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @Column(name = "user_id")
    private UUID user_id;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "expdate")
    private Instant expDate;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
