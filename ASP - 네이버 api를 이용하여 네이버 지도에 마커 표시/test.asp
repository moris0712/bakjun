<%
	my_latitude = 37.296974431356176
	my_longitude = 126.83416796439519
	my_addr = "대충 한양대학교 에리카 인곳"
	my_name = "대충 한양대학교 에리카 쯤"
	
	sql = "SELECT ROW_NUMBER() OVER(ORDER BY myResult.dist ASC) AS rownum, Name, Addr, Latitude, Longitude, dist "
	sql = sql & "FROM ( "
	sql = sql & "SELECT Name, Addr, Latitude, Longitude, ( 6371 * acos( cos( radians("&my_latitude&") ) * cos( radians( Latitude ) ) * "
	sql = sql & "cos( radians( Longitude ) - radians("&my_longitude&") ) + sin( radians("&my_latitude&") ) * "
	sql = sql & "sin( radians( Latitude ) ) ) ) AS dist FROM T_AEST) myResult "
	sql = sql & "WHERE myResult.dist <= 1000 ORDER BY myResult.dist ASC"

	Set distance = Db.execRs(sql, DB_CMDTYPE_TEXT, arrParams, Nothing)

	If Not distance.bof And Not distance.eof Then
		arrget = distance.getrows()
	End If

	Dim Name_arr() ' 위치 이름 배열
	ReDim Name_arr(UBound(arrget,2))

	Dim Addr_arr() ' 위치 이름 배열
	ReDim Addr_arr(UBound(arrget,2))

	Dim Latitude_arr() ' 위도 배열 
	ReDim Latitude_arr(UBound(arrget,2))

	Dim Longitude_arr() ' 경도 배열
	ReDim Longitude_arr(UBound(arrget,2))

	Dim Dist_arr() ' 거리 배열
	ReDim Dist_arr(UBound(arrget,2))

	For k = 0 To distance.RecordCount-1 Step 1
		Name_arr(k) = arrget(1,k)
		Addr_arr(k) = arrget(2,k)
		Latitude_arr(k) = arrget(3,k)
		Longitude_arr(k) = arrget(4,k)
		Dist_arr(k) = arrget(5,k)
	Next
%>

