<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="UTF-8">


<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>BucketKurly::Admin-BannerInsert</title>

    <!-- Custom fonts for this template -->
    <link href="resources/bootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/bootstrap/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="resources/bootstrap/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">
	<!-- Page Wrapper -->
    <div id="wrapper">

  	<!-- header -->
	<%@ include file="/WEB-INF/views/admin_layout/admin_sidebar.jsp"%>  		

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
  				
  				<!-- topbar -->
      			<%@ include file="/WEB-INF/views/admin_layout/admin_topbar.jsp"%>  		

               <!-- Begin Page Content -->
	<div class="container-fluid">
		<!-- Page Heading -->
		<h1 class="h3 mb-2 text-gray-800">배너 관리</h1>
		<br>
			<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 style="color: #5f0080; font-weight: bolder;">배너등록</h6> <!-- class="m-0 font-weight-bold text-warning" -->
			</div>
			<div class="card-body">
				<div class="table-responsive">

			<form action="/bannerUpload.mdo" method="post" enctype="multipart/form-data">
		<div class="container-fluid">
					<!-- 전체 frame -->
					<ul>						
						<!-- 제목 -->
						<li class="title">
							<div>
								<div class="index">제목</div>
								<input class="title_text" type="text" id="banner_title" name="banner_title" style="width: 50%" required />
							</div><br>							
						</li>						
						<!-- 베너 이미지 업로드 -->
						<li class="img"> 
							<div>
								<div class="index">배너 이미지 : <input type="file" id="banner_filepath" name="file" required/>
								</div>
								
							</div><br>
						</li>
						<!-- 베너 내용 -->
						<li class="contents">
							<div>
								<div class="index">내용</div>
								<input type="text" id="banner_contents" name="banner_contents" style="width: 50%;" required/>
							</div><br>
						</li>
					</ul>
					<div align="center">
						<input style="padding: 5px; background:#5f0080; border: #5f0080" class="btn btn-warning btn-icon-split"
							type="submit" id="registerBtn" value="등록하기" />
					</div>
					<div align="right">
						<a href="admin_banner_getList.mdo"  class="btn btn-light btn-icon-split">
							<span class="text">목록가기</span>
						</a>
					</div>
				</div>
		
			</form>
		</div>
	</div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->


        <!-- Footer -->
     		 <%@ include file="/WEB-INF/views/admin_layout/admin_footer.jsp"%>  
        <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Bootstrap core JavaScript-->
    <script src="resources/bootstrap/vendor/jquery/jquery.min.js"></script>
    <script src="resources/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/bootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="resources/bootstrap/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="resources/bootstrap/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="resources/bootstrap/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="resources/bootstrap/js/demo/datatables-demo.js"></script>

	

</body>

</html>