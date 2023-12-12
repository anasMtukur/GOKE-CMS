<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- HEADER DESKTOP-->
<header class="header-desktop3 d-none d-lg-block">
    <div class="section__content section__content--p35">
        <div class="header3-wrap">
            <div class="header__logo">
                <a href="/home">
                    <img src="/images/icon/logowhite.png" class="logo" alt="Paylode Archive System" />
                </a>
            </div>
            <div class="header__navbar">
                <ul class="list-unstyled">
                    <li>
                        <a href="/home">
                            <i class="fas fa-home"></i>
                            <span class="bot-line"></span>
                            Home
                        </a>
                    </li>
                    <!-- <li>
                        <a href="/doctype">
                            <i class="fa fa-files-o"></i>
                            <span class="bot-line"></span>
                            Document Types
                        </a>
                    </li> -->
                    <c:if test="${authUser.superAdmin}">
                    <li>
                        <a href="/user">
                            <i class="fas fa-users"></i>
                            <span class="bot-line"></span>
                            System Users
                        </a>
                    </li>
                    </c:if>
                    <li>
                        <a href="/api-setup-doc">
                            <i class="fa fa-code"></i>
                            <span class="bot-line"></span>API
                        </a>
                    </li>
                </ul>
            </div>
            <div class="header__tool">
                
                <div class="account-wrap">
                    <div class="account-item account-item--style2 clearfix js-item-menu">
                        <div class="image">
                            <img src="/images/icon/user.png" alt="John Doe" />
                        </div>
                        <div class="content">
                            <a class="js-acc-btn" href="#"><c:out value='${authUser.name}' /></a>
                        </div>
                        <div class="account-dropdown js-dropdown">
                            <div class="info clearfix">
                                <div class="image">
                                    <a href="#">
                                        <img src="/images/icon/user.png" alt="<c:out value='${authUser.name}' />" />
                                    </a>
                                </div>
                                <div class="content">
                                    <h5 class="name">
                                        <a href="#"><c:out value='${authUser.name}' /></a>
                                    </h5>
                                    <span class="email"><c:out value='${authUser.username}' /></span>
                                </div>
                            </div>
                            <div class="account-dropdown__body">
                                <div class="account-dropdown__item">
                                    <a href="/account">
                                        <i class="zmdi zmdi-account"></i>Account</a>
                                </div>
                            </div>
                            <div class="account-dropdown__footer">
                                <a href="/signout" class="text-danger">
                                    <i class="zmdi zmdi-power"></i>Logout</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- END HEADER DESKTOP-->

<!-- HEADER MOBILE-->
<header class="header-mobile header-mobile-2 d-block d-lg-none">
    <div class="header-mobile__bar">
        <div class="container-fluid">
            <div class="header-mobile-inner">
                <a href="index.html">
                    <img src="/images/icon/logowhite.png" class="logo" alt="Paylode Archive System" />
                </a>
                <button class="hamburger hamburger--slider" type="button">
                    <span class="hamburger-box">
                        <span class="hamburger-inner"></span>
                    </span>
                </button>
            </div>
        </div>
    </div>
    <nav class="navbar-mobile">
        <div class="container-fluid">
            <ul class="navbar-mobile__list list-unstyled">
                <li>
				    <a href="/home">
				        <i class="fas fa-home"></i>
				        <span class="bot-line"></span>
				        Home
				    </a>
				</li>
				<!-- <li>
				    <a href="/document-types">
				        <i class="fa fa-files-o"></i>
				        <span class="bot-line"></span>
				        Document Types
				    </a>
				</li> -->
				<li>
				    <a href="/user">
				        <i class="fas fa-users"></i>
				        <span class="bot-line"></span>
				        System Users
				    </a>
				</li>
				<li>
				    <a href="/api-setup-doc">
				        <i class="fa fa-code"></i>
				        <span class="bot-line"></span>API
				    </a>
				</li>
            </ul>
        </div>
    </nav>
</header>
<div class="sub-header-mobile-2 d-block d-lg-none">
    <div class="header__tool">
        
        <div class="account-wrap">
            <div class="account-item account-item--style2 clearfix js-item-menu">
                <div class="image">
                    <img src="/images/icon/user.png" alt="<c:out value='${authUser.name}' />" />
                </div>
                <div class="content">
                    <a class="js-acc-btn" href="#"><c:out value='${authUser.name}' /></a>
                </div>
                <div class="account-dropdown js-dropdown">
                    <div class="info clearfix">
                        <div class="image">
                            <a href="#">
                                <img src="/images/icon/user.png" alt="<c:out value='${authUser.name}' />" />
                            </a>
                        </div>
                        <div class="content">
                            <h5 class="name">
                                <a href="#"><c:out value='${authUser.name}' /></a>
                            </h5>
                            <span class="email"><c:out value='${authUser.username}' /></span>
                        </div>
                    </div>
                    <div class="account-dropdown__body">
                        <div class="account-dropdown__item">
                            <a href="#">
                                <i class="zmdi zmdi-account"></i>Account</a>
                        </div>
                        
                    </div>
                    <div class="account-dropdown__footer">
                        <a href="/signout" class="text-danger"> 
                            <i class="zmdi zmdi-power"></i>Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END HEADER MOBILE -->