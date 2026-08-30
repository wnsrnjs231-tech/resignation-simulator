package com.example.resignationweb;

// ==================================================
// 플레이어 정보 저장 클래스
// 게임 진행 중 유지되는 상태값을 보관
// ==================================================
public class Player {

    // 현재 날짜
    public int day;

    // 현재 직급
    public String position;

    // 현재 보유 금액
    public int money;

    // 현재 스트레스
    public int stress;

    // 현재 업무 능력
    public int ability;

    // 현재 체력
    public int stamina;

    // 현재 평판
    public int reputation;

    // ==================================================
    // 이전 상태
    // 하루 동안 변화한 수치를 계산하기 위해 사용
    // ==================================================

    public int previousMoney;
    public int previousCardDebt;
    public int previousSalary;

    public int previousStress;
    public int previousAbility;
    public int previousStamina;
    public int previousReputation;

    // ==================================================
    // NPC 관계 특수 효과 해금 여부
    // 같은 보상이 여러 번 발생하지 않도록 저장
    // ==================================================

    // 팀장 지원 해금 여부
    public boolean teamLeaderSupportUnlocked;

    // 선배 도움 해금 여부
    public boolean seniorHelpUnlocked;

    // 동료 도움 해금 여부
    public boolean coworkerHelpUnlocked;

    // ==================================================
    // NPC 관계도 낮음 페널티 발생 여부
    // 같은 페널티가 반복해서 발생하지 않도록 저장
    // ==================================================

    // 팀장 관계도 낮음 페널티 발생 여부
    public boolean teamLeaderPenaltyTriggered;

    // 선배 관계도 낮음 페널티 발생 여부
    public boolean seniorPenaltyTriggered;

    // 동료 관계도 낮음 페널티 발생 여부
    public boolean coworkerPenaltyTriggered;


    // 자동 승진 메시지
    public String promotionMessage =
            "";

    // ==================================================
    // 과장 승진 선택 상태
    // 대리 → 과장 승진 제안을 화면에 표시할 때 사용
    // ==================================================

    // 승진 선택 대기 중인지 여부
    public boolean promotionChoicePending =
            false;


    // ==================================================
    // 승진 포기 상태
    // 과장 승진을 영구적으로 포기했는지 여부
    // 워라밸 루트 판정에 사용
    // ==================================================

    public boolean promotionAbandoned =
            false;

    // ==================================================
    // 사원 연쇄 이벤트 상태
    // ==================================================

    // 선배 도움 수락 여부
    public boolean juniorHelpAccepted;

    // 선배 도움 수락 날짜
    public int juniorHelpAcceptedDay;

    // 후속 이벤트 발생 여부
    public boolean juniorFollowupSeen;


    // ==================================================
    // 대리 연쇄 이벤트 상태
    // ==================================================

    // 후배 도움 수락 여부
    public boolean assistantHelpAccepted;

    // 후배 도움 수락 날짜
    public int assistantHelpAcceptedDay;

    // 후속 이벤트 발생 여부
    public boolean assistantFollowupSeen;


    // ==================================================
    // 과장 연쇄 이벤트 상태
    // ==================================================

    // 프로젝트 수락 여부
    public boolean projectAccepted;

    // 프로젝트 수락 날짜
    public int projectAcceptedDay;

    // 프로젝트 결과 발생 여부
    public boolean projectResultSeen;


    // ==================================================
    // 차장 연쇄 이벤트 상태
    // ==================================================

    // 갈등 중재 여부
    public boolean conflictMediated;

    // 갈등 중재 날짜
    public int conflictMediatedDay;

    // 후속 이벤트 발생 여부
    public boolean conflictFollowupSeen;


    // ==================================================
    // 부장 연쇄 이벤트 상태
    // ==================================================

    // 부서 운영 결정 여부
    public boolean departmentDecisionMade;

    // 결정 날짜
    public int departmentDecisionDay;

    // 선택한 결정
    // 1 = 프로세스 개선
    // 2 = 성과 압박
    public int departmentDecisionChoice;

    // 결과 이벤트 발생 여부
    public boolean departmentResultSeen;

