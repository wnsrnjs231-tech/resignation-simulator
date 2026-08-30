package com.example.resignationweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ==================================================
// 이벤트 생성 / 관리 클래스
// 게임에서 사용할 이벤트들을 만들고
// 현재 발생할 이벤트를 랜덤으로 선택
// ==================================================
public class EventManager {


    // ==================================================
    // 평일 이벤트 목록
    // ==================================================
    private final ArrayList<Event> events =
            new ArrayList<>();


    // ==================================================
    // 주말 이벤트 목록
    // 토요일 / 일요일에 사용할 이벤트 저장
    // ==================================================
    private final ArrayList<Event> weekendEvents =
            new ArrayList<>();

    // 랜덤 이벤트 선택에 사용
    private final Random random =
            new Random();

    // ==================================================
    // 직전 이벤트 타입
    // 같은 이벤트 연속 등장 방지
    // ==================================================
    private String lastEventType =
            "";

    // ==================================================
    // 월급 이벤트
    // 30일마다 발생
    // ==================================================
    private Event salaryEvent;


    // ==================================================
    // EventManager 생성자
    // 게임 시작 시 사용할 이벤트들을 미리 생성
    // ==================================================
    public EventManager() {

        // ==================================================
        // 월급 이벤트 생성
        // ==================================================
        salaryEvent =
                new Event();

        salaryEvent.type =
                "salary";

        salaryEvent.question =
                "💰 월급날입니다!";

        // ==================================================
        // 야근 이벤트 생성
        // ==================================================

        Event overtimeEvent =
                new Event();

        overtimeEvent.type =
                "overtime";

        overtimeEvent.question =
                "팀장이 야근을 부탁합니다.";

        overtimeEvent.choice1 =
                "한다";

        overtimeEvent.choice2 =
                "거절한다";

        overtimeEvent.result1 =
                "야근을 하기로 했습니다.";

        overtimeEvent.result2 =
                "야근을 거절했습니다.";


        // ==================================================
        // 선택 1 - 야근한다
        // ==================================================

        overtimeEvent.moneyChange1 = 0;
        overtimeEvent.stressChange1 = 5;
        overtimeEvent.abilityChange1 = 2;
        overtimeEvent.staminaChange1 = -10;
        overtimeEvent.reputationChange1 = 0;


        // ==================================================
        // 선택 2 - 거절한다
        // ==================================================

        overtimeEvent.moneyChange2 = 0;
        overtimeEvent.stressChange2 = -2;
        overtimeEvent.abilityChange2 = -1;
        overtimeEvent.staminaChange2 = 5;
        overtimeEvent.reputationChange2 = 0;


        // ==================================================
        // 커피 이벤트 생성
        // ==================================================

        Event coffeeEvent =
                new Event();

        coffeeEvent.type =
                "coffee";

        coffeeEvent.question =
                "동료가 커피를 사달라고 합니다.";

        coffeeEvent.choice1 =
                "사준다";

        coffeeEvent.choice2 =
                "거절한다";

        coffeeEvent.result1 =
                "동료에게 커피를 사줬습니다.";

        coffeeEvent.result2 =
                "커피를 사주지 않았습니다.";


        // ==================================================
        // 선택 1 - 커피를 사준다
        // ==================================================

        coffeeEvent.moneyChange1 = -10_000;
        coffeeEvent.stressChange1 = 1;
        coffeeEvent.abilityChange1 = 1;
        coffeeEvent.staminaChange1 = 0;
        coffeeEvent.reputationChange1 = 2;


        // ==================================================
        // 선택 2 - 거절한다
        // ==================================================

        coffeeEvent.moneyChange2 = 0;
        coffeeEvent.stressChange2 = 0;
        coffeeEvent.abilityChange2 = -1;
        coffeeEvent.staminaChange2 = 0;
        coffeeEvent.reputationChange2 = -1;


        // ==================================================
        // 업무량 증가 이벤트
        // ==================================================

        Event workEvent =
                new Event();

        workEvent.type =
                "workload";

        workEvent.question =
                "갑자기 업무가 몰려왔습니다.";

        workEvent.choice1 =
                "열심히 한다";

        workEvent.choice2 =
                "대충 한다";

        workEvent.result1 =
                "열심히 업무를 처리했습니다.";

        workEvent.result2 =
                "적당히 처리하고 퇴근했습니다.";


        // ==================================================
        // 선택 1 - 열심히 한다
        // ==================================================

        workEvent.moneyChange1 = 0;
        workEvent.stressChange1 = 8;
        workEvent.abilityChange1 = 3;
        workEvent.staminaChange1 = 0;
        workEvent.reputationChange1 = 0;


        // ==================================================
        // 선택 2 - 대충 한다
        // ==================================================

        workEvent.moneyChange2 = 0;
        workEvent.stressChange2 = 2;
        workEvent.abilityChange2 = -2;
        workEvent.staminaChange2 = 0;
        workEvent.reputationChange2 = 0;


        // ==================================================
        // 점심 이벤트
        // ==================================================

        Event lunchEvent =
                new Event();

        lunchEvent.type =
                "lunch";

        lunchEvent.question =
                "동료가 점심을 같이 먹자고 합니다.";

        lunchEvent.choice1 =
                "같이 먹는다";

        lunchEvent.choice2 =
                "혼자 먹는다";

        lunchEvent.result1 =
                "동료와 맛있게 점심을 먹었습니다.";

        lunchEvent.result2 =
                "혼자 조용히 점심을 먹었습니다.";


        // ==================================================
        // 선택 1 - 같이 먹는다
        // ==================================================

        lunchEvent.moneyChange1 = -15_000;
        lunchEvent.stressChange1 = -4;
        lunchEvent.abilityChange1 = 0;
        lunchEvent.staminaChange1 = 0;
        lunchEvent.reputationChange1 = 0;


        // ==================================================
        // 선택 2 - 혼자 먹는다
        // ==================================================

        lunchEvent.moneyChange2 = 0;
        lunchEvent.stressChange2 = 2;
        lunchEvent.abilityChange2 = 0;
        lunchEvent.staminaChange2 = 0;
        lunchEvent.reputationChange2 = 0;


        // ==================================================
        // 회식 이벤트
        // ==================================================

        Event dinnerEvent =
                new Event();

        dinnerEvent.type =
                "dinner";

        dinnerEvent.question =
                "팀에서 회식을 하자고 합니다.";

        dinnerEvent.choice1 =
                "참석한다";

        dinnerEvent.choice2 =
                "거절한다";

        dinnerEvent.result1 =
                "동료들과 즐겁게 회식을 했습니다.";

        dinnerEvent.result2 =
                "회식을 거절하고 집에 갔습니다.";


        // ==================================================
        // 선택 1 - 회식 참석
        // ==================================================

        dinnerEvent.moneyChange1 = -50_000;
        dinnerEvent.stressChange1 = -5;
        dinnerEvent.abilityChange1 = 2;
        dinnerEvent.staminaChange1 = 0;
        dinnerEvent.reputationChange1 = 0;


        // ==================================================
        // 선택 2 - 회식 거절
        // ==================================================

        dinnerEvent.moneyChange2 = 0;
        dinnerEvent.stressChange2 = 5;
        dinnerEvent.abilityChange2 = -1;
        dinnerEvent.staminaChange2 = 0;
        dinnerEvent.reputationChange2 = 0;


        // ==================================================
        // 이벤트 목록 등록
        // getRandomEvent()가 이 목록에서 랜덤으로 선택
        // ==================================================

        events.add(
                overtimeEvent
        );

        events.add(
                coffeeEvent
        );

        events.add(
                workEvent
        );

        events.add(
                lunchEvent
        );

        events.add(
                dinnerEvent
        );

        // ==================================================
        // 🌴 주말 이벤트
        // ==================================================


        // --------------------------------------------------
        // 🛋️ 휴식 / 자기계발 이벤트
        // --------------------------------------------------

        Event weekendRestEvent =
                new Event();

        weekendRestEvent.type =
                "weekend";

        weekendRestEvent.question =
                "이번 주도 힘들었습니다. 주말에 무엇을 할까요?";


        // 선택지
        weekendRestEvent.choice1 =
                "집에서 푹 쉰다";

        weekendRestEvent.choice2 =
                "자기계발을 한다";


        // 선택 결과 메시지
        weekendRestEvent.result1 =
                "집에서 푹 쉬었습니다.";

        weekendRestEvent.result2 =
                "자기계발을 했습니다.";


        // --------------------------------------------------
        // 선택 1 - 집에서 휴식
        // --------------------------------------------------
        weekendRestEvent.moneyChange1 = 0;
        weekendRestEvent.stressChange1 = -10;
        weekendRestEvent.abilityChange1 = 0;
        weekendRestEvent.staminaChange1 = 20;
        weekendRestEvent.reputationChange1 = 0;


        // --------------------------------------------------
        // 선택 2 - 자기계발
        // --------------------------------------------------
        weekendRestEvent.moneyChange2 = 0;
        weekendRestEvent.stressChange2 = 5;
        weekendRestEvent.abilityChange2 = 3;
        weekendRestEvent.staminaChange2 = -10;
        weekendRestEvent.reputationChange2 = 0;


        // --------------------------------------------------
        // 👥 친구 만나기 이벤트
        // --------------------------------------------------

        Event weekendFriendEvent =
                new Event();

        weekendFriendEvent.type =
                "weekend";

        weekendFriendEvent.question =
                "친구가 주말에 만나자고 합니다.";


        // 선택지
        weekendFriendEvent.choice1 =
                "만난다";

        weekendFriendEvent.choice2 =
                "거절하고 쉰다";


        // 선택 결과 메시지
        weekendFriendEvent.result1 =
                "친구와 만나 즐거운 시간을 보냈습니다.";

        weekendFriendEvent.result2 =
                "약속을 거절하고 집에서 쉬었습니다.";


        // --------------------------------------------------
        // 선택 1 - 친구 만나기
        // --------------------------------------------------
        weekendFriendEvent.moneyChange1 = -50_000;
        weekendFriendEvent.stressChange1 = -15;
        weekendFriendEvent.abilityChange1 = 0;
        weekendFriendEvent.staminaChange1 = -5;
        weekendFriendEvent.reputationChange1 = 0;


        // --------------------------------------------------
        // 선택 2 - 집에서 쉬기
        // --------------------------------------------------
        weekendFriendEvent.moneyChange2 = 0;
        weekendFriendEvent.stressChange2 = -5;
        weekendFriendEvent.abilityChange2 = 0;
        weekendFriendEvent.staminaChange2 = 10;
        weekendFriendEvent.reputationChange2 = 0;


        // --------------------------------------------------
        // 💵 주말 알바 이벤트
        // --------------------------------------------------

        Event weekendPartTimeEvent =
                new Event();

        weekendPartTimeEvent.type =
                "weekend";

        weekendPartTimeEvent.question =
                "생활비가 부족합니다. 주말에 알바를 할까요?";


        // 선택지
        weekendPartTimeEvent.choice1 =
                "알바한다";

        weekendPartTimeEvent.choice2 =
                "그냥 쉰다";


        // 선택 결과 메시지
        weekendPartTimeEvent.result1 =
                "주말에 알바를 해서 생활비를 벌었습니다.";

        weekendPartTimeEvent.result2 =
                "돈보다 휴식을 선택했습니다.";


        // --------------------------------------------------
        // 선택 1 - 알바
        // --------------------------------------------------
        weekendPartTimeEvent.moneyChange1 = 100_000;
        weekendPartTimeEvent.stressChange1 = 5;
        weekendPartTimeEvent.abilityChange1 = 0;
        weekendPartTimeEvent.staminaChange1 = -15;
        weekendPartTimeEvent.reputationChange1 = 0;


        // --------------------------------------------------
        // 선택 2 - 휴식
        // --------------------------------------------------
        weekendPartTimeEvent.moneyChange2 = 0;
        weekendPartTimeEvent.stressChange2 = -5;
        weekendPartTimeEvent.abilityChange2 = 0;
        weekendPartTimeEvent.staminaChange2 = 10;
        weekendPartTimeEvent.reputationChange2 = 0;


        // --------------------------------------------------
        // 🏋️ 운동 이벤트
        // --------------------------------------------------

        Event exerciseEvent =
                new Event();

        exerciseEvent.type =
                "exercise";

        exerciseEvent.question =
                "주말입니다. 운동을 할까요?";


        // 선택지
        exerciseEvent.choice1 =
                "운동한다";

        exerciseEvent.choice2 =
                "집에서 쉰다";


        // 선택 결과 메시지
        exerciseEvent.result1 =
                "운동으로 몸을 단련했습니다.";

        exerciseEvent.result2 =
                "집에서 편하게 쉬었습니다.";


        // --------------------------------------------------
        // 선택 1 - 운동
        // --------------------------------------------------
        exerciseEvent.moneyChange1 = 0;
        exerciseEvent.stressChange1 = -5;
        exerciseEvent.abilityChange1 = 0;
        exerciseEvent.staminaChange1 = 15;
        exerciseEvent.reputationChange1 = 0;


        // --------------------------------------------------
        // 선택 2 - 휴식
        // --------------------------------------------------
        exerciseEvent.moneyChange2 = 0;
        exerciseEvent.stressChange2 = -8;
        exerciseEvent.abilityChange2 = 0;
        exerciseEvent.staminaChange2 = 10;
        exerciseEvent.reputationChange2 = 0;


        // --------------------------------------------------
        // 🛍️ 쇼핑 이벤트
        // --------------------------------------------------

        Event shoppingEvent =
                new Event();

        shoppingEvent.type =
                "weekend";

        shoppingEvent.question =
                "쇼핑을 하고 싶습니다.";


        // 선택지
        shoppingEvent.choice1 =
                "쇼핑한다";

        shoppingEvent.choice2 =
                "참는다";


        // 선택 결과 메시지
        shoppingEvent.result1 =
                "쇼핑으로 스트레스를 풀었습니다.";

        shoppingEvent.result2 =
                "충동구매를 참았습니다.";


        // --------------------------------------------------
        // 선택 1 - 쇼핑
        // 큰 지출 대신 스트레스 감소
        // --------------------------------------------------
        shoppingEvent.moneyChange1 = -300_000;
        shoppingEvent.stressChange1 = -10;
        shoppingEvent.abilityChange1 = 0;
        shoppingEvent.staminaChange1 = 0;
        shoppingEvent.reputationChange1 = 0;


        // --------------------------------------------------
        // 선택 2 - 참기
        // --------------------------------------------------
        shoppingEvent.moneyChange2 = 0;
        shoppingEvent.stressChange2 = 2;
        shoppingEvent.abilityChange2 = 0;
        shoppingEvent.staminaChange2 = 0;
        shoppingEvent.reputationChange2 = 0;


        // --------------------------------------------------
        // 📚 공부 이벤트
        // --------------------------------------------------

        Event studyEvent =
                new Event();

        studyEvent.type =
                "study";

        studyEvent.question =
                "주말에 자기계발을 할까요?";


        // 선택지
        studyEvent.choice1 =
                "공부한다";

        studyEvent.choice2 =
                "쉰다";


        // 선택 결과 메시지
        studyEvent.result1 =
                "주말에도 열심히 공부했습니다.";

        studyEvent.result2 =
                "공부는 다음 주로 미뤘습니다.";


        // --------------------------------------------------
        // 선택 1 - 공부
        // --------------------------------------------------

        studyEvent.moneyChange1 = 0;
        studyEvent.stressChange1 = 5;
        studyEvent.abilityChange1 = 5;
        studyEvent.staminaChange1 = -10;
        studyEvent.reputationChange1 = 0;


        // --------------------------------------------------
        // 선택 2 - 휴식
        // --------------------------------------------------
        studyEvent.moneyChange2 = 0;
        studyEvent.stressChange2 = -8;
        studyEvent.abilityChange2 = 0;
        studyEvent.staminaChange2 = 10;
        studyEvent.reputationChange2 = 0;


        // ==================================================
        // 📦 주말 이벤트 등록
        // ==================================================

        weekendEvents.add(
                weekendRestEvent
        );

        weekendEvents.add(
                weekendFriendEvent
        );

        weekendEvents.add(
                weekendPartTimeEvent
        );

        weekendEvents.add(
                exerciseEvent
        );

        weekendEvents.add(
                shoppingEvent
        );

        weekendEvents.add(
                studyEvent
        );
    }

