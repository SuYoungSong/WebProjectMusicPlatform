<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="/css/controller/conBarback.css">
  <link rel="stylesheet" href="/css/controller/conBar_1.css">
  <link rel="stylesheet" href="/css/controller/conBar_2.css">
  <link rel="stylesheet" href="/css/controller/conBar_3.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://unpkg.com/wavesurfer.js"></script>
</head>
<body>
<div class="conBarBack">
  <div class="conBar_1">
    <div class="play_music_info">
      <div class="play_music_image" id="play_music_image">
        <img src="/resources/images/defaultPlayMusicImage.png"/>
      </div>
      <div class="play_music_text" id="play_music_text">
        <div class="play_music_title" id="play_music_title">
          재생중인 음악이 없습니다.
        </div>
        <div class="play_music_singer" id="play_music_singer">

        </div>
      </div>
    </div>
  </div>
  <div class="conBar_2">
    <main class="container">
      <div class="audio-player">
        <div class="player-header">
            <button id="playButton" class="play-button">
              <img id="playButtonIcon" class="play-button-icon" src="/resources/images/play.png"/>
            </button>
            <div id="waveform" class="waveform"></div>
        </div>
        <div class="player-body">
          <div class="controls">
            <div class="volume">
              <img id="volumeIcon" class="volume-icon" src="/resources/images/volume.png"/>
              <input id="volumeSlider" class="volume-slider" type="range" name="volume-slider" min="0" max="100" value="50"/>
            </div>
            <div class="timecode">
              <span id="currentTime">00:00:00</span>
              <span>/</span>
              <span id="totalDuration">00:00:00</span>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</div>
</body>
</html>


<script>
  var tillPlayed = getMusicPlayTime();

  function setMusicPlayTime(playTime){
    localStorage.setItem("playTime",playTime)
  }
  function getMusicPlayTime(){
    return localStorage.getItem("playTime")
  }
  function setMusicPlayState(playState){
    localStorage.setItem("playState",playState)
  }
  function getMusicPlayState(){
    return localStorage.getItem("playState")
  }
  function musicPlayButton(key){
    localStorage.setItem("playMusicNumber", key)
    setMusic()
    wavesurfer.setCurrentTime(0)
    setMusicPlayTime(0)
    setMusicPlayState(1)
    wavesurfer.play()
    setPlayMusicInfo()
    playButtonIcon.src = "/resources/images/pause.png"

  }
  // 페이지 이동시 음악 상태에 따라서 출력시키기
  function update(){
    var isPlay = localStorage.getItem("playState")
    var iconUrlSplit = playButtonIcon.src.split("/")
    var iconUrl = iconUrlSplit.pop()

    if( isPlay == 1 ){
      wavesurfer.play()
      if(iconUrl != "pause.png"){
        playButtonIcon.src = "/resources/images/pause.png"
      }
    }else{
      wavesurfer.pause()
      if(iconUrl != "play.png"){
        playButtonIcon.src = "/resources/images/play.png"
      }
    }
  }

  // 음악 설정
  function setMusic(){
    if(localStorage.getItem("playMusicNumber") == null){
      wavesurfer.load("/resources/musics/defaultMusic.mp3")
      setPlayMusicInfo()
    }else{
      wavesurfer.load("/api/music/play/" + localStorage.getItem("playMusicNumber"))
      setPlayMusicInfo()
    }
  }

  setInterval(update,100);

  const playButton = document.querySelector("#playButton")
  const playButtonIcon = document.querySelector("#playButtonIcon")
  const waveform = document.querySelector("#waveform")
  const volumeIcon = document.querySelector("#volumeIcon")
  const volumeSlider = document.querySelector("#volumeSlider")
  const currentTime = document.querySelector("#currentTime")
  const totalDuration = document.querySelector("#totalDuration")
  // --------------------------------------------------------- //

  const initializeWavesurfer = () => {

    return WaveSurfer.create({
      container: '#waveform',
      barWidth: 3,
      progressColor: '#E2B026',
      cursorColor: 'transparent',
      waveColor: '#fff',
      normalize:true,
      height:80,
      responsive:true,
      fillParent:true
    })
  }
  // --------------------------------------------------------- //
  // Functions
  /**
   * 플레이 버튼 토글
   */
  const togglePlay = () => {
    const isPlaying = wavesurfer.isPlaying()
    if (isPlaying) {
      wavesurfer.pause()
      playButtonIcon.src = "/resources/images/play.png"
      setMusicPlayState(0)
    } else {
      wavesurfer.play()
      playButtonIcon.src = "/resources/images/pause.png"
      setMusicPlayState(1)
    }
  }
  /**
   * 볼륨 슬라이더 입력 변경 핸들
   * @param {event} e
   */
  const handleVolumeChange = e => {
    // Set volume as input value divided by 100
    // NB: Wavesurfer only excepts volume value between 0 - 1
    const volume = e.target.value / 100
    wavesurfer.setVolume(volume)
    // Save the value to local storage so it persists between page reloads
    localStorage.setItem("audio-player-volume", volume)
  }

  // 로컬 저장소에서 볼륨 값을 검색하고 볼륨 슬라이더를 설정합니다.
  const setVolumeFromLocalStorage = () => {
    // Retrieves the volume from local storage, or falls back to default value of 50
    const volume = localStorage.getItem("audio-player-volume") * 100 || 50
    volumeSlider.value = volume
  }

  /**
   * Formats time as HH:MM:SS
   * @param {number} seconds
   * @returns time as HH:MM:SS
   */
  const formatTimecode = seconds => {
    return new Date(seconds * 1000).toISOString().substr(11, 8)
  }

  // Wavesurfer 볼륨의 음소거/음소거 해제를 전환합니다.
  // 볼륨 아이콘을 변경하고 볼륨 슬라이더를 비활성화합니다.
  const toggleMute = () => {
    wavesurfer.toggleMute()
    const isMuted = wavesurfer.getMute()
    if (isMuted) {
      volumeIcon.src = "/resources/images/mute.png"
      volumeSlider.disabled = true
    } else {
      volumeSlider.disabled = false
      volumeIcon.src = "/resources/images/volume.png"
    }
  }
  // --------------------------------------------------------- //
  // Create a new instance and load the wavesurfer
  const wavesurfer = initializeWavesurfer()
  setMusic()

  // --------------------------------------------------------- //
  // Javascript Event listeners
  window.addEventListener("load", setVolumeFromLocalStorage)
  playButton.addEventListener("click", togglePlay)
  volumeIcon.addEventListener("click", toggleMute)
  volumeSlider.addEventListener("input", handleVolumeChange)
  // --------------------------------------------------------- //
  // Wavesurfer 이벤트 설정
  wavesurfer.on("ready", () => {
    // Set wavesurfer volume
    wavesurfer.setVolume(volumeSlider.value / 100)
    // 오디오 트랙 총 지속 시간 설정
    const duration = wavesurfer.getDuration()
    totalDuration.innerHTML = formatTimecode(duration)
  })
  wavesurfer.on("audioprocess", () =>{
    setMusicPlayTime(wavesurfer.getCurrentTime())
  })
  wavesurfer.on("ready", () =>{
    wavesurfer.setCurrentTime(localStorage.getItem("playTime"));
  })
  // 오디오 재생 시 타임코드 현재 타임스탬프를 설정
  wavesurfer.on("audioprocess", () => {
    const time = wavesurfer.getCurrentTime()
    currentTime.innerHTML = formatTimecode(time)
  })
  // 오디오 종료 후 재생 버튼 아이콘을 재설정
  wavesurfer.on("finish", () => {
    wavesurfer.pause()
    playButtonIcon.src = "/resources/images/play.png"
    setMusicPlayState(0)
  })



  // ===================================
  //     플레이 중인 음악 정보 관련 함수
  // ===================================


  function setPlayMusicInfo(){

    const playMusicTitle = document.getElementById("play_music_title")
    const playMusicSinger = document.getElementById("play_music_singer")
    const playMusicImage = document.getElementById("play_music_image")

    var musicNumber = localStorage.getItem("playMusicNumber")
    var musicInfo = getMusicInfo(musicNumber)
    if ( musicNumber != null){
      playMusicImage.innerHTML = callMusicImage(musicNumber)
      playMusicTitle.innerHTML = musicInfo.musicName
      playMusicSinger.innerHTML = musicInfo.singer
    }else{
      playMusicImage.innerHTML = "<img src=\"/resources/images/defaultPlayMusicImage.png\"/>"
      playMusicTitle.innerHTML = "재생중인 음악이 없습니다."
      playMusicSinger.innerHTML = ""
    }
  }

  function getMusicInfo(musicNumber){
    var temp;
    $.ajax({
      type: "get",
      url: "/api/music/info/" + musicNumber,
      async: false,
      dataType: "json"
    }).done(function (result) {
      temp = result
    }).fail(function (error) {
      console.log("음악 정보 로딩 실패");
    })
    return temp
  }

  function callMusicImage(key){
    // 이미지 뜨는 html 수정하려면 여기 수정하면 됌
    var string = ""
    $.ajax({
      type: "get",
      url: "/api/music/image/" + (key),
      async: false,
      dataType: "json"
    }).done(function (result) {
        string +="<img src=\"/resources/images/"+ result.serverFilePath +"\"/>"
    }).fail(function (error) {
    })
    return string;
  }

</script>