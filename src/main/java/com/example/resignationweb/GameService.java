package com.example.resignationweb;

import org.springframework.ui.Model;
import java.text.NumberFormat;
import java.util.Locale;


// ==================================================
// 게임 전체 진행 관리 클래스
// 이벤트 선택 / 하루 진행 / 월급 / 후속 이벤트 등
// 실제 게임 로직을 담당
// ==================================================
public class GameService {

    // ==================================================
    // 1. 게임 상태 / 관리자
    // ==================================================

    // 현재 플레이어
    private final Player player =
            new Player();

    // 이벤트 관리자
    private final EventManager eventManager =
            new EventManager();

    // 능력치 관리자
    private final StatManager statManager =
            new StatManager();

    // 돈 관리자
    private final MoneyManager moneyManager =
            new MoneyManager();

    // 승진 관리자
    private final PromotionManager promotionManager =
            new PromotionManager();

    // NPC 관리자
    private final NpcManager npcManager =
            new NpcManager();

    // 인사평가 관리자
    private final PerformanceManager performanceManager =
            new PerformanceManager();

    // 엔딩 관리자
    private final EndingManager endingManager =
            new EndingManager();

    // 게임 행동 관리자
    private final GameActionManager gameActionManager =
            new GameActionManager();

    // 게임 행동 기록 관리자
    private final ActionManager actionManager =
            new ActionManager();

    // 업무 태만 관리자
    private final NeglectManager neglectManager =
            new NeglectManager();

    // 현재 이벤트
    private Event currentEvent =
            eventManager.getTodayEvent(
                    player.day,
                    player
            );

    // ==================================================
    // 2. Controller 요청 처리
    // ==================================================

