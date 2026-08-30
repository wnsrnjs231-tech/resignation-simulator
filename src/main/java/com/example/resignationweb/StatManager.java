package com.example.resignationweb;

// ==================================================
// 플레이어 능력치 관리 클래스
// 스트레스 / 업무 능력 / 체력 / 평판 변화를 적용하고
// 모든 능력치를 0 ~ 100 범위로 제한
// ==================================================
public class StatManager {

    // ==================================================
    // 플레이어 능력치 변화 적용
    // ==================================================
    public void applyChanges(
            Player player,
            int stressChange,
            int abilityChange,
            int staminaChange,
            int reputationChange) {

        // 스트레스 변화 적용
        player.stress =
                clamp(
                        player.stress + stressChange
                );

        // 업무 능력 변화 적용
        player.ability =
                clamp(
                        player.ability + abilityChange
                );

        // 체력 변화 적용
        player.stamina =
                clamp(
                        player.stamina + staminaChange
                );

        // 평판 변화 적용
        player.reputation =
                clamp(
                        player.reputation + reputationChange
                );
    }


    // ==================================================
    // 능력치를 0 ~ 100 범위로 제한
    // ==================================================
    private int clamp(
            int value) {

        // 0보다 작으면 0
        if (value < 0) {
            return 0;
        }

        // 100보다 크면 100
        if (value > 100) {
            return 100;
        }

        // 정상 범위면 그대로 반환
        return value;
    }
}