    // ==================================================
    // 게임 종료 여부
    // true가 되면 더 이상 게임 진행 불가
    // ==================================================
    public boolean gameOver;


    // ==================================================
    // 엔딩 메시지
    // 게임 종료 이유를 저장
    // ==================================================
    public String endingMessage;

    // ==================================================
    // 엔딩 결과 상태
    // 좋은 엔딩인지 나쁜 엔딩인지 구분
    // ==================================================

    // 좋은 엔딩 여부
    public boolean goodEnding =
            false;


    // ==================================================
    // Player 생성자
    // 새 게임 시작 시 초기 상태 설정
    // ==================================================
    public Player() {

        // 시작 날짜
        day = 1;

        // 시작 직급
        position = "사원";

        // 시작 자금
        money = 200_000;

        // 시작 스트레스
        stress = 10;

        // 시작 업무 능력
        ability = 30;

        // 시작 체력
        stamina = 100;

        // 시작 평판
        reputation = 10;

        // 시작 카드빚
        cardDebt = 0;

        // 시작 월급
        salary = 2_500_000;

        // --------------------------------------------------
        // 행동 기록 초기값
        // --------------------------------------------------

        overtimeCount = 0;
        coffeeCount = 0;
        dinnerCount = 0;
        studyCount = 0;
        exerciseCount = 0;
        neglectCount = 0;

        // --------------------------------------------------
        // NPC 특수 효과 초기값
        // 게임 시작 시 모두 해금되지 않은 상태
        // --------------------------------------------------

        teamLeaderSupportUnlocked = false;
        seniorHelpUnlocked = false;
        coworkerHelpUnlocked = false;

        // --------------------------------------------------
        // NPC 관계도 낮음 페널티 초기값
        // --------------------------------------------------

        teamLeaderPenaltyTriggered = false;
        seniorPenaltyTriggered = false;
        coworkerPenaltyTriggered = false;

        // --------------------------------------------------
        // 사원 연쇄 이벤트 초기값
        // --------------------------------------------------
        juniorHelpAccepted = false;
        juniorHelpAcceptedDay = 0;
        juniorFollowupSeen = false;


        // --------------------------------------------------
        // 대리 연쇄 이벤트 초기값
        // --------------------------------------------------
        assistantHelpAccepted = false;
        assistantHelpAcceptedDay = 0;
        assistantFollowupSeen = false;


        // --------------------------------------------------
        // 과장 연쇄 이벤트 초기값
        // --------------------------------------------------
        projectAccepted = false;
        projectAcceptedDay = 0;
        projectResultSeen = false;


        // --------------------------------------------------
        // 차장 연쇄 이벤트 초기값
        // --------------------------------------------------
        conflictMediated = false;
        conflictMediatedDay = 0;
        conflictFollowupSeen = false;


        // --------------------------------------------------
        // 부장 연쇄 이벤트 초기값
        // --------------------------------------------------
        departmentDecisionMade = false;
        departmentDecisionDay = 0;
        departmentDecisionChoice = 0;
        departmentResultSeen = false;

        // --------------------------------------------------
        // 게임 시작 시 종료되지 않은 상태
        // --------------------------------------------------
        gameOver = false;

        // --------------------------------------------------
        // 시작 시 엔딩 메시지 없음
        // --------------------------------------------------
        endingMessage = "";
    }

    // --------------------------------------------------
    // 카드빚
    // 돈이 부족할 때 발생한 부족 금액을 저장
    // --------------------------------------------------
    public int cardDebt;

    // --------------------------------------------------
    // 월급
    // 현재 직급에 따라 변경될 수 있는 기본 월급
    // --------------------------------------------------
    public int salary;

    // --------------------------------------------------
    // 행동 기록
    // 특정 행동을 몇 번 했는지 저장
    // --------------------------------------------------

    // 야근 횟수
    public int overtimeCount;

    // 커피 구매 횟수
    public int coffeeCount;

    // 회식 참석 횟수
    public int dinnerCount;

    // 공부 횟수
    public int studyCount;

    // 운동 횟수
    public int exerciseCount;

    // 택시 이용 횟수
    public int taxiCount;

    // ==================================================
    // 업무 태만 상태
    // 업무 태만 횟수와 경고 발생 여부를 저장
    // ==================================================