    // ==================================================
    // 화면 데이터 전달
    // 현재 게임 상태를 Mustache에 전달
    // ==================================================
    public void addGameData(
            Model model) {

        // ==================================================
        // 첫날 변화량 숨김
        // 1일차에는 + / - 표시를 보여주지 않음
        // ==================================================

        boolean firstDay =
                player.day == 1;


        // --------------------------------------------------
        // 이벤트 화면 종류
        // --------------------------------------------------

        // 후속 이벤트 여부
        model.addAttribute(
                "followupEvent",
                currentEvent.followupEvent
        );

        // 월급 이벤트 여부
        model.addAttribute(
                "salaryEvent",
                currentEvent.type.equals("salary")
        );

        // --------------------------------------------------
        // 일반 이벤트 여부
        // 승진 선택이나 징계 선택 중에는 일반 이벤트를 숨김
        // --------------------------------------------------

        model.addAttribute(
                "normalEvent",
                !currentEvent.type.equals("salary")
                        && !currentEvent.followupEvent
                        && !player.neglectDisciplinePending
                        && !player.promotionChoicePending
        );


        // --------------------------------------------------
        // 현재 이벤트
        // --------------------------------------------------

        // 이벤트 질문
        model.addAttribute(
                "event",
                currentEvent.question
        );

        // 일반 이벤트에만 선택지 전달
        if (!currentEvent.type.equals("salary")
                && !currentEvent.followupEvent) {

            // 선택지 1
            model.addAttribute(
                    "choice1",
                    currentEvent.choice1
            );

            // 선택지 2
            model.addAttribute(
                    "choice2",
                    currentEvent.choice2
            );
        }


        // --------------------------------------------------
        // 플레이어 상태
        // --------------------------------------------------

        // 날짜
        model.addAttribute(
                "day",
                player.day
        );

        // 직급
        model.addAttribute(
                "position",
                player.position
        );


        // 승진 직후 한 번만 표시
        model.addAttribute(
                "promotionMessage",
                player.promotionMessage
        );


        // 다음 날부터는 다시 표시되지 않음
        player.promotionMessage =
                "";


        // 보유 금액
        model.addAttribute(
                "money",
                NumberFormat
                        .getNumberInstance(
                                Locale.KOREA
                        )
                        .format(
                                player.money
                        )
        );


        // 카드빚
        model.addAttribute(
                "cardDebt",
                NumberFormat
                        .getNumberInstance(
                                Locale.KOREA
                        )
                        .format(
                                player.cardDebt
                        )
        );


        // 월급
        model.addAttribute(
                "salary",
                NumberFormat
                        .getNumberInstance(
                                Locale.KOREA
                        )
                        .format(
                                player.salary
                        )
        );

        // 스트레스
        model.addAttribute(
                "stress",
                player.stress
        );

        // 업무 능력
        model.addAttribute(
                "ability",
                player.ability
        );

        // 체력
        model.addAttribute(
                "stamina",
                player.stamina
        );

        // 평판
        model.addAttribute(
                "reputation",
                player.reputation
        );

        // 업무 태만 누적 횟수
        model.addAttribute(
                "neglectCount",
                player.neglectCount
        );

        // ==================================================
        // 게임 종료 상태 화면 전달
        // ==================================================

        model.addAttribute(
                "gameOver",
                player.gameOver
        );

        model.addAttribute(
                "gamePlaying",
                !player.gameOver
        );

        model.addAttribute(
                "endingMessage",
                player.endingMessage
        );


        // ==================================================
        // 오늘 상태 변화량 계산
        // 현재 상태 - 이벤트 처리 전 상태
        // ==================================================


        // --------------------------------------------------
        // 돈 변화량
        // --------------------------------------------------
        int todayMoneyChange =
                player.money
                        - player.previousMoney;


        // --------------------------------------------------
        // 카드빚 변화량
        // --------------------------------------------------
        int todayCardDebtChange =
                player.cardDebt
                        - player.previousCardDebt;


        // --------------------------------------------------
        // 월급 변화량
        // --------------------------------------------------
        int todaySalaryChange =
                player.salary
                        - player.previousSalary;


        // --------------------------------------------------
        // 스트레스 변화량
        // --------------------------------------------------
        int todayStressChange =
                player.stress
                        - player.previousStress;


        // --------------------------------------------------
        // 업무 능력 변화량
        // --------------------------------------------------
        int todayAbilityChange =
                player.ability
                        - player.previousAbility;


        // --------------------------------------------------
        // 체력 변화량
        // --------------------------------------------------
        int todayStaminaChange =
                player.stamina
                        - player.previousStamina;


        // --------------------------------------------------
        // 평판 변화량
        // --------------------------------------------------
        int todayReputationChange =
                player.reputation
                        - player.previousReputation;

        // ==================================================
        // 오늘 변화량 화면 전달
        // 첫날에는 변화량을 표시하지 않음
        // ==================================================

        model.addAttribute(
                "moneyChange",
                firstDay
                        ? ""
                        : formatMoneyChange(
                        todayMoneyChange
                )
        );

        model.addAttribute(
                "cardDebtChange",
                firstDay
                        ? ""
                        : formatMoneyChange(
                        todayCardDebtChange
                )
        );

        model.addAttribute(
                "salaryChange",
                firstDay
                        ? ""
                        : formatMoneyChange(
                        todaySalaryChange
                )
        );

        model.addAttribute(
                "stressChange",
                firstDay
                        ? ""
                        : formatChange(
                        todayStressChange
                )
        );

        model.addAttribute(
                "abilityChange",
                firstDay
                        ? ""
                        : formatChange(
                        todayAbilityChange
                )
        );

        model.addAttribute(
                "staminaChange",
                firstDay
                        ? ""
                        : formatChange(
                        todayStaminaChange
                )
        );

        model.addAttribute(
                "reputationChange",
                firstDay
                        ? ""
                        : formatChange(
                        todayReputationChange
                )
        );;

        // ==================================================
        // 월세 납부 메시지
        // ==================================================

        if (!model.containsAttribute(
                "rentMessage")) {

            model.addAttribute(
                    "rentMessage",
                    ""
            );
        }


        // --------------------------------------------------
        // NPC 관계도
        // --------------------------------------------------

        // 팀장 관계도
        model.addAttribute(
                "teamLeaderRelationship",
                npcManager.getTeamLeader().relationship
        );

        // 선배 관계도
        model.addAttribute(
                "seniorRelationship",
                npcManager.getSenior().relationship
        );

        // 동료 관계도
        model.addAttribute(
                "coworkerRelationship",
                npcManager.getCoworker().relationship
        );


        // --------------------------------------------------
        // NPC 특수 효과
        // --------------------------------------------------

        // 팀장 지원 효과
        model.addAttribute(
                "teamLeaderSupportUnlocked",
                player.teamLeaderSupportUnlocked
        );

        // 선배 도움 효과
        model.addAttribute(
                "seniorHelpUnlocked",
                player.seniorHelpUnlocked
        );

        // 동료 도움 효과
        model.addAttribute(
                "coworkerHelpUnlocked",
                player.coworkerHelpUnlocked
        );


        // --------------------------------------------------
        // 업무 태만 징계 상태
        // --------------------------------------------------

        // 징계 선택 화면 표시 여부
        model.addAttribute(
                "neglectDisciplinePending",
                player.neglectDisciplinePending
        );

        // --------------------------------------------------
        // 과장 승진 선택 상태
        // --------------------------------------------------

        // 승진 선택 화면 표시 여부
        model.addAttribute(
                "promotionChoicePending",
                player.promotionChoicePending
        );


        // --------------------------------------------------
        // 게임 종료 상태
        // --------------------------------------------------

        // 게임 종료 여부
        model.addAttribute(
                "gameOver",
                player.gameOver
        );

        // 게임 진행 여부
        model.addAttribute(
                "gamePlaying",
                !player.gameOver
        );

        // 엔딩 메시지
        model.addAttribute(
                "endingMessage",
                player.endingMessage
        );

        // ==================================================
        // 엔딩 종류 화면 전달
        // 좋은 엔딩 / 나쁜 엔딩 제목 구분
        // ==================================================

        model.addAttribute(
                "goodEnding",
                player.goodEnding
        );

        model.addAttribute(
                "badEnding",
                player.gameOver
                        && !player.goodEnding
        );
    }

