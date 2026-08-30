package com.example.resignationweb;

// ==================================================
// NPC 관리 클래스
// 팀장 / 선배 / 동료 객체와 관계도 변화를 관리
// ==================================================
public class NpcManager {

    // ==================================================
    // NPC 생성
    // 모두 관계도 50에서 시작
    // ==================================================
    private final Npc teamLeader =
            new Npc(
                    "팀장",
                    50
            );

    private final Npc senior =
            new Npc(
                    "선배",
                    50
            );

    private final Npc coworker =
            new Npc(
                    "동료",
                    50
            );


    // ==================================================
    // 팀장 반환
    // ==================================================
    public Npc getTeamLeader() {

        return teamLeader;
    }


    // ==================================================
    // 선배 반환
    // ==================================================
    public Npc getSenior() {

        return senior;
    }


    // ==================================================
    // 동료 반환
    // ==================================================
    public Npc getCoworker() {

        return coworker;
    }


    // ==================================================
    // 관계도 변경
    // 변경 후 0 ~ 100 범위로 제한
    // ==================================================
    public void changeRelationship(
            Npc npc,
            int change) {

        // 관계도 변화 적용
        npc.relationship +=
                change;

        // 0보다 작으면 0
        if (npc.relationship < 0) {

            npc.relationship = 0;
        }

        // 100보다 크면 100
        if (npc.relationship > 100) {

            npc.relationship = 100;
        }
    }

    // ==================================================
    // NPC 관계도 특수 효과 확인
    // 관계도가 일정 수치에 도달하면
    // 플레이어에게 보상 또는 페널티 적용
    // ==================================================
    public void checkRelationshipEffects(
            Player player) {


        // 팀장 관계도 85 이상이면 해금
        if (teamLeader.relationship >= 85
                && !player.teamLeaderSupportUnlocked) {

            // 평판 +5
            player.reputation += 5;

            // 평판 최대 100 제한
            if (player.reputation > 100) {

                player.reputation = 100;
            }

            // 팀장 지원 효과 해금
            player.teamLeaderSupportUnlocked = true;
        }


        // 선배 관계도 85 이상이면 해금
        if (senior.relationship >= 85
                && !player.seniorHelpUnlocked) {

            // 업무 능력 +5
            player.ability += 5;

            // 최대 100 제한
            if (player.ability > 100) {

                player.ability = 100;
            }

            // 선배 도움 효과 해금
            player.seniorHelpUnlocked = true;
        }


        // 동료 관계도 85 이상이면 해금
        if (coworker.relationship >= 85
                && !player.coworkerHelpUnlocked) {

            // 스트레스 -5
            player.stress -= 5;

            // 최소 0 제한
            if (player.stress < 0) {

                player.stress = 0;
            }

            // 동료 도움 효과 해금
            player.coworkerHelpUnlocked = true;
        }

        // ==================================================
        // 팀장 관계도 30 이하
        // 한 번만 평판 -5 적용
        // 부장이 된 이후에는 적용하지 않음
        // ==================================================
        if (teamLeader.relationship <= 30
                && !player.teamLeaderPenaltyTriggered
                && !player.position.equals("부장")) {

            // 평판 -5
            player.reputation -= 5;

            // 최소값 0 제한
            if (player.reputation < 0) {

                player.reputation = 0;
            }

            // 페널티 발생 기록
            player.teamLeaderPenaltyTriggered = true;
        }


        // ==================================================
        // 선배 관계도 30 이하
        // 한 번만 스트레스 +5 적용
        // ==================================================
        if (senior.relationship <= 30
                && !player.seniorPenaltyTriggered) {

            // 스트레스 +5
            player.stress += 5;

            // 최대값 100 제한
            if (player.stress > 100) {

                player.stress = 100;
            }

            // 페널티 발생 기록
            player.seniorPenaltyTriggered = true;
        }


        // ==================================================
        // 동료 관계도 30 이하
        // 한 번만 스트레스 +5 적용
        // ==================================================
        if (coworker.relationship <= 30
                && !player.coworkerPenaltyTriggered) {

            // 스트레스 +5
            player.stress += 5;

            // 최대값 100 제한
            if (player.stress > 100) {

                player.stress = 100;
            }

            // 페널티 발생 기록
            player.coworkerPenaltyTriggered = true;
        }
    }

    // ==================================================
    // NPC 관계도 초기화
    // ==================================================
    public void reset() {

        // 팀장 관계도 초기화
        teamLeader.relationship = 50;

        // 선배 관계도 초기화
        senior.relationship = 50;

        // 동료 관계도 초기화
        coworker.relationship = 50;
    }
}