    // 업무 태만 횟수
    public int neglectCount =
            0;

    // 1차 경고 발생 여부
    public boolean neglectWarning1 =
            false;

    // 2차 경고 발생 여부
    public boolean neglectWarning2 =
            false;

    // 징계 선택 대기 여부
    public boolean neglectDisciplinePending =
            false;

    // ==================================================
    // 야근 택시 이벤트 상태
    // 야근 후 택시 이벤트 발생 여부를 저장
    // ==================================================

    // 택시 이벤트 예약 여부
    public boolean taxiEventPending =
            false;


    // ==================================================
    // 최근 인사평가 결과
    // 승진 심사에 사용
    // ==================================================

    // 최근 인사평가 등급
    public String lastPerformanceGrade =
            "";

    // ==================================================
    // 플레이어 상태 초기화
    // 새 게임 시작 시 사용
    // ==================================================
    public void reset() {

        // --------------------------------------------------
        // 기본 게임 상태
        // --------------------------------------------------

        day = 1;
        position = "사원";

        money = 200_000;
        cardDebt = 0;
        salary = 2_500_000;

        stress = 10;
        ability = 30;
        stamina = 100;
        reputation = 10;

        gameOver = false;
        endingMessage = "";


        // --------------------------------------------------
        // NPC 특수 효과 초기화
        // --------------------------------------------------

        teamLeaderSupportUnlocked = false;
        seniorHelpUnlocked = false;
        coworkerHelpUnlocked = false;

        teamLeaderPenaltyTriggered = false;
        seniorPenaltyTriggered = false;
        coworkerPenaltyTriggered = false;

        // --------------------------------------------------
        // 승진 선택 상태 초기화
        // --------------------------------------------------

        // 승진 선택 대기 상태 초기화
        promotionChoicePending =
                false;

        // 승진 포기 상태 초기화
        promotionAbandoned =
                false;

        // 승진 메시지 초기화
        promotionMessage = "";


        // --------------------------------------------------
        // 행동 기록 초기화
        // --------------------------------------------------

        overtimeCount = 0;
        coffeeCount = 0;
        dinnerCount = 0;
        studyCount = 0;
        exerciseCount = 0;
        neglectCount = 0;


        // 최근 인사평가 등급 초기화
        lastPerformanceGrade = "";


        // 야근 택시 이벤트 초기화
        taxiEventPending = false;

        // --------------------------------------------------
        // 연쇄 이벤트 초기화
        // --------------------------------------------------

        juniorHelpAccepted = false;
        juniorHelpAcceptedDay = 0;
        juniorFollowupSeen = false;

        assistantHelpAccepted = false;
        assistantHelpAcceptedDay = 0;
        assistantFollowupSeen = false;

        projectAccepted = false;
        projectAcceptedDay = 0;
        projectResultSeen = false;

        conflictMediated = false;
        conflictMediatedDay = 0;
        conflictFollowupSeen = false;

        departmentDecisionMade = false;
        departmentDecisionDay = 0;
        departmentDecisionChoice = 0;
        departmentResultSeen = false;

        // ==================================================
        // 이전 상태 초기화
        // 게임 시작 시 변화량이 잘못 표시되는 것을 방지
        // ==================================================

        previousMoney =
                money;

        previousCardDebt =
                cardDebt;

        previousSalary =
                salary;

        previousStress =
                stress;

        previousAbility =
                ability;

        previousStamina =
                stamina;

        previousReputation =
                reputation;
    }

    // ==================================================
    // 현재 상태 저장
    // 이벤트 처리 직전에 호출
    // 오늘 변화량 계산에 사용
    // ==================================================
    public void savePreviousStatus() {

        // --------------------------------------------------
        // 돈 관련 상태 저장
        // --------------------------------------------------

        previousMoney =
                money;

        previousCardDebt =
                cardDebt;

        previousSalary =
                salary;


        // --------------------------------------------------
        // 능력치 상태 저장
        // --------------------------------------------------

        previousStress =
                stress;

        previousAbility =
                ability;

        previousStamina =
                stamina;

        previousReputation =
                reputation;
    }
}