    // ==================================================
    // 일반 이벤트 선택 처리
    // Controller에서 호출하는 메서드
    // ==================================================
    public void selectChoice(
            int choice,
            Model model) {


        // 오늘 이벤트 처리 전 상태 저장
        player.savePreviousStatus();

        // 선택 결과 적용
        applyChoiceResult(
                choice,
                model
        );


        // 행동 기록
        actionManager.recordAction(
                player,
                currentEvent,
                choice
        );


        // ==================================================
        // 업무 태만 처리
        // workload에서 "대충 한다" 선택 시 실행
        // ==================================================

        if (currentEvent.type.equals("workload")
                && choice == 2) {

            // --------------------------------------------------
            // 현재 업무 태만 횟수 표시
            // --------------------------------------------------

            model.addAttribute(
                    "neglectCountMessage",
                    "🛌 업무 태만 "
                            + player.neglectCount
                            + "회 누적"
            );


            // --------------------------------------------------
            // 경고 / 징계 확인
            // --------------------------------------------------

            String neglectMessage =
                    neglectManager.checkNeglect(
                            player
                    );

            // 경고 메시지가 있으면 화면에 전달
            if (neglectMessage != null) {

                model.addAttribute(
                        "neglectMessage",
                        neglectMessage
                );
            }


            // --------------------------------------------------
            // 징계 선택 대기 상태
            // 10회 도달 시 하루 진행을 멈춤
            // --------------------------------------------------

            if (player.neglectDisciplinePending) {

                // 현재 상태 화면 전달
                addGameData(
                        model
                );

                // 이후 finishDay() 실행 방지
                return;
            }
        }

        // 추가 효과 처리
        processChoiceEffects(
                choice
        );

        // 인사평가 처리
        processPerformance(
                model
        );

        // 하루 마무리
        finishDay();

        // 화면 데이터 전달
        addGameData(
                model
        );
    }

    // ==================================================
    // 월급 이벤트 처리
    // Controller에서 월급 받기 요청이 오면 실행
    // ==================================================
    public void receiveSalary(
            Model model) {

        // 월급 처리 전 상태 저장
        player.savePreviousStatus();

        // 월급 지급 및 카드빚 상환
        moneyManager.processSalary(
                player
        );

        // 월세 납부
        moneyManager.payRent(
                player
        );

        model.addAttribute(
                "rentMessage",
                "🏠 월세 700,000원을 납부했습니다."
        );

        // --------------------------------------------------
        // 인사평가 처리
        // --------------------------------------------------

        // 평가 날짜이면 인사평가 실행
        processPerformance(
                model
        );


        // --------------------------------------------------
        // 하루 마무리
        // --------------------------------------------------

        // 생활비 / 카드빚 / 엔딩 / 날짜 증가 / 승진 처리
        finishDay();

        model.addAttribute(
                "result",
                "💰 월급이 지급되었습니다."
        );


        // --------------------------------------------------
        // 결과 메시지
        // --------------------------------------------------

        // 월급 지급 결과 전달
        model.addAttribute(
                "result",
                "💰 월급이 지급되었습니다."
        );


        // --------------------------------------------------
        // 화면 갱신
        // --------------------------------------------------

        // 최신 게임 상태 전달
        addGameData(
                model
        );
    }