    // ==================================================
    // 야근 택시 이벤트 생성
    // ==================================================
    private Event createTaxiEvent() {

        // 택시 이벤트 생성
        Event event =
                new Event();

        // 이벤트 종류
        event.type =
                "taxi";

        // 질문
        event.question =
                "🌙 야근이 끝났다. 막차가 끊겼다.";

        // 선택지 1
        event.choice1 =
                "🚕 택시를 탄다.";

        // 선택지 2
        event.choice2 =
                "🚶 그냥 걸어서 간다.";

        // --------------------------------------------------
        // 택시 선택
        // --------------------------------------------------

        event.result1 =
                "택시를 타고 편하게 귀가했다.";

        // 택시비
        event.moneyChange1 =
                -50_000;

        // 스트레스 감소
        event.stressChange1 =
                -5;

        // 체력 회복
        event.staminaChange1 =
                5;


        // --------------------------------------------------
        // 걸어서 귀가
        // --------------------------------------------------

        event.result2 =
                "돈은 아꼈지만 너무 피곤하다.";

        // 스트레스 증가
        event.stressChange2 =
                5;

        // 체력 감소
        event.staminaChange2 =
                -10;


        // 이벤트 반환
        return event;
    }

    // ==================================================
    // 현재 직급에 맞는 이벤트 생성
    // 직급별로 하나의 전용 이벤트를 반환
    // ==================================================
    private Event getRankEvent(
            Player player) {

        // ==================================================
        // 사원 이벤트
        // ==================================================
        if (player.position.equals("사원")) {

            Event event =
                    new Event();

            event.type =
                    "junior_task";

            event.question =
                    "선배가 잡무를 부탁했습니다.";

            event.choice1 =
                    "도와준다";

            event.choice2 =
                    "거절한다";

            event.result1 =
                    "선배의 일을 도와줬습니다.";

            event.result2 =
                    "선배의 부탁을 거절했습니다.";

            event.moneyChange1 = 0;
            event.stressChange1 = 3;
            event.abilityChange1 = 2;
            event.staminaChange1 = -2;
            event.reputationChange1 = 2;

            event.moneyChange2 = 0;
            event.stressChange2 = -1;
            event.abilityChange2 = 0;
            event.staminaChange2 = 0;
            event.reputationChange2 = -2;

            return event;
        }


        // ==================================================
        // 대리 이벤트
        // ==================================================
        if (player.position.equals("대리")) {

            Event event =
                    new Event();

            event.type =
                    "assistant_task";

            event.question =
                    "후배가 업무 실수를 하고 도움을 요청했습니다.";

            event.choice1 =
                    "도와준다";

            event.choice2 =
                    "알아서 해결하라고 한다";

            event.result1 =
                    "후배의 실수를 함께 해결했습니다.";

            event.result2 =
                    "후배에게 스스로 해결하라고 했습니다.";

            event.stressChange1 = 3;
            event.abilityChange1 = 2;
            event.reputationChange1 = 2;

            event.stressChange2 = -1;
            event.abilityChange2 = 0;
            event.reputationChange2 = -2;

            return event;
        }


        // ==================================================
        // 과장 이벤트
        // ==================================================
        if (player.position.equals("과장")) {

            Event event =
                    new Event();

            event.type =
                    "manager_task";

            event.question =
                    "팀장이 중요한 프로젝트를 맡아보겠냐고 합니다.";

            event.choice1 =
                    "맡는다";

            event.choice2 =
                    "거절한다";

            event.result1 =
                    "중요 프로젝트를 맡았습니다.";

            event.result2 =
                    "프로젝트를 거절했습니다.";

            event.stressChange1 = 7;
            event.abilityChange1 = 3;
            event.reputationChange1 = 4;

            event.stressChange2 = -2;
            event.abilityChange2 = 0;
            event.reputationChange2 = -3;

            return event;
        }


        // ==================================================
        // 차장 이벤트
        // ==================================================
        if (player.position.equals("차장")) {

            Event event =
                    new Event();

            event.type =
                    "deputy_general_task";

            event.question =
                    "팀원들 사이에 갈등이 발생했습니다.";

            event.choice1 =
                    "직접 중재한다";

            event.choice2 =
                    "팀원들에게 맡긴다";

            event.result1 =
                    "팀원들의 갈등을 중재했습니다.";

            event.result2 =
                    "팀원들이 알아서 해결하도록 했습니다.";

            event.stressChange1 = 5;
            event.abilityChange1 = 2;
            event.reputationChange1 = 4;

            event.stressChange2 = -2;
            event.abilityChange2 = 0;
            event.reputationChange2 = -3;

            return event;
        }


        // ==================================================
        // 부장 이벤트
        // ==================================================
        if (player.position.equals("부장")) {

            Event event =
                    new Event();

            event.type =
                    "general_manager_task";

            event.question =
                    "경영진에서 부서 비용 절감을 요구했습니다.";

            event.choice1 =
                    "업무 프로세스를 개선한다";

            event.choice2 =
                    "직원들의 성과 압박을 높인다";

            event.result1 =
                    "업무 프로세스 개선을 시작했습니다.";

            event.result2 =
                    "직원들의 성과 압박을 높였습니다.";

            event.stressChange1 = 4;
            event.abilityChange1 = 3;
            event.reputationChange1 = 3;

            event.stressChange2 = 8;
            event.abilityChange2 = 2;
            event.reputationChange2 = -3;

            return event;
        }


        // ==================================================
        // 해당하는 직급이 없으면 null 반환
        // ==================================================
        return null;
    }

