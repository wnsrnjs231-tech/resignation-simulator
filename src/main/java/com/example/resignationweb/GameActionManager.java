package com.example.resignationweb;

// ==================================================
// 게임 행동 처리 관리자
// 일반 이벤트 선택 후 발생하는
// NPC 관계도 / 연쇄 이벤트 / 후속 이벤트 결과를 처리
// ==================================================
public class GameActionManager {

    // --------------------------------------------------
    // 일반 이벤트 NPC 관계도 처리
    // --------------------------------------------------

    public void processNormalNpcRelationship(
            Player player,
            Event event,
            int choice,
            NpcManager npcManager,
            StatManager statManager) {

        // --------------------------------------------------
        // 야근
        // --------------------------------------------------

        if (event.type.equals("overtime")) {

            if (choice == 1) {

                // 팀장 관계도 +5
                npcManager.changeRelationship(
                        npcManager.getTeamLeader(),
                        5
                );

            } else if (choice == 2) {

                // 팀장 관계도 -5
                npcManager.changeRelationship(
                        npcManager.getTeamLeader(),
                        -5
                );
            }

            return;
        }


        // --------------------------------------------------
        // 커피
        // --------------------------------------------------

        if (event.type.equals("coffee")) {

            if (choice == 1) {

                // 동료 관계도 +5
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        5
                );

            } else if (choice == 2) {

                // 동료 관계도 -3
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        -3
                );
            }

            return;
        }


        // --------------------------------------------------
        // 점심
        // --------------------------------------------------

        if (event.type.equals("lunch")) {

            if (choice == 1) {

                // 동료 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        3
                );

            } else if (choice == 2) {

                // 동료 관계도 -2
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        -2
                );
            }

            return;
        }


        // --------------------------------------------------
        // 업무량
        // --------------------------------------------------

        if (event.type.equals("workload")) {

            if (choice == 1) {

                // 선배 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getSenior(),
                        3
                );

            } else if (choice == 2) {

                // 선배 관계도 -3
                npcManager.changeRelationship(
                        npcManager.getSenior(),
                        -3
                );
            }

            return;
        }


        // --------------------------------------------------
        // 회식
        // --------------------------------------------------

        if (event.type.equals("dinner")) {

            if (choice == 1) {

                // 팀장 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getTeamLeader(),
                        3
                );

                // 동료 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        3
                );

            } else if (choice == 2) {

                // 팀장 관계도 -3
                npcManager.changeRelationship(
                        npcManager.getTeamLeader(),
                        -3
                );

                // 동료 관계도 -1
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        -1
                );
            }
        }
    }


    // ==================================================