    // ==================================================
    // 후속 이벤트 처리
    // Controller에서 확인 버튼을 누르면 실행
    // ==================================================
    public void processFollowup(
            Model model) {


        // 후속 이벤트 처리 전 상태 저장
        player.savePreviousStatus();

        // --------------------------------------------------
        // 후속 이벤트 효과 적용
        // --------------------------------------------------

        // 후속 이벤트는 선택지가 없으므로
        // 선택 1 변화량을 사용
        statManager.applyChanges(
                player,
                currentEvent.stressChange1,
                currentEvent.abilityChange1,
                currentEvent.staminaChange1,
                currentEvent.reputationChange1
        );

        // 돈 변화 적용
        moneyManager.applyMoneyChange(
                player,
                currentEvent.moneyChange1
        );

        // 결과 메시지 전달
        model.addAttribute(
                "result",
                currentEvent.result1
        );


        // --------------------------------------------------
        // 후속 이벤트 결과 처리
        // --------------------------------------------------

        // 완료 상태와 NPC 관계도 변화 처리
        gameActionManager.processFollowupResult(
                player,
                currentEvent,
                npcManager
        );


        // --------------------------------------------------
        // NPC 특수 효과 확인
        // --------------------------------------------------

        // 관계도 변화 이후 보상 / 페널티 검사
        npcManager.checkRelationshipEffects(
                player
        );


        // 하루 마무리
        finishDay();


        // 화면 갱신
        addGameData(
                model
        );
    }

    // ==================================================
    // 과장 승진 수락
    // 대리 → 과장 승진 처리
    // ==================================================
    public void acceptPromotion(
            Model model) {


        // --------------------------------------------------
        // 승진 선택 대기 상태가 아니면 처리하지 않음
        // --------------------------------------------------

        if (!player.promotionChoicePending) {

            addGameData(
                    model
            );

            return;
        }


        // --------------------------------------------------
        // 직급 변경
        // --------------------------------------------------

        player.position =
                "과장";


        // --------------------------------------------------
        // 승진 급여 인상
        // 기존 월급에서 15% 인상
        // --------------------------------------------------

        player.salary =
                (int) (player.salary * 1.15);


        // --------------------------------------------------
        // 승진에 따른 스트레스 증가
        // --------------------------------------------------

        player.stress =
                Math.min(
                        100,
                        player.stress + 10
                );


        // --------------------------------------------------
        // 승진에 따른 평판 증가
        // --------------------------------------------------

        player.reputation =
                Math.min(
                        100,
                        player.reputation + 5
                );


        // --------------------------------------------------
        // 승진 선택 상태 종료
        // --------------------------------------------------

        player.promotionChoicePending =
                false;


        // --------------------------------------------------
        // 결과 메시지
        // --------------------------------------------------

        model.addAttribute(
                "result",
                "🎉 과장으로 승진했습니다. "
                        + "월급이 15% 인상되고 스트레스가 10, 평판이 5 증가했습니다."
        );


        // --------------------------------------------------
        // 화면 갱신
        // --------------------------------------------------

        addGameData(
                model
        );
    }


    // ==================================================
    // 과장 승진 포기
    // 대리 유지 + 워라밸 루트 진입
    // ==================================================
    public void declinePromotion(
            Model model) {


        // --------------------------------------------------
        // 승진 선택 대기 상태가 아니면 처리하지 않음
        // --------------------------------------------------

        if (!player.promotionChoicePending) {

            addGameData(
                    model
            );

            return;
        }


        // --------------------------------------------------
        // 승진 영구 포기
        // 이후 과장 승진 제안을 받지 않음
        // --------------------------------------------------

        player.promotionAbandoned =
                true;


        // --------------------------------------------------
        // 승진 선택 상태 종료
        // --------------------------------------------------

        player.promotionChoicePending =
                false;


        // --------------------------------------------------
        // 승진 경쟁에서 벗어나 스트레스 감소
        // --------------------------------------------------

        player.stress =
                Math.max(
                        0,
                        player.stress - 15
                );


        // --------------------------------------------------
        // 여유가 생겨 체력 회복
        // --------------------------------------------------

        player.stamina =
                Math.min(
                        100,
                        player.stamina + 10
                );


        // --------------------------------------------------
        // 결과 메시지
        // --------------------------------------------------

        model.addAttribute(
                "result",
                "🌿 과장 승진을 포기했습니다. "
                        + "대리 직급을 유지하며 워라밸 루트에 진입했습니다. "
                        + "스트레스 -15 / 체력 +10"
        );


        // --------------------------------------------------
        // 화면 갱신
        // --------------------------------------------------

        addGameData(
                model
        );
    }