    // ==================================================
    // 사원 후속 이벤트 생성
    // 선배의 부탁을 도와준 뒤 5일 후 발생
    // ==================================================
    private Event createJuniorFollowupEvent() {

        // 새로운 이벤트 생성
        Event event =
                new Event();

        // 이벤트 종류
        event.type =
                "junior_followup";

        // 후속 이벤트 표시
        event.followupEvent =
                true;

        // 이벤트 내용
        event.question =
                "며칠 전 도와준 일을 선배가 기억하고 있습니다.";

        // 결과 메시지
        event.result1 =
                "선배의 도움으로 업무가 한결 수월해졌습니다.";

        event.result2 =
                "선배의 도움으로 업무가 한결 수월해졌습니다.";

        // 능력 +2
        event.abilityChange1 = 2;
        event.abilityChange2 = 2;

        // 스트레스 -3
        event.stressChange1 = -3;
        event.stressChange2 = -3;

        // 완성된 이벤트 반환
        return event;
    }

    // ==================================================
    // 대리 후속 이벤트 생성
    // 후배를 도와준 뒤 5일 후 발생
    // ==================================================
    private Event createAssistantFollowupEvent() {

        // 새로운 이벤트 생성
        Event event =
                new Event();

        // 이벤트 종류
        event.type =
                "assistant_followup";

        // 후속 이벤트 표시
        event.followupEvent =
                true;

        // 이벤트 내용
        event.question =
                "며칠 전 도와준 후배가 다시 찾아왔습니다.";

        // 결과 메시지
        event.result1 =
                "후배가 고마워하며 업무를 더 잘 처리하게 됐습니다.";

        event.result2 =
                "후배가 고마워하며 업무를 더 잘 처리하게 됐습니다.";

        // 스트레스 -5
        event.stressChange1 = -5;
        event.stressChange2 = -5;

        // 평판 +2
        event.reputationChange1 = 2;
        event.reputationChange2 = 2;

        // 완성된 이벤트 반환
        return event;
    }


