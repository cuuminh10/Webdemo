<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/templates/taglib.jsp"%>
	  <form action="" method="post" enctype='multipart/form-data' >
 		<div class="row">
                               <div class="col-sm-6">
                                   <div class="form-group">
                                       <label for="name">Tên Tin tức</label>
                                       <input type="text" class="form-control" id="name" name="name" placeholder="Nhập tên truyện" value="${prod.name}">
                                   </div>
					
                                   <div class="form-group">
                                       <label>Danh mục truyện</label>
                       				 <select id="mySelect" name="id_cat" class="form-control">
                                       	<c:forEach items="${listMenu}" var="item">
                                       			${item}
                                       	</c:forEach>
									</select>
                                   </div>
					 				<div class="form-group">
                                       <label>Thêm hình ảnh</label>
                                       <input type="file" class="btn btn-default"  id="hinhanh" name="hinhanh"  multiple>
                                       <p class="help-block"><em>Định dạng: jpg, png, jpeg,...</em></p>
                                       <c:choose>
                                       	<c:when test="${not empty prod.picture}">
                                       		  <img alt="" src="${pageContext.request.contextPath}/files/${prod.picture}"/>
                                       	</c:when>
                                       	<c:otherwise>
                                       		<p class="help-block">Empty Image</p>
                                       	</c:otherwise>
                                       </c:choose>
                                      
                                   </div>
                                   
                                   <div class="form-group">
                                       <label for="name">Giá gốc</label>
                                       <input type="text" class="form-control" id="price" name="price" placeholder="Nhập tên truyện" value="${prod.price}">
                                   </div>
                                   
									<div class="form-group">
                                       <label for="name">Giá giảm</label>
                                       <input type="text" class="form-control" id="sale" name="sale" placeholder="Nhập tên truyện" value="${prod.sale}">
                                   </div>
					
												
                                   <div class="form-group">
                                       <label>Mô tả</label>
                                       <textarea class="ckeditor" name="description" class="form-control" rows="3"><p>${prod.description}</p></textarea>
                                   </div>
                               </div>
                               <div class="col-sm-6"></div>
                           </div>
                           <hr>
                           <div class="row">
                               <div class="col-sm-12">
                                   <input type="submit" value="Thêm" class="btn btn-success" />
                                   <input type="reset" value="Nhập lại" class="btn btn-default" />
                               </div>
                           </div>
 	</form>



		<script>
				if(${prod.id_cat} !== ""){
						var id_prod = ${prod.id_cat};
						var x = document.getElementById("mySelect");
					    var txt = "All options: ";
					    var i;
					    for (i = 0; i < x.length; i++) {
					    	if(x.options[i].value  ==  id_prod){
					    		x.options[i].selected = true;
					    	}
					    }
				}
				function delAjax(id,ItenPicture) {
					alert(ItenPicture)
					$.ajax({
						url: '${pageContext.request.contextPath}/admin/news/delAjax',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								lid:id,
								picture:ItenPicture
								},
						success: function(data){
							// Xử lý thành công
							$('#news').html(data);
						},
						error: function (){
							alert('123')
						}
					});
				}
			</script>



<script type="text/javascript">
		        function saveMedia(id) {
		            var formData = new FormData();
		            formData.append('file', $('#file')[0].files[0]);
		            formData.append('lid',id);
		            console.log("form data " + formData);
		            $.ajax({
		                url : '${pageContext.request.contextPath}/admin/news/plus',
		                data : formData,
		                processData : false,
		                contentType : false,
		                type : 'POST',
		                success : function(data) {
		                	$('#news').html(data);
		                },
		                error : function(err) {
		                    alert(err);
		                }
		            });
		        }
   		 	</script>
			
			
			
			
			