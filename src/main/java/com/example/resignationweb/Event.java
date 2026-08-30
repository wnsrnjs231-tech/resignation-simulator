package com.example.resignationweb;

// ==================================================
// 게임 이벤트 정보 저장 클래스
// 이벤트 질문, 선택지, 결과,
// 선택에 따른 능력치 변화량을 저장
// ==================================================
public class Event {

    // ==================================================
    // 이벤트 기본 정보
    // ==================================================

    // 이벤트 종류
    public String type;

    // 이벤트 질문
    public String question;

    // 선택지 1
    public String choice1;

    // 선택지 2
    public String choice2;

    // 선택 1 결과 메시지
    public String result1;

    // 선택 2 결과 메시지
    public String result2;

    // ==================================================
    // 후속 이벤트 여부
    // true면 선택지가 아니라 확인 버튼 하나만 표시
    // ==================================================
    public boolean followupEvent;


    // ==================================================
    // 선택 1 변화량
    // ==================================================

    // 돈 변화
    public int moneyChange1;

    // 스트레스 변화
    public int stressChange1;

    // 업무 능력 변화
    public int abilityChange1;

    // 체력 변화
    public int staminaChange1;

    // 평판 변화
    public int reputationChange1;


    // ==================================================
    // 선택 2 변화량
    // ==================================================

    // 돈 변화
    public int moneyChange2;

    // 스트레스 변화
    public int stressChange2;

    // 업무 능력 변화
    public int abilityChange2;

    // 체력 변화
    public int staminaChange2;

    // 평판 변화
    public int reputationChange2;
}