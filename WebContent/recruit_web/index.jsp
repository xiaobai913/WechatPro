﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<% String path=request.getContextPath();  
   String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
 	request.setAttribute("path",path);  
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basepath %>recruit_web/">
<base src="<%=basepath %>recruit_web/">
<meta charset="utf8">
<title>公众号招聘</title>
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link href="assets/css/swiper.min.css" rel="stylesheet">
<link href="assets/css/base.css" rel="stylesheet">
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/swiper.min.js"></script>
<script src="assets/js/base.js"></script>
</head>

<body>

        <div class="header">
            <a class="btn-left" id="city"><i class="ico-posi"></i></a>
            <h3 class="title">
                <div class="top-search">
                    <input class="txt" type="text" placeholder="输入职位或公司名">
                    <input class="btn" type="submit" value="">
                </div>
            </h3>
            <a class="btn-right" href="javascript:;" id="topuser"><i class="ico-user"></i></a>
            <div class="top-menu">
                <ul>
                    <li><a href="javascript:;">${nickname}</a></li>
                    <li class="divider"></li>
                    <li><a href="#"><i class="ico-resume"></i><span>我的简历</span></a></li>
                    <li><a href="#"><i class="ico-exit"></i><span>退出</span></a></li>
                </ul>
            </div>
        </div>
        <div class="content">
            <div class="swiper-container index-slider">
                <div class="swiper-wrapper">
                    <div class="swiper-slide"><img src="assets/images/banner.jpg"></div>
                    <div class="swiper-slide"><img src="assets/images/banner.jpg"></div>
                    <div class="swiper-slide"><img src="assets/images/banner.jpg"></div>
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>
            </div>
            <div class="menu-list clearfix">
                <a class="item" href="#"><i class="ico-menu ico-menu-1"></i><span>UI设计</span></a>
                <a class="item" href="#"><i class="ico-menu ico-menu-2"></i><span>PHP</span></a>
                <a class="item" href="#"><i class="ico-menu ico-menu-3"></i><span>JAVA</span></a>
                <a class="item" href="#"><i class="ico-menu ico-menu-4"></i><span>IOS</span></a>
                <a class="item" href="#"><i class="ico-menu ico-menu-5"></i><span>架构师</span></a>
                <a class="item" href="#"><i class="ico-menu ico-menu-6"></i><span>安卓</span></a>
                <a class="item" href="#"><i class="ico-menu ico-menu-7"></i><span>产品</span></a>
                <a class="item" href="#"><i class="ico-menu ico-menu-8"></i><span>UED</span></a>
                <a class="item" href="#"><i class="ico-menu ico-menu-9"></i><span>运营</span></a>
            </div>
            <div class="line-divider"></div>
            <div class="layout">
                <h3 class="m-title">招聘信息</h3>
                <div class="list zp-list">
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                    <a class="item">
                        <span class="tit">UI设计师</span>
                        <span class="txt">武汉市美约极客教育机构</span>
                    </a>
                </div>
            </div>
        </div>

<!-- 返回顶部 --> 
<a href="javascript:" target="_parent" class="gotop"></a> 

<script type="text/javascript" src="assets/js/city-select.js"></script>
<script type="text/javascript">
//loading.show();
setTimeout(function(){
	//loading.hide();
},2000)

	
    var swiper = new Swiper('.index-slider', {
        pagination: '.swiper-pagination',
        autoplay: 3000,
        paginationClickable: true,
		onInit: function(swiper){
		  $('.index-slider').height($('.index-slider img').eq(0).height());
		}

    });

$(function(){
    $('#city').click(function(){
        $.city().then(function(city){
            console.log(city);
        })
    })
})

</script>
</body>
</html>
