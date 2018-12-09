package com.zaytsevp.modelservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auto extends BaseEntity {

    private String name;

    private int foundYear;

}
