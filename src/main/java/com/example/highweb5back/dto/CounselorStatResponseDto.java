package com.example.highweb5back.dto;

import lombok.Data;

@Data
public class CounselorStatResponseDto {
    private Long totalClientNum;
    private Long TodayClientNum;
    private Long AfterLoginClientNum;
}
