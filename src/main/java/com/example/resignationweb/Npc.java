package com.example.resignationweb;

// ==================================================
// NPC 정보 저장 클래스
// 이름과 플레이어와의 관계도를 보관
// ==================================================
public class Npc {

    // NPC 이름
    public String name;

    // 관계도
    // 0 ~ 100 범위
    public int relationship;


    // ==================================================
    // NPC 생성자
    // NPC 이름과 시작 관계도를 설정
    // ==================================================
    public Npc(
            String name,
            int relationship) {

        // NPC 이름 저장
        this.name =
                name;

        // 시작 관계도 저장
        this.relationship =
                relationship;
    }
}