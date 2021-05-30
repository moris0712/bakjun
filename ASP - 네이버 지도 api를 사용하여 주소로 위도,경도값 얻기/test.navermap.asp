<!-- ASP, 네이버 지도 API를 이용하여 주소를 위도, 경도로 바꾸는 -->

<%
	sql = "SELECT Addr,Name FROM TABLE "   '테이블에서 주소를 가져옴'
	Set Rs = Db.execRs(sql, DB_CMDTYPE_TEXT, arrParams, Nothing)
	member_addr = null2Blank(Rs("Addr")) 
	name = null2Blank(Rs("Name"))
	If Not Rs.eof And NOT Rs.bof Then
		arrget = Rs.getrows() '가져온 컬름을 getrows()함수로 2차원 배열을 만듬 '
	End If 
	'    Addr  Name '
	' 1  주소1  이름1 '
	' 2  주소2  이름2 '
	' 3  주소3  이름3 '
	'  arrget(0,0)=  주소1'
	'  arrget(1,0)=  이름1'
	'  arrget(0,1)=  주소2'
	'  arrget(1,1)=  이름2'
	'  arrget(2,0)=>  오류'
	' UBound(배열명,차수) LBound는 배열 차원의 최소범위, UBound는 배열차원의 최대범위 반환'
	' UBound(arrget,2) =>  arrget의 행 개수'
%>

<script type="text/javascript">
	var num =0;
	var arrrr = new Array();
	<% For k = LBound(arrget,2) To UBound(arrget,2) Step 1 'k는 0 ~ 행 개수'%> 
		arrrr[num] = '<%= arrget(0,k) %>'; 
    	//console.log(num+ " : " + "  " +arrrr[num]);
		num++;
	<% Next %>
	// arrrr 배열에 테이블에서 가져온 주소 차례대로 저장 
</script>

<form name="Frm" method="post">
	<input type="hidden" name="latitude" value="" />
	<input type="hidden" name="longitude" value="" />
	<input type="hidden" name="addr" value="" />
	<input type="hidden" name="count" value="<%=UBound(arrget,2)%>" />
</form>	

<!--<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=( 네이버api 클라이언트 아이디 )&submodules=geocoder"></script>-->

<script type="text/javascript">
	var aaa = [];
	var f = document.Frm;
	var q=0;

	for(q=0; q<= <%= UBound(arrget,2) %>; q++){
		setTimeout(searchAddressToCoordinate(arrrr[q],1000));
	}

	function searchAddressToCoordinate(address) {
		naver.maps.Service.geocode({
			address: address 
		}, function(status, response) {
			if (status === naver.maps.Service.Status.ERROR) {
				return alert('주소를 확인후 다시 시도 바랍니다.');
			}
		 	var i=0;
			var item=response.result.items[i];

			f.method="post";
			f.addr.value = address;
			f.longitude.value = item.point.x;
			f.latitude.value = item.point.y;
			f.action = "test.navermap.act.asp"; 
			//console.log("addr: "+f.addr.value);
			//console.log("lat: "+f.longitude.value);
			//console.log("lnc: "+f.latitude.value);
		  	f.submit();
		});
	}

	
</script>






<% 'test.navermap.asp 파일'
    latitude = null2Blank(getRequest("latitude", REQUEST_POST))
	longitude = null2Blank(getRequest("longitude", REQUEST_POST))
    addr = null2Blank(getRequest("addr", REQUEST_POST))
    count= null2Blank(getRequest("count", REQUEST_POST))


    For p = 0 To count Step 1
        sql = "UPDATE T_AEST SET Latitude=? ,Longitude=? WHERE Addr = ? "
        arrParams = Array(_
            Db.makeParam("@Latitude", adDouble, adParamInput, 8, latitude), _
            Db.makeParam("@Hardness", adDouble, adParamInput, 8, longitude), _
            Db.makeParam("@Addr", adVarWChar, adParamInput, 300, addr) _
        )
        Call Db.exec(sql, DB_CMDTYPE_TEXT, arrParams, Nothing)
    Next
    Call Db.commitTrans(Nothing)
    Call gotoURL("test.navermap.asp", "T")
%>