    // ==================================================
    // 자발적 퇴사 처리
    // 현재 상태에 따라 자발적 퇴사 엔딩을 결정
    // ==================================================
    public void resign(
            Model model) {

        // --------------------------------------------------
        // 자발적 퇴사 엔딩 판정
        // --------------------------------------------------

        endingManager.processVoluntaryResignation(
                player
        );


        // --------------------------------------------------
        // 화면 데이터 전달
        // --------------------------------------------------

        addGameData(
                model
        );
    }

    // ==================================================
    // 3. 내부 게임 처리
    // ==================================================


    // ==================================================
    // 일반 이벤트 선택 결과 적용
    // 선택한 번호에 따라 능력치 / 돈 / 결과 메시지를 처리
    // ==================================================
    private void applyChoiceResult(
            int choice,
            Model model) {

        // --------------------------------------------------
        // 선택 1 처리
        // --------------------------------------------------

        if (choice == 1) {

            // 능력치 변화 적용
            statManager.applyChanges(
                    player,
                    currentEvent.stressChange1,
                    currentEvent.abilityChange1,
                    currentEvent.staminaChange1,
                    currentEvent.reputationChange1
            );

            // 돈 변화 적용
            moneyManager.applyMoneyChange(
                    player,
                    currentEvent.moneyChange1
            );

            // 결과 메시지 전달
            model.addAttribute(
                    "result",
                    currentEvent.result1
            );

            return;
        }


        // --------------------------------------------------
        // 선택 2 처리
        // --------------------------------------------------

        if (choice == 2) {

            // 능력치 변화 적용
            statManager.applyChanges(
                    player,
                    currentEvent.stressChange2,
                    currentEvent.abilityChange2,
                    currentEvent.staminaChange2,
                    currentEvent.reputationChange2
            );

            // 돈 변화 적용
            moneyManager.applyMoneyChange(
                    player,
                    currentEvent.moneyChange2
            );

            // 결과 메시지 전달
            model.addAttribute(
                    "result",
                    currentEvent.result2
            );
        }
    }

    // ==================================================
    // 일반 이벤트 추가 처리
    // NPC 관계도 / 연쇄 이벤트 / 특수 효과 처리
    // ==================================================
    private void processChoiceEffects(
            int choice) {

        // 일반 이벤트 NPC 관계도 처리
        gameActionManager.processNormalNpcRelationship(
                player,
                currentEvent,
                choice,
                npcManager,
                statManager
        );

        // 직급 이벤트 NPC 관계도 처리
        gameActionManager.processRankNpcRelationship(
                currentEvent,
                choice,
                npcManager
        );

        // 연쇄 이벤트 시작 처리
        gameActionManager.processChainStart(
                player,
                currentEvent,
                choice
        );

        // NPC 특수 효과 확인
        npcManager.checkRelationshipEffects(
                player
        );
    }

