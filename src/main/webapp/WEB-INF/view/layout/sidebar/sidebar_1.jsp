<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <base href="/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Sidebar 1</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/layout/common.css">
    <link rel="stylesheet" href="/layout/sidebar/sidebar_1/common.css">
    <link rel="stylesheet" href="/layout/sidebar/sidebar_1/header.css">
    <link rel="stylesheet" href="/layout/sidebar/sidebar_1/aside.css">
    <link rel="stylesheet" href="/layout/sidebar/sidebar_1/main.css">
    <script src="/layout/sidebar/sidebar_1/toggle.js" defer></script>
</head>
<body>
    <header class="sidebar1_lay_hd">
        <div class="sidebar1_lay_hd_toggle">
            <span class="bi bi-list" id="headerToggle" aria-expanded="false"></span>
        </div>

        <div class="sidebar1_lay_hd_title">
            <span>Smart Chatbot Plus for Kakao</span>
        </div>

        <div class="sidebar1_lay_hd_icons">
            <div><button type="button" class="btn btn-default"><span class="bi bi-bell lao_hd_icon"></span></button></div>
            <div><button type="button" class="btn btn-default"><span class="bi bi-window-dock lao_hd_icon"></span></button></div>
            <div><button type="button" class="btn btn-default"><span <%--sec:authentication="name"--%>></span></button></div>
        </div>
    </header>

    <aside class="sidebar1_lay_sd" id="sidebar" aria-expanded="false">
        <ul>
            <li>
                <a class="collapsed" data-bs-target="#asideScn" data-bs-toggle="collapse" href="#">
                    <i class="bi bi-book side-icon"></i>
                    <span>시나리오 관리</span>
                    <i class="bi bi-chevron-down ms-auto"></i>
                </a>
                <ul id="asideScn" class="collapse" data-bs-parent="#sidebar" aria-visible="false">
                    <li><span>챗봇트리 관리</span></li>
                    <li><span>엔티티 관리</span></li>
                </ul>
            </li>
            <li>
                <a class="collapsed" data-bs-target="#asideQna" data-bs-toggle="collapse" href="#">
                    <i class="bi bi-question-circle side-icon"></i>
                    <span>문답관리</span>
                    <i class="bi bi-chevron-down ms-auto"></i>
                </a>
                <ul id="asideQna" class="collapse" data-bs-parent="#sidebar" aria-collapsed="true">
                    <li><span>답변관리</span></li>
                </ul>
            </li>
            <li>
                <a class="collapsed" data-bs-target="#asideCnt" data-bs-toggle="collapse" href="#">
                    <i class="bi bi-body-text side-icon"></i>
                    <span>컨텐츠관리</span>
                    <i class="bi bi-chevron-down ms-auto"></i>
                </a>
                <ul id="asideCnt" class="collapse" data-bs-parent="#sidebar">
                    <li><span>알림톡컨텐츠</span></li>
                </ul>
            </li>
            <li>
                <a class="collapsed" data-bs-target="#asideSdb" data-bs-toggle="collapse" href="#">
                    <i class="bi bi-clipboard-data side-icon"></i>
                    <span>전문DB관리</span>
                    <i class="bi bi-chevron-down ms-auto"></i>
                </a>
                <ul id="asideSdb" class="collapse" data-bs-parent="#sidebar">
                    <li><span>지방관서정보</span></li>
                    <li><span>근로복지공단정보</span></li>
                </ul>
            </li>
            <li>
                <a class="collapsed" data-bs-target="#asideChn" data-bs-toggle="collapse" href="#">
                    <i class="bi bi-megaphone side-icon"></i>
                    <span>채널요청관리</span>
                    <i class="bi bi-chevron-down ms-auto"></i>
                </a>
                <ul id="asideChn" class="collapse" data-bs-parent="#sidebar">
                    <li><span>채널요청이력</span></li>
                    <li><span>채널답변이력</span></li>
                </ul>
            </li>
            <li>
                <a class="collapsed" data-bs-target="#asideCbt" data-bs-toggle="collapse" href="#">
                    <i class="bi bi-chat-dots side-icon"></i>
                    <span>챗봇상담관리</span>
                    <i class="bi bi-chevron-down ms-auto"></i>
                </a>
                <ul id="asideCbt" class="collapse" data-bs-parent="#sidebar">
                    <li><span>챗봇상담이력</span></li>
                </ul>
            </li>
            <li>
                <a class="collapsed" data-bs-target="#asideStt" data-bs-toggle="collapse" href="#">
                    <i class="bi bi-bar-chart-line side-icon"></i>
                    <span>통계관리</span>
                    <i class="bi bi-chevron-down ms-auto"></i>
                </a>
                <ul id="asideStt" class="collapse" data-bs-parent="#sidebar">
                    <li><span>챗봇상담통계</span></li>
                </ul>
            </li>
            <li>
                <a class="collapsed" data-bs-target="#asideLnk" data-bs-toggle="collapse" href="#">
                    <i class="bi bi-file-person side-icon"></i>
                    <span>업무연결서비스</span>
                    <i class="bi bi-chevron-down ms-auto"></i>
                </a>
                <ul id="asideLnk" class="collapse" data-bs-parent="#sidebar">
                    <li><span>알림톡발송이력</span></li>
                    <li><span>본인인증이력</span></li>
                </ul>
            </li>
        </ul>

        <!--
        <div id="logout" class="logout d-flex" aria-expanded="false">
            <i class="bi bi-box-arrow-left"></i>
            <span>도움말?</span>
        </div>
        -->

    </aside>

    <main class="sidebar1_lay_ma" id="main" aria-expanded="false">
        <div style="border:1px solid red; height:100%;">
            메인영역입니다.
        </div>
    </main>
</body>
</html>