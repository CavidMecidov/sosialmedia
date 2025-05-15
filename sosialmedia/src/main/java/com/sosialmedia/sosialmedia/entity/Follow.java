package com.sosialmedia.sosialmedia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User follower;//izleyen
    @ManyToOne
    @JoinColumn(name = "followed_id",nullable = false)
    private User following;//izlediyim
    private LocalDateTime  followAt;

}