    // ==================================================
    // 인사평가 처리
    // 30일차는 권고사직 심사
    // 이후 평가는 등급에 따라 급여 조정
    // ==================================================
    private void processPerformance(
            Model model) {

        // --------------------------------------------------
        // 평가 날짜 확인
        // --------------------------------------------------

        if (!performanceManager.isPerformanceDay(
                player.day)) {

            return;
        }


        // --------------------------------------------------
        // 평가 점수 계산
        // --------------------------------------------------

        int performanceScore =
                performanceManager.calculateScore(
                        player
                );


        // 평가 등급 계산
        String performanceGrade =
                performanceManager.getPerformanceGrade(
                        performanceScore
                );

        // 최근 인사평가 등급 저장
        player.lastPerformanceGrade =
                performanceGrade;


        // --------------------------------------------------
        // 기본 평가 결과 전달
        // --------------------------------------------------

        model.addAttribute(
                "performanceResult",
                "📋 인사평가 점수: "
                        + performanceScore
                        + " / 등급: "
                        + performanceGrade
        );


        // ==================================================
        // 30일차 수습 평가
        // ==================================================

        if (player.day == 30) {

            // --------------------------------------------------
            // 수습 평가 권고사직
            // --------------------------------------------------

            if (performanceManager.isRecommendedResignation(
                    performanceScore)) {

                player.gameOver =
                        true;

                player.goodEnding =
                        false;

                // 권고사직 엔딩
                player.endingMessage =
                        "📋 수습평가 권고사직 엔딩\n"
                                + "수습기간 평가에서 기준 점수를 넘지 못해 권고사직을 받았습니다.";

                return;
            }


            // --------------------------------------------------
            // 수습 해제
            // --------------------------------------------------

            // 수습 평가 통과 메시지
            model.addAttribute(
                    "performanceMessage",
                    "🎉 수습기간 평가를 통과했습니다. 정식 직원으로 전환되었습니다."
            );

            // 30일차에는 급여 조정 없음
            return;
        }


        // ==================================================
        // 정기 인사평가
        // 90 / 180 / 270일차
        // ==================================================

        int salaryChange =
                performanceManager.applySalaryAdjustment(
                        player,
                        performanceGrade
                );


        // --------------------------------------------------
        // 인상
        // --------------------------------------------------

        if (salaryChange > 0) {

            model.addAttribute(
                    "performanceMessage",
                    "📈 인사평가 결과 월급이 "
                            + salaryChange
                            + "원 인상되었습니다."
            );
        }


        // --------------------------------------------------
        // 감봉
        // --------------------------------------------------

        else if (salaryChange < 0) {

            model.addAttribute(
                    "performanceMessage",
                    "📉 인사평가 결과 월급이 "
                            + Math.abs(salaryChange)
                            + "원 감봉되었습니다."
            );
        }


        // --------------------------------------------------
        // 변동 없음
        // --------------------------------------------------

        else {

            model.addAttribute(
                    "performanceMessage",
                    "➖ 인사평가 결과 월급 변동이 없습니다."
            );
        }
    }

    // ==================================================
// 업무 태만 징계 - 감봉 선택
// 감봉을 받아들이고 계속 근무
// ==================================================
    public void acceptNeglectPayCut(
            Model model) {

        // --------------------------------------------------
        // 급여 5% 감봉
        // --------------------------------------------------

        player.salary =
                (int) (player.salary * 0.95);


        // --------------------------------------------------
        // 평판 감소
        // --------------------------------------------------

        player.reputation =
                Math.max(
                        0,
                        player.reputation - 10
                );


        // --------------------------------------------------
        // 업무 태만 상태 초기화
        // --------------------------------------------------

        player.neglectCount =
                0;

        player.neglectWarning1 =
                false;

        player.neglectWarning2 =
                false;

        player.neglectDisciplinePending =
                false;


        // 결과 메시지
        model.addAttribute(
                "result",
                "💸 감봉 징계를 받아들였습니다. 월급이 5% 감소하고 평판이 10 감소했습니다."
        );


        // 징계가 끝났으므로 다음 날 진행
        finishDay();


        // 화면 갱신
        addGameData(
                model
        );
    }