    // ==================================================
    // 과장 프로젝트 결과 이벤트 생성
    // 프로젝트 수락 후 7일 뒤 발생
    // 업무 능력에 따라 성공 / 실패 결과 결정
    // ==================================================
    private Event createProjectResultEvent(
            Player player) {

        // 새로운 이벤트 생성
        Event event =
                new Event();

        // 이벤트 종류
        event.type =
                "project_result";

        // 후속 이벤트 표시
        event.followupEvent =
                true;

        // ==================================================
        // 업무 능력 75 이상
        // 프로젝트 성공
        // ==================================================
        if (player.ability >= 75) {

            // 성공 이벤트 내용
            event.question =
                    "중요 프로젝트가 성공적으로 끝났습니다!";

            // 성공 결과 메시지
            event.result1 =
                    "프로젝트 성공으로 좋은 평가를 받았습니다.";

            event.result2 =
                    "프로젝트 성공으로 좋은 평가를 받았습니다.";

            // 평판 +8
            event.reputationChange1 = 8;
            event.reputationChange2 = 8;

            // 능력 +3
            event.abilityChange1 = 3;
            event.abilityChange2 = 3;


        // ==================================================
        // 업무 능력 75 미만
        // 프로젝트 실패
        // ==================================================
        } else {

            // 실패 이벤트 내용
            event.question =
                    "중요 프로젝트에서 문제가 발생했습니다.";

            // 실패 결과 메시지
            event.result1 =
                    "프로젝트 결과가 좋지 않아 책임을 지게 됐습니다.";

            event.result2 =
                    "프로젝트 결과가 좋지 않아 책임을 지게 됐습니다.";

            // 스트레스 +10
            event.stressChange1 = 10;
            event.stressChange2 = 10;

            // 평판 -5
            event.reputationChange1 = -5;
            event.reputationChange2 = -5;
        }

        // 완성된 이벤트 반환
        return event;
    }


