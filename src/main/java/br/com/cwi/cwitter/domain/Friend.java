package br.com.cwi.cwitter.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long friendId;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Status status;

    public Friend(Long userId, Long friendId, Status status) {
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
    }
}