// 직급 이벤트 NPC 관계도 처리
// ==================================================
    public void processRankNpcRelationship(
            Event event,
            int choice,
            NpcManager npcManager) {

        // --------------------------------------------------
        // 사원 이벤트
        // --------------------------------------------------

        if (event.type.equals("junior_task")) {

            if (choice == 1) {

                // 선배 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getSenior(),
                        3
                );

            } else if (choice == 2) {

                // 선배 관계도 -2
                npcManager.changeRelationship(
                        npcManager.getSenior(),
                        -2
                );
            }

            return;
        }


        // --------------------------------------------------
        // 대리 이벤트
        // --------------------------------------------------

        if (event.type.equals("assistant_task")) {

            if (choice == 1) {

                // 동료 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        3
                );

            } else if (choice == 2) {

                // 동료 관계도 -2
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        -2
                );
            }

            return;
        }


        // --------------------------------------------------
        // 과장 이벤트
        // --------------------------------------------------

        if (event.type.equals("manager_task")) {

            if (choice == 1) {

                // 팀장 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getTeamLeader(),
                        3
                );

            } else if (choice == 2) {

                // 팀장 관계도 -3
                npcManager.changeRelationship(
                        npcManager.getTeamLeader(),
                        -3
                );
            }

            return;
        }


        // --------------------------------------------------
        // 차장 이벤트
        // --------------------------------------------------

        if (event.type.equals("deputy_general_task")) {

            if (choice == 1) {

                // 동료 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        3
                );

            } else if (choice == 2) {

                // 동료 관계도 -3
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        -3
                );
            }

            return;
        }


        // --------------------------------------------------
        // 부장 이벤트
        // --------------------------------------------------

        if (event.type.equals("general_manager_task")) {

            if (choice == 1) {

                // 동료 관계도 +3
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        3
                );

            } else if (choice == 2) {

                // 동료 관계도 -5
                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        -5
                );
            }
        }
    }


    // --------------------------------------------------
    // 연쇄 이벤트 시작 처리
    // --------------------------------------------------

    public void processChainStart(
            Player player,
            Event currentEvent,
            int choice) {

        // 사원 연쇄 이벤트
        if (currentEvent.type.equals("junior_task")
                && choice == 1) {

            player.juniorHelpAccepted =
                    true;

            player.juniorHelpAcceptedDay =
                    player.day;

            return;
        }


        // 대리 연쇄 이벤트
        if (currentEvent.type.equals("assistant_task")
                && choice == 1) {

            player.assistantHelpAccepted =
                    true;

            player.assistantHelpAcceptedDay =
                    player.day;

            return;
        }


        // 과장 연쇄 이벤트
        if (currentEvent.type.equals("manager_task")
                && choice == 1) {

            player.projectAccepted =
                    true;

            player.projectAcceptedDay =
                    player.day;

            return;
        }


        // 차장 연쇄 이벤트
        if (currentEvent.type.equals("deputy_general_task")
                && choice == 1) {

            player.conflictMediated =
                    true;

            player.conflictMediatedDay =
                    player.day;

            return;
        }


        // 부장 연쇄 이벤트
        if (currentEvent.type.equals("general_manager_task")) {

            player.departmentDecisionMade =
                    true;

            player.departmentDecisionDay =
                    player.day;

            player.departmentDecisionChoice =
                    choice;
        }


        // 야근 택시 이벤트 예약
        if (currentEvent.type.equals("overtime")
                && choice == 1) {

            // 야근 후 택시 이벤트 예약
            player.taxiEventPending =
                    true;
        }
    }


    // --------------------------------------------------
    // 후속 이벤트 완료 처리
    // --------------------------------------------------

    public void processFollowupResult(
            Player player,
            Event currentEvent,
            NpcManager npcManager) {

        // 사원 후속 이벤트
        if (currentEvent.type.equals("junior_followup")) {

            player.juniorFollowupSeen =
                    true;

            return;
        }


        // 대리 후속 이벤트
        if (currentEvent.type.equals("assistant_followup")) {

            player.assistantFollowupSeen =
                    true;

            return;
        }


        // 과장 프로젝트 결과
        if (currentEvent.type.equals("project_result")) {

            player.projectResultSeen =
                    true;

            // 프로젝트 성공
            if (player.ability >= 75) {

                npcManager.changeRelationship(
                        npcManager.getTeamLeader(),
                        10
                );

            // 프로젝트 실패
            } else {

                npcManager.changeRelationship(
                        npcManager.getTeamLeader(),
                        -5
                );
            }

            return;
        }


        // 차장 후속 이벤트
        if (currentEvent.type.equals("conflict_followup")) {

            player.conflictFollowupSeen =
                    true;

            npcManager.changeRelationship(
                    npcManager.getCoworker(),
                    5
            );

            return;
        }


        // 부장 후속 이벤트
        if (currentEvent.type.equals("department_result")) {

            player.departmentResultSeen =
                    true;

            // 프로세스 개선
            if (player.departmentDecisionChoice == 1) {

                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        5
                );

                // 성과 압박
            } else if (player.departmentDecisionChoice == 2) {

                npcManager.changeRelationship(
                        npcManager.getCoworker(),
                        -10
                );
            }
        }
    }
}