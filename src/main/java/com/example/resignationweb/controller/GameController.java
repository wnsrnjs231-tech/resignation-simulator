package com.example.resignationweb.controller;

import com.example.resignationweb.GameService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// ==================================================
// 게임 Controller
// 웹 요청을 받고 GameService에게 게임 처리를 맡김
// ==================================================
@Controller
public class GameController {

    // ==================================================
    // 1. 게임 서비스
    // ==================================================

    // 실제 게임 진행 로직 담당
    private final GameService gameService =
            new GameService();


    // ==================================================
    // 2. GET 요청
    // ==================================================

    // --------------------------------------------------
    // 게임 메인 화면
    // --------------------------------------------------

    @GetMapping("/game")
    public String game(
            Model model) {

        // 현재 게임 상태를 화면에 전달
        gameService.addGameData(
                model
        );

        // game.mustache 화면 출력
        return "game";
    }


    // ==================================================
    // 3. POST 요청
    // ==================================================

    // --------------------------------------------------
    // 일반 이벤트 선택
    // --------------------------------------------------

    @PostMapping("/game/choice")
    public String selectChoice(
            @RequestParam("choice") int choice,
            Model model) {

        // 일반 이벤트 선택 처리
        gameService.selectChoice(
                choice,
                model
        );

        // 게임 화면 출력
        return "game";
    }


    // --------------------------------------------------
    // 월급 이벤트
    // --------------------------------------------------

    @PostMapping("/game/salary")
    public String receiveSalary(
            Model model) {

        // 월급 이벤트 처리
        gameService.receiveSalary(
                model
        );

        // 게임 화면 출력
        return "game";
    }


    // --------------------------------------------------
    // 후속 이벤트
    // --------------------------------------------------

    @PostMapping("/game/followup")
    public String processFollowup(
            Model model) {

        // 후속 이벤트 처리
        gameService.processFollowup(
                model
        );

        // 게임 화면 출력
        return "game";
    }


    // --------------------------------------------------
    // 실제 퇴사 처리
    // --------------------------------------------------

    @PostMapping("/game/resign/confirm")
    public String resign(
            Model model) {

        // 실제 퇴사 처리
        gameService.resign(
                model
        );

        // 게임 화면 출력
        return "game";
    }

    // --------------------------------------------------
    // 업무 태만 징계 - 감봉
    // --------------------------------------------------

    @PostMapping("/game/neglect/paycut")
    public String acceptNeglectPayCut(
            Model model) {

        // 감봉 징계 처리
        gameService.acceptNeglectPayCut(
                model
        );

        // 게임 화면 출력
        return "game";
    }


    // --------------------------------------------------
    // 업무 태만 징계 - 퇴사
    // --------------------------------------------------

    @PostMapping("/game/neglect/resign")
    public String resignFromNeglect(
            Model model) {

        // 업무 태만 퇴사 처리
        gameService.resignFromNeglect(
                model
        );

        // 게임 화면 출력
        return "game";
    }

    // ==================================================
    // 과장 승진 수락
    // ==================================================
    @PostMapping("/game/promotion/accept")
    public String acceptPromotion(
            Model model) {

        // 승진 수락 처리
        gameService.acceptPromotion(
                model
        );

        // 게임 화면 출력
        return "game";
    }


    // ==================================================
    // 과장 승진 포기
    // 워라밸 루트 진입
    // ==================================================
    @PostMapping("/game/promotion/decline")
    public String declinePromotion(
            Model model) {

        // 승진 포기 처리
        gameService.declinePromotion(
                model
        );

        // 게임 화면 출력
        return "game";
    }

    // --------------------------------------------------
    // 처음부터 다시 시작
    // --------------------------------------------------

    @PostMapping("/game/restart")
    public String restartGame(
            Model model) {

        // 게임 초기화
        gameService.restartGame(
                model
        );

        // 게임 화면 출력
        return "game";
    }
}