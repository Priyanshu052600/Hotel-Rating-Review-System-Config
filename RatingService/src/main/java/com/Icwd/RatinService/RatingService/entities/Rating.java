package com.Icwd.RatinService.RatingService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @Column(name = "ratingid")
    private String ratingId;
    @Column(name = "userid")
    private String userId;
    @Column(name = "hotelid")
    private String hotelId;
    @Column(name ="ratingdetail")
    private int rating;
    @Column(name = "remark")
    private String remark;

}