    // ==================================================
    // 차장 후속 이벤트 생성
    // 갈등 중재 후 5일 뒤 발생
    // ==================================================
    private Event createConflictFollowupEvent() {

        // 새로운 이벤트 생성
        Event event =
                new Event();

        // 이벤트 종류
        event.type =
                "conflict_followup";

        // 후속 이벤트 표시
        event.followupEvent =
                true;

        // 이벤트 내용
        event.question =
                "중재했던 팀원들의 갈등이 정리되었습니다.";

        // 결과 메시지
        event.result1 =
                "팀 분위기가 좋아지고 신뢰를 얻었습니다.";

        event.result2 =
                "팀 분위기가 좋아지고 신뢰를 얻었습니다.";

        // 평판 +5
        event.reputationChange1 = 5;
        event.reputationChange2 = 5;

        // 스트레스 -3
        event.stressChange1 = -3;
        event.stressChange2 = -3;

        // 완성된 이벤트 반환
        return event;
    }


    // ==================================================
    // 부장 후속 이벤트 생성
    // 부서 운영 결정 후 7일 뒤 발생
    // 선택했던 방식에 따라 결과가 달라짐
    // ==================================================
    private Event createDepartmentResultEvent(
            Player player) {

        // 새로운 이벤트 생성
        Event event =
                new Event();

        // 이벤트 종류
        event.type =
                "department_result";

        // 후속 이벤트 표시
        event.followupEvent =
                true;

        // ==================================================
        // 선택 1
        // 프로세스 개선
        // ==================================================
        if (player.departmentDecisionChoice == 1) {

            // 결과 내용
            event.question =
                    "업무 프로세스 개선 결과가 나왔습니다.";

            // 결과 메시지
            event.result1 =
                    "업무 효율이 개선되고 팀 분위기도 좋아졌습니다.";

            event.result2 =
                    "업무 효율이 개선되고 팀 분위기도 좋아졌습니다.";

            // 평판 +7
            event.reputationChange1 = 7;
            event.reputationChange2 = 7;

            // 능력 +2
            event.abilityChange1 = 2;
            event.abilityChange2 = 2;


        // ==================================================
        // 선택 2
        // 직원 성과 압박
        // ==================================================
        } else {

            // 결과 내용
            event.question =
                    "성과 압박 정책의 결과가 나왔습니다.";

            // 결과 메시지
            event.result1 =
                    "성과는 올랐지만 팀 분위기가 크게 나빠졌습니다.";

            event.result2 =
                    "성과는 올랐지만 팀 분위기가 크게 나빠졌습니다.";

            // 능력 +3
            event.abilityChange1 = 3;
            event.abilityChange2 = 3;

            // 스트레스 +7
            event.stressChange1 = 7;
            event.stressChange2 = 7;
        }

        // 완성된 이벤트 반환
        return event;
    }

