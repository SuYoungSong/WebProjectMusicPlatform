<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../../css/controller/conBarback.css">
  <link rel="stylesheet" href="../../css/controller/conBar_1.css">
  <link rel="stylesheet" href="../../css/controller/conBar_2.css">
  <link rel="stylesheet" href="../../css/controller/conBar_3.css">
  <script src="https://unpkg.com/wavesurfer.js"></script>
</head>
<body>
<div class="conBarBack">
  <div class="conBar_1">
    <div>

    </div>
  </div>

<%--  <div class="conBar_2">--%>
<%--    <div class="main_bar">--%>
<%--      <div class="music_controll">--%>
<%--        <button onclick="musicPlay()" >재생</button>--%>
<%--      </div>--%>
<%--      <div id="waveform"></div>--%>
<%--    </div>--%>

<%--    <div class="sub_Bar">--%>
<%--      <div class="volume">--%>
<%--        <img id="volumeIcon" class="volume-icon" src="assets/icons/volume.png"/>--%>
<%--        <input id="volumeSlider" class="volume-slider" type="range" name="volume-slider" min="0" max="100" value="50"/>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--      <div class="time_bar">--%>
<%--        <span id="waveform-time-indicator">00:00:00</span>/<span id="waveform-time-end">00:00:00</span>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>


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


  var played = false
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

  // 페이지 이동시 음악 상태에 따라서 출력시키기
  function update(){
    var isPlay = localStorage.getItem("playState")

    if( isPlay == 1 ){
      wavesurfer.play()
      playButtonIcon.src = "/resources/images/pause.png"
      played = true;
    }else{
      wavesurfer.pause()
      playButtonIcon.src = "/resources/images/play.png"
      played = false;
    }
  }

  // 음악 설정
  function setMusic(){
    if(localStorage.getItem("playMusicNumber") == null){
      wavesurfer.load("/resources/musics/defaultMusic.mp3")
    }else{
      wavesurfer.load("/api/music/play/" + localStorage.getItem("playMusicNumber"))
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
    playButtonIcon.src = "/resources/images/play.png"
  })
</script>