    // ==================================================
    // 하루 마무리 처리
    // 생활비 → 카드빚 → 엔딩 → 날짜 → 승진 → 다음 이벤트
    // ==================================================
    private void finishDay() {

        // --------------------------------------------------
        // 금전 처리
        // --------------------------------------------------

        // 하루 생활비 차감
        moneyManager.payLivingCost(
                player
        );

        // 부족한 금액을 카드빚으로 전환
        moneyManager.handleNegativeBalance(
                player
        );

        // --------------------------------------------------
        // 직급별 기본 스트레스
        // 직급이 올라갈수록 책임 증가
        // --------------------------------------------------

        int dailyStress =
                1;

        // 과장부터 책임 증가
        if (player.position.equals("과장")) {

            dailyStress =
                    2;
        }

        // 차장 / 부장
        else if (player.position.equals("차장")
                || player.position.equals("부장")) {

            dailyStress =
                    3;
        }


        // --------------------------------------------------
        // 하루 기본 스트레스 적용
        // 평일에만 스트레스 증가
        // 1일차를 월요일로 계산
        // --------------------------------------------------

        int dayOfWeek =
                (player.day - 1) % 7;


        // 토요일 = 5
        // 일요일 = 6
        boolean weekend =
                dayOfWeek == 5
                        || dayOfWeek == 6;


        // 평일에만 기본 스트레스 증가
        if (!weekend) {

            player.stress =
                    Math.min(
                            100,
                            player.stress + dailyStress
                    );
        }


        // --------------------------------------------------
        // 강제 엔딩 확인
        // 번아웃 / 파산
        // --------------------------------------------------

        endingManager.checkEnding(
                player
        );

        // 게임이 종료되었으면 중단
        if (player.gameOver) {

            return;
        }


        // ==================================================
        // 다음 날 진행
        // ==================================================

        // 날짜 증가
        player.day++;


        // --------------------------------------------------
        // 365일 완주 엔딩 확인
        // --------------------------------------------------

        if (player.day >= 365) {

            // 현재 최종 상태를 기준으로
            // 365일 완주 엔딩 결정
            endingManager.processSurvivalEnding(
                    player
            );

            // 게임 종료
            return;
        }


        // --------------------------------------------------
        // 승진 확인
        // --------------------------------------------------

        promotionManager.checkPromotion(
                player
        );

        // --------------------------------------------------
        // 다음 날 이벤트 선택
        // 승진된 직급을 기준으로 새로운 이벤트를 가져옴
        // --------------------------------------------------

        currentEvent =
                eventManager.getTodayEvent(
                        player.day,
                        player
                );
    }


    // ==================================================
    // 업무 태만 징계 - 퇴사 선택
    // 업무 태만 권고사직 엔딩
    // ==================================================
    public void resignFromNeglect(
            Model model) {

        // --------------------------------------------------
        // 징계 상태 종료
        // --------------------------------------------------

        player.neglectDisciplinePending =
                false;


        // --------------------------------------------------
        // 게임 종료
        // --------------------------------------------------

        player.gameOver =
                true;

        // 업무 태만 전용 엔딩
        player.endingMessage =
                "🛌 업무태만 퇴사 엔딩\n"
                        + "반복된 업무 태만과 징계 끝에 회사를 떠나게 되었습니다.";


        // --------------------------------------------------
        // 화면 갱신
        // --------------------------------------------------

        addGameData(
                model
        );
    }

    // ==================================================
    // 게임 처음부터 시작
    // 플레이어와 게임 상태를 초기화
    // ==================================================
    public void restartGame(
            Model model) {

        // 플레이어 초기화
        player.reset();


        // NPC 초기화
        npcManager.reset();


        // 첫 이벤트 다시 불러오기
        currentEvent =
                eventManager.getTodayEvent(
                        player.day,
                        player
                );


        // 화면 갱신
        addGameData(
                model
        );
    }

    // ==================================================
    // 일반 상태 변화량 표시
    // 양수는 +, 음수는 -, 변화 없음은 빈칸
    // ==================================================
    private String formatChange(
            int value) {

        // --------------------------------------------------
        // 증가
        // --------------------------------------------------

        if (value > 0) {

            return "+"
                    + value;
        }


        // --------------------------------------------------
        // 감소
        // --------------------------------------------------

        if (value < 0) {

            return String.valueOf(
                    value
            );
        }


        // 변화 없음
        return "";
    }

    // ==================================================
    // 금액 변화량 표시
    // 천 단위 콤마 + 부호 표시
    // ==================================================
    private String formatMoneyChange(
            int value) {

        // --------------------------------------------------
        // 천 단위 콤마 변환
        // --------------------------------------------------

        String formatted =
                NumberFormat
                        .getNumberInstance(
                                Locale.KOREA
                        )
                        .format(
                                Math.abs(value)
                        );


        // --------------------------------------------------
        // 증가
        // --------------------------------------------------

        if (value > 0) {

            return "+"
                    + formatted;
        }


        // --------------------------------------------------
        // 감소
        // --------------------------------------------------

        if (value < 0) {

            return "-"
                    + formatted;
        }


        // 변화 없음
        return "";
    }
}