    // ==================================================
    // 오늘 발생할 이벤트 선택
    // 날짜 + 현재 직급을 함께 확인
    // ==================================================
    public Event getTodayEvent(
            int day,
            Player player) {


        // --------------------------------------------------
        // 야근 택시 이벤트
        // 일반 이벤트보다 먼저 확인
        // --------------------------------------------------

        if (player.taxiEventPending) {

            // 예약 상태 해제
            player.taxiEventPending =
                    false;

            // 택시 이벤트 반환
            return createTaxiEvent();
        }

        // ==================================================
        // 연쇄 이벤트 확인
        // 일반 이벤트보다 우선적으로 발생
        // ==================================================

        // ==================================================
        // 사원 후속 이벤트
        // 도움 수락 5일 뒤 발생
        // ==================================================
        if (player.juniorHelpAccepted
                && !player.juniorFollowupSeen
                && day >= player.juniorHelpAcceptedDay + 5) {

            return createJuniorFollowupEvent();
        }


        // ==================================================
        // 대리 후속 이벤트
        // 도움 수락 5일 뒤 발생
        // ==================================================
        if (player.assistantHelpAccepted
                && !player.assistantFollowupSeen
                && day >= player.assistantHelpAcceptedDay + 5) {

            return createAssistantFollowupEvent();
        }


        // ==================================================
        // 과장 후속 이벤트
        // 프로젝트 수락 7일 뒤 발생
        // ==================================================
        if (player.projectAccepted
                && !player.projectResultSeen
                && day >= player.projectAcceptedDay + 7) {

            return createProjectResultEvent(
                    player
            );
        }


        // ==================================================
        // 차장 후속 이벤트
        // 갈등 중재 5일 뒤 발생
        // ==================================================
        if (player.conflictMediated
                && !player.conflictFollowupSeen
                && day >= player.conflictMediatedDay + 5) {

            return createConflictFollowupEvent();
        }


        // ==================================================
        // 부장 후속 이벤트
        // 결정 7일 뒤 발생
        // ==================================================
        if (player.departmentDecisionMade
                && !player.departmentResultSeen
                && day >= player.departmentDecisionDay + 7) {

            return createDepartmentResultEvent(
                    player
            );
        }

        // ==================================================
        // 월급날
        // ==================================================
        if (day % 30 == 0) {

            return salaryEvent;
        }


        // ==================================================
        // 주말
        // ==================================================
        if (day % 7 == 6 ||
                day % 7 == 0) {

            return getRandomWeekendEvent();
        }


        // ==================================================
        // 일반 이벤트 후보 생성
        // ==================================================

        List<Event> availableEvents =
                new ArrayList<>(
                        events
                );


        // ==================================================
        // 직급별 일반 이벤트 필터링
        // ==================================================

        // 과장
        if (player.position.equals("과장")) {

            // 야근 이벤트 제거
            availableEvents.removeIf(
                    event -> event.type.equals("overtime")
            );
        }


        // 차장
        else if (player.position.equals("차장")) {

            // 야근 / 업무량 이벤트 제거
            availableEvents.removeIf(
                    event -> event.type.equals("overtime")
                            || event.type.equals("workload")
            );
        }


        // 부장
        else if (player.position.equals("부장")) {

            // 야근 / 업무량 이벤트 제거
            availableEvents.removeIf(
                    event -> event.type.equals("overtime")
                            || event.type.equals("workload")
            );
        }


        // ==================================================
        // 현재 직급 전용 이벤트 추가
        // ==================================================
        Event rankEvent =
                getRankEvent(
                        player
                );

        // 직급 이벤트가 있으면 후보 목록에 추가
        if (rankEvent != null) {

            availableEvents.add(
                    rankEvent
            );
        }

        // --------------------------------------------------
        // 직전 이벤트 제외
        // 같은 이벤트 연속 등장 방지
        // --------------------------------------------------

        availableEvents.removeIf(
                event -> event.type.equals(lastEventType)
        );

        // --------------------------------------------------
        // 오늘 이벤트 선택
        // --------------------------------------------------

        Event selectedEvent =
                availableEvents.get(
                        random.nextInt(
                                availableEvents.size()
                        )
                );


        // --------------------------------------------------
        // 오늘 이벤트 기록
        // --------------------------------------------------

        lastEventType =
                selectedEvent.type;


        // 선택된 이벤트 반환
        return selectedEvent;
    }

    // ==================================================
    // 랜덤 이벤트 선택
    // 등록된 이벤트 중 하나를 랜덤으로 반환
    // ==================================================
    public Event getRandomEvent() {

        // 랜덤 이벤트 번호 생성
        int eventIndex =
                random.nextInt(
                        events.size()
                );

        // 선택된 이벤트 반환
        return events.get(
                eventIndex
        );

    }

    // ==================================================
    // 주말 랜덤 이벤트 선택
    // weekendEvents 목록에서 이벤트 하나를 랜덤 반환
    // ==================================================
    private Event getRandomWeekendEvent() {

        // 랜덤 이벤트 번호 생성
        int eventIndex =
                random.nextInt(
                        weekendEvents.size()
                );

        // 선택된 주말 이벤트 반환
        return weekendEvents.get(
                eventIndex
        );
    }
}