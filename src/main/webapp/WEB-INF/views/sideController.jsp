<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../../css/controller/conBarback.css">
  <link rel="stylesheet" href="../../css/controller/conBar_1.css">
  <link rel="stylesheet" href="../../css/controller/conBar_2.css">
  <link rel="stylesheet" href="../../css/controller/conBar_3.css">

</head>
<body>
<div class="conBarBack">
  <div class="conBar_1">
    <div>


    </div>
  </div>
  <div class="conBar_2">
    <button onclick="musicPlay()" >재생</button>
    <button onclick="musicPause()">정지</button>
    <button onclick="musicStop()">멈춤</button>
  </div>

  <div class="conBar_3">

    <audio src="/api/music/play/7b72e383-792b-48c4-ba98-b1caf05835e7.wav"  controls="controls"  loop="loop"></audio>

  </div>
</div>
<script>

  var song = document.getElementsByTagName('audio')[0];
  var played = false
  var tillPlayed = getMusicPlayTime();

  function setMusicPlayTime(playTime){
    sessionStorage.setItem("playTime",playTime)
  }
  function getMusicPlayTime(){
    return sessionStorage.getItem("playTime")
  }
  function setMusicPlayState(playState){
    sessionStorage.setItem("playState",playState)
  }
  function getMusicPlayState(){
    return sessionStorage.getItem("playState")
  }

  function musicPlay(){
    song.play()
    setMusicPlayState(1)
  }
  function musicPause(){
    song.pause()
    setMusicPlayState(0)
  }
  function musicStop(){
    song.pause()
    setMusicPlayState(0)
    setMusicPlayTime(0)
  }

// 음악 상태 저장
  function update(){
    var isPlay = sessionStorage.getItem("playState")

    // 음악 스탑 경우
    if(!played) {
      // 음악 시작지점을 지정
      if(tillPlayed) {
        song.currentTime = tillPlayed;
      }
    // 음악 플레이 경우
    }else{
      // 음악 시작지점을 갱신
      setMusicPlayTime(song.currentTime)
    }

    if( isPlay == 1 ){
      song.play()
      played = true;
    }else{
      song.pause()
      played = false;
    }
  }
  setInterval(update,100);
</script>
</body>
</html>