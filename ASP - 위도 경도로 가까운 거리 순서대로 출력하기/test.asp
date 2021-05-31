<%

	sql = "SELECT Latitude, Longitude FROM TABLE WHERE UserNo=? " 
	arrParams = Array( _
		Db.makeParam("@UserNo", adInteger, adParamInput, 4, idx) _
	)
	Set Rs = Db.execRs(sql, DB_CMDTYPE_TEXT, arrParams, Nothing)
	If Not Rs.bof And Not Rs.eof Then
		latitude = toNumeric(Rs("Latitude"))
		longitude = toNumeric(Rs("Longitude"))	
	End If
	Call closeRs(Rs)
	'기준이되는 해당 UserNo의 위도와 경도를 가져와서 addr에 저장한다.'

	'Latitude - 위도    37
	'longitude - 경도   127

	sql = "SELECT ROW_NUMBER() OVER(ORDER BY myResult.dist ASC) AS rownum, Name, Addr,Latitude, Longitude, dist "
	sql = sql & "FROM ( "
	sql = sql & "SELECT Name, Addr, Latitude, Longitude, ( 6371 * acos( cos( radians("&latitude&") ) * cos( radians( Latitude ) ) * "
	sql = sql & "cos( radians( Longitude ) - radians("&longitude&") ) + sin( radians("&latitude&") ) * "
	sql = sql & "sin( radians( Latitude ) ) ) ) AS dist FROM TABLE) myResult "
	sql = sql & "WHERE myResult.dist <= 500 ORDER BY myResult.dist ASC"

	'이 질의문은 latitude longitude 기준으로 TABLE에 있는 튜플들을 가까운 거리(dist)순으로 출력해준다.'
	'여기서 숫자 6371은 dist를 km단위로 표시하기위함이고 만약 3959를 사용한다면 dist가 마일 단위로 출력이된다고한다.'
	'그리고 dist<=500은 해당 latitude longtitude 기준으로 반경 500km내에 있는것만 검색한다는 뜻이다.'


	Set distance = Db.execRs(sql, DB_CMDTYPE_TEXT, arrParams, Nothing)

%>