<div id="map" style="width:900px;height:600px;"></div>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=( 네이버api 클라이언트 아이디 )&submodules=geocoder"></script>
<script>
	var map = new naver.maps.Map('map', {
		center: new naver.maps.LatLng( <%=my_latitude%> , <%=my_longitude%> ), // 지도가 띄어질때 중간 지점  my_latitude, my_longitude 기준으로 한다.
		zoom: 10
	});

	var temp_markers = [], markers = [], infoWindows = [], polylines = [];
	// 위도/경도 배열, 마커 배열, 모달 배열, 폴리 라인 배열

	<% '여기서부터 마커를 만들기위한 함수'
	For k = 0 To distance.RecordCount Step 1
	' k가 = 배열size+1 일때 자기 자신'
		If k = distance.RecordCount Then
	%>		var name = '<%=my_name%>';
			var addr = '<%=my_addr%>';
			var latitude = <%=my_latitude%>;
			var longitude = <%=my_longitude%>;
			var img = './nmanager/imgs/icon/my.png';  // 마커는 이미지 사용하기로 함 (기본 마커 어떻게 사용하는지 모르겠음)
	<%
		Else
	' 그 외에는 배열 애들 저장'
	%>		var name = '<%=Name_arr(k)%>';
			var addr = '<%=Addr_arr(k)%>';
			var latitude = <%=Latitude_arr(k)%>;
			var longitude = <%=Longitude_arr(k)%>;
			var img = './nmanager/imgs/icon/fac.png'; 
			var dist =  <%=Formatnumber(cdbl(Dist_arr(k)),3)%>;
	<%
		End If
	%>  
		temp_markers[ <%=k%> ] = new naver.maps.LatLng( latitude , longitude ); 
		// LatLng : 위/경도 좌표 정의
		content_<%=k%> = '<div style="padding:10px 5px 0px 5px;"><div style="font-size:15px;font-weight:bold;padding-bottom:5px;"'
		+'>'+name+'</div><div style="padding-bottom:2px;font-size:7px;"><p>주소: '+addr+'</p></div>'
		+'<div style="font-size:10px;font-weight:bold;padding-top:2px;">거리: '+dist+' Km</p></div>';
		// content_번호 : 마커 클릭시 보여질 모달 정의 

		var ICON_GAP = 31;

		var iconSpritePositionX = ( <%=k%> * ICON_GAP) + 1; // 정해진 위도/경도 기준으로 얼만큼 떨어진곳에 마커가 생성되는거라함 (잘 모르겠음)
		var iconSpritePositionY = 1;

		var marker = new naver.maps.Marker({ // 새로운 마커 추가
			map: map,
			position: temp_markers[ <%=k%> ],
			icon: {
				content: '<div><img src="'+img+'" alt="" style="vertical-align:middle;'
				+'margin: 0px; padding: 0px; border: 0px solid transparent; display: block;'
				+'max-width: none; max-height: none; -webkit-user-select: none; position: absolute;'
				+'width: 20px; height: 32px; left: 0px; top: 0px; "><span style="padding-left:20px;'
				+'font-size:10px">'+name+'</span></div>',
				size: new naver.maps.Size(15, 36), // 이미지 크기
				origin: new naver.maps.Point(iconSpritePositionX, iconSpritePositionY), // 스프라이트 이미지에서 클리핑 위치
				anchor: new naver.maps.Point(13, 36), // 지도상 위치에서 이미지 위치의 offset값
			},
			zIndex: 100
		});
		markers.push(marker); // 마커스 배열에다가 방금 만든 마커 push


		var infoWindow = new naver.maps.InfoWindow({ // 마커 클릭시 보여질 모달 정의
			content: content_<%=k%>,
			backgroundColor: "white",
			borderColor: "black",
			borderWidth: 2,
			anchorSize: new naver.maps.Size(30, 30),
			anchorSkew: true,
			anchorColor: "white",
		});
		infoWindows.push(infoWindow); //모달 배열에다가 방금 만든 모달 push

	<% If k <> distance.RecordCount Then %>
		var polypath = [ // 폴리 라인 점 2개 (내 위치와 목적지)
			new naver.maps.LatLng( <%=my_latitude%> , <%=my_longitude%>),
			new naver.maps.LatLng( latitude, longitude)
		];
	<%
		End If
	%>

		polyline = new naver.maps.Polyline({
			path: polypath,      //선 위치 변수배열
			endIcon: naver.maps.PointingIcon.OPEN_ARROW,
			strokeColor: '#FF0000', //선 색 빨강 #빨강,초록,파랑
			strokeOpacity: 0.5, //선 투명도 0 ~ 1
			strokeWeight: 2,   //선 두께
			map: map           //오버레이할 지도
		});

		polylines.push(polyline); // 폴리라인스 배열에 방금 만든 폴리 라인 push
		polyline.setMap(null); // 방금 만든 폴리 라인은 지도 띄우는 순간 보여지므로 비활성화
		
	<%
	Next
	Call closeRs(distance)
	'여기까지 마커, 폴리라인, 모달, 위도/경도를 각각 배열에 넣었음'
	%>
	

	naver.maps.Event.addListener(map, 'idle', function() {
		updateMarkers(map, markers, polylines);
	});

	function updateMarkers(map, markers, polylines) {

		var mapBounds = map.getBounds();
		var marker, position;

		for (var i = 0; i < markers.length; i++) {

			marker = markers[i]
			position = marker.getPosition();

			if (mapBounds.hasLatLng(position)) {
				showMarker(map, marker);
			} else {
				hideMarker(map, marker);
			}
		}
	}

	function showMarker(map, marker) {

		if (marker.setMap()) return;
		marker.setMap(map);
	}

	function hideMarker(map, marker) {

		if (!marker.setMap()) return;
		marker.setMap(null);
	}

	function getClickHandler(seq){
		return function(e){ // 클릭 시, 클릭한 마커의 모달창과 폴리라인을 맵에 표시함
			var marker = markers[seq],
				infoWindow = infoWindows[seq],
				poly = polylines[seq];
		
			for(var a=0; a<polylines.length; a++){
				polylines[a].setMap(null); 
			}

			if (infoWindow.getMap()) { 
				infoWindow.close();
			}
			else{ 
				infoWindow.open(map, marker);
				poly.setMap(map);
			}
		}
	}

	for (var i=0, ii=markers.length; i<ii; i++){
		naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 마커 클릭시 이벤트 발생
	}
 
</script>