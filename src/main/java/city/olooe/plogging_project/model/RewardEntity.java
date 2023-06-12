package city.olooe.plogging_project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_reward")
/*
 * @author : 이재원
 * 
 * @date: 2023.06.01
 * 
 * @brief: 회원 포인트 클래스
 */
public class RewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardNo; // PK

    private String name;

    private String detail;

    @Enumerated(EnumType.STRING)
    @Setter
    private RewardTypeStatus